package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * The KeyboardInput class handles keyboard input by implementing the KeyListener interface.
 * It keeps track of the state of each key, allowing easy retrieval of key press/release events.
 * @author Giorgio
 */
public class KeyboardInput implements KeyListener {
    /** Map to store the state of each key (pressed or released). */
    private final Map<Integer, Boolean> keyStateMap;

    /**
     * Constructs a new KeyboardInput instance.
     * Initializes the key state map.
     */
    public KeyboardInput() {
        keyStateMap = new HashMap<>();
    }

    /**
     * Invoked when a key has been typed.
     * Not used in this example.
     *
     * @param e The KeyEvent representing the typed key event.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this example
    }

    /**
     * Invoked when a key has been pressed.
     * Updates the key state map to indicate that the key is pressed.
     *
     * @param e The KeyEvent representing the pressed key event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keyStateMap.put(keyCode, true);
        // You can perform additional actions on key press if needed
    }

    /**
     * Invoked when a key has been released.
     * Updates the key state map to indicate that the key is released.
     *
     * @param e The KeyEvent representing the released key event.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keyStateMap.put(keyCode, false);
    }

    /**
     * Checks the state of a specific key.
     *
     * @param keyCode The key code of the key to check.
     * @return true if the key is currently pressed, false otherwise.
     */
    public boolean isKeyPressed(int keyCode) {
        return keyStateMap.getOrDefault(keyCode, false);
    }
}
