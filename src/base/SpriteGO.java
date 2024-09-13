package base;

import utils.ImageHelper;

import java.awt.Image;

public class SpriteGO extends GameObject {
    protected final Image sprite;
    
    public SpriteGO(String folder, String fileName, int x, int y){
        super(x, y);
        sprite = ImageHelper.getImage(folder, fileName);
    }
    
    @Override
    public Image display() {
        return sprite;
    }
    
}
