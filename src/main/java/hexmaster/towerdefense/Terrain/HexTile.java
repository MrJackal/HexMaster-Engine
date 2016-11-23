package hexmaster.towerdefense.Terrain;

import com.sun.javafx.geom.Vec3f;

import java.util.UUID;

/**
 * Created by MrJackal on 19/09/16.
 */
public class HexTile {

    private static Vec3f location;

    private static String type;
    private static UUID tile_id;

    public HexTile(Vec3f location, String type){
        this.location = location;
        this.type = type;
        this.tile_id = new UUID(15,15);
    }

}
