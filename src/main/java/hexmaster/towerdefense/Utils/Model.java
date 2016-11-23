package hexmaster.towerdefense.Utils;

import com.jogamp.opengl.GL2;

import java.util.ArrayList;

public class Model {
    private ArrayList<int[]> faces;
    private ArrayList<float[]> vertices;
    private ArrayList<Integer> colorInts;
    private float xCoord;
    private float yCoord;
    private float zCoord;
    private float xDegrees;
    private float yDegrees;
    private float zDegrees;

    public Model(ArrayList<int[]> facesList, ArrayList<float[]> verticesList, ArrayList<Integer> colorInts) {
        this.faces = facesList;
        this.vertices = verticesList;
        this.colorInts = colorInts;
    }

    public void display(GL2 gl,float[] tileColor) {

        gl.glPushMatrix();
        gl.glTranslatef(xCoord, yCoord, zCoord);
        gl.glRotatef(xDegrees, 1.0f, 0, 0);
        gl.glRotatef(yDegrees, 0, 1.0f, 0);
        gl.glRotatef(zDegrees, 0, 0, 1.0f);

        gl.glBegin(GL2.GL_TRIANGLES);
        for (int i=0; i<faces.size(); i++) {
            int[] face = faces.get(i);
            int color = colorInts.get(i);
            gl.glColor3f(tileColor[0],tileColor[1],tileColor[2]);

            for (int j=0; j<face.length; j++) {
                float[] vertex = vertices.get((face[j] - 1));
                gl.glVertex3f((vertex[0]), (vertex[1]), (vertex[2]));
            }
        }
        gl.glEnd();
        gl.glPopMatrix();
    }


    public float[] convertRGB2Float(int r, int g, int b){
        float[] f = new float[3];
        f[0] = r/255f;
        f[1] = g/255f;
        f[2] = b/255f;
        return f;
    }

    private void setRotation(float xAngle, float yAngle, float zAngle) {
        float x = xAngle;
        float y = yAngle;
        float z = zAngle;
        if (x >= 360.0f) {
            x += -360.0f;
        }
        if (y >= 360.0f) {
            y += -360.0f;
        }
        if (z >= 360.0f) {
            z += -360.0f;
        }

        this.xDegrees = x;
        this.yDegrees = y;
        this.zDegrees = z;
    }

    public void rotateBy(float xIncrement, float yIncrement, float zIncrement) {
        float x = xDegrees;
        float y = yDegrees;
        float z = zDegrees;
        setRotation((x + xIncrement), (y + yIncrement), (z + zIncrement));
    }

    public void setLocation(float zLoc, float xLoc, float yLoc) {
        this.xCoord = xLoc;
        this.yCoord = yLoc;
        this.zCoord = zLoc;
    }

    public void move(float xLocIncrement, float yLocIncrement, float zLocIncrement) {
        float xLoc = this.xCoord;
        float yLoc = this.yCoord;
        float zLoc = this.zCoord;

        setLocation((xLoc + xLocIncrement), (yLoc + yLocIncrement), (zLoc + zLocIncrement));
    }
}
