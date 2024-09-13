import java.util.ArrayList;
import java.util.List;

public abstract class Level {
    protected List<GameObject> objectsToDraw = new ArrayList<>();
    protected List<IHitbox> collidables = new ArrayList<>();

    public List<GameObject> getObjectDrawList(){ return objectsToDraw; }
    public List<IHitbox> getCollidablesList(){ return collidables; }

    public void setBackground(){
        objectsToDraw.add(new SpriteGO("screen", "background.png", 0, 0));
		objectsToDraw.add(new SpriteGO("enviornment", "cliffBot.png", -10, -30));
		objectsToDraw.add(new SpriteGO("enviornment", "cliffTop.png", -10, -30));
		objectsToDraw.add(new SpriteGO("UI", "metal.png", 0, 0));
    }
}
