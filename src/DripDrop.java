import java.awt.Rectangle;
import java.util.List;

public final class DripDrop extends SpriteGO implements IHitbox, IMovable{
    private SpriteGO dripFlat;
    private final Rectangle HIT_BOX;
    private final int START_Y_POS;

    private int groundY = 270;
    private final int DELAY = 45;
    private int count = 0;
    private boolean startCount = false;

    public DripDrop(List<GameObject> toDraw, int x, int y) {
        super("drip", "drip.png", x, y);
        dripFlat = new SpriteGO("drip", "dripFlat.png", x-25, groundY+30);
        HIT_BOX = new Rectangle(x, y, 30, 40);
        START_Y_POS = y;
        toDraw.add(dripFlat);
        dripFlat.setDraw(false);
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
        
        if(yPos >= groundY){
            yPos = START_Y_POS;
            dripFlat.setDraw(true);
            startCount = true;
        }

        yPos += 5;
        count++;
        if(startCount && count > DELAY){
            dripFlat.setDraw(false);
            startCount = false;
            count = 0;
        }

        updateHitBox(xPos, yPos);    
    }
    
    private void updateHitBox(int x, int y){
        HIT_BOX.setLocation(x+10, y);
    }
}
