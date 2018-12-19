

package animals;

import diet.Herbivore;
import graphics.ZooPanel;



/**
 * @author or
 *
 */
public class Turtle extends AnimalThatChews {

    private final double WEIGHT_FACTOR = 0.5;
    private final String name = "Turtle";
    
    public Turtle(ZooPanel panel, int horSpeed, int verSpeed, int size, String color){
        super(panel, horSpeed, verSpeed, size, color);
        setWeight(getSize()*WEIGHT_FACTOR);
        setDiet(new Herbivore());
        loadImages("trt");
    }
    
    /**
     * 
     * @return the animals type
     */
    public String getAnimalName(){
        return name;
    }
    
}
