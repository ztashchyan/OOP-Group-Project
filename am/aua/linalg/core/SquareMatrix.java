package am.aua.linalg.core;

import java.util.InputMismatchException;

/**
 * Represents a square matrix, a special case of the Matrix class where the number of rows equals the number of columns.
 */
public class SquareMatrix extends Matrix {


    // Instance variables
    private int size;


    // Constructors 
    /**
     * Constructs a square matrix with the specified size, where the number of rows and columns are equal.
     * 
     * @param size The size of the square matrix (number of rows and columns)
    */    
    public SquareMatrix(int size){
        super(size,size);
    }
 
    /**
     * Constructs a square matrix initialized with the specified 2D array.
     * The dimensions of the square matrix are determined by the dimensions of the array.
     * 
     * @param array The 2D array used to initialize the square matrix
     * @throws InputMismatchException If the input array is not a square matrix (number of rows is not equal to number of columns)
     */   
    public SquareMatrix(double[][] array){   
        super(array);
        if (array.length!= array[0].length){
            throw new InputMismatchException("Array is not a square");
        }
        this.size = array.length;
    }


    /**
     * Constructs a square matrix with the specified size and initializes it with the provided values.
     * The values are filled row-wise into the square matrix.
     * 
     * @param size The size of the square matrix (number of rows and columns)
     * @param allValuesInARow The values to initialize the square matrix, filled row-wise
     * @throws IllegalArgumentException If the number of values provided does not match the size of the square matrix
     */
    public SquareMatrix(int size , double... allValuesInARow){
        super(size ,size, allValuesInARow);
        this.size = size;
    }


    // Copy constructor
    /**
     * Constructs a square matrix initialized with the values of another SquareMatrix object.
     * 
     * @param M The SquareMatrix object to initialize from
    */
    public SquareMatrix(SquareMatrix M){
        super(M);
        this.size = M.getSize();

    }

 
    

    // Accessors and Mutators
    /**
     * Gets the size of the square matrix, which represents the number of rows or columns.
     * 
     * @return The size of the square matrix
    */
    public int getSize(){
        return this.size;
    }

    /**
     * Attempts to create a SquareMatrix object from a Matrix object.
     * If the input Matrix is not square (number of rows != number of columns), it throws an Exception.
     * 
     * @param M The Matrix object to convert into a SquareMatrix
     * @return A SquareMatrix object created from the input Matrix
     * @throws Exception If the input Matrix is not square
     */   
    public static SquareMatrix makeSquare(Matrix M) throws Exception {
        if (M.getNumOfColumns() != M.getNumOfRows()) {
            throw new Exception("Not a SquareMatrix");
            
        }
        // Assuming SquareMatrix has a constructor that takes a 2D array of doubles
        double[][] matrixArray = M.getValue();
        return new SquareMatrix(matrixArray);
    }
    
  
    
    ///// Methods



    // Division
    /**
     * Divides one matrix by another.
     * 
     * @param A The numerator matrix
     * @param B The denominator square matrix
     * @return The result of dividing matrix A by matrix B
     * @throws IllegalArgumentException If the matrices have incompatible dimensions for division
     * @throws ArithmeticException If the denominator matrix B is not invertible
    */
    public static Matrix divide(Matrix A, SquareMatrix B) {
        // Check if the matrices have compatible dimensions for multiplication
        if (A.getNumOfColumns() != B.getNumOfRows()) {
            throw new IllegalArgumentException("Matrices cannot be divided: Incompatible dimensions.");
        }
    
        // Compute the inverse of matrix B
        Matrix inverseB = B.inverse();
    
        // Check if the inverse exists
        if (inverseB == null) {
            throw new ArithmeticException("Matrix B is not invertible, division is not possible.");
        }
    
        // Perform matrix multiplication: A * inverse(B)
        return Matrix.multiply(A, inverseB);
    }

    
    // Power   
    /**
     * Raises a square matrix to a given power.
     * 
     * @param M The square matrix to be raised to the power
     * @param n The exponent to raise the matrix to
     * @return The result of raising the matrix to the given power
     * @throws ArithmeticException If the matrix is not invertible and the exponent is negative
    */
    public static SquareMatrix power(SquareMatrix M, int n) {
    
        // If the exponent is negative, compute the inverse of the matrix
        if (n < 0) {
            // Compute the absolute value of the exponent
            int absN = Math.abs(n);
            
            // Compute the inverse of the matrix
            SquareMatrix inverseM = M.inverse();
            
            // Check if the matrix is invertible
            if (inverseM == null) {
                throw new ArithmeticException("Matrix is not invertible, cannot raise to negative power.");
            }
            
            // Return the result of the inverse matrix raised to the absolute value of the exponent
            return power(inverseM, absN);
        }
    
        // Initialize the result matrix as the identity matrix of the same size as M
        SquareMatrix result = new IdentityMatrix(M.getNumOfRows());
    
        // Perform matrix exponentiation
        while (n > 0) {
            if (n % 2 == 1) {
                result = (SquareMatrix) SquareMatrix.multiply(result, M);
            }
            M = (SquareMatrix) SquareMatrix.multiply(M, M);
            n /= 2;
        }
    
        return result;
    }


 
 
    /**
     * Multiplies two square matrices.
     * 
     * @param A The first square matrix
     * @param B The second square matrix
     * @return The result of multiplying matrices A and B
     *         If the multiplication fails, returns null
     */
    public static SquareMatrix multiply(SquareMatrix A, SquareMatrix B) {
        SquareMatrix result;
        try {
            result = makeSquare(Matrix.multiply(A, B));
        } catch (Exception e) {
            return null;
        }
        return result;
    }




    // Inverse
    /**
     * Computes the inverse of the square matrix using Gaussian elimination.
     * 
     * @return The inverse of the square matrix
     * @throws UnsupportedOperationException If the matrix is singular and does not have an inverse
    */
    public SquareMatrix inverse(){

        // Create an identity matrix of the same size
        IdentityMatrix identity = new IdentityMatrix(this.getSize());

        // Augment the original matrix with the identity matrix
        Matrix augmentedMatrix = augment(this,identity);

        // Perform Gaussian elimination to transform the augmented matrix into REF
        Matrix refMatrix = ref(augmentedMatrix);

        // Check if the original matrix is invertible (i.e., has full rank)
        for (int i = 0; i < size; i++) {
            if (refMatrix.getEntry(i, i) == 0) {
                throw new UnsupportedOperationException("The matrix is singular and does not have an inverse.");
            }
        }

        // Perform back substitution to transform the REF matrix into RREF
        Matrix rrefMatrix = rref(refMatrix);

        // Extract the inverse matrix from the augmented matrix
       SquareMatrix inverseMatrix = extractInverse(rrefMatrix,size);

        return inverseMatrix;
    }
    

    private static SquareMatrix extractInverse(Matrix matrix, int size) {
        // Create a new matrix to store the inverse
        SquareMatrix inverseMatrix = new SquareMatrix(size);
    
        // Copy the values from the right part of the augmented matrix to the inverse matrix
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                inverseMatrix.setEntry(i, j, matrix.getEntry(i, size + j));
            }
        }
    
        return inverseMatrix;
    }
    
    
    private static Matrix augment(SquareMatrix sqMatrix , IdentityMatrix identityMatrix) {
        // Check if the dimensions match
        if (sqMatrix.getNumOfRows()!= identityMatrix.getNumOfRows()) {
            throw new IllegalArgumentException("Matrices must have the same number of rows to be augmented.");
        }
        

        // Create a new matrix with augmented dimensions
        Matrix augmentedMatrix = new Matrix(sqMatrix.getNumOfRows(), sqMatrix.getNumOfColumns() + identityMatrix.getNumOfColumns());

        // Copy the values from the original matrix to the left part of the augmented matrix
        for (int i = 0; i < sqMatrix.getNumOfRows(); i++) {
            for (int j = 0; j < sqMatrix.getNumOfColumns(); j++) {
                augmentedMatrix.setEntry(i, j, sqMatrix.getEntry(i, j));
            }
        }

        // Copy the values from the identity matrix to the right part of the augmented matrix
        for (int i = 0; i < identityMatrix.getNumOfRows(); i++) {
            for (int j = 0; j < identityMatrix.getNumOfColumns(); j++) {
                augmentedMatrix.setEntry(i, identityMatrix.getNumOfColumns() + j, identityMatrix.getEntry(i, j));
            }
        }

        return augmentedMatrix;
    }





    // Determinant
    /**
     * Computes the determinant of the square matrix recursively using Laplace expansion.
     * 
     * @return The determinant of the square matrix
    */
    public double det() {
        if (size == 1) {
            return getEntry(0, 0); // For a 1x1 matrix, determinant is the only element
        } else if (size == 2) {
            return getEntry(0, 0) * getEntry(1, 1) - getEntry(0, 1) * getEntry(1, 0); // For a 2x2 matrix, use the simple formula
        } else {
            double determinant = 0;
            for (int i = 0; i < size; i++) {
                determinant += Math.pow(-1, i) * getEntry(0, i) * subMatrix(0, i).det();
            }
            return determinant;
        }
    }
    
    // Method to get sub-matrix by excluding given row and column
    private SquareMatrix subMatrix(int excludingRow, int excludingCol) {
        double[][] newArray = new double[size - 1][size - 1];
        int newRow = 0, newCol = 0;
        for (int i = 0; i < size; i++) {
            if (i == excludingRow) {
                continue;
            }
            newCol = 0;
            for (int j = 0; j < size; j++) {
                if (j == excludingCol) {
                    continue;
                }
                newArray[newRow][newCol] = getEntry(i, j);
                newCol++;
            }
            newRow++;
        }
        return new SquareMatrix(newArray);
    }


    /**
     * Computes the determinant of a square matrix using the SquareMatrix.det() method.
     * 
     * @param M The square matrix for which to compute the determinant
     * @return The determinant of the square matrix
    */
    public static double det(SquareMatrix M) {
        return M.det();
    }








}
