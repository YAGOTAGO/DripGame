import java.awt.Image;

public final class Explosion extends AnimatedGO{
    
    public Explosion(int x, int y) {
        super("explosion", 1.0, x, y);
        setDraw(false);
    }
    
    public void trigger(int x, int y){
        xPos = x;
        yPos = y;
        setDraw(true);
    }
    
    @Override
    public Image display() {
        if(currFrame > maxFrames){
            currFrame = 0;
            setDraw(false);
        }else{
            currFrame += animationRate;
        }
        return animation.get((int)currFrame);
    }
}
