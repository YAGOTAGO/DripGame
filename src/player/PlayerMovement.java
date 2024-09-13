package player;

import java.awt.image.BufferedImage;

import player.Fuel;
import utils.ImageHelper;
import utils.RotationHelper;

public final class PlayerMovement {
    // Movement-related fields
    private double xVel;
    private double yVel;
    
    private final int FUEL_USE_AMOUNT = 1; //means 1 fuel per movement
    private final double GRAVITY = 0.5; // Gravity force
    private final double FRICTION = 0.97; // Friction to slow down the spaceship
    private final double Y_VEL_COEFFICIENT = 1;
    private final double X_VEL_COEFFICIENT = 1.2;

    //Image
    private BufferedImage flameOn;
    private BufferedImage flameOff;
    
    //Angles in DEGREEs
    private double angle;
    private final double ANGLE_CHANGE = 15.0; 

    public PlayerMovement() {
        this.xVel = 0;
        this.yVel = 0;
        this.angle = 0;
        flameOn = ImageHelper.getBufferedImage("ship", "spaceshipFlame.png");
        flameOff = ImageHelper.getBufferedImage("ship", "spaceship.png");
    }

    // Apply thrust, update velocities
    public void applyThrust(Fuel fuel, boolean thrust) {
        if (thrust & fuel.hasFuel()) {
            // Calculate velocity based on angle and thrust
            yVel -= (Y_VEL_COEFFICIENT * Math.cos(Math.toRadians(angle)));
            xVel += (X_VEL_COEFFICIENT * Math.sin(Math.toRadians(angle)));
            fuel.useFuel(FUEL_USE_AMOUNT);
        }
        
        // Apply gravity and friction
        yVel += GRAVITY;
        yVel *= FRICTION;
        xVel *= FRICTION;
    }

    //Position, Angle, Movement
    public void changeAngle(boolean isPositiveChange){
        //determine the sign
        double signedAngleChange = isPositiveChange ? ANGLE_CHANGE : -ANGLE_CHANGE;

        //change the angle
        angle += signedAngleChange;
        angle = RotationHelper.normalizeAngle(angle);

        //rotate the image
        flameOn = RotationHelper.getRotatedBufferedImage(flameOn, signedAngleChange, angle, true);
        flameOff = RotationHelper.getRotatedBufferedImage(flameOff, signedAngleChange, angle, false);
    }

    // Update X position
    public int updateX(int xPos) {
        return xPos + (int) xVel;
    }

    // Update Y position
    public int updateY(int yPos) {
        return yPos + (int) yVel;
    }

    public void reset(){
        yVel = 0;
        xVel = 0;
        angle = 0;
    }

    //GETTERS
    public double getAngle(){ return angle; }
    public BufferedImage getFlameOn(){ return flameOn; }
    public BufferedImage getFlameOff(){ return flameOff; }

}
