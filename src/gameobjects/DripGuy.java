package gameobjects;

import base.*;
import player.Player;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class DripGuy extends SpriteGO implements IMovable, IParentGO, IHitbox {

    private final DripProjectile PROJECTILE;
    private final Rectangle HIT_BOX;
    private final int MAX_HEIGHT = 400;
    private final int MIN_HEIGHT = 120;
    private boolean goingUp = true;
    private final int MOVE_SPEED = 2;

    private final int TIMER = 65;
    private int count = 65;
    public DripGuy() {
        super("drip", "dripGuy.png", 910, 120);
        PROJECTILE = new DripProjectile(910, 120);
        HIT_BOX = new Rectangle(910, 120, 60, 60);
    }

    @Override
    public void move() {
        if(yPos < MIN_HEIGHT || yPos > MAX_HEIGHT){
            goingUp = !goingUp;
        }
        yPos += goingUp ? MOVE_SPEED : -MOVE_SPEED;
        updateHitbox(xPos, yPos);

        count++;
        if(count >= TIMER){
            count = 0;
            PROJECTILE.shoot(xPos, yPos);
        }
    }
    @Override
    public List<GameObject> getChildObjectsToDraw() {
        return Collections.singletonList(PROJECTILE);
    }

    @Override
    public Rectangle getHitbox() {
        return HIT_BOX;
    }
    @Override
    public void onHit(Player player) {
        player.respawn();
    }
    private void updateHitbox(int x, int y){
        HIT_BOX.setLocation(x + 20, y + 20); //slight offset to line up better
    }
}
