import java.util.ArrayList;
import java.util.List;

public class TerrainCollection {

    List<IHitbox> terrainList = new ArrayList<>();

    public TerrainCollection() {
        terrainList.add(new Terrain(0, 0, 470, 200));
        terrainList.add(new Terrain(466, 0, 933, 100));
        terrainList.add(new Terrain(0, 600, 233, 20));
        terrainList.add(new Terrain(0, 0, 1, 800));    //left side
        terrainList.add(new Terrain(1399, 0, 1, 800)); //right side
        terrainList.add(new Terrain(233, 480, 250, 170));
        terrainList.add(new Terrain(350, 400, 125, 300));
        terrainList.add(new Terrain(466, 480, 300, 100));
        terrainList.add(new Terrain(700, 320, 125, 400));
        terrainList.add(new Terrain(816, 666, 250, 50));
        terrainList.add(new Terrain(1050, 320, 125, 400));
    }
    
    public List<IHitbox> getTerrainList(){
        return terrainList;
    }
}
