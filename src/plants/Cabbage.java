package plants;

import graphics.ZooPanel;
import utilities.MessageUtility;

/**
 * @author or
 *
 */
public class Cabbage extends Plant {
    public Cabbage(ZooPanel opanel){
        super(opanel);
        loadImages("cabbage.png");
        MessageUtility.logConstractor("Cabbage", "Cabbage");
    }
}
