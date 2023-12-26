package engine;

import java.awt.*;
import geometry.*;

import javax.swing.*;

/**
 * The Engine class represents a simple 3D engine for rendering a mesh.
 * @author Giorgio
 */
public class Engine {

    private final EngineFrame frame;

    /**
     * Returns the window frame.
     *
     * @return Window frame.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Repaints the JPanel in the frame.
     */
    public void repaint(){
        frame.getJPanel().repaint();
    }

    /**
     * Checks if a specific key is currently pressed.
     *
     * @param keyCode The code of the key to check.
     * @return True if the key is pressed, false otherwise.
     */
    public boolean isKeyPressed(int keyCode){
        return frame.getKeyboardInput().isKeyPressed(keyCode);
    }

    // --- Engine relevant section starts here ---

    private final Mesh mesh;
    private Matrix worldMatrix;

    // angle theta for the rotation of the mesh
    private double theta = 0;
    private final Vector3D camera;

    /**
     * Constructs an Engine instance, initializes mesh and camera, and creates a window frame.
     */
    public Engine(){
        mesh = new Mesh("data/UtahTeapot.obj");
        camera = new Vector3D(0, 0, 0);

        // creating a window frame
        frame = new EngineFrame(this);
    }

    /**
     * Updates the engine state, rotating the world matrix.
     */
    public void update(){
        theta += 0.05;
        Matrix translationMatrix = MathUtils.getTranslationMatrix(new Vector3D(0, 0, 6));
        worldMatrix = MathUtils.getRotationZMatrix(theta)
                .multiply(MathUtils.getRotationYMatrix(0))
                .multiply(MathUtils.getRotationXMatrix(theta));
        worldMatrix = worldMatrix.multiply(translationMatrix);
    }

    /**
     * Paints the mesh on the specified Graphics context.
     *
     * @param g The Graphics context to paint on.
     */
    public void paint(Graphics g){
        mesh.paint(g, worldMatrix, camera);
    }
}
