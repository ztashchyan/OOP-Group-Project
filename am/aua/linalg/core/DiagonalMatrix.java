package am.aua.linalg.core;

/**
 * Represents a diagonal matrix, which is a special type of square matrix
 * where all elements off the main diagonal are zero.
*/
public class DiagonalMatrix extends SquareMatrix{
 
    /**
     * Constructs a diagonal matrix with the specified size and diagonal entries.
     * 
     * @param size The size of the diagonal matrix (number of rows/columns)
     * @param diagonalEntries The diagonal entries of the matrix
     * @throws IllegalArgumentException If the number of diagonal entries provided does not match the size of the matrix
    */
    public DiagonalMatrix(int size, double... diagonalEntries){
        super(size);
        if (diagonalEntries.length != size) {
            System.out.println("Invalid input: Number of diagonal values must match the size of the matrix.");
            System.exit(0);
        }
    
        // Initialize as a diagonal matrix with provided diagonal values
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    setEntry(i,j,diagonalEntries[i]);    // Access the value array directly
                } else {
                    setEntry(i,j,0);  // Off-diagonal elements are set to 0.0
                }
            }
        }
    }
    
    
}
