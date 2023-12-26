package geometry;

import static engine.EngineFrame.ASPECT_RATIO;

/**
 * The MathUtils class provides utility methods for mathematical operations and matrix transformations.
 * It includes methods for creating rotation matrices, translation matrices, and projection matrices.
 * @author Giorgio
 */
public class MathUtils {

    /**
     * Gets a rotation matrix around the Z-axis.
     *
     * @param theta The angle of rotation in radians.
     * @return The rotation matrix.
     */
    public static Matrix getRotationZMatrix(double theta) {
        Matrix rotationZMatrix = new Matrix();
        rotationZMatrix.matrix[0][0] = Math.cos(theta);
        rotationZMatrix.matrix[0][1] = Math.sin(theta);
        rotationZMatrix.matrix[1][0] = -Math.sin(theta);
        rotationZMatrix.matrix[1][1] = Math.cos(theta);
        rotationZMatrix.matrix[2][2] = 1;
        rotationZMatrix.matrix[3][3] = 1;
        return rotationZMatrix;
    }

    /**
     * Gets a rotation matrix around the Y-axis.
     *
     * @param theta The angle of rotation in radians.
     * @return The rotation matrix.
     */
    public static Matrix getRotationYMatrix(double theta) {
        Matrix rotationYMatrix = new Matrix();
        rotationYMatrix.matrix[0][0] = Math.cos(theta);
        rotationYMatrix.matrix[0][2] = Math.sin(theta);
        rotationYMatrix.matrix[1][1] = 1;
        rotationYMatrix.matrix[2][0] = -Math.sin(theta);
        rotationYMatrix.matrix[2][2] = Math.cos(theta);
        rotationYMatrix.matrix[3][3] = 1;
        return rotationYMatrix;
    }

    /**
     * Gets a rotation matrix around the X-axis.
     *
     * @param theta The angle of rotation in radians.
     * @return The rotation matrix.
     */
    public static Matrix getRotationXMatrix(double theta) {
        Matrix rotationXMatrix = new Matrix();
        rotationXMatrix.matrix[0][0] = 1;
        rotationXMatrix.matrix[1][1] = Math.cos(theta * 0.5d);
        rotationXMatrix.matrix[1][2] = Math.sin(theta * 0.5d);
        rotationXMatrix.matrix[2][1] = -Math.sin(theta * 0.5d);
        rotationXMatrix.matrix[2][2] = Math.cos(theta * 0.5d);
        rotationXMatrix.matrix[3][3] = 1;
        return rotationXMatrix;
    }

    /**
     * Gets a translation matrix.
     *
     * @param vector3D The translation vector.
     * @return The translation matrix.
     */
    public static Matrix getTranslationMatrix(Vector3D vector3D) {
        Matrix translationMatrix = new Matrix();
        translationMatrix.matrix[0][0] = 1;
        translationMatrix.matrix[1][1] = 1;
        translationMatrix.matrix[2][2] = 1;
        translationMatrix.matrix[3][3] = 1;
        translationMatrix.matrix[3][0] = vector3D.x;
        translationMatrix.matrix[3][1] = vector3D.y;
        translationMatrix.matrix[3][2] = vector3D.z;
        return translationMatrix;
    }

    /**
     * Gets a perspective projection matrix.
     *
     * @return The projection matrix.
     */
    public static Matrix getProjectionMatrix() {
        double near = 0.1d;
        double far = 1000d;
        double fov = 90d;
        double fovRad = 1d / Math.tan(fov * 0.5d / 180d * Math.PI);

        Matrix projectionMatrix = new Matrix();
        projectionMatrix.matrix[0][0] = ASPECT_RATIO * fovRad;
        projectionMatrix.matrix[1][1] = fovRad;
        projectionMatrix.matrix[2][2] = far / (far - near);
        projectionMatrix.matrix[2][3] = 1d;
        projectionMatrix.matrix[3][2] = (-far * near) / (far - near);
        return projectionMatrix;
    }
}
