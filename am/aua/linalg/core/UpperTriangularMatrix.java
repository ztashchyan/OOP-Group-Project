package am.aua.linalg.core;

/* 
* Represents an upper triangular matrix, which is a special type of square matrix
* where all elements below the main diagonal are zero.
*/
public class UpperTriangularMatrix extends SquareMatrix {
    
    /**
     * Constructs an upper triangular matrix with the specified size and values.
     * The values provided are filled into the upper triangular part of the matrix.
     * 
     * @param size The size of the upper triangular matrix (number of rows/columns)
     * @param values The values to fill into the upper triangular part of the matrix
     * @throws IllegalArgumentException If the number of elements provided does not match the expected number for an upper triangular matrix
    */
    public UpperTriangularMatrix(int size, double... values) {
        super(size);
        if (values.length != size * (size + 1) / 2) {
            throw new IllegalArgumentException("Invalid number of elements for an upper triangular matrix.");
        }

        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) { // Start from i to fill only upper triangular part
                setEntry(i, j, values[index]);
                index++;
            }
        }
    }
}

