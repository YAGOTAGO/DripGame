import java.awt.Rectangle;

public class Coin extends AnimatedGO implements IHitbox{
    Rectangle hitBox;

    public Coin(String folder, int x, int y) {
        super(folder, .5, x, y);
        hitBox = new Rectangle(x, y, 50, 50);
    }

    @Override
    public Rectangle getHitbox() {
        return hitBox;
    }

    @Override
    public void onHit() {
        //increase the score
        //dont draw this anymore
        System.out.println("COIN HIT");
    }

}
