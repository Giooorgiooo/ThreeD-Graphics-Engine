package geometry;

import engine.Engine;
import engine.EngineFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * The Mesh class represents a 3D mesh composed of triangles.
 * @author Giorgio
 */
public class Mesh {
    /** List of triangles forming the mesh. */
    public List<Triangle> triangles;

    /**
     * Constructs a mesh from an array of triangles.
     *
     * @param triangles The triangles forming the mesh.
     */
    public Mesh(Triangle... triangles) {
        this.triangles = List.of(triangles);
    }

    /**
     * Constructs a mesh by loading vertices and faces from an OBJ file.
     *
     * @param filePath The path to the OBJ file.
     */
    public Mesh(String filePath) {
        triangles = new ArrayList<>();
        loadFromObjectFile(filePath);
    }

    /**
     * Loads vertices and faces from an OBJ file to construct the mesh.
     *
     * @param filePath The path to the OBJ file.
     */
    public void loadFromObjectFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<Vector3D> vertices = new ArrayList<>();

            // Read each line from the file
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+");

                switch (tokens[0]) {
                    case "v": // Vertex
                        // Parse vertex coordinates and add to the list of vertices
                        double x = Double.parseDouble(tokens[1]);
                        double y = Double.parseDouble(tokens[2]);
                        double z = Double.parseDouble(tokens[3]);
                        vertices.add(new Vector3D(x, y, z));
                        break;

                    case "f": // Face
                        // Parse face indices and create a new triangle
                        int[] indices = new int[3];
                        for (int i = 0; i < 3; i++) {
                            indices[i] = Integer.parseInt(tokens[i + 1]) - 1; // Subtract 1 to convert to 0-based indexing
                        }
                        triangles.add(new Triangle(vertices.get(indices[0]), vertices.get(indices[1]), vertices.get(indices[2])));
                        break;

                    default:
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Paints the mesh on the specified Graphics context after transformations.
     *
     * @param g            The Graphics context to paint on.
     * @param worldMatrix  The world transformation matrix.
     * @param camera       The camera position in 3D space.
     */
    public void paint(Graphics g, Matrix worldMatrix, Vector3D camera) {
        List<Triangle> trianglesToRaster = new ArrayList<>();

        for (Triangle triangle : triangles) {

            Triangle projectedTriangle, transformedTriangle, viewedTriangle;

            // Apply world transformation to the triangle vertices
            transformedTriangle = new Triangle(
                    triangle.points[0].multiply(worldMatrix),
                    triangle.points[1].multiply(worldMatrix),
                    triangle.points[2].multiply(worldMatrix)
            );

            Vector3D normal = transformedTriangle.getNormal();
            normal.normalize();
            Vector3D cameraRay = transformedTriangle.points[0].subtract(camera);

            // Check if the triangle is facing the camera
            if (normal.dotProduct(cameraRay) < 0) {

                Matrix projectionMatrix = MathUtils.getProjectionMatrix();
                // Apply projection transformation to the triangle vertices
                projectedTriangle = new Triangle(
                        transformedTriangle.points[0].multiply(projectionMatrix),
                        transformedTriangle.points[1].multiply(projectionMatrix),
                        transformedTriangle.points[2].multiply(projectionMatrix)
                );

                // Apply perspective division to obtain normalized device coordinates (NDC).
                // This step transforms the projected points from homogeneous coordinates to NDC.
                Vector3D offsetView = new Vector3D(1, 1, 0);
                for (int i = 0; i < 3; i++) {
                    projectedTriangle.points[i] = projectedTriangle.points[i]
                            .divide(projectedTriangle.points[i].w)
                            .add(offsetView)
                            .multiply(new Vector3D(0.5d * EngineFrame.WIDTH, 0.5d * EngineFrame.HEIGHT, 1)
                    );
                }

                // Calculate lighting intensity based on the normal and light direction
                Vector3D lightDirection = new Vector3D(0, -1, -1);
                lightDirection.normalize();
                int green = (int) (255 * (Math.max(0.1, normal.dotProduct(lightDirection))));
                projectedTriangle.color = new Color(0, green, 0);

                // Add the projected triangle to the list for rasterization
                trianglesToRaster.add(projectedTriangle);
            }
        }

        // Sort triangles based on their average depth for proper rendering order
        trianglesToRaster.sort(
                Comparator.comparingDouble(triangle ->
                        (triangle.points[0].z + triangle.points[1].z + triangle.points[2].z) / 3d
                )
        );
        Collections.reverse(trianglesToRaster);

        // Render each triangle in the sorted order
        for (Triangle projectedTriangle : trianglesToRaster) {
            projectedTriangle.paint(g);
        }
    }


    /**
     * Converts the mesh to a string representation.
     *
     * @return The string representation of the mesh.
     */
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",\n", "{\n", "\n}");
        triangles.forEach(triangle -> stringJoiner.add(triangle.toString()));
        return stringJoiner.toString();
    }
}
