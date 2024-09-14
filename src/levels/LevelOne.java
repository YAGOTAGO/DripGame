package levels;

import java.util.ArrayList;

import base.SpriteGO;
import player.Player;
import gameobjects.*;

public final class LevelOne extends Level{
    Player ship;
    public LevelOne(){
        ship = new Player(250);
        registerGameObject(ship);
        setBackground(ship);
        registerGameObject(new ArrayList<>(){{
            
            //patern at the start
            add(new Coin(250, 400));
            add(new Coin(250, 200));
            add(new Coin(500, 400));
            add(new Coin(550, 270));
            add(new Coin(600, 400));
            add(new Coin(380, 270));

            //top ones going right
            add(new Coin(500, 150));
            add(new Coin(650, 150));
            add(new Coin(800, 150));
            add(new Coin(950, 150));
            add(new Coin(1100, 150));
            add(new Coin(1250, 150));

            //down to platform
            add(new Coin(1250, 300));
            add(new Coin(1250, 450));
            add(new Coin(1250, 600));

            //down to hole
            add(new Coin(900, 250));
            add(new Coin(900, 400));
            add(new Coin(900, 550));
        }});
        registerGameObject(new SpriteGO("UI", "fuelCanister.png", 60, 42));
    }

    @Override
    public Player getShip() {
        return ship;
    }
}
