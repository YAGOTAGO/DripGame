import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public final class LevelTwo extends Level {
    
    public LevelTwo(Player ship){
        //Add objects in
        Platform platform = new Platform();
        FuelCanister fuel = new FuelCanister(100, 900, 550);
        Queue<GameObject> hearts = ship.getHearts();
		TerrainCollection terrainCollection = new TerrainCollection();
        List<Coin> coins = new ArrayList<>(){{
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
        }};
        
        //Draw objects
        setBackground();
		objectsToDraw.add(ship.getFuel());
		objectsToDraw.add(new SpriteGO("UI", "fuelCanister.png", 60, 42));
		objectsToDraw.addAll(hearts);
		objectsToDraw.addAll(coins);
        objectsToDraw.add(fuel);
		objectsToDraw.add(ship);
        objectsToDraw.add(platform);

        //Collidables
		collidables.addAll(coins);
		collidables.addAll(terrainCollection.getTerrainList());
		collidables.add(platform);
        collidables.add(fuel);
    }
}
