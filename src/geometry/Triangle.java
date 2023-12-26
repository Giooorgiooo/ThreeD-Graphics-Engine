package geometry;

import java.awt.*;

/**
 * The Triangle class represents a 3D triangle defined by three vertices in 3D space.
 * It provides methods for rendering the triangle, cloning, and calculating the triangle's normal vector.
 * @author Giorigo
 */
public class Triangle implements Cloneable {
    /** Array of three vertices representing the triangle. */
    public Vector3D[] points;

    /** The color of the triangle when rendered. */
    public Color color;

    /**
     * Constructs a triangle using three Vector3D points.
     *
     * @param points The vertices of the triangle.
     */
    public Triangle(Vector3D... points) {
        if (points.length != 3){
            throw new IllegalArgumentException("A triangle must be indicated by 3 vertices");
        }
        this.points = points.clone();
    }

    /**
     * Constructs a triangle using individual coordinates for each vertex.
     *
     * @param x1 The x-coordinate of the first vertex.
     * @param y1 The y-coordinate of the first vertex.
     * @param z1 The z-coordinate of the first vertex.
     * @param x2 The x-coordinate of the second vertex.
     * @param y2 The y-coordinate of the second vertex.
     * @param z2 The z-coordinate of the second vertex.
     * @param x3 The x-coordinate of the third vertex.
     * @param y3 The y-coordinate of the third vertex.
     * @param z3 The z-coordinate of the third vertex.
     */
    public Triangle(double x1, double y1, double z1,
                    double x2, double y2, double z2,
                    double x3, double y3, double z3) {
        this.points = new Vector3D[]{
                new Vector3D(x1, y1, z1),
                new Vector3D(x2, y2, z2),
                new Vector3D(x3, y3, z3)
        };
    }

    /**
     * Renders the triangle on the specified Graphics context.
     *
     * @param g The Graphics context on which to render the triangle.
     */
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillPolygon(
                new int[]{(int) points[0].x, (int) points[1].x, (int) points[2].x},
                new int[]{(int) points[0].y, (int) points[1].y, (int) points[2].y},
                3
        );

        // Alternative: Draw triangle edges
        // g.drawLine((int) points[0].x, (int) points[0].y, (int) points[1].x, (int) points[1].y);
        // g.drawLine((int) points[1].x, (int) points[1].y, (int) points[2].x, (int) points[2].y);
        // g.drawLine((int) points[2].x, (int) points[2].y, (int) points[0].x, (int) points[0].y);
    }

    /**
     * Clones the triangle.
     *
     * @return A new Triangle instance that is a copy of this triangle.
     */
    @Override
    public Triangle clone() {
        try {
            return (Triangle) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /**
     * Returns a string representation of the triangle.
     *
     * @return The string representation of the triangle.
     */
    public String toString() {
        return "{ " + points[0].toString() + ", " + points[1].toString() + ", " + points[2].toString() + " }";
    }

    /**
     * Calculates and returns the normal vector of the triangle.
     *
     * @return The normal vector of the triangle.
     */
    public Vector3D getNormal() {
        Vector3D normal = points[1].subtract(points[0]).crossProduct(points[2].subtract(points[0]));
        normal.normalize();
        return normal;
    }
}
