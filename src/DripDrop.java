import java.awt.Rectangle;
import java.util.Collections;
import java.util.List;

public final class DripDrop extends SpriteGO implements IHitbox, IMovable, IParentGO{
    private final SpriteGO DRIP_FLAT_GO;
    private final Rectangle HIT_BOX;
    private final int START_Y_POS;

    //timing
    private final int GROUND_Y = 270;
    private final int DELAY = 45;
    private int count = 0;
    private boolean startCount = false;

    public DripDrop(int x, int y) {
        super("drip", "drip.png", x, y);
        DRIP_FLAT_GO = new SpriteGO("drip", "dripFlat.png", x-25, GROUND_Y+30);
        HIT_BOX = new Rectangle(x, y, 30, 40);
        START_Y_POS = y;
        DRIP_FLAT_GO.setDraw(false);
    }
    
    @Override
    public Rectangle getHitbox() {
        return HIT_BOX;
    }   

    @Override
    public void onHit(Player player) {
        player.respawn();
    }

    @Override
    public void move() {
        
        if(yPos >= GROUND_Y){
            yPos = START_Y_POS;
            DRIP_FLAT_GO.setDraw(true);
            startCount = true;
        }

        yPos += 5;
        count++;
        if(startCount && count > DELAY){
            DRIP_FLAT_GO.setDraw(false);
            startCount = false;
            count = 0;
        }

        updateHitBox(xPos, yPos);    
    }
    
    private void updateHitBox(int x, int y){
        HIT_BOX.setLocation(x+10, y);
    }

    @Override
    public List<GameObject> getChildObjectsToDraw() {
        return Collections.singletonList(DRIP_FLAT_GO);
    }
}
