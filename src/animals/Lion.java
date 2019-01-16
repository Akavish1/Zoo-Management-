package animals;

import diet.Carnivore;
import food.EFoodType;
import graphics.ZooPanel;


/**
 * 
 *
 */
public class Lion extends AnimalThatRoars {

    private final double WEIGHT_FACTOR = 0.8;
    private final String name = "Lion";
    
    public Lion(ZooPanel panel, int horSpeed, int verSpeed, int size, String color){
        super(panel, horSpeed, verSpeed, size, color);
        setWeight(getSize()*WEIGHT_FACTOR);
        setDiet(new Carnivore());
        loadImages("lio");
    }
    
    /**
     * assuming the point from hw2 still stads, lion is of type not food
     * @return 
     */
    public EFoodType getFoodType() {
        return EFoodType.NOTFOOD;
    }
    
    /**
     * 
     * @return the animals type
     */
    public String getAnimalName(){
        return name;
    }
}
