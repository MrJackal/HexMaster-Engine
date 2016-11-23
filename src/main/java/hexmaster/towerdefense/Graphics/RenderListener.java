package hexmaster.towerdefense.Graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import hexmaster.towerdefense.Utils.Model;

import java.util.ArrayList;

public class RenderListener implements GLEventListener {

    private static int[][][] map = {};

    private static ArrayList<Model> displayedModels = new ArrayList<>();

    private static void setMap(int[][][] map){RenderListener.map = map;}

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        //Canvas preparation
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(1.0f );
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glEnable(GL2.GL_CULL_FACE);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_FASTEST);

        // Clear The Screen And The Depth Buffer
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glTranslatef(-3,-5,-10);

        float[] tileColor;
        for(int x=0;x<map.length;x++){
            Model tile = displayedModels.get(0);
            for(int y=0;y<map[x].length;y++){
                for(int z=0;z<map[x][y].length;z++) {
                    for(int i=0;i<map[x][y][z];i++) {//Tile height
                        if(map[x][y][0]==2){
                            tileColor = tile.convertRGB2Float(92,73,10);
                        }else if(map[x][y][0]==3){
                            tileColor = tile.convertRGB2Float(87,60,23);
                        }else{
                            tileColor = tile.convertRGB2Float(17,150,43);
                        }
                        if ((x & 1) == 0) {
                                tile.setLocation(-(x * 1.10f), y + 0.75f, (i * 0.25f));
                        } else {
                            tile.setLocation(-(x * 1.10f), y, (i*0.25f));
                        }
                        tile.display(gl,tileColor);
                    }
                }
            }
        }


       // for (int i=0; i<displayedModels.size(); i++) {
            //displayedModels.get(i).display(gl);
       // }
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();
        if( height == 0) {
            height = 1;
        }

        final float h = (float) width / (float) height;
        gl.glViewport(3, 6, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(65.0f, h, 1.0, 4000.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public static void addModel(Model model) {
        displayedModels.add(model);
    }
}
