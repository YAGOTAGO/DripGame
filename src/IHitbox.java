import java.awt.Rectangle;

public interface IHitbox {  
    
    public Rectangle getHitbox();
    
    //checks if collectible have been intersected
    default boolean intersects(IHitbox target) {
        return this.getHitbox().intersects(target.getHitbox());
    }
    
    public void onHit(Player player);

    public boolean canCollide();
}
