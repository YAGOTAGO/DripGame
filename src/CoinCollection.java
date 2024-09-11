import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class CoinCollection {
    
    private static ArrayList<Coin> coins;
    private ArrayList<Coin> coinsToDraw;
    private int numHitCoins = 0;

    public CoinCollection(){
        coins = new ArrayList<>(13);
        coins.add(new Coin(250, 400));
        coins.add(new Coin(500, 400));
        coins.add(new Coin(600, 400));
        coins.add(new Coin(600, 150));
        coins.add(new Coin(500, 150));
        coins.add(new Coin(910, 480));
        coins.add(new Coin(910, 400));
        coins.add(new Coin(910, 320));
        coins.add(new Coin(250, 220));
        coins.add(new Coin(550, 270));
        coins.add(new Coin(910, 240));
        coins.add(new Coin(380, 270));
        coinsToDraw = new ArrayList<>(coins);
    }

    public int hitCoins(Rectangle2D target){
        numHitCoins = 0;
        for (Coin coin : coins) {
            if(coin.setHit(target)){
                coinsToDraw.remove(coin);
                numHitCoins++;
            }
        }
        return numHitCoins;
    }
    
    public void resetCoins(){
        for (Coin coin : coins) {
            coin.hasBeenHit = false;
        }
    }

    public void drawCoins(Image currCoin, Graphics g, ImageObserver obsv){
        for(Coin coin : coinsToDraw){
            coin.drawCoin(currCoin, g, obsv);
        }
    }
}
