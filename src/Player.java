
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public final class Player extends GameObject implements IHitbox, IMovable {
    Rectangle hitBox;
    BufferedImage flameOn;
    BufferedImage flameOff;
    
    int numLives = 3;

    //Move values
    final double initialAngle = 0;
    double angle;
    double xVel = 0;
    double yVel = 0;
    double angleChange = 15.0; //in degrees

    public Player(int x, int y){
        super(x, y);
        hitBox = new Rectangle(x, y, 45, 50);
        angle = initialAngle;
        flameOn = ImageHelper.getBufferedImage("ship", "spaceshipFlame.png");
        flameOff = ImageHelper.getBufferedImage("ship", "spaceship.png");
    }
    
    //changes theta and the image rotation
    public void changeTheta(boolean isPositiveChange){
        //change the angle
        angle = isPositiveChange ? angle+angleChange : angle-angleChange;
        angle = RotationHelper.normalizeAngle(angle);
        System.out.println(angle);

        //rotate the image
        flameOn = RotationHelper.getRotatedBufferedImage(flameOn, angleChange, angle, true);
        flameOff = RotationHelper.getRotatedBufferedImage(flameOff, angleChange, angle, false);
    }

    @Override
    public Image display() {
        //either return flame on or off
        return flameOff;
    }

    @Override
    public Rectangle getHitbox() {
        return hitBox; 
    }

    @Override
    public void onHit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
