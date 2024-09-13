import java.util.ArrayList;
import java.util.List;

public class TerrainCollection {
    private final List<IHitbox> TERRAIN_LIST = new ArrayList<>();

    public TerrainCollection() {
        TERRAIN_LIST.add(new Terrain(0, 0, 470, 200));
        TERRAIN_LIST.add(new Terrain(466, 0, 933, 100));
        TERRAIN_LIST.add(new Terrain(0, 600, 233, 20));
        TERRAIN_LIST.add(new Terrain(0, 0, 1, 800));    //left side
        TERRAIN_LIST.add(new Terrain(1399, 0, 1, 800)); //right side
        TERRAIN_LIST.add(new Terrain(233, 480, 250, 170));
        TERRAIN_LIST.add(new Terrain(350, 400, 125, 300));
        TERRAIN_LIST.add(new Terrain(466, 480, 300, 100));
        TERRAIN_LIST.add(new Terrain(700, 320, 125, 400));
        TERRAIN_LIST.add(new Terrain(816, 666, 250, 50));
        TERRAIN_LIST.add(new Terrain(1050, 320, 125, 400));
    }
    
    public List<IHitbox> getTerrainList(){
        return TERRAIN_LIST;
    }
}
