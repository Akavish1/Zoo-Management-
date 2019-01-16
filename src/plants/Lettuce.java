package plants;

import graphics.ZooPanel;
import utilities.MessageUtility;

/**
 *
 */
public class Lettuce extends Plant {
    public Lettuce(ZooPanel opanel){
        super(opanel);
        loadImages("lettuce.png");
        MessageUtility.logConstractor("lettuce", "lettuce");
    }
}
