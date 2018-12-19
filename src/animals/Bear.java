package animals;


import diet.Omnivore;
import graphics.ZooPanel;



/**
 * @author or
 *
 */
public class Bear extends AnimalThatRoars {
    
    private final double WEIGHT_FACTOR = 1.5;
    private final String name = "Bear";
    
    public Bear(ZooPanel panel, int horSpeed, int verSpeed, int size, String color){
        super(panel, horSpeed, verSpeed, size, color);
        setWeight(getSize()*WEIGHT_FACTOR);
        setDiet(new Omnivore());
        loadImages("bea");
    }
    
    /**
     * 
     * @return the animals type
     */
    public String getAnimalName(){
        return name;
    }
    
}
