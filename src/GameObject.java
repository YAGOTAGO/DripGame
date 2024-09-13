import java.awt.Image;

public abstract class GameObject{
    protected  boolean canDraw = true;
    protected int xPos;
    protected int yPos;

    public GameObject(int x, int y){
        this.xPos = x;
        this.yPos = y;
    }
    
    //getters
    public int getX(){return xPos;}
    public int getY(){return yPos;}
    public abstract Image display();
    public boolean canDraw(){ return canDraw; }
}