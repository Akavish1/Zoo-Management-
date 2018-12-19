package animals;

import graphics.ZooPanel;


public abstract class AnimalThatChews extends Animal {

    public AnimalThatChews(ZooPanel panel, int horSpeed, int verSpeed, int size, String color) {
            super(panel, horSpeed, verSpeed, size, color);
    }
    
}
