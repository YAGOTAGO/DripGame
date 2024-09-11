import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;

public class Coin {
	
	public boolean hasBeenHit = false;

	//dimensions
	private int coinWidth = 50;
	private int coinHeight = 50;
	private int x;
	private int y;
	private Rectangle2D.Double coinBounds = new Rectangle2D.Double();

		public Coin(int x, int y) {
			coinBounds.setRect(x, y, coinWidth, coinHeight);
			this.x = x;
			this.y = y;
		}
		
		//checks if coins have been intersected
		public boolean setHit(Rectangle2D target) {
			if (!hasBeenHit && target.intersects(coinBounds)) {
				hasBeenHit = true;
				return true;
			}
				return false;
		}

		public void drawCoin(Image currCoin, Graphics g, ImageObserver obsv){
			g.drawImage(currCoin, x, y, obsv);
		}
		
}
