package hexmaster.towerdefense;

import hexmaster.towerdefense.Utils.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ObjParser {
    private BufferedReader objReader;
    private ArrayList<float[]> vertices;
    private ArrayList<int[]> faces;
    private ArrayList<Integer> colorInts = new ArrayList<Integer>();
    int colorInt = 0;
    private String objName;

    public ObjParser(String objResourceFilename) {
        objReader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/obj-models/" + objResourceFilename + ".obj")));
        objName = objResourceFilename;
        vertices = new ArrayList<float[]>();
        faces = new ArrayList<int[]>();
    }

    private void parseLine(String unFormattedString) {
        if (unFormattedString.startsWith("v ") && !unFormattedString.contains("#") && unFormattedString.length() > 0) {
            String formattedString = unFormattedString.replaceAll("\\s{2}?", " ");
            float[] vertexCoords = new float[3];
            int xIndex;
            int yIndex;
            int zIndex;
            xIndex = formattedString.indexOf(" ");
            yIndex = formattedString.indexOf(" ", (xIndex + 1));
            zIndex = formattedString.indexOf(" ", (yIndex + 1));

            vertexCoords[0] = Float.parseFloat(formattedString.substring((xIndex+1), yIndex));
            vertexCoords[1] = Float.parseFloat(formattedString.substring((yIndex+1), zIndex));
            vertexCoords[2] = Float.parseFloat(formattedString.substring((zIndex+1), formattedString.length()));
            vertices.add(vertexCoords);
        }else if (unFormattedString.startsWith("f ") && !unFormattedString.contains("#") && unFormattedString.length() > 0) {
            String formattedString = unFormattedString.replaceAll("\\s{2}?", " ");
            ArrayList<Integer> ints = new ArrayList<Integer>();

            int startIndex = 0;
            int endIndex = 0;

            while (endIndex != -1) {
                if (startIndex == 0) {
                    startIndex = formattedString.indexOf(" ");
                }else{
                    startIndex = formattedString.indexOf(" ", endIndex);
                }
                endIndex = formattedString.indexOf(" ", (startIndex+1));

                if (endIndex != -1) {
                    Integer integer = extractVertex(formattedString.substring((startIndex + 1), endIndex));
                    if (integer != null) {
                        ints.add(integer);
                    }
                }else{
                    Integer integer = extractVertex(formattedString.substring((startIndex + 1), formattedString.length()));
                    if (integer != null) {
                        ints.add(integer);
                    }
                }
            }

            colorInts.add(colorInt);
            if (colorInt == 0) {
                colorInt = 1;
            }else if (colorInt == 1) {
                colorInt = 2;
            }else if (colorInt == 2) {
                colorInt = 0;
            }

            int[] intArray = new int[ints.size()];
            for (int i=0; i<ints.size(); i++) {
                intArray[i] = ints.get(i);
            }
            faces.add(intArray);
        }
    }

    public Model parseObj() {
        try {
            while (objReader.ready()) {
                parseLine(objReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Successfully loaded model '" + objName + "' into memory!");
        return new Model(faces, vertices, colorInts);
    }

    private Integer extractVertex(String string) {
        Integer outputInt = null;

        if (string.contains("/")) {
            outputInt = Integer.parseInt(string.substring(string.indexOf(" ")+1, string.indexOf("/")));
        }else if (string.length() > 0){
            outputInt = Integer.parseInt(string);
        }

        return outputInt;
    }
}
