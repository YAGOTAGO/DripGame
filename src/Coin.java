import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Coin extends Collectible implements IAnimated{
    List<Image> animation;
    double animationStep = 0;
    
    public Coin(int x, int y) {
        super(x, y, 50, 50);
        animation = new ArrayList<>();
        initArr();
    }
    
    private void initArr(){
        for (int i = 1; i <= 10; i++) {
            animation.add(i-1 , ImageHelper.getImage("coin", "gold"+i));
        }
    }
    
    @Override
    public void onCollected() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Image GetFrame() {
        return animation.get((int)animationStep);
    }

    @Override
    public void updateAnimationStep() {
        animationStep = animationStep > 9 ? 0 : animationStep + .5;
    }
	
}
