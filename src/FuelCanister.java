import java.awt.Rectangle;

public final class FuelCanister extends SpriteGO implements IHitbox, IMovable{
    private final Rectangle HITBOX;
    private final int FUEL_AMOUNT;
    private boolean canCollide = true;
    private final int START_X_POS;
    private boolean movingRight = true;

    public FuelCanister(int fuelAmount, int x, int y) {
        super("UI", "fuel.png", x, y);
        this.FUEL_AMOUNT = fuelAmount;
        START_X_POS = x;
        HITBOX = new Rectangle(x, y, 50, 50);
    }

    @Override
    public Rectangle getHitbox() {
        return HITBOX;
    }

    @Override
    public void onHit(Player player) {
        player.changeFuel(FUEL_AMOUNT);
        canDraw = false;
        canCollide = false;
    }
    
    @Override
    public boolean canCollide(){
        return canCollide;
    }

    @Override
    public void move() {
        if (xPos > (START_X_POS + 50) || xPos < (START_X_POS - 50)) {
            movingRight = !movingRight;
        }
        xPos += movingRight ? 1 : -1;
        HITBOX.setLocation(xPos, yPos);
    }
    
}
