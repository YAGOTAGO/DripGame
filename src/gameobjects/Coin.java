package gameobjects;

import base.AnimatedGO;
import base.IHitbox;
import player.Player;
import java.awt.Rectangle;

public class Coin extends AnimatedGO implements IHitbox {
    Rectangle hitBox;
    final int COIN_VALUE = 100;
    boolean canCollide = true;

    public Coin(int x, int y) {
        super("coin", .5, x, y);
        hitBox = new Rectangle(x, y, 50, 50);
        randomizeCurrFrame();
    }
    
    @Override
    public Rectangle getHitbox() {
        return hitBox;
    }
    
    @Override
    public void onHit(Player player) {
        //ExecuteGame.getInstance().getLogic().updateScore(COIN_VALUE);
        canDraw = canCollide = false;
    }

    @Override   
    public boolean canCollide() {
        return canCollide;
    }

}
