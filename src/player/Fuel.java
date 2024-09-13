package player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import base.GameObject;

public final class Fuel extends GameObject {
    final int INIT_FUEL;
    private int fuel;
    private final int WIDTH = 20;
    private final int MAX_HEIGHT = 200;

    public Fuel(int fuel) {
        super(85, -50);
        this.fuel = fuel;
        INIT_FUEL = fuel;
    }
    
    @Override
    public Image display() {
        int filledHeight = (fuel > 0) ? (int) ((fuel / (2.0 * INIT_FUEL)) * MAX_HEIGHT) : 0;
        Color color = fuel > INIT_FUEL/2 ? Color.MAGENTA : Color.RED;
        BufferedImage image = new BufferedImage(WIDTH, MAX_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = image.createGraphics();
        g2d.setColor(color);
        g2d.fillRect(0, MAX_HEIGHT - filledHeight, WIDTH, filledHeight);
        g2d.dispose(); //so garbage collector doesn't have to
        
        return image;
    }

    public void useFuel(int reduceBy){
        fuel = Math.max(0, fuel - reduceBy); // Ensure fuel does not go below 0
    }

    public void gainFuel(int amount){
        fuel += amount;
    }

    public boolean hasFuel(){
        return fuel > 0;
    }

    public int getFuel(){return fuel;}

    public void resetFuel(){
        fuel = INIT_FUEL;
    }

    public int getMaxHeight() {
        return MAX_HEIGHT;
    }
}
