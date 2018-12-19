 // Or Hadad / 308101591 / Beer Sheva

package graphics;

import animals.Animal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import plants.Plant;
import java.io.File;
import java.io.IOException;
import plants.*;
import animals.*;


/**
 @author אור
 */
public class ZooPanel extends JPanel implements ActionListener, Runnable{
    private static final long serialVersionUID = 1L;
    JPanel panel;
    ZooFrame frame;
    int numOfAnimals;
    private ArrayList<Animal> animals;
    int totalEaten;
    private Plant foods;
    private boolean bgflag;
    private Thread controller;
    public final static String PICTURE_PATH = "pictures\\";
    private JTable chart;
    private boolean isVisible;
    private String[] columns = {"Animal", "Color" ,"Weight" ,"Hor. speed" ,"Ver. speed" ,"Eat Counter"};
    private JScrollPane scrollpane;
    private String[] foodOptions = {"Meat", "Cabbage", "Lettuce"};
    
    //buttons
    JPanel botpanel = new JPanel(new GridLayout(1, 7));
    JButton button1 = new JButton("Add animal");
    JButton button2 = new JButton("Sleep");
    JButton button3 = new JButton("Wake up");
    JButton button4 = new JButton("Clear");
    JButton button5 = new JButton("Food");
    JButton button6 = new JButton("Info");
    JButton button7 = new JButton("Exit");
    
    /**
     * initializing attributes
     * @param frame is the parent frame
     */
    public ZooPanel(ZooFrame frame){
        foods = null;
        this.frame = frame;
        numOfAnimals = 0;
        totalEaten = 0;
        isVisible = false;
        animals = new ArrayList<Animal>();
        panel = new JPanel(new BorderLayout());
        buttoninit();
        controller = new Thread(this);
        controller.start();
    }
    
    /**
     * adding an animal
     */
    public void addDialog(){
        new AddAnimalDialog(this);
    }
    
    /**
     * initializing the buttons in the upper panel
     */
    public void buttoninit(){
        botpanel.add(button1);
        button1.addActionListener(this);
        botpanel.add(button2);
        button2.addActionListener(this);
        botpanel.add(button3);
        button3.addActionListener(this);
        botpanel.add(button4);
        button4.addActionListener(this);
        botpanel.add(button5);
        button5.addActionListener(this);
        botpanel.add(button6);
        button6.addActionListener(this);
        botpanel.add(button7);
        button7.addActionListener(this);
        setLayout(new BorderLayout());
        add(botpanel, BorderLayout.SOUTH);
    }
    
    /**
     * setting up the info button
     */
    public void setChart(){
        String[][] structure = new String[11][6];
        totalEaten = 0;
        int i = 0;
        if (isVisible == false){
            for (Animal j : animals){
                structure[i][0] = j.getAnimalName();
                structure[i][1] = j.getColor();
                structure[i][2] = j.getWeight() + " ";
                structure[i][3] = j.gethorSpeed() + " ";
                structure[i][4] = j.getverSpeed() + " ";
                structure[i][5] = j.getEatCount() + " ";
                totalEaten += j.getEatCount();
                i++;
            }
            System.out.println(i);
            structure[i][0] = "Total";
            structure[i][5] = totalEaten + " ";
            chart = new JTable(structure, columns);
            scrollpane = new JScrollPane(chart);
            scrollpane.setSize(600, chart.getRowHeight()* 35);
            add(scrollpane, BorderLayout.NORTH);
            isVisible = true;
        }
        else isVisible = false;
        scrollpane.setVisible(isVisible);
        repaint();
    }
    
    /**
     * setting the chosen background
     * @param bg represents the chosen background
     */
    public void background(String bg){
        if(bg == "Image"){
            bgflag = true;
        }
        if(bg == "Green"){
            bgflag = false;
            setBackground(null); 
            paintComponent(getGraphics());
            setBackground(Color.GREEN);			
        }
        if(bg == "None"){
            bgflag = false;
            setBackground(null);
        }
        repaint();
	}
    
    /**
     * adding food to the cener of the panel
     */
    synchronized public void addFood(){
        int i = JOptionPane.showOptionDialog(frame, "Choose food type", "foods", 0, JOptionPane.QUESTION_MESSAGE, null, foodOptions, foodOptions[2]);
        if (i == 0);
            foods = new Meat(this);
        if (i == 1)
            foods = new Cabbage(this);
        if (i == 2)
            foods = new Lettuce(this);
        repaint();
  }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(bgflag){
            try{ 
                g.drawImage(ImageIO.read(new File("pictures\\savanna.jpg")), 0, 0, getWidth(), getHeight(), this);
            }
            catch (IOException e){
                System.out.println("Error loading file");
            }
        }
        if(foods != null)
            foods.drawObject(g);
        synchronized(this) {
                for(Animal an : animals)
                an.drawObject(g);
        }
   }
    
    /**
     * clearing the panel
     */
    synchronized public void clear() {
        for(Animal an : animals){
            an.setInterrupted();
        }
        animals.clear();
        foods = null;
        totalEaten = 0;
        numOfAnimals = 0;
        repaint();
   }
    
    /**
     * an animal eats the food
     * @param animal 
     */
    synchronized public void eatFood(Animal animal){
        totalEaten++;
        foods = null;
        animal.eatInc();
        
    }
    
    /**
     * a predator devours an animal
     * @param predator
     * @param eaten 
     */
    synchronized public void eatAnimal(Animal predator, Animal eaten){
        predator.eatInc();
        totalEaten -= eaten.getEatCount() + 1;
   }
    
    /**
     * resumes the animals' threads
     */
    public void start() {
        for(Animal an : animals)
            an.setResumed();
   }
    
    /**
     * suspends the animals' threads
     */
    public void stop() {
        for(Animal an : animals)
            an.setSuspended();
   }
    
    /**
     * indicates if changes have beem made
     * @return 
     */
    public boolean isChanged() {
        boolean flag = false;
        for(Animal animal : animals) {
            if(animal.getChanges()){
                animal.setChanges(false);
                flag = true;
                }
        }
        return flag;
    }
    
    /**
     * animal adding, sleep, wake up, clearing the panel, food adding, information chart, exit
     * @param e represents the choice
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1)
            addDialog();
        if(e.getSource() == button2) 
            stop();
        if(e.getSource() == button3)
            start();
        if(e.getSource() == button4) 
            clear();
        if(e.getSource() == button5)
            addFood();
        if(e.getSource() == button6) 
            setChart();
        if(e.getSource() == button7){ 
            for(Animal an : animals)
                an.setInterrupted();
            controller.interrupt();
            System.exit(0);
        }
   }
    
    /**
     * adds animal to the panel with the respective attributes
     * @param name
     * @param hor
     * @param ver
     * @param size
     * @param color 
     */
    public void addAnimal(String name, int hor, int ver, int size, String color){
        Animal animal = null;
        if(name == "Elephant")
            animal = new Elephant(this, hor, ver, size, color);
        if (name == "Lion")
            animal = new Lion(this, hor, ver, size, color);
        if (name == "Turtle") 
            animal = new Turtle(this, hor, ver, size, color);
        if (name == "Bear")
            animal = new Bear(this, hor, ver, size, color);
        if (name == "Giraffe") 
            animal = new Giraffe(this, hor, ver, size, color);
        animals.add(animal);
        animal.setStarted();
   }
    
    /**
     * checks if the animal is close enough to eat the food
     * @param pred
     * @param eaten
     * @return 
     */
    public boolean checkEatLocation(Animal pred, Animal eaten){
        if ((Math.abs(pred.getLocation().getX() - eaten.getLocation().getX()) < eaten.getSize()) && (Math.abs(pred.getLocation().getY() - eaten.getLocation().getY()) < eaten.getSize()))
            return true;
        return false;
    }
    
    /**
     * checks if the food can be eaten by the animal
     * @param pred
     * @param eaten
     * @return 
     */
    public boolean canBeEaten(Animal pred, Animal eaten){
        if(pred != eaten && pred.getDiet().canEat(eaten.getFoodType()) && checkEatLocation(pred, eaten) && pred.getWeight() >= eaten.getWeight() * 2)
            return true;
        return false;
    }
    
    /**
     * checks if theres food on the panel
     * @return 
     */
    public boolean hasFood(){
        if (foods != null)
            return true;
        return false;
    }
    
    /**
     * 
     * @return the food
     */
    public Plant getFood(){
        return foods;
    }
    
    /**
     * clears the food after its eaten
     */
    public void destroyFood(){
        foods = null;
    }
    
    @Override
    public void run() {
        while(true) {
            if(isChanged())
                repaint();
            boolean hasBeenEaten = false;
            synchronized(this) {
                for(Animal pred : animals) {
                    for(Animal eaten : animals) {
                        if(canBeEaten(pred, eaten)){
                            eatAnimal(pred,eaten);
                            eaten.setInterrupted();
                            animals.remove(eaten);
                            repaint();
                            hasBeenEaten = true;
                            break;
                        }
                    }
                    if(hasBeenEaten)
                            break;
                }
            }
            try {
                Thread.sleep(50);
            } 
            catch (InterruptedException e) {
                return;
            }
        }
    }
}
