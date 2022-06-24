import java.awt.geom.Rectangle2D;

public class GoldCoins {
	
	boolean touch = false;
	Rectangle2D.Double coinBounds = new Rectangle2D.Double();
	private int coinW = 50;
	private int coinH = 50;
	
		public GoldCoins(int x, int y) {
			coinBounds.setRect(x, y, coinW, coinH);
			
		}
		
		//checks if coins have been intersected
		public boolean touchCoins(Rectangle2D coin, Rectangle2D ship) {
			if (ship.intersects(coin) && touch == false) {
				FlyingDrone.score += 200;
				touch = true;
				return true;
			}
				return false;
		}
		
		public void setTouch(boolean a) {
			touch = a;
		}
		
}
