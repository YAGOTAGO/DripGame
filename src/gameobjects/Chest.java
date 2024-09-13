package gameobjects;

import base.AnimatedGO;
import base.IHitbox;
import player.Player;
import java.awt.*;

public class Chest extends AnimatedGO implements IHitbox {
    private final Rectangle HIT_BOX;
    private boolean canCollide = true;
    public Chest(int x, int y) {
        super("chest", .5, x, y);
        HIT_BOX = new Rectangle(x + 30, y + 60, 100, 80);
    }

    @Override
    public Rectangle getHitbox() {
        return HIT_BOX;
    }

    @Override
    public void onHit(Player player) {
        //Score a bunch of points
        canDraw = false;
        canCollide = false;
    }

    @Override
    public boolean canCollide() {
        return canCollide;
    }
}
