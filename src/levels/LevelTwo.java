package levels;

import java.util.ArrayList;
import player.Player;
import gameobjects.*;
import base.*;

public final class LevelTwo extends Level {
    Player ship;

    public LevelTwo(){
        ship = new Player(200);
        registerGameObject(ship);
        setBackground(ship);
        registerGameObject(new FuelCollectable(100, 900, 550));
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
        }});
		registerGameObject(new SpriteGO("UI", "fuelCanister.png", 60, 42));
        registerGameObject(new DripDrop(740, 75));
        registerGameObject(new DripDrop(1100, 75));
    }

    @Override
    public Player getShip() {
        return ship;
    }
}
