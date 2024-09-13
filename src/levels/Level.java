package levels;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.*;
import base.*;
import gameobjects.*;
import player.Player;

public abstract class Level {
    //objects to draw
    private final List<GameObject> BACKGROUND_DRAW = new ArrayList<>();
    private final List<GameObject> OBJECT_DRAW = new ArrayList<>();
    private final List<IHitbox> COLLIDABLES = new ArrayList<>();
    private final List<IMovable> MOVABLES = new ArrayList<>();
    
    //GETTERS
    public abstract Player getShip();

    //Background registers
    protected void registerBackground(GameObject go){
        BACKGROUND_DRAW.add(go);
        registerInRelevantLists(go);
        if(go instanceof IParentGO iParentGO){
            registerBackground(iParentGO.getChildObjectsToDraw());
        }
    }
    protected void registerBackground(Collection<GameObject> list){
        for(GameObject go : list){
            registerBackground(go);
        }
    }

    //Game object registers
    protected void registerGameObject(GameObject go){
        OBJECT_DRAW.add(go);
        registerInRelevantLists(go);
        if(go instanceof IParentGO iParentGO){
            registerGameObject(iParentGO.getChildObjectsToDraw());
        }
    }

    protected void registerGameObject(Collection<GameObject> list){
        for(GameObject go : list){
            registerGameObject(go);
        }
    }
    
    private void registerInRelevantLists(GameObject go){
        if(go instanceof IHitbox iHitbox){
            COLLIDABLES.add(iHitbox);
        }
        if(go instanceof IMovable iMove){
            MOVABLES.add(iMove);
        }
    }

    /**
     * Registers background elements and adds terrain collisions and platform
     */
    protected void setBackground(Player ship){
        registerBackground(new SpriteGO("screen", "background.png", 0, 0));
		registerBackground(new SpriteGO("enviornment", "cliffBot.png", -10, -30));
		registerBackground(new SpriteGO("enviornment", "cliffTop.png", -10, -30));
		registerBackground(new SpriteGO("UI", "metal.png", 0, 0));
        registerBackground(ship.getFuel());
        registerBackground(new Platform());
        COLLIDABLES.addAll(new TerrainCollection().getTerrainList());
    }

    public void drawLevel(Graphics g, ImageObserver obsv){
        //draw background
        for(GameObject go : BACKGROUND_DRAW){
			if(go.canDraw()){
				g.drawImage(go.display(), go.getX(), go.getY(), obsv);
			}
		}

        //move objects
        for(IMovable elem : MOVABLES){
			elem.move();
		}

        //draw Game Objects
        for(GameObject go : OBJECT_DRAW){
			if(go.canDraw()){
				g.drawImage(go.display(), go.getX(), go.getY(), obsv);
			}
		}

        //check collisions
        for(IHitbox curr : COLLIDABLES){
			Rectangle r = curr.getHitbox();
			g.drawRect(r.x, r.y, r.width, r.height);
			if(curr.canCollide() && curr.intersects(getShip())){
				curr.onHit(getShip());
			}
		}
    }

}
