
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public final class Player extends GameObject implements IHitbox {
    Rectangle hitBox;

    //Image
    BufferedImage flameOn;
    BufferedImage flameOff;
    
    //Move variables
    private final PlayerMovement movement;
    boolean thrust = false;
    
    //Angles in DEGREEs
    double angle;
    final double ANGLE_CHANGE = 15.0; 

    public Player(int x, int y){
        super(x, y);
        hitBox = new Rectangle(x, y, 45, 50);
        flameOn = ImageHelper.getBufferedImage("ship", "spaceshipFlame.png");
        flameOff = ImageHelper.getBufferedImage("ship", "spaceship.png");
        movement = new PlayerMovement();
    }

    //changes theta and the image rotation
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
    
    public void move(){
        // Handles thrust, gravity, and friction
        movement.applyThrust(angle, thrust); 

        // Update position based on velocities
        xPos = movement.updateX(xPos);
        yPos = movement.updateY(yPos);
    }   
    
    @Override
    public Image display() {
        return thrust ? flameOn : flameOff;
    }

    @Override
    public Rectangle getHitbox() {
        return hitBox; 
    }

    @Override
    public void onHit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setThrust(boolean on){
        thrust = on;
    }

}
