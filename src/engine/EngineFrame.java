package engine;

import input.KeyboardInput;

import javax.swing.*;
import java.awt.*;

/**
 * The EngineFrame class represents the main window frame for the 3D engine.
 * It extends JFrame and contains the rendering panel and keyboard input handling.
 * @author Giorgio
 */
public class EngineFrame extends JFrame {
    /** The width of the frame. */
    public static int WIDTH = 1280;
    /** The height of the frame. */
    public static int HEIGHT = 720;
    /** The aspect ratio of the frame. */
    public static double ASPECT_RATIO;

    /** The rendering panel where the 3D engine renders its graphics. */
    private final JPanel panel;
    /** The keyboard input handler for user interaction. */
    private final KeyboardInput keyboardInput;

    /**
     * Constructs an EngineFrame for the 3D engine.
     *
     * @param engine The instance of the Engine associated with this frame.
     */
    public EngineFrame(Engine engine) {
        // Set up JFrame properties
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // Initialize keyboard input handler
        keyboardInput = new KeyboardInput();
        addKeyListener(keyboardInput);

        // Create rendering panel
        panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                // Clear the panel with a black background
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, EngineFrame.WIDTH, EngineFrame.HEIGHT);
                // Invoke the engine's paint method to render graphics
                engine.paint(g);
            }
        };

        add(panel);

        // Adjust frame dimensions based on insets and calculate aspect ratio
        WIDTH -= getInsets().left;
        HEIGHT -= getInsets().top;
        ASPECT_RATIO = (double) HEIGHT / WIDTH;
    }

    /**
     * Gets the rendering panel associated with this frame.
     *
     * @return The rendering panel.
     */
    public JPanel getJPanel() {
        return panel;
    }

    /**
     * Gets the keyboard input handler associated with this frame.
     *
     * @return The keyboard input handler.
     */
    public KeyboardInput getKeyboardInput() {
        return keyboardInput;
    }
}
