import java.util.LinkedList;
import java.util.Queue;

public final class PlayerHealth {
    
    int health = 3;

    StaticGO heart1;
    StaticGO heart2;
    StaticGO heart3;

    Queue<GameObject> hearts;
    
    public PlayerHealth(){
        hearts = new LinkedList<>();
        heart1 = new StaticGO("UI", "heart.png", 150, 80);
        heart2 = new StaticGO("UI", "heart.png", 200, 80);
        heart3 = new StaticGO("UI", "heart.png", 250, 80);
        hearts.add(heart1);
        hearts.add(heart2);
        hearts.add(heart3);
    }

    public Queue<GameObject> getHearts(){
        return hearts;
    }

    public int takeDamage(){
        ExecuteGame.getInstance().getLogic().removeObjectFromDraw(hearts.poll());
        return --health;
    }

    public int getHealth(){
        return health;
    }
}
