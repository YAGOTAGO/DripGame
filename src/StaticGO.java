import java.awt.Image;

public final class StaticGO extends GameObject {
    private final Image sprite;

    public StaticGO(String folder, String fileName, int x, int y){
        super(x, y);
        sprite = ImageHelper.getImage(folder, fileName);
    }
    
    @Override
    public Image display() {
        return sprite;
    }
    
}
