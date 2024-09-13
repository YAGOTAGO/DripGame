package gameobjects;

import base.IHitbox;
import base.IMovable;
import base.SpriteGO;
import player.Player;

import java.awt.*;

public class DripProjectile extends SpriteGO implements IMovable, IHitbox {
    private final Rectangle HIT_BOX;
    private boolean canCollide;
    private final int SPEED = 7;
    public DripProjectile(int x, int y) {
        super("drip", "dripBall.png", x, y);
        HIT_BOX = new Rectangle(x, y, 27, 27);
        canDraw = false;
        canCollide = false;
    }

    @Override
    public void move() {
        xPos -= SPEED;
        HIT_BOX.setLocation(xPos, yPos);
    }
    @Override
    public Rectangle getHitbox() {
        return HIT_BOX;
    }

    @Override
    public void onHit(Player player) {
        player.respawn();
    }

    @Override
    public boolean canCollide() {
        return canCollide;
    }

    public void shoot(int x, int y) {
        canCollide = true;
        canDraw = true;
        xPos = x;
        yPos = y;
        HIT_BOX.setLocation(x, y);
    }

}
