import java.awt.Rectangle;

public class Platform extends AnimatedGO implements IHitbox{
    Rectangle hitBox;

    public Platform(){
        super("platform", .3, 1178, 630);
        hitBox = new Rectangle(1178, 690, 210, 50);
    }   
    
    @Override
    public Rectangle getHitbox() {
        return hitBox;
    }

    @Override
    public void onHit(Player player) {
        if(player.getAngle() == 0){
            //game call next level
        }else{
            player.respawn();
        }
    }
}
