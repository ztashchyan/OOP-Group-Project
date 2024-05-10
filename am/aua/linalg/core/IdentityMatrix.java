package am.aua.linalg.core;

/**
 * The IdentityMatrix class represents an identity matrix.
 * An identity matrix is a square matrix in which all the elements of the main diagonal are ones,
 * and all other elements are zeros.
 * It extends the DiagonalMatrix class.
 */
public class IdentityMatrix extends DiagonalMatrix{

    /**
     * Constructs a new IdentityMatrix with the specified size.
     * An identity matrix is a square matrix in which all the elements of the main diagonal are ones,
     * and all other elements are zeros.
     *
     * @param size the size of the identity matrix (number of rows and columns)
    */    
    public IdentityMatrix(int size) {
        super(size, initializeDiagonal(size));
    }


    // Helper method to initialize the diagonal with 1s
    private static double[] initializeDiagonal(int size) {
        double[] diagonal = new double[size];
        for (int i = 0; i < size; i++) {
            diagonal[i] = 1.0;
        }
        return diagonal;
}
    
}
