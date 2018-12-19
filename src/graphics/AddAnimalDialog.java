 // Or Hadad / 308101591 / Beer Sheva

package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import graphics.ZooPanel;

/**
 @author אור
 */
public class AddAnimalDialog extends JDialog implements ActionListener{
    private static final long serialVersionUID = 1L;
    ZooPanel panel;
    private String[] animals = {"Elephant", "Lion", "Giraffe", "Turtle", "Bear"};
    private String[] speeds = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private String[] color = {"Red", "Blue", "Natural"};
    private JComboBox<String> ani, hor, ver, colors;
    private JSlider size;
    private JButton apply, cancel;
    
    public AddAnimalDialog(ZooPanel parpanel){
        super(new JFrame(), "Add Animal", true);
        panel = parpanel;
        setLayout(new GridLayout(6,3));
        setSize(300, 500);
        
        //creating the animal choosing box
        JPanel animalPanel = new JPanel();
        JLabel animalLabel = new JLabel("Choose an animal");
        ani = new JComboBox<String>(animals);
        animalPanel.add(animalLabel);
        animalPanel.add(ani);
        add(animalPanel);
        		
        //creating the size choosing field
        JPanel sizePanel = new JPanel();
        JLabel sizeLabel = new JLabel("Enter animal size (in pixels, 50 - 300)");		
        size = new JSlider(50, 300);
        size.setMajorTickSpacing(50);
        size.setMinorTickSpacing(10);
        size.setPaintTicks(true);
        size.setPaintLabels(true);
        sizePanel.add(sizeLabel);
        sizePanel.add(size);
        add(sizePanel);
        
        //creating the horizontal speed choosing box
        JPanel horPanel = new JPanel();
        JLabel horLabel= new JLabel("Enter horizontal speed (pps, 1-10)");		
        hor = new JComboBox<String>(speeds);
        horPanel.add(horLabel);
        horPanel.add(hor);
        add(horPanel);
        
        //creating the vertical speed choosing box
        JPanel verPanel = new JPanel();
        JLabel verLabel= new JLabel("Enter vertical speed (pps, 1-10)");
        ver = new JComboBox<String>(speeds);
        verPanel.add(verLabel);
        verPanel.add(ver);
        add(verPanel);
        
        //creating the color choosing box
        JPanel colorPanel = new JPanel();
        JLabel colorLabel = new JLabel("Choose color");
        colors = new JComboBox<String>(color);
        colorPanel.add(colorLabel);
        colorPanel.add(colors);
        add(colorPanel);
        
        //creating the basic forward and back buttons
        JPanel buttonPanel = new JPanel();
        apply = new JButton("Apply");
        cancel = new JButton("Cancel");
        apply.addActionListener(this);
        cancel.addActionListener(this);
        buttonPanel.add(apply);
        buttonPanel.add(cancel);
        add(buttonPanel);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent act){
        if (act.getSource() == apply){
            if (panel.numOfAnimals >= 10){
                JOptionPane.showMessageDialog(this, "The number of animals is limited to 10");
                dispose();
                return;
            }
            panel.addAnimal((String)ani.getSelectedItem(), Integer.parseInt((String)hor.getSelectedItem()), Integer.parseInt((String)ver.getSelectedItem()), size.getValue(), (String)colors.getSelectedItem());
            panel.numOfAnimals++;
            dispose();
        }
        if (act.getSource() == cancel)
            dispose();
    }
}
