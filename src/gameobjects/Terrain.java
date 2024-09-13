package gameobjects;

import base.IHitbox;
import player.Player;

import java.awt.Rectangle;

public final class Terrain implements IHitbox {
    Rectangle hitBox;

    public Terrain(int x, int y, int width, int height){
        hitBox = new Rectangle(x, y, width, height);
    }

    @Override
    public Rectangle getHitbox() {
        return hitBox;
    }

    @Override
    public void onHit(Player player) {
        player.respawn();
    }

}
