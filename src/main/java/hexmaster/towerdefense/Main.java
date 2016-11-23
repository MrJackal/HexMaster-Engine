package hexmaster.towerdefense;

import hexmaster.towerdefense.Graphics.GameWindow;
import hexmaster.towerdefense.Graphics.RenderListener;
import hexmaster.towerdefense.Utils.Model;

public class Main {

    private static void startEngine(){

        int[][][] map = {
                {{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1}},
                {{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1}},
                {{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1}},
                {{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1}},
                {{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1}},
                {{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1}},
                {{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1}},
                {{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1}},
                {{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1}},
                {{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1},{0,1}},
        };


        ObjParser parser = new ObjParser("hex_tile");
        Model newModel = parser.parseObj();

        newModel.setLocation(0, 0, -1.0f);
        RenderListener.addModel(newModel);

        GameWindow window = new GameWindow(map);
    }

    public static void main(String[] args) {
        startEngine();
    }
}