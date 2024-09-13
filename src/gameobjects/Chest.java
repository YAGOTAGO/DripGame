package gameobjects;

import base.AnimatedGO;
import base.IHitbox;
import game.ExecuteGame;
import player.Player;
import java.awt.*;

public class Chest extends AnimatedGO implements IHitbox {
    private final Rectangle HIT_BOX;
    private boolean canCollide = true;
    private final int SCORE;
    public Chest(int score, int x, int y) {
        super("chest", .5, x, y);
        HIT_BOX = new Rectangle(x + 30, y + 60, 100, 80);
        SCORE = score;
    }

    @Override
    public Rectangle getHitbox() {
        return HIT_BOX;
    }

    @Override
    public void onHit(Player player) {
        ExecuteGame.getInstance().getLogic().updateScore(SCORE);
        canDraw = false;
        canCollide = false;
    }

    @Override
    public boolean canCollide() {
        return canCollide;
    }
}
