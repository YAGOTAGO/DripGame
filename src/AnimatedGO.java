import java.awt.Image;
import java.util.List;
import java.util.Random;

public class AnimatedGO extends GameObject{
    protected List<Image> animation;
    protected double currFrame;
    protected double animationRate;
    protected int maxFrames;
        
    public AnimatedGO(String folder, double animationRate, int x, int y){
        super(x, y);
        this.animationRate = animationRate;
        animation = ImageHelper.getAnimation(folder);
        maxFrames = animation.size() - 1;
        currFrame = 0;
    }

    protected void randomizeCurrFrame(){
        Random Random = new Random();
        currFrame = Random.nextInt(maxFrames);
    }

    @Override
    public Image display() {
        currFrame = currFrame > maxFrames ? 0 : currFrame + animationRate;
        return animation.get((int)currFrame);
    }
}
