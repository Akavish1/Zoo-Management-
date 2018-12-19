 // Or Hadad / 308101591 / Beer Sheva

package plants;

import food.EFoodType;
import graphics.ZooPanel;
import utilities.MessageUtility;

/**
 @author אור
 */
public class Meat extends Plant{
    public Meat(ZooPanel opanel){
        super(opanel);
        loadImages("meat.gif");
        MessageUtility.logConstractor("Meat", "Meat");
    }
    public EFoodType getFoodType() {
        MessageUtility.logGetter(this.getClass().getSimpleName(), "getFoodType", EFoodType.VEGETABLE);
        return EFoodType.MEAT;
    }
        
}
