package animals;

import diet.IDiet;
import food.EFoodType;
import food.IEdible;
import mobility.Mobile;
import mobility.Point;
import graphics.IAnimalBehavior;
import graphics.IDrawable;
import static graphics.IDrawable.PICTURE_PATH;
import graphics.ZooPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * 
 * @author or
 *
 */
public abstract class Animal extends Mobile implements IEdible, IDrawable, IAnimalBehavior, Runnable {

	/**
	 * Weight loss factor after moving
	 */
	public static final double WEIGHT_LOSS_FACTOR = 0.00025;

	private IDiet diet;
	protected String name;
	private double weight;
        //new atts
        protected final int EAT_DISTANCE = 5;
        protected int size; 
        protected Color col;
        protected int horSpeed;
        protected int verSpeed;
        protected boolean coordChanged;
        protected Thread thread;
	protected int x_dir;
	protected int y_dir;
	protected int eatCount;
	protected ZooPanel panel;
	protected boolean threadSuspended;	 
	protected BufferedImage img1, img2;

        /**
         * 
         * @param panel is the parent panel
         * @param horSpeed horizontal speed
         * @param verSpeed vertical speed
         * @param size size of the animal
         * @param color 
         */       
	public Animal(ZooPanel panel, int horSpeed, int verSpeed, int size, String color) {
            super(new Point(0, 0));
            this.panel = panel;
            this.horSpeed = horSpeed;
            this.verSpeed = verSpeed;
            this.size = size;
            setColor(color);
            x_dir = -1;
            y_dir = -1;
            thread = new Thread(this);
            threadSuspended = false;
	}
        
        @Override
        public void run(){
            while (true){
                try{
                    Thread.sleep(50);
                    synchronized(this) {
                    while (threadSuspended)
                        wait();
                    }  
                } 
                catch (InterruptedException e){	
                    return;
                }      
                if(panel.getFood() != null && this.getDiet().canEat(panel.getFood().getFoodType())){
                    double oldSpeed = Math.sqrt(horSpeed*horSpeed+verSpeed*verSpeed);
                    double newhorSpeed = oldSpeed*(getLocation().getX() - panel.getWidth()/2)/(Math.sqrt(Math.pow(getLocation().getX() - panel.getWidth()/2,2)+Math.pow(getLocation().getY() - panel.getHeight()/2,2)));
                    double newverSpeed = oldSpeed*(getLocation().getY() - panel.getHeight()/2)/(Math.sqrt(Math.pow(getLocation().getX() - panel.getWidth()/2,2)+Math.pow(getLocation().getY() - panel.getHeight()/2,2)));
                    int v = 1;
                    if(newverSpeed < 0) 
                        v = -1;
                        newverSpeed = -newverSpeed;
                    if(newverSpeed > 10)
              		newverSpeed = 10;
                    else if(newverSpeed < 1) {
                        if(getLocation().getY() != panel.getHeight()/2)
                            newverSpeed = 1;   
                    else
              		newverSpeed = 0;  
              	}
                    int h = 1;
                    if(newhorSpeed < 0) 
                        h=-1; newhorSpeed = -newhorSpeed; 
                    if(newhorSpeed > 10)
                        newhorSpeed = 10;
                    else if(newhorSpeed < 1) {
                       if(getLocation().getX() != panel.getWidth()/2)
                            newhorSpeed = 1;   
                       else
                            newhorSpeed = 0;  
                    }
                    getLocation().setX((int)(getLocation().getX() - newhorSpeed*h));
                    getLocation().setY((int)(getLocation().getY() - newverSpeed*v));
                    if(getLocation().getX()<panel.getWidth()/2)
                        x_dir = 1;
                    else
                        x_dir = -1;
                    if((Math.abs(getLocation().getX()-panel.getWidth()/2)<EAT_DISTANCE) && (Math.abs(getLocation().getY()-panel.getHeight()/2)<EAT_DISTANCE))
                        panel.eatFood(this); 	
                }
                else{
                     getLocation().setX(getLocation().getX() + horSpeed*x_dir);
                     getLocation().setY(getLocation().getY() + verSpeed*y_dir);
                }

                     if(getLocation().getX() > panel.getWidth()+size/4)
                         x_dir = -1;

                     else if(getLocation().getX() < -size * 0.25){
                         x_dir = 1;
                     }
                      if(getLocation().getY() > (int)(panel.getHeight()-30 - size*9/10)){
                         y_dir = -1;
                     }
                     else if(getLocation().getY() < size/10){
                         y_dir = 1;
                     }
                     setChanges(true);
            }  
        }
        
	/**
	 * The animal eats the food and gains weight
	 * 
	 * @param food
	 *            - Food to eat
	 * @return True if food was eaten
	 */
	public boolean eat(IEdible food) {
		double gainedWeight = this.diet.eat(this.weight, food);
		if (gainedWeight > 0) {
			this.weight += gainedWeight;
			return true;
		}
		return false;
	}
        
        /**
         * 
         * @return the size
         */
        public int getSize(){
            return this.size;
        }
        
        /**
         * 
         * @return horizontal speed
         */
        public int gethorSpeed(){
            return this.horSpeed;
        }
        
        /**
         * 
         * @return vertical speed
         */
        public int getverSpeed(){
            return this.verSpeed;
        }
        
        /**
         * 
         * @return coordination changes
         */
        
        /**
         * accesses the changes parameter
         */
	synchronized public boolean getChanges(){ 
            return coordChanged; 
        }
        
        /**
         * sets the changes.
         */
	synchronized public void setChanges(boolean state){ 
            coordChanged = state; 
        }	
        
        /**
         * starts the thread
         */
        public void setStarted() {
            thread.start(); 
        }
        
        /**
         * 
         * @return the color
         */
        public String getColor(){
            if (this.col == Color.RED)
                return "Red";
            if (this.col == Color.BLUE)
                return "Blue";
            return "Natural";
        }
        
	/**
	 * @return the diet
	 */
	public IDiet getDiet() {
		return this.diet;
	}

	@Override
	public EFoodType getFoodType() {
		return EFoodType.MEAT;
	}


	/**
	 * @return the weight
	 */
	public double getWeight() {
		return this.weight;
	}

	/**
	 * @param newDiet
	 */
	protected void setDiet(IDiet newDiet) {
		this.diet = newDiet;
	}
        
        /**
         * 
         * @param newhorSpeed
         * @return true if assignment is successful
         */
        public boolean sethorSpeed(int newhorSpeed){
            if (newhorSpeed < 10 && newhorSpeed > 1){
                this.horSpeed = newhorSpeed;
                return true;
            }
            return false;
        }
        
        /**
         * 
         * @param newverSpeed
         * @return true if assignment is successful
         */
        public boolean setverSpeed(int newverSpeed){
            if (newverSpeed < 10 && newverSpeed > 1){
                this.verSpeed = newverSpeed;
                return true;
            }
            return false;
        }
        
        /**
         * sets the animal's color
         * @param newcolor 
         */
        public void setColor(String newcolor){
            if (newcolor == "Red")
                this.col = Color.RED;
            if (newcolor == "Blue")
                this.col = Color.BLUE;
            if (newcolor == null)
                this.col = null;
        }
        
        
        /**
         * 
         * @param newSize
         *          - the new size
         * @return True if assignment is successful
         */
        public boolean setSize(int newSize){
            if (newSize > 300 || newSize < 50){
                return false;
            }
            this.size = newSize;
            return true;
        }
        
	/**
	 * @param newWeight
	 *            - The new weight
	 * @return True if assignment is successful
	 */
	public boolean setWeight(double newWeight) {
		if (utilities.Validators.IsPositive(newWeight)) {
			this.weight = newWeight;
			return true;
		}
		return false;
	}
        
        /**
         * interrupts the thread
         */
        public void setInterrupted(){
            thread.interrupt();
        }

        @Override
	public void loadImages(String nm){
            switch(getColor()){
                case "Red":
                    try{ 
                        img1 = ImageIO.read(new File(PICTURE_PATH + nm + "_r_1.png"));
                        img2 = ImageIO.read(new File(PICTURE_PATH + nm + "_r_2.png"));
                    }
                    catch (IOException e) {
                        System.out.println("Cannot load picture");
                    }
                    break;
                case "Blue":
                    try{ 
                        img1 = ImageIO.read(new File(PICTURE_PATH + nm + "_b_1.png"));
                        img2 = ImageIO.read(new File(PICTURE_PATH + nm + "_b_2.png"));
                    }
                    catch (IOException e) {
                        System.out.println("Cannot load picture");
                    }
                    break;
                default:
                    try {
                    img1 = ImageIO.read(new File(PICTURE_PATH + nm + "_n_1.png"));
                    img2 = ImageIO.read(new File(PICTURE_PATH + nm + "_n_2.png"));
                    }
                    catch (IOException e) {
                    System.out.println("Cannot load picture");
                    }
                    break;
            }
	}
        
        @Override
	public void drawObject(Graphics g)
	{
	   g.setColor(col);
	   if(x_dir == -1) //goes right
		   g.drawImage(img1, getLocation().getX() - size/2, getLocation().getY() - size/2, size/2, size, panel);
	   else //goes left
		   g.drawImage(img2, getLocation().getX(), getLocation().getY() - size/2, size/2, size, panel);
	}
        
        /**
         * suspends the thread
         */
        synchronized public void setSuspended() { 
            threadSuspended = true; 
        }
        
        /**
         * resumes the thread
         */
	synchronized public void setResumed() { 
            threadSuspended = false; 
            notify(); 
        }
        
	@Override
	public void eatInc() {
            this.eatCount++;
        }

	@Override
	public int getEatCount() {
            return this.eatCount;
        }
        
	@Override
	public String toString() {
            return "[" + this.getClass().getSimpleName() + "] " + this.name;
	}
}
