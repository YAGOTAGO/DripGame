import java.awt.Image;


public final class Explosion extends AnimatedGO{
    private final int X_OFFSET = 50;
    private final int Y_OFFSET = 50;

    public Explosion(int x, int y) {
        super("explosion", .7, x, y);
        setDraw(false);
    }

    public void trigger(int x, int y){
        xPos = x - X_OFFSET;
        yPos = y - Y_OFFSET;
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
