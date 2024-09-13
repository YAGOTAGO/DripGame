
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Queue;

public final class Player extends GameObject implements IHitbox {
    private final Rectangle hitBox;
    private final Fuel fuel;
    private final PlayerHealth health;
    private Explosion explosion;

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
    
    public Player(List<GameObject> toDraw, int fuel){
        super(50, 300); 
        this.fuel = new Fuel(fuel);
        hitBox = new Rectangle(SPAWN_POS_X, SPAWN_POS_Y, WIDTH, HEIGHT);
        flameOn = ImageHelper.getBufferedImage("ship", "spaceshipFlame.png");
        flameOff = ImageHelper.getBufferedImage("ship", "spaceship.png");
        movement = new PlayerMovement();
        health = new PlayerHealth();
        explosion = new Explosion(xPos, yPos);
        toDraw.add(explosion);
        }

    @Override
    public Image display() {
        return (fuel.hasFuel() && thrust) ? flameOn : flameOff;
    }

    public void respawn(){
        explosion.trigger(xPos, yPos);
        if(health.takeDamage() >=-100){
            angle = 0;
            fuel.resetFuel();
            changePosition(SPAWN_POS_X, SPAWN_POS_Y);
            movement.reset();
        }else{
            //lose the game
        }
    }
    
    public void changeFuel(int amount){
        fuel.gainFuel(amount);
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
    public int getFuelAmount(){ return fuel.getFuel(); }
    public Fuel getFuel() { return fuel; }
    public double getAngle() { return angle; }
    public Queue<GameObject> getHearts(){ return health.getHearts(); }   

    //IHitBox methods
    @Override
    public void onHit(Player player) {}
    }
