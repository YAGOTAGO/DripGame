package base;

import java.util.List;

/**
 * Put this on any gameobject that has children gameobjects that need to be registered
 */
public interface IParentGO {
    public List<GameObject> getChildObjectsToDraw();
}
