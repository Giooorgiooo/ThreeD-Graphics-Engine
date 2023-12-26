package geometry;

/**
 * The Vector3D class represents a three-dimensional vector in Euclidean space.
 * It provides operations for vector arithmetic, normalization, and transformations.
 * @author Giorgio
 */
public class Vector3D {
    /** X-coordinate of the vector. */
    public double x;
    /** Y-coordinate of the vector. */
    public double y;
    /** Z-coordinate of the vector. */
    public double z;
    /** Homogeneous coordinate (w) of the vector. */
    public double w;

    /**
     * Constructs a Vector3D with specified coordinates.
     *
     * @param x The x-coordinate of the vector.
     * @param y The y-coordinate of the vector.
     * @param z The z-coordinate of the vector.
     */
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1; // Homogeneous coordinate defaults to 1.
    }

    /**
     * Returns a string representation of the vector.
     *
     * @return The string representation of the vector.
     */
    public String toString() {
        return "{" + x + ", " + y + ", " + z + "}";
    }

    /**
     * Computes the length (magnitude) of the vector.
     *
     * @return The length of the vector.
     */
    public double getLength() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Normalizes the vector to have a length of 1.
     * If the vector is the zero vector, no action is taken.
     */
    public void normalize() {
        double length = getLength();
        if (length != 0) {
            x /= length;
            y /= length;
            z /= length;
        }
    }

    /**
     * Adds another vector to this vector and returns the result as a new vector.
     *
     * @param otherVector The vector to add.
     * @return The resulting vector after addition.
     */
    public Vector3D add(Vector3D otherVector) {
        return new Vector3D(
                otherVector.x + x,
                otherVector.y + y,
                otherVector.z + z
        );
    }

    /**
     * Subtracts another vector from this vector and returns the result as a new vector.
     *
     * @param otherVector The vector to subtract.
     * @return The resulting vector after subtraction.
     */
    public Vector3D subtract(Vector3D otherVector) {
        return new Vector3D(
                x - otherVector.x,
                y - otherVector.y,
                z - otherVector.z
        );
    }

    /**
     * Multiplies the vector by a scalar factor and returns the result as a new vector.
     *
     * @param factor The scalar factor to multiply by.
     * @return The resulting vector after multiplication.
     */
    public Vector3D multiply(double factor) {
        return new Vector3D(
                x * factor,
                y * factor,
                z * factor
        );
    }

    /**
     * Divides the vector by a scalar divisor and returns the result as a new vector.
     *
     * @param divisor The scalar divisor to divide by.
     * @return The resulting vector after division.
     */
    public Vector3D divide(double divisor) {
        return new Vector3D(
                x / divisor,
                y / divisor,
                z / divisor
        );
    }

    /**
     * Computes the dot product of this vector and another vector.
     *
     * @param otherVector The vector to compute the dot product with.
     * @return The dot product of the two vectors.
     */
    public double dotProduct(Vector3D otherVector) {
        return x * otherVector.x + y * otherVector.y + z * otherVector.z;
    }

    /**
     * Computes the cross product of this vector and another vector.
     *
     * @param otherVector The vector to compute the cross product with.
     * @return The resulting vector after the cross product.
     */
    public Vector3D crossProduct(Vector3D otherVector) {
        return new Vector3D(
                y * otherVector.z - z * otherVector.y,
                z * otherVector.x - x * otherVector.z,
                x * otherVector.y - y * otherVector.x
        );
    }

    /**
     * Multiplies the vector by a 4x4 matrix and returns the resulting vector.
     *
     * @param matrix The 4x4 matrix to multiply by.
     * @return The resulting vector after the matrix multiplication.
     */
    public Vector3D multiply(Matrix matrix) {
        double[][] m = matrix.matrix;
        Vector3D outputVector = new Vector3D(
                x * m[0][0] + y * m[1][0] + z * m[2][0] + w * m[3][0],
                x * m[0][1] + y * m[1][1] + z * m[2][1] + w * m[3][1],
                x * m[0][2] + y * m[1][2] + z * m[2][2] + w * m[3][2]
        );
        outputVector.w = x * m[0][3] + y * m[1][3] + z * m[2][3] + w * m[3][3];
        return outputVector;
    }

    /**
     * Multiplies the vector component-wise with another vector and returns the resulting vector.
     *
     * @param vector3D The vector to multiply component-wise.
     * @return The resulting vector after the component-wise multiplication.
     */
    public Vector3D multiply(Vector3D vector3D) {
        return new Vector3D(x * vector3D.x, y * vector3D.y, z * vector3D.z);
    }
}
