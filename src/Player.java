
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public final class Player extends GameObject implements IHitbox {
    Rectangle hitBox;
    
    Fuel fuel;
    PlayerHealth health;

    //Image
    BufferedImage flameOn;
    BufferedImage flameOff;

    //Move variables
    private final PlayerMovement movement;
    boolean thrust = false;
    
    //Angles in DEGREEs
    double angle;
    final double ANGLE_CHANGE = 15.0; 

    public Player(Fuel fuel, int x, int y){
        super(x, y);
        this.fuel = fuel;
        hitBox = new Rectangle(x, y, 45, 50);
        flameOn = ImageHelper.getBufferedImage("ship", "spaceshipFlame.png");
        flameOff = ImageHelper.getBufferedImage("ship", "spaceship.png");
        movement = new PlayerMovement();
        health = new PlayerHealth();
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
        movement.applyThrust(fuel, angle, thrust); 
        xPos = movement.updateX(xPos);
        yPos = movement.updateY(yPos);
        hitBox.setLocation(xPos, yPos);
    }   

    @Override
    public Image display() {
        return (fuel.hasFuel() && thrust) ? flameOn : flameOff;
    }

    @Override
    public Rectangle getHitbox() {
        return hitBox; 
    }
    
    public void setThrust(boolean on){
        thrust = on;
    }

    public PlayerHealth getPlayerHealth(){
        return health;
    }

    @Override
    public void onHit(Player player) {}

    @Override
    public boolean canCollide() {
        return true;
    }
    

}
