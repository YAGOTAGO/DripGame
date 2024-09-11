import java.awt.geom.Rectangle2D;

public class DripBalls {
	
	Rectangle2D.Double ballBounds = new Rectangle2D.Double();
	private int ballW = 30;
	private int ballH = 30;

	public DripBalls(int x, int y) {
		
		ballBounds.setRect(x, y, ballW, ballH);
		
}
	
	public void respawnBalls(int x, int y) {
		FlyingDrone.dripBallX = x;
		FlyingDrone.dripBallY = y;
	}
}
