package animals;

import diet.Herbivore;
import graphics.ZooPanel;


/**
 * @author or
 *
 */
public class Giraffe extends AnimalThatChews {

    private final double WEIGHT_FACTOR =  2.2;
    private final String name = "Giraffe";
    
    public Giraffe(ZooPanel panel, int horSpeed, int verSpeed, int size, String color){
        super(panel, horSpeed, verSpeed, size, color);
        setWeight(getSize()*WEIGHT_FACTOR);
        setDiet(new Herbivore());
        loadImages("grf");
    }
    
    /**
     * 
     * @return the animals type
     */
    public String getAnimalName(){
        return name;
    }
}
