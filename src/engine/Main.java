package engine;

/**
 * The Main class serves as the entry point for the 3D engine application.
 * It initializes the engine, sets the target frames per second, and manages the game loop.
 * @author Giorgio
 */
public class Main {

    /**
     * The main method is the entry point of the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String... args) {
        // Create an instance of the Engine
        Engine engine = new Engine();

        // Set target frames per second
        int targetFPS = 60;
        long targetFrameTime = 1000000000 / targetFPS; // 1 second in nanoseconds divided by FPS

        long lastFrameTime = System.nanoTime();
        long fpsUpdateTime = System.nanoTime();
        int frameCount = 0;

        // Game loop
        while (true) {
            long currentTime = System.nanoTime();
            long elapsedNanos = currentTime - lastFrameTime;

            // Check if it's time to update and repaint the engine
            if (elapsedNanos >= targetFrameTime) {
                engine.update();
                engine.repaint();

                lastFrameTime = currentTime;

                // Calculate FPS
                frameCount++;
                if (currentTime - fpsUpdateTime >= 1000000000) { // Update every second
                    double fps = frameCount / ((currentTime - fpsUpdateTime) / 1e9);
                    engine.getFrame().setTitle("ThreeDGraphicsEngineV1 - " + fps);
                    frameCount = 0;
                    fpsUpdateTime = currentTime;
                }
            }
        }
    }
}
