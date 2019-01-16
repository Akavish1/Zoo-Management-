package plants;

import java.util.Random;

import food.EFoodType;
import food.IEdible;
import mobility.ILocatable;
import mobility.Point;
import utilities.MessageUtility;
import utilities.Validators;
import graphics.IDrawable;
import static graphics.IDrawable.PICTURE_PATH;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import graphics.ZooPanel;

/**
 * Represents a plant
 * 
 *
 */
public abstract class Plant implements IEdible, ILocatable, IDrawable {

	private double height;
	private Point location;
	private double weight;
        private BufferedImage img = null;
        private ZooPanel panel;

	public Plant(ZooPanel opanel) {
                this.panel = opanel;
		Random rand = new Random();
		int x = rand.nextInt(30);
		int y = rand.nextInt(12);
		this.location = new Point(x, y);
		this.height = rand.nextInt(30);
		this.weight = rand.nextInt(12);
		MessageUtility.logConstractor("Plant", "Plant");
	}
        
        @Override
        public void drawObject(Graphics g){
            if (img != null){
                g.drawImage(img, panel.getWidth()/2, panel.getHeight()/2, 50, 50, panel);
            }
        }
        
        @Override
        public String getColor(){
            return null;
        }
        
        @Override
        public void loadImages(String nm){
            try{
                img = ImageIO.read(new File(PICTURE_PATH + nm));
		}
            catch (IOException e){
                e.printStackTrace();
            }
        }
        
        
	@Override
	public EFoodType getFoodType() {
		return EFoodType.VEGETABLE;
	}

	/**
	 * @return The height of the plant
	 */
	public double getHeight() {
		return this.height;
	}

	@Override
	public Point getLocation() {
		return this.location;
	}

	/**
	 * @return The weight of the plant
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param newHeight - the new height
	 * @return True if assignment is successful
	 */
	public boolean setHeight(double newHeight) {

		if (Validators.IsPositive(newHeight)) {
			this.height = newHeight;
			return true;
		}
		this.height = 0;

		return false;
	}

	@Override
	public boolean setLocation(Point newLocation) {
		this.location = new Point(0, 0);
		return false;
	}

	/**
	 * @param newWeight - the new weight
	 * @return True if assignment is successful
	 */
	public boolean setWeight(double newWeight) {

		if (Validators.IsPositive(newWeight)) {
			this.weight = newWeight;
			return true;
		}
		this.weight = 0;

		return false;
	}

	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] ";
	}

}
