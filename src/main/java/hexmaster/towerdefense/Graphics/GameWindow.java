package hexmaster.towerdefense.Graphics;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow {
    private static Frame awtFrame = new Frame("HexMaster -- Tower Defense");
    private static GLCapabilities capabilities = new GLCapabilities(GLProfile.get("GL2"));
    private static GLCanvas gameCanvas;

    public GameWindow(int[][][] map) {
        awtFrame.setSize(800, 800);
        capabilities.setHardwareAccelerated(true);
        capabilities.setDepthBits(32);
        capabilities.setAlphaBits(8);
        capabilities.setRedBits(8);
        capabilities.setGreenBits(8);
        capabilities.setRedBits(8);

        gameCanvas = new GLCanvas(capabilities);
        gameCanvas.addGLEventListener(new RenderListener());
        RenderListener.setMap(map);
        awtFrame.add(gameCanvas);

        awtFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                awtFrame.dispose();
                System.exit(0);
            }
        });

        awtFrame.setVisible(true);

        Thread renderThread = new Thread(new GameRenderThread(gameCanvas));
        renderThread.start();
    }
}
