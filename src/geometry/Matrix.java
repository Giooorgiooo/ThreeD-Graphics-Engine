package geometry;

/**
 * The Matrix class represents a 4x4 matrix commonly used in 3D graphics transformations.
 * It provides methods for matrix multiplication.
 * @author Giorgio
 */
public class Matrix {
    /** Two-dimensional array to store matrix elements. */
    public double[][] matrix;
    private static final int ROWS = 4;
    private static final int COLUMNS = 4;

    /**
     * Constructs a new matrix with the specified number of rows and columns.
     *
     * @param rows    The number of rows in the matrix.
     * @param columns The number of columns in the matrix.
     */
    public Matrix() {
        matrix = new double[COLUMNS][COLUMNS];
        // Initialize all elements to zero
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < COLUMNS; row++) {
                matrix[col][row] = 0d;
            }
        }
    }

    /**
     * Performs matrix multiplication with another 4x4 matrix.
     * Multiplies the current matrix by the provided matrix and returns the result.
     *
     * @param otherMatrix The matrix to multiply with.
     * @return The result of the matrix multiplication.
     */
    public Matrix multiply(Matrix otherMatrix) {
        // Create a new matrix to store the result
        Matrix result = new Matrix();

        // Perform matrix multiplication using nested loops
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < ROWS; row++) {
                result.matrix[row][col] = 0; // Initialize the result element to zero

                // Calculate the dot product of the current row in the first matrix and current column in the second matrix
                for (int k = 0; k < 4; k++) {
                    result.matrix[row][col] += this.matrix[row][k] * otherMatrix.matrix[k][col];
                }
            }
        }
        return result;
    }
}
