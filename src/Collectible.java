
import java.awt.Rectangle;

public abstract class Collectible {
    protected Rectangle rect;
    private boolean hit;

    public Collectible(int x, int y, int width, int height){
        rect = new Rectangle(x, y, width, height);
        hit = false;
    }

    //checks if coins have been intersected
    public boolean checkHit(Rectangle target) {
        if (!hit && target.intersects(rect)) {
            hit = true;
            return true;
        }
            return false;
    }

    public boolean isHit(){return hit;}
    public void resetHit(){ hit = false;}
}
