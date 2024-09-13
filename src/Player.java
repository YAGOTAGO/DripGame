
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Queue;

public final class Player extends GameObject implements IHitbox {
    private Rectangle hitBox;
    private Fuel fuel;
    private PlayerHealth health;

    //spawn pos
    private final int SPAWN_POS_X = 50;
    private final int SPAWN_POS_Y = 300;
    private final int WIDTH = 40;
    private final int HEIGHT = 40;

    //Image
    private BufferedImage flameOn;
    private BufferedImage flameOff;

    //Move variables
    private final PlayerMovement movement;
    private boolean thrust = false;
    
    //Angles in DEGREEs
    private double angle;
    private final double ANGLE_CHANGE = 15.0; 
    
    public Player(Fuel fuel){
        super(50, 300); 
        this.fuel = fuel;
        hitBox = new Rectangle(SPAWN_POS_X, SPAWN_POS_Y, WIDTH, HEIGHT);
        flameOn = ImageHelper.getBufferedImage("ship", "spaceshipFlame.png");
        flameOff = ImageHelper.getBufferedImage("ship", "spaceship.png");
        movement = new PlayerMovement();
        health = new PlayerHealth();
    }
    @Override
    public Image display() {
        return (fuel.hasFuel() && thrust) ? flameOn : flameOff;
    }

    public void respawn(){
        if(health.takeDamage() >=-100){
            //do respawn stuff
            angle = 0;
            fuel.resetFuel();
            changePosition(SPAWN_POS_X, SPAWN_POS_Y);
        }else{
            //lose the game
        }
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
    public void move(){
        movement.applyThrust(fuel, angle, thrust); 
        changePosition(movement.updateX(xPos), movement.updateY(yPos));
    }   
    public void setThrust(boolean on){
        thrust = on;
    }
    private void changePosition(int x, int y){
        xPos = x;
        yPos = y;
        hitBox.setLocation(x + 16, y + 10);  
    }
    
    //GETTERS
    @Override
    public Rectangle getHitbox() { return hitBox;  }
    public int getFuel(){ return fuel.getFuel(); }
    public double getAngle() { return angle; }
    public Queue<GameObject> getHearts(){ return health.getHearts(); }   

    //IHitBox methods
    @Override
    public void onHit(Player player) {}
    }
