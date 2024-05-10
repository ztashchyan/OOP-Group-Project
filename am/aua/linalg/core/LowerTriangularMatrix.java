package am.aua.linalg.core;

public class LowerTriangularMatrix extends SquareMatrix{
    
    /**
     * Constructs a lower triangular matrix with the specified size and values.
     * The values provided are filled into the lower triangular part of the matrix.
     * 
     * @param size The size of the lower triangular matrix (number of rows/columns)
     * @param values The values to fill into the lower triangular part of the matrix
     * @throws IllegalArgumentException If the number of elements provided does not match the expected number for a lower triangular matrix
    */
    public LowerTriangularMatrix(int size, double... values) {
        super(size);
        if (values.length != size * (size + 1) / 2) {
            throw new IllegalArgumentException("Invalid number of elements for a lower triangular matrix.");
        }

        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                setEntry(i, j, values[index]);
                index++;
            }
        }
    }
}
