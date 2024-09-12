import java.awt.Rectangle;

public interface IHitbox {  
    
    public Rectangle getHitbox();
    
    //checks if collectible have been intersected
    default boolean checkHit(IHitbox target) {
        return target.getHitbox().intersects(target.getHitbox());
    }
    
    public void onHit();
}
