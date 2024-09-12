import java.awt.Image;

public interface IAnimated {
    public Image GetFrame();
    public void updateAnimationStep();
    public int getX();
    public int getY();
}
