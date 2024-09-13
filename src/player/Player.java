package player;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import base.*;
import gameobjects.Explosion;
public final class Player extends GameObject implements IHitbox, IParentGO {
    private final int SPAWN_POS_X = 50;
    private final int SPAWN_POS_Y = 300;
    private final int WIDTH = 40;
    private final int HEIGHT = 40;
    private final Rectangle HIT_BOX;

    //Movement related
    private final PlayerMovement movement;
    private boolean thrust = false;

    //Child objects
    private final PlayerHealth health;
    private final Explosion explosion;
    private final Fuel fuel;

    public Player(int fuel){
        super(50, 300); 
        this.fuel = new Fuel(fuel);
        HIT_BOX = new Rectangle(SPAWN_POS_X, SPAWN_POS_Y, WIDTH, HEIGHT);
        movement = new PlayerMovement();
        health = new PlayerHealth();
        explosion = new Explosion(xPos, yPos);
    }

    @Override
    public Image display() {
        return (fuel.hasFuel() && thrust) ? movement.getFlameOn() : movement.getFlameOff();
    }

    public void respawn(){
        explosion.trigger(xPos, yPos);
        if(health.takeDamage() > 0){
            changePosition(SPAWN_POS_X, SPAWN_POS_Y);
            fuel.resetFuel();
            movement.reset();
        }else{
            //lose the game
        }
    }

    public void changeAngle(boolean isPositiveChange){
        movement.changeAngle(isPositiveChange);
    }

    public void changeFuel(int amount){
        fuel.gainFuel(amount);
    }
    
    public void move(){
        movement.applyThrust(fuel, thrust); 
        changePosition(movement.updateX(xPos), movement.updateY(yPos));
    }   
    
    public void setThrust(boolean on){
        thrust = on;
    }
    
    private void changePosition(int x, int y){
        xPos = x;
        yPos = y;
        HIT_BOX.setLocation(x + 16, y + 10); //slight offset to line up better
    }
    
    //GETTERS
    @Override
    public Rectangle getHitbox() { return HIT_BOX;  }
    public int getFuelAmount(){ return fuel.getFuel(); }
    public Fuel getFuel() { return fuel; }
    public double getAngle() { return movement.getAngle(); }
    
    @Override
    public List<GameObject> getChildObjectsToDraw() {
        List<GameObject> list = new ArrayList<>();
        list.addAll(health.getHearts());
        list.add(explosion);
        return list;
    }
    
    //IHitBox methods
    @Override
    public void onHit(Player player) {}

    
}