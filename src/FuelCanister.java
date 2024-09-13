import java.awt.Rectangle;

public final class FuelCanister extends SpriteGO implements IHitbox{
    private final Rectangle hitBox;
    private final int fuelAmount;
    private boolean canCollide = true;

    public FuelCanister(int fuelAmount, int x, int y) {
        super("UI", "fuel.png", x, y);
        this.fuelAmount = fuelAmount;
        hitBox = new Rectangle(x, y, 50, 50);
    }

    @Override
    public Rectangle getHitbox() {
        return hitBox;
    }
    
    @Override
    public void onHit(Player player) {
        player.changeFuel(fuelAmount);
        canDraw = false;
        canCollide = false;
    }
    
    @Override
    public boolean canCollide(){
        return canCollide;
    }
    
}
