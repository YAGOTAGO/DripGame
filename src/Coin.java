import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Coin extends Collectible{

    public Coin(int x, int y) {
        super(x, y, 50, 50);
    }
	
	public void drawCoin(Image currCoin, Graphics g, ImageObserver obsv){
		g.drawImage(currCoin, rect.x, rect.y, obsv);
	}
		
}
