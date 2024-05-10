package am.aua.linalg.core;

/**
 * Represents a matrix with a specific number of rows and columns.
 * This class provides basic operations for matrices such as addition, multiplication, and so on.
 */
public class Matrix implements Cloneable { 

    // Instance variables
    private int numOfRows;       // Number of rows in the matrix
    private int numOfColumns;    // Number of columns in the matrix
    private double[][] value;    // 2D array to store the matrix elements
    

    // Constructors   
    /**
     * Constructs a new Matrix object with the specified number of rows and columns.
     * Values are filled in all 0s.
     *
     * @param rows The number of rows in the matrix
     * @param cols The number of columns in the matrix
     */
    public Matrix(int rows, int cols){

        this.numOfRows = rows;
        this.numOfColumns = cols;
        this.value = new double[rows][cols];
    }
   
    
    /**
     * Constructs a new Matrix object initialized with the specified 2D array.
     * The dimensions of the matrix are determined by the dimensions of the array.
     *
     * @param array The 2D array used to initialize the matrix
     * @throws IllegalArgumentException If the input array is not rectangular (not all rows have the same length)
     */
    public Matrix(double[][] array) {
        // Check if the input array is rectangular
        for (int i = 1; i < array.length; i++) {
            if (array[i].length != array[0].length) {
                throw new IllegalArgumentException("Input array is not rectangular");
            }
        }
        
        // Initialize matrix with provided array
        this.numOfRows = array.length;
        this.numOfColumns = array[0].length;
        setValue(array);
    }



    /**
     * Constructs a new Matrix object with the specified number of rows and columns,
     * initializing its elements with the provided values row by row.
     *
     * @param numOfRows The number of rows in the matrix
     * @param numOfColumns The number of columns in the matrix
     * @param allValuesInARow The values to initialize the matrix row by row
     * @throws IllegalArgumentException If the length of allValuesInARow is not equal to numOfRows * numOfColumns
     */
    public Matrix(int numOfRows, int numOfColumns, double... allValuesInARow) throws IllegalArgumentException{

        if (allValuesInARow.length != numOfRows*numOfColumns){
            throw new IllegalArgumentException("Cannot construct such a matrix");
        }
        this.value = new double[numOfRows][numOfColumns];
        int index = 0;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                this.value[i][j] = allValuesInARow[index];
                index++;
            }
        }
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
    }
 
 
    

    // Copy constructor
    /**
     * Constructs a new Matrix object by copying the contents of another Matrix.
     *
     * @param M The Matrix object to copy
     */
    public Matrix(Matrix M){
        this.numOfRows = M.numOfRows;
        this.numOfColumns = M.numOfColumns;

        this.value = new double[M.getNumOfRows()][M.getNumOfColumns()]; 
        for (int i = 0; i < M.getNumOfRows(); i++) {
            for (int j = 0; j < M.getNumOfColumns(); j++) {
                this.value[i][j] = M.getValue()[i][j];
            }
        }              
    }





    // Accessors and Mutators
    
    /**
     * Returns a deep copy of the 2D array representing the values of this Matrix.
     *
     * @return A deep copy of the 2D array representing the values of this Matrix
     */
    public double[][] getValue() {
        
        int numRows = this.value.length;
        int numColumns = this.value[0].length;        
        double[][] copy = new double[numRows][numColumns];        
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                copy[i][j] = this.value[i][j];
            }
        }              
        return copy;
    }


    /**
     * Sets the values of this Matrix using the provided 2D array.
     * 
     * @param array The 2D array containing the new values for this Matrix
     * @throws IllegalArgumentException If the input array is not rectangular (not all rows have the same length)
     */
    public void setValue(double[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        
        // Check if the input array is rectangular
        for (int i = 1; i < rows; i++) {
            if (array[i].length != cols) {
                throw new IllegalArgumentException("Input array is not rectangular");
            }
        }
    
        // Create a new array and copy the values
        double[][] newArray = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(array[i], 0, newArray[i], 0, cols);
        }
    
        // Assign the new array to the value field
        this.value = newArray;
    }

    
    /**
     * Retrieves the value of a specific entry in the matrix.
     * 
     * @param row The row index of the entry
     * @param column The column index of the entry
     * @return The value of the specified entry
     */   
    public double getEntry(int row, int column){
        return this.value[row][column];
    }


    /**
     * Sets the value of a specific entry in the matrix.
     * 
     * @param i The row index of the entry
     * @param j The column index of the entry
     * @param value The new value to be set
     * @throws IndexOutOfBoundsException If the specified row or column index is out of bounds
     */
    public void setEntry(int i, int j, double value){
        
        if (i < 0 || i >= numOfRows || j < 0 || j >= numOfColumns) {
            throw new IndexOutOfBoundsException("Invalid matrix indices");
        }
        
        this.value[i][j] = value;
    }
  

 

    
    /**
     * Retrieves the number of rows in the matrix.
     * 
     * @return The number of rows in the matrix
     */    
    public int getNumOfRows(){
        return this.numOfRows;

    }


    /**
     * Retrieves the number of columns in the matrix.
     * 
     * @return The number of columns in the matrix
     */    
    public int getNumOfColumns(){
        return this.numOfColumns;

    }

    /**
     * Returns a string representation of the matrix.
     * The string representation consists of rows enclosed in square brackets,
     * with elements separated by spaces.
     * Each row is separated by a newline character.
     *
     * @return A string representation of the matrix
     */    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.value.length; i++) {
            sb.append("[ "); // Start of the row
            for (int j = 0; j < this.value[i].length; j++) {
                sb.append(this.value[i][j]).append(" ");
            }
            sb.append("]").append("\n"); // End of the row
        }
        return sb.toString();
    }
    
 
    /**
     * Compares this Matrix with another Matrix for equality.
     * 
     * @param M The Matrix object to compare with
     * @return true if the two matrices are equal, false otherwise
     */
    public boolean equals(Matrix M) {
        // Check if dimensions are the same
        if (this.numOfRows != M.numOfRows || this.numOfColumns != M.numOfColumns) {
            return false;
        }
    
        // Compare each element
        for (int i = 0; i < this.numOfRows; i++) {
            for (int j = 0; j < this.numOfColumns; j++) {
                if (this.value[i][j] != M.value[i][j]) {
                    return false;
                }
            }
        }
        return true; // Matrices are equal
    }


    /**
     * Creates and returns a deep copy of this Matrix.
     * 
     * @return A deep copy of this Matrix
     */
    @Override
    public Matrix clone() {
        try {
            Matrix clonedMatrix = (Matrix) super.clone();
            clonedMatrix.value = this.value.clone(); 
            for (int i = 0; i < this.numOfRows; i++) {
                clonedMatrix.value[i] = this.value[i].clone(); 
            }
            return clonedMatrix;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }



    
    //// Methods 
    

    // Add
    /**
     * Adds two matrices together and returns the result.
     * 
     * @param A The first Matrix to be added
     * @param B The second Matrix to be added
     * @return The result of adding the two matrices
     * @throws IllegalArgumentException If the input matrices have different dimensions
     */
    public static Matrix add(Matrix A, Matrix B) {
        // Check if matrices have the same dimensions
        if (A.getNumOfRows() != B.getNumOfRows() || A.getNumOfColumns() != B.getNumOfColumns()) {
            throw new IllegalArgumentException("Cannot add matrices with different dimensions.");
            
        }

        int numRows = A.getNumOfRows();
        int numCols = A.getNumOfColumns();
        double[][] result = new double[numRows][numCols];

        // Add corresponding elements of x and y matrices
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result[i][j] = A.value[i][j] + B.value[i][j];
            }
        }

        // Create and return the result matrix
        return new Matrix(result);
    }

    
    /**
     * Adds another Matrix to this Matrix and returns the result.
     * 
     * @param O The Matrix to be added to this Matrix
     * @return The result of adding the two matrices
     * @throws IllegalArgumentException If the input matrix has different dimensions from this Matrix
     */
    public Matrix add(Matrix O) {
        return add(this,O);
    }



    // Subtract
    /**
    * Subtracts one matrix from another and returns the result.
    * 
    * @param A The Matrix from which to subtract
    * @param B The Matrix to subtract
    * @return The result of subtracting matrix B from matrix A
    * @throws IllegalArgumentException If the input matrices have different dimensions
    */
    public static Matrix subtract(Matrix A, Matrix B) {
        // Check if matrices have the same dimensions
        if (A.getNumOfRows() != B.getNumOfRows() || A.getNumOfColumns() != B.getNumOfColumns()) {
            throw new IllegalArgumentException("Cannot add matrices with different dimensions.");
            
        }

        int numRows = A.getNumOfRows();
        int numCols = A.getNumOfColumns();
        double[][] result = new double[numRows][numCols];

        // Add corresponding elements of x and y matrices
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result[i][j] = A.value[i][j] - B.value[i][j];
            }
        }

        // Create and return the result matrix
        return new Matrix(result);
    }

    /**
    * Subtracts another Matrix from this Matrix and returns the result.
    * 
    * @param O The Matrix to subtract from this Matrix
    * @return The result of subtracting the input Matrix from this Matrix
    * @throws IllegalArgumentException If the input matrix has different dimensions from this Matrix
    */
    public Matrix subtract(Matrix O) {
        return subtract(this,O);
    }




    // Scalar multiplication
    /**
     * Multiplies a matrix by a scalar value and returns the result.
     * 
     * @param M The Matrix to be multiplied
     * @param a The scalar value to multiply by
     * @return The result of multiplying the matrix by the scalar value
    */
    public static Matrix scalarMultiply(Matrix M , Scalar a){
        
        int numRows = M.getNumOfRows();
        int numCols = M.getNumOfColumns();
        double[][] result = new double[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result[i][j] = M.value[i][j] * a.getValue();
            }
        }  
        
        return new Matrix(result);

    }

    /**
     * Multiplies this Matrix by a scalar value and returns the result.
     * 
     * @param a The scalar value to multiply by
     * @return The result of multiplying this Matrix by the scalar value
    */
    public Matrix scalarMultiply(Scalar a) {
        return scalarMultiply(this,a);
    }


    
    
    // Multiplication
    /**
     * Multiplies two matrices and returns the result.
     * 
     * @param A The first Matrix to be multiplied
     * @param B The second Matrix to be multiplied
     * @return The result of multiplying matrix A by matrix B
     * @throws IllegalArgumentException If the number of columns of A is not equal to the number of rows of B
    */
    public static  Matrix  multiply(Matrix A, Matrix B) {
        // Check if matrices can be multiplied (number of columns of A = number of rows of B)
        if (A.getNumOfColumns() != B.getNumOfRows()) {
            throw new IllegalArgumentException("Cannot multiply matrices with incompatible dimensions.");
            
        }
    
        int numRowsA = A.getNumOfRows();
        int numColsA = A.getNumOfColumns();
        int numColsB = B.getNumOfColumns();
        double[][] result = new double[numRowsA][numColsB];
    
        // Perform matrix multiplication
        for (int i = 0; i < numRowsA; i++) {
            for (int j = 0; j < numColsB; j++) {
                double sum = 0;
                for (int k = 0; k < numColsA; k++) {
                    sum += A.getEntry(i, k) * B.getEntry(k, j);
                }
                result[i][j] = sum;
            }
        }
    
        // Create and return the result matrix
        return new Matrix(result);
    }
    

    /**
     * Multiplies this Matrix by another Matrix and returns the result.
     * 
     * @param O The Matrix to multiply by
     * @return The result of multiplying this Matrix by the input Matrix
     * @throws IllegalArgumentException If the number of columns of this Matrix is not equal to the number of rows of the input Matrix
    */
    public Matrix multiply(Matrix O) {
        return multiply(this,O);
    }






    // Transpose
    /**
     * Transposes this Matrix and returns the result.
     * 
     * @return The transpose of this Matrix
     */
    public Matrix transpose() {
        int numRows = this.getNumOfColumns(); // Number of rows in the transpose will be equal to number of columns in the original matrix
        int numCols = this.getNumOfRows(); // Number of columns in the transpose will be equal to number of rows in the original matrix
        double[][] result = new double[numRows][numCols];
    
        // Transpose the matrix
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result[i][j] = this.getEntry(j, i); // Swap rows and columns for the transpose
            }
        }
    
        // Create and return the transpose matrix
        return new Matrix(result);
    }

    


    

    
    // REF
    /**
     * Computes the row echelon form (REF) of the input matrix using Gaussian elimination and returns the result.
     * 
     * @param M The input Matrix
     * @return The row echelon form (REF) of the input matrix
     */
    public static Matrix ref(Matrix M) {
        // Create a copy of the input matrix
        Matrix refMatrix = M.clone();

        int numRows = refMatrix.getNumOfRows();
        int numCols = refMatrix.getNumOfColumns();
        int lead = 0;

        // Iterate through each row
        for (int r = 0; r < numRows; r++) {
            if (numCols <= lead) {
                break;
            }

            // Find the pivot row
            int i = r;
            while (refMatrix.getEntry(i, lead) == 0) {
                i++;
                if (numRows == i) {
                    i = r;
                    lead++;
                    if (numCols == lead) {
                        return refMatrix; // Return the matrix if there are no more pivots
                    }
                }
            }

            // Swap rows if necessary to bring the pivot to the current row
            double[] temp = refMatrix.value[i];
            refMatrix.value[i] = refMatrix.value[r];
            refMatrix.value[r] = temp;

            // Scale the current row so that the pivot becomes 1
            double pivotValue = refMatrix.value[r][lead];
            for (int j = 0; j < numCols; j++) {
                refMatrix.value[r][j] /= pivotValue;
                // Correct -0 values to 0
                if (Math.abs(refMatrix.value[r][j]) == 0) {
                    refMatrix.value[r][j] = 0;
                }
            }

            // Eliminate other entries in the current column
            for (int i2 = 0; i2 < numRows; i2++) {
                if (i2 != r) {
                    double scaleFactor = refMatrix.value[i2][lead];
                    for (int j = 0; j < numCols; j++) {
                        refMatrix.value[i2][j] -= scaleFactor * refMatrix.value[r][j];
                        // Correct -0 values to 0
                        if (Math.abs(refMatrix.value[i2][j]) == 0) {
                            refMatrix.value[i2][j] = 0;
                        }
                    }
                }
            }

            lead++;
        }

        return refMatrix;
    }

    
      





    // RREF
    /**
     * Computes the reduced row echelon form (RREF) of the input matrix using Gaussian elimination and returns the result.
     * 
     * @param M The input Matrix
     * @return The reduced row echelon form (RREF) of the input matrix
    */
    public static Matrix rref(Matrix M) {
        // Compute the REF of the matrix
        Matrix refMatrix = ref(M);
    
        int numRows = refMatrix.getNumOfRows();
        int numCols = refMatrix.getNumOfColumns();
        int lead = 0;
    
        // Iterate through each row (from bottom to top)
        for (int r = numRows - 1; r >= 0; r--) {
            if (numCols <= lead) {
                break;
            }
    
            // Find the pivot column
            int i = r;
            while (refMatrix.getEntry(i, lead) == 0) {
                i--;
                if (i < 0) {
                    break;
                }
            }
    
            // If a pivot column is found, make the leading entry the only non-zero entry in its column
            if (i >= 0) {
                double pivotValue = refMatrix.getEntry(i, lead);
                for (int j = 0; j < numCols; j++) {
                    refMatrix.setEntry(i, j, refMatrix.getEntry(i, j) / pivotValue);
                }
                for (int i2 = 0; i2 < numRows; i2++) {
                    if (i2 != i) {
                        double scaleFactor = refMatrix.getEntry(i2, lead);
                        for (int j = 0; j < numCols; j++) {
                            refMatrix.setEntry(i2, j, refMatrix.getEntry(i2, j) - scaleFactor * refMatrix.getEntry(i, j));
                        }
                    }
                }
            }
    
            lead++;
        }
    
        return refMatrix;
    }
    








}