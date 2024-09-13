public final class PlayerMovement {
    // Movement-related fields
    private double xVel;
    private double yVel;
    
    private final int FUEL_USE_AMOUNT = 1; //means 1 fuel per movement
    private final double GRAVITY = 0.5; // Gravity force
    private final double FRICTION = 0.97; // Friction to slow down the spaceship
    private final double Y_VEL_COEFFICIENT = 1;
    private final double X_VEL_COEFFICIENT = 1.2;

    public PlayerMovement() {
        this.xVel = 0;
        this.yVel = 0;
    }

    // Apply thrust, update velocities
    public void applyThrust(Fuel fuel, double angle, boolean thrust) {
        if (thrust & fuel.hasFuel()) {
            // Calculate velocity based on angle and thrust
            yVel -= (Y_VEL_COEFFICIENT * Math.cos(Math.toRadians(angle)));
            xVel += (X_VEL_COEFFICIENT * Math.sin(Math.toRadians(angle)));
            fuel.useFuel(FUEL_USE_AMOUNT);
        }
        
        // Apply gravity and friction
        yVel += GRAVITY;
        yVel *= FRICTION;
        xVel *= FRICTION;
    }

    // Update X position
    public int updateX(int xPos) {
        return xPos + (int) xVel;
    }

    // Update Y position
    public int updateY(int yPos) {
        return yPos + (int) yVel;
    }

    public void reset(){
        yVel = 0;
        xVel = 0;
    }
}
