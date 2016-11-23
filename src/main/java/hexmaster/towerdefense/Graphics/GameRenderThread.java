package hexmaster.towerdefense.Graphics;

import com.jogamp.opengl.awt.GLCanvas;

public class GameRenderThread implements Runnable {
    public GameRenderThread(GLCanvas drawable) {
        autoDrawable = drawable;
    }

    private static GLCanvas autoDrawable = null;
    private static int runsThisSecond = 0;
    private static long timeLastSecond = System.nanoTime();
    public static boolean threadRunning = true;
    public void run() {
        while (GameRenderThread.threadRunning) {
            long currentTime = System.nanoTime();
            if (Math.abs(currentTime - timeLastSecond) <= 1000000000) {
                GameRenderThread.runsThisSecond++;
                autoDrawable.display();
            }else{
                GameRenderThread.runsThisSecond++;
                System.out.println(runsThisSecond + " FPS");
                GameRenderThread.runsThisSecond = 0;
                GameRenderThread.timeLastSecond = currentTime;
                autoDrawable.display();
            }
        }
    }
}
