package animals;

import diet.Herbivore;
import graphics.ZooPanel;



/**
 * 
 *
 */
public class Elephant extends AnimalThatChews {
    
    private final double WEIGHT_FACTOR = 10;
    private final String name = "Elephant";
        
    public Elephant(ZooPanel panel, int horSpeed, int verSpeed, int size, String color){
        super(panel, horSpeed, verSpeed, size, color);
        setWeight(getSize()*WEIGHT_FACTOR);
        setDiet(new Herbivore());
        loadImages("elf");
    }
    
    /**
     * 
     * @return the animals type
     */
    public String getAnimalName(){
        return name;
    }
}
