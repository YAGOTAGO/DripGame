import java.util.LinkedList;
import java.util.Queue;

public final class PlayerHealth {
    
    int health = 3;
    Queue<GameObject> hearts;
    
    public PlayerHealth(){
        hearts = new LinkedList<>(){{
            add(new SpriteGO("UI", "heart.png", 150, 80));
            add(new SpriteGO("UI", "heart.png", 200, 80));
            add(new SpriteGO("UI", "heart.png", 250, 80));
        }};
    }

    public Queue<GameObject> getHearts(){
        return hearts;
    }

    public int takeDamage(){
        //hearts.poll().canDraw = false; //may cause issues with reset
        return --health;
    }

    public int getHealth(){
        return health;
    }
}
