package am.aua.linalg.ui;

import javax.swing.*;
import am.aua.linalg.core.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(2, 2));

        JButton matrixButton = new JButton("Matrix Operations");
        JButton vectorButton = new JButton("Vector Operations");
        JButton complexVectorButton = new JButton("Complex Vector Operations");
        JButton complexNumbersButton = new JButton("Complex Numbers Operations");

        matrixButton.addActionListener(e -> showMatrixSubButtons(matrixButton));
        vectorButton.addActionListener(e -> showVectorSubButtons(vectorButton));
        complexVectorButton.addActionListener(e -> showComplexVectorSubButtons(complexVectorButton));
        complexNumbersButton.addActionListener(e -> showComplexNumbersSubButtons(complexNumbersButton));

        mainPanel.add(matrixButton);
        mainPanel.add(vectorButton);
        mainPanel.add(complexVectorButton);
        mainPanel.add(complexNumbersButton);

        frame.add(mainPanel);

        frame.setVisible(true);
    }

    private static void showMatrixSubButtons(JButton mainButton) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Matrix Operations");

        JPanel subPanel = new JPanel(new GridLayout(4, 3));
        
        String[] matrixOperations = {
                "Add", "Subtract", "Multiply", "Multiply by a scalar",
                "Transpose", "REF", "RREF", "Divide",
                "Power of n", "Inverse", "Determinant"
        };

        for (String operation : matrixOperations) {
            JButton subButton = new JButton(operation);
            
            subButton.addActionListener(e -> {
                dialog.dispose();
                new MatrixOperationActionListener(operation).actionPerformed(new ActionEvent(subButton, ActionEvent.ACTION_PERFORMED, null));
            });

            subPanel.add(subButton);
        }

        dialog.add(subPanel);

        dialog.setSize(500, 200);
        dialog.setLocationRelativeTo(mainButton);
        dialog.setModal(true);
        dialog.setVisible(true);
    }

    private static void showVectorSubButtons(JButton mainButton) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Vector Operations");

        JPanel subPanel = new JPanel(new GridLayout(5, 3));

        String[] vectorOperations = {
                "Add", "Subtract", "Multiply by a Scalar",
                "Calculate Magnitude", "Calculate Unit Vector",
                "Dot Product", "Calculate Angle between Vectors",
                "Calculate Scalar Projection", "Calculate Vector Projection",
                "Calculate Vector Rejection", "Cross Product", "Area of a Triangle",
                "Are orthogonal?", "Are parallel?"
        };

        for (String operation : vectorOperations) {

            JButton subButton = new JButton(operation);

            subButton.addActionListener(e -> {
                dialog.dispose();
                new VectorOperationActionListener(operation).actionPerformed(new ActionEvent(subButton, ActionEvent.ACTION_PERFORMED, null));
            });
            subPanel.add(subButton);

        }

        dialog.add(subPanel);

        dialog.setSize(800, 300);
        dialog.setLocationRelativeTo(mainButton);
        dialog.setModal(true);
        dialog.setVisible(true);
    }

    private static void showComplexVectorSubButtons(JButton mainButton) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Complex Vector Operations");

        JPanel subPanel = new JPanel(new GridLayout(3, 2));

        String[] complexVectorOperations = {
                "Add Complex Vectors", "Subtract Complex Vectors",
                "Multiply Complex Vector by a Scalar", "Dot product of Complex Vectors",
                "Magnitude of a Complex Vector", "Conjugate of a Complex Vector"
        };

        for (String operation : complexVectorOperations) {
            JButton subButton = new JButton(operation);

            subButton.addActionListener(e -> {
                dialog.dispose();
                new ComplexVectorOperationActionListener(operation).actionPerformed(new ActionEvent(subButton, ActionEvent.ACTION_PERFORMED, null));
            });
            subPanel.add(subButton);
            
        }

        dialog.add(subPanel);

        dialog.setSize(600, 200);
        dialog.setLocationRelativeTo(mainButton);
        dialog.setModal(true);
        dialog.setVisible(true);
    }

    private static void showComplexNumbersSubButtons(JButton mainButton) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Complex Numbers Operations");

        JPanel subPanel = new JPanel(new GridLayout(3, 2));

        String[] complexNumbersOperations = {
                "Add", "Subtract",
                "Multiply", "Modulus of a Complex number",
                "Conjugate of a Complex number"
        };

        for (String operation : complexNumbersOperations) {
            JButton subButton = new JButton(operation);

            subButton.addActionListener(e -> {
                dialog.dispose();
                new ComplexNumberOperationActionListener(operation).actionPerformed(new ActionEvent(subButton, ActionEvent.ACTION_PERFORMED, null));
            });
            subPanel.add(subButton);
        }

        dialog.add(subPanel);

        dialog.setSize(600, 200);
        dialog.setLocationRelativeTo(mainButton);
        dialog.setModal(true);
        dialog.setVisible(true);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static class MatrixOperationActionListener implements ActionListener {
        private String operation;
        private JTextField[][] matrixFields;
        private JTextField scalarField;


        MatrixOperationActionListener(String operation) {
            this.operation = operation;
        }

        

        @Override
        public void actionPerformed(ActionEvent e) {
            // Perform the specific matrix operation based on the operation field
            switch (operation) {
                case "Add":
                    addOrSubtract("Add");
                    break;
                case "Subtract":
                    addOrSubtract("Subtract");
                    break;
                case "Multiply":
                    forthSetOperations("Multiply");
                    break;
                case "Multiply by a scalar":
                    thirdSetOperations("Multiply by a scalar");
                    break;
                case "Transpose":
                    secondSetOperations("Transpose");
                    break;
                case "REF":
                    secondSetOperations("REF");
                    break;
                case "RREF":
                    secondSetOperations("RREF");
                    break;
                case "Divide":
                    forthSetOperations("Divide");
                    break;
                case "Power of n":
                    thirdSetOperations("Power of n");
                    break;
                case "Inverse":
                    secondSetOperations("Inverse");
                    break;
                case "Determinant":
                    fifthSetOperations("Determinant");
                    break;
                default:
                    System.out.println("Operation not implemented: " + operation);
            }
            
        }

        ///////// 1 /////////////// 1 /////////////// 1 /////////////// 1 /////////////// 1 //////
        private void addOrSubtract(String operation) {
            // Create a panel to input row and column sizes
            JPanel sizePanel = new JPanel(new GridLayout(3, 2));
            sizePanel.add(new JLabel("Enter the number of rows:"));
            JTextField rowsField = new JTextField(5);
            sizePanel.add(rowsField);
            sizePanel.add(new JLabel("Enter the number of columns:"));
            JTextField colsField = new JTextField(5);
            sizePanel.add(colsField);
        
            // Show dialog to input row and column sizes
            int resultSize = JOptionPane.showConfirmDialog(null, sizePanel, "Enter the Matrix Size", JOptionPane.OK_CANCEL_OPTION);
            if (resultSize == JOptionPane.OK_OPTION) {
                // Parse row and column sizes
                int rows, cols;
                try {
                    rows = Integer.parseInt(rowsField.getText());
                    cols = Integer.parseInt(colsField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter numeric values for rows and columns.");
                    return;
                }
        
                // Create a panel to input matrix values for both matrices
                JPanel inputPanel = new JPanel(new GridLayout(3, 1));
        
                // Create fields for the first matrix
                JPanel firstMatrixPanel = new JPanel(new GridLayout(rows, cols));
                JTextField[][] firstMatrixFields = new JTextField[rows][cols];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        firstMatrixFields[i][j] = new JTextField(5);
                        firstMatrixPanel.add(firstMatrixFields[i][j]);
                    }
                }
                inputPanel.add(new JLabel("Fill in the first matrix values:"));
                inputPanel.add(firstMatrixPanel);
        
                // Create fields for the second matrix
                JPanel secondMatrixPanel = new JPanel(new GridLayout(rows, cols));
                JTextField[][] secondMatrixFields = new JTextField[rows][cols];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        secondMatrixFields[i][j] = new JTextField(5);
                        secondMatrixPanel.add(secondMatrixFields[i][j]);
                    }
                }
                inputPanel.add(new JLabel("Fill in the second matrix values:"));
                inputPanel.add(secondMatrixPanel);
        
                // Show dialog to input matrix values for both matrices
                int result = JOptionPane.showConfirmDialog(null, inputPanel, "Fill in the Matrix Values", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    // Create matrices based on user input
                    double[][] matrixValues1 = new double[rows][cols];
                    double[][] matrixValues2 = new double[rows][cols];
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            try {
                                matrixValues1[i][j] = Double.parseDouble(firstMatrixFields[i][j].getText());
                                matrixValues2[i][j] = Double.parseDouble(secondMatrixFields[i][j].getText());
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Invalid input! Please enter numeric values.");
                                return;
                            }
                        }
                    }
        
                    // Perform the selected matrix operation
                    Matrix matrix1 = new Matrix(matrixValues1);
                    Matrix matrix2 = new Matrix(matrixValues2);
        
                    Matrix resultMatrix = null;
                    switch (operation) {
                        case "Add":
                            resultMatrix = Matrix.add(matrix1, matrix2);
                            break;
                        case "Subtract":
                            resultMatrix = Matrix.subtract(matrix1, matrix2);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Operation not implemented yet: " + operation);
                    }
        
                    // Display the result
                    if (resultMatrix != null) {
                        JFrame resultFrame = new JFrame("Result Matrix");
                        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set close operation
                        resultFrame.setSize(400, 300);
                        resultFrame.setLocationRelativeTo(null);
        
                        JPanel resultPanel = new JPanel(new GridLayout(rows + 1, cols));
                        for (int i = 0; i < rows; i++) {
                            for (int j = 0; j < cols; j++) {
                                JTextField textField = new JTextField(String.valueOf(resultMatrix.getEntry(i, j)));
                                textField.setEditable(false);
                                resultPanel.add(textField);
                            }
                        }
        
                        JButton closeButton = new JButton("Close");
                        closeButton.addActionListener(e -> {
                            resultFrame.dispose(); // Close the frame when the button is clicked
                            // Close the input dialog windows
                            SwingUtilities.getWindowAncestor(sizePanel).dispose();
                            SwingUtilities.getWindowAncestor(inputPanel).dispose();
                        });
                        resultPanel.add(closeButton);
        
                        resultFrame.add(resultPanel);
                        resultFrame.setVisible(true);
                    }
        
        
                }
            }
        }

         ///////// 2 ////// ///////// 2 ////// ///////// 2 ////// ///////// 2 ////// ///////// 2 ////// ///////// 2 ////// ///////// 2 //////  
        private void secondSetOperations(String operation) {
            try {
                // Create a panel to input row and column sizes
                JPanel sizePanel = new JPanel(new GridLayout(2, 2));
                sizePanel.add(new JLabel("Enter the number of rows:"));
                JTextField rowsField = new JTextField(5);
                sizePanel.add(rowsField);
                sizePanel.add(new JLabel("Enter the number of columns:"));
                JTextField colsField = new JTextField(5);
                sizePanel.add(colsField);
        
                // Show dialog to input row and column sizes
                int resultSize = JOptionPane.showConfirmDialog(null, sizePanel, "Enter the Matrix Size", JOptionPane.OK_CANCEL_OPTION);
                if (resultSize == JOptionPane.OK_OPTION) {
                    // Parse row and column sizes
                    int rows, cols;
                    try {
                        rows = Integer.parseInt(rowsField.getText());
                        cols = Integer.parseInt(colsField.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input! Please enter numeric values for rows and columns.");
                        return;
                    }
        
                    // Create a panel to input matrix values
                    JPanel inputPanel = createMatrixInputPanel(rows, cols);
        
                    // Show dialog to input matrix values
                    int resultInput = JOptionPane.showConfirmDialog(null, inputPanel, "Fill in the Matrix Values", JOptionPane.OK_CANCEL_OPTION);
                    if (resultInput == JOptionPane.OK_OPTION) {
                        // Create matrix based on user input
                        double[][] matrixValues = new double[rows][cols];
                        for (int i = 0; i < rows; i++) {
                            for (int j = 0; j < cols; j++) {
                                try {
                                    matrixValues[i][j] = Double.parseDouble(matrixFields[i][j].getText());
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter numeric values.");
                                    return;
                                }
                            }
                        }
        
                        // Perform the selected matrix operation
                        Matrix matrix = new Matrix(matrixValues);
                        Matrix resultMatrix = null;
                        boolean transposeIdentifier = false;
                        switch (operation) {
                            case "Transpose":
                                resultMatrix = matrix.transpose();
                                transposeIdentifier = true;
                                break;
                            case "REF":
                                resultMatrix = Matrix.ref(matrix);
                                break;
                            case "RREF":
                                resultMatrix = Matrix.rref(matrix);
                                break;
                            case "Inverse":
                                SquareMatrix sqm = SquareMatrix.makeSquare(matrix);
                                resultMatrix = sqm.inverse();
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Operation not implemented yet: " + operation);
                        }
        
                        // Display the result
                        if (resultMatrix != null) {
                            JFrame resultFrame = new JFrame("Result Matrix");
                            resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set close operation
                            resultFrame.setSize(400, 300);
                            resultFrame.setLocationRelativeTo(null);

                            if(transposeIdentifier){
                                JPanel resultPanel = new JPanel(new GridLayout(cols + 1, rows));
                                for (int i = 0; i < cols; i++) {
                                    for (int j = 0; j < rows; j++) {
                                        JTextField textField = new JTextField(String.valueOf(resultMatrix.getEntry(i, j)));
                                        textField.setEditable(false);
                                        resultPanel.add(textField);
                                    }
                                }
                
                                JButton closeButton = new JButton("Close");
                                closeButton.addActionListener(e -> {
                                    resultFrame.dispose(); // Close the frame when the button is clicked
                                    // Close the input dialog windows
                                    SwingUtilities.getWindowAncestor(sizePanel).dispose();
                                    SwingUtilities.getWindowAncestor(inputPanel).dispose();
                                });
                                resultPanel.add(closeButton);
                
                                resultFrame.add(resultPanel);
                                resultFrame.setVisible(true);

                            }else{
                                JPanel resultPanel = new JPanel(new GridLayout(rows + 1, cols));
                                for (int i = 0; i < rows; i++) {
                                    for (int j = 0; j < cols; j++) {
                                        JTextField textField = new JTextField(String.valueOf(resultMatrix.getEntry(i, j)));
                                        textField.setEditable(false);
                                        resultPanel.add(textField);
                                    }
                                }
                
                                JButton closeButton = new JButton("Close");
                                closeButton.addActionListener(e -> {
                                    resultFrame.dispose(); // Close the frame when the button is clicked
                                    // Close the input dialog windows
                                    SwingUtilities.getWindowAncestor(sizePanel).dispose();
                                    SwingUtilities.getWindowAncestor(inputPanel).dispose();
                                });
                                resultPanel.add(closeButton);
                
                                resultFrame.add(resultPanel);
                                resultFrame.setVisible(true);

                            }
                        }
                    }
                }
        
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        
        // Method to create input panel for matrix values
        private JPanel createMatrixInputPanel(int rows, int cols) {
            JPanel matrixPanel = new JPanel(new GridLayout(rows, cols));
            matrixFields = new JTextField[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrixFields[i][j] = new JTextField(5);
                    matrixPanel.add(matrixFields[i][j]);
                }
            }
            return matrixPanel;
        }
               
    ///////// 3 /////////////// 3 /////////////// 3 /////////////// 3 /////////////// 3 /////////////// 3 //////
    private void thirdSetOperations(String operation) {
        try {
            // Create a panel to input row and column sizes
            JPanel sizePanel = new JPanel(new GridLayout(2, 2));
            sizePanel.add(new JLabel("Enter the number of rows:"));
            JTextField rowsField = new JTextField(5);
            sizePanel.add(rowsField);
            sizePanel.add(new JLabel("Enter the number of columns:"));
            JTextField colsField = new JTextField(5);
            sizePanel.add(colsField);
    
            // Show dialog to input row and column sizes
            int resultSize = JOptionPane.showConfirmDialog(null, sizePanel, "Enter the Matrix Size", JOptionPane.OK_CANCEL_OPTION);
            if (resultSize == JOptionPane.OK_OPTION) {
                // Parse row and column sizes
                int rows, cols;
                try {
                    rows = Integer.parseInt(rowsField.getText());
                    cols = Integer.parseInt(colsField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter numeric values for rows and columns.");
                    return;
                }
    
                // Create a panel to input matrix values and scalar value
                JPanel inputPanel = createMatrixAndScalarInputPanel(rows, cols);
    
                // Show dialog to input matrix values and scalar value
                int resultInput = JOptionPane.showConfirmDialog(null, inputPanel, "Fill in the Matrix and The Power", JOptionPane.OK_CANCEL_OPTION);
                if (resultInput == JOptionPane.OK_OPTION) {
                    // Parse matrix values
                    double[][] matrixValues = new double[rows][cols];
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            try {
                                matrixValues[i][j] = Double.parseDouble(matrixFields[i][j].getText());
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Invalid input! Please enter numeric values.");
                                return;
                            }
                        }
                    }
    
                    // Parse scalar value
                    int scalar;
                    try {
                        scalar = Integer.parseInt(scalarField.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid scalar value! Please enter a numeric value.");
                        return;
                    }
    
                    // Create matrix and scalar objects
                    Matrix matrix = new Matrix(matrixValues);
                    Scalar scalarObj = new Scalar(scalar);
    
                    // Perform the selected matrix operation with the scalar
                    Matrix resultMatrix = null;
                    switch (operation) {
                        case "Power of n":
                            SquareMatrix sq = SquareMatrix.makeSquare(matrix);
                            resultMatrix = SquareMatrix.power(sq, scalar);
                            break;
                        case "Multiply by a scalar":
                            resultMatrix = matrix.scalarMultiply(scalarObj);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Operation not implemented yet: " + operation);
                    }
    
                    // Display the result
                    if (resultMatrix != null) {
                        displayResultMatrix(resultMatrix, rows, cols);
                    }
                }
            }
    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    // Method to create input panel for matrix values and scalar value
    private JPanel createMatrixAndScalarInputPanel(int rows, int cols) {
        JPanel inputPanel = new JPanel(new GridLayout(rows + 2, 2));
        matrixFields = new JTextField[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrixFields[i][j] = new JTextField(5);
                inputPanel.add(matrixFields[i][j]);
            }
        }
        inputPanel.add(new JLabel("Enter the power value:"));
        scalarField = new JTextField(5);
        inputPanel.add(scalarField);
        return inputPanel;
    }
    
    // Method to display the result matrix
    public void displayResultMatrix(Matrix resultMatrix, int rows, int cols) {
        JFrame resultFrame = new JFrame("Result Matrix");
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.setSize(400, 300);
        resultFrame.setLocationRelativeTo(null);
    
        JPanel resultPanel = new JPanel(new GridLayout(rows + 1, cols));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JTextField textField = new JTextField(String.valueOf(resultMatrix.getEntry(i, j)));
                textField.setEditable(false);
                resultPanel.add(textField);
            }
        }
    
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            resultFrame.dispose();
        });
        resultPanel.add(closeButton);
    
        resultFrame.add(resultPanel);
        resultFrame.setVisible(true);
    }

///////// 4 //////////////////////// 4 //////////////////////// 4 //////////////////////// 4 //////////////////////// 4 ///////////////

    // Method to create an input panel for matrix values
    private JPanel createInputPanel(int rows, int cols, String message) {
        JPanel inputPanel = new JPanel(new GridLayout(rows, cols));
        JTextField[][] matrixFields = new JTextField[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrixFields[i][j] = new JTextField(5);
                inputPanel.add(matrixFields[i][j]);
            }
        }
        return inputPanel;
    }

    // Method to create a matrix from the values entered in an input panel
    private Matrix createMatrixFromInputPanel(JPanel inputPanel, int rows, int cols) {
        double[][] matrixValues = new double[rows][cols];
        Component[] components = inputPanel.getComponents();
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JTextField textField = (JTextField) components[index++];
                try {
                    matrixValues[i][j] = Double.parseDouble(textField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter numeric values.");
                    return null;
                }
            }
        }
        return new Matrix(matrixValues);
    }
    
    private void forthSetOperations(String operation) {
        try {
            // Create a panel to input row and column sizes for the first matrix
            JPanel sizePanel1 = new JPanel(new GridLayout(2, 2));
            sizePanel1.add(new JLabel("Enter the number of rows for the first matrix:"));
            JTextField rowsField1 = new JTextField(5);
            sizePanel1.add(rowsField1);
            sizePanel1.add(new JLabel("Enter the number of columns for the first matrix:"));
            JTextField colsField1 = new JTextField(5);
            sizePanel1.add(colsField1);
    
            // Show dialog to input row and column sizes for the first matrix
            int resultSize1 = JOptionPane.showConfirmDialog(null, sizePanel1, "Enter Size for First Matrix", JOptionPane.OK_CANCEL_OPTION);
            if (resultSize1 == JOptionPane.OK_OPTION) {
                int rows1 = Integer.parseInt(rowsField1.getText());
                int cols1 = Integer.parseInt(colsField1.getText());
    
                // Create a panel to input matrix values for the first matrix
                JPanel inputPanel1 = createInputPanel(rows1, cols1, "Fill in the values for the first matrix:");
    
                // Show dialog to input matrix values for the first matrix
                int result1 = JOptionPane.showConfirmDialog(null, inputPanel1, "Fill in the Values for First Matrix", JOptionPane.OK_CANCEL_OPTION);
                if (result1 == JOptionPane.OK_OPTION) {
                    // Create matrix based on user input for the first matrix
                    Matrix matrix1 = createMatrixFromInputPanel(inputPanel1, rows1, cols1);
    
                    // Prompt user to enter row and column sizes for the second matrix
                    JPanel sizePanel2 = new JPanel(new GridLayout(2, 2));
                    sizePanel2.add(new JLabel("Enter the number of rows for the second matrix:"));
                    JTextField rowsField2 = new JTextField(5);
                    sizePanel2.add(rowsField2);
                    sizePanel2.add(new JLabel("Enter the number of columns for the second matrix:"));
                    JTextField colsField2 = new JTextField(5);
                    sizePanel2.add(colsField2);
    
                    // Show dialog to input row and column sizes for the second matrix
                    int resultSize2 = JOptionPane.showConfirmDialog(null, sizePanel2, "Enter the Size for the Second Matrix", JOptionPane.OK_CANCEL_OPTION);
                    if (resultSize2 == JOptionPane.OK_OPTION) {
                        int rows2 = Integer.parseInt(rowsField2.getText());
                        int cols2 = Integer.parseInt(colsField2.getText());
    
                        // Create a panel to input matrix values for the second matrix
                        JPanel inputPanel2 = createInputPanel(rows2, cols2, "Fill in the values for the second matrix:");
    
                        // Show dialog to input matrix values for the second matrix
                        int result2 = JOptionPane.showConfirmDialog(null, inputPanel2, "Fill in the Values for Second Matrix", JOptionPane.OK_CANCEL_OPTION);
                        if (result2 == JOptionPane.OK_OPTION) {
                            // Create matrix based on user input for the second matrix
                            Matrix matrix2 = createMatrixFromInputPanel(inputPanel2, rows2, cols2);
    
                            // Perform the selected matrix operation
                            Matrix resultMatrix = null;
                            switch (operation) {
                                case "Multiply":
                                    // Perform multiplication operation
                                    resultMatrix = Matrix.multiply(matrix1, matrix2);
                                    break;
                                case "Divide":
                                    // Perform division operation
                                    SquareMatrix sq1 = SquareMatrix.makeSquare(matrix1);
                                    SquareMatrix sq2 = SquareMatrix.makeSquare(matrix2);
                                    resultMatrix = SquareMatrix.divide(sq1, sq2);
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Operation not implemented yet: " + operation);
                                    return;
                            }
    
                            // Display the result
                            if (resultMatrix != null) {
                                displayResultMatrix(resultMatrix,resultMatrix.getNumOfRows(),resultMatrix.getNumOfColumns());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
 
/////// 5 /////////////// 5 /////////////// 5 /////////////// 5 /////////////// 5 /////////////// 5 /////////////// 5 ////////
    
    private void fifthSetOperations(String operation) {
        try {
            // Create a panel to input row and column sizes
            JPanel sizePanel = new JPanel(new GridLayout(2, 2));
            sizePanel.add(new JLabel("Enter the number of rows:"));
            JTextField rowsField = new JTextField(5);
            sizePanel.add(rowsField);
            sizePanel.add(new JLabel("Enter the number of columns:"));
            JTextField colsField = new JTextField(5);
            sizePanel.add(colsField);

            // Show dialog to input row and column sizes
            int resultSize = JOptionPane.showConfirmDialog(null, sizePanel, "Enter the Matrix Size", JOptionPane.OK_CANCEL_OPTION);
            if (resultSize == JOptionPane.OK_OPTION) {
                int rows = Integer.parseInt(rowsField.getText());
                int cols = Integer.parseInt(colsField.getText());

                // Create a panel to input matrix values
                JPanel inputPanel = new JPanel(new GridLayout(rows, cols));
                JTextField[][] matrixFields = new JTextField[rows][cols];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        matrixFields[i][j] = new JTextField(5);
                        inputPanel.add(matrixFields[i][j]);
                    }
                }

                // Show dialog to input matrix values
                int result = JOptionPane.showConfirmDialog(null, inputPanel, "Fill in the Matrix Values", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    // Create matrix based on user input
                    double[][] matrixValues = new double[rows][cols];
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            try {
                                matrixValues[i][j] = Double.parseDouble(matrixFields[i][j].getText());
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Invalid input! Please enter numeric values.");
                                return;
                            }
                        }
                    }

                    // Perform the selected matrix operation
                    Matrix matrix = new Matrix(matrixValues);
                    double resultValue = 0;
                    switch (operation) {
                        case "Determinant":
                            SquareMatrix sqm = SquareMatrix.makeSquare(matrix);
                            resultValue = sqm.det();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Operation not implemented yet: " + operation);
                            return;
                    }

                    // Display the result
                    JOptionPane.showMessageDialog(null, "Result:\n" + resultValue);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }



}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
    static class VectorOperationActionListener implements ActionListener {
        private String operation;
    
        VectorOperationActionListener(String operation) {
            this.operation = operation;
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField vector1Field = new JTextField(20); // Text field for Vector 1
            JTextField vector2Field = new JTextField(20); // Text field for Vector 2
    
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
    
            inputPanel.add(new JLabel("Vector 1 (comma-separated values):"));
            inputPanel.add(vector1Field);
    
            inputPanel.add(new JLabel("Vector 2 (comma-separated values):"));
            inputPanel.add(vector2Field);
    
            int result = JOptionPane.showConfirmDialog(null, inputPanel, "Enter Vectors", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String[] vector1ValuesStr = vector1Field.getText().split(",");
                    double[] vector1Values = new double[vector1ValuesStr.length];
                    for (int i = 0; i < vector1ValuesStr.length; i++) {
                        vector1Values[i] = Double.parseDouble(vector1ValuesStr[i].trim());
                    }
    
                    String[] vector2ValuesStr = vector2Field.getText().split(",");
                    double[] vector2Values = new double[vector2ValuesStr.length];
                    for (int i = 0; i < vector2ValuesStr.length; i++) {
                        vector2Values[i] = Double.parseDouble(vector2ValuesStr[i].trim());
                    }
    
                    // Perform the selected vector operation
                    Vector vector1 = new Vector(vector1Values);
                    Vector vector2 = new Vector(vector2Values);
                    Vector resultVector = null;
    
                    switch (operation) {
                        // Implement cases for different vector operations
                        case "Add":
                            resultVector = vector1.add(vector2);
                            break;
                        case "Subtract":
                            resultVector = vector1.subtract(vector2);
                            break;
                        case "Cross Product":
                            Vector3D v1 = Vector.makeA3DVector(vector1);
                            Vector3D v2 = Vector.makeA3DVector(vector2);
                            resultVector = v1.crossProduct(v2);
                            break;
                        case "Calculate Vector Projection":
                            resultVector = vector1.calculateVectorProjection(vector2);
                            break;
                        case "Calculate Vector Rejection":
                            resultVector = vector1.calculateVectorProjection(vector2);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Operation not implemented yet: " + operation);
                    }
    
                    // Display the result
                    if (resultVector != null) {
                        JOptionPane.showMessageDialog(null, "Result Vector:\n" + resultVector.toString());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
    }
    
    
    

    


////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    static class ComplexVectorOperationActionListener implements ActionListener {
        private String operation;

        ComplexVectorOperationActionListener(String operation) {
            this.operation = operation;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Implement action for complex vector operation
            System.out.println("Complex Vector Operation: " + operation);
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    static class ComplexNumberOperationActionListener implements ActionListener {
        private String operation;

        ComplexNumberOperationActionListener(String operation) {
            this.operation = operation;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            // Create a new JFrame for the input window
            JFrame inputFrame = new JFrame("Complex Number Input");
            inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            inputFrame.setSize(800, 200);
            inputFrame.setLocationRelativeTo(null); // Center the window
        
            // Create a JPanel to hold input fields
            JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        
            // Create a JPanel for the first complex number input
            JPanel firstNumberPanel = new JPanel(new GridLayout(1, 7));
            firstNumberPanel.add(new JLabel("Complex Number 1 = "));
            JTextField real1Field = new JTextField(5);
            firstNumberPanel.add(real1Field);
            JComboBox<String> operator1Box = new JComboBox<>(new String[]{"+", "-"});
            firstNumberPanel.add(operator1Box);
            JTextField imag1Field = new JTextField(5);

            firstNumberPanel.add(imag1Field);
            firstNumberPanel.add(new JLabel("i"));           
            inputPanel.add(firstNumberPanel);
        
            // Create a JPanel for the second complex number input
            JPanel secondNumberPanel = new JPanel(new GridLayout(1, 7));
            secondNumberPanel.add(new JLabel("Complex Number 2 = "));
            JTextField real2Field = new JTextField(5);
            secondNumberPanel.add(real2Field);
            JComboBox<String> operator2Box = new JComboBox<>(new String[]{"+", "-"});
            secondNumberPanel.add(operator2Box);
            JTextField imag2Field = new JTextField(5);

            secondNumberPanel.add(imag2Field);
            secondNumberPanel.add(new JLabel("i"));
            inputPanel.add(secondNumberPanel);
            
            // If the operation is "Modulus of a Complex number" or "Conjugate of a Complex number",
            // hide the second complex number input panel
            if (operation.equals("Modulus of a Complex number") || operation.equals("Conjugate of a Complex number")) {
                secondNumberPanel.setVisible(false);
            }
        
            // Create a button to trigger calculation
            JButton calculateButton = new JButton("Calculate");
            calculateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Perform calculation
                    try {

                        double real1 = Double.parseDouble(real1Field.getText());
                        double imag1 = Double.parseDouble(imag1Field.getText());
                        double real2 ;
                        double imag2 ;
                        if (operation.equals("Modulus of a Complex number") || operation.equals("Conjugate of a Complex number")){
                            real2 = 0;
                            imag2 = 0;
                        }else{
                            real2 = Double.parseDouble(real2Field.getText());
                            imag2 = Double.parseDouble(imag2Field.getText());                           
                        }

        
                        // Apply the operator selected
                        if (operator1Box.getSelectedItem().equals("-")) {
                            imag1 *= -1; // Negate imaginary part if subtraction is selected
                        }
                        if (operator2Box.getSelectedItem().equals("-")) {
                            imag2 *= -1; // Negate imaginary part if subtraction is selected
                        }
        
                        ComplexNumber num1 = new ComplexNumber(real1, imag1);
                        ComplexNumber num2 = new ComplexNumber(real2, imag2);
        
                        // Perform operation
                        ComplexNumber result ;
                        double modResult=0 ;
                        if (operation.equals("Add")) {
                            result = num1.add(num2);
                        } else if (operation.equals("Subtract")) {
                            result = num1.subtract(num2);
                        } else if (operation.equals("Multiply")) {
                            result = num1.multiply(num2);
                        } else if (operation.equals("Modulus of a Complex number")) {
                            result =new  ComplexNumber(0,0);
                            modResult = num1.modulus();
                        } else if (operation.equals("Conjugate of a Complex number")) {
                            result = num1.conjugate();
                        } else {
                            // Handle other operations
                            result = new ComplexNumber(0, 0); // Placeholder
                        }
        
                        // Display the result
                        if (operation.equals("Modulus of a Complex number")) {
                            JOptionPane.showMessageDialog(inputFrame, "Result: " + modResult);
                            inputFrame.dispose(); // Close the input window after calculation
                        }else{
                            JOptionPane.showMessageDialog(inputFrame, "Result: " + result);
                            inputFrame.dispose(); // Close the input window after calculation                           
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(inputFrame, "Invalid input. Please enter valid numbers.");
                    }
                }
            });
        
            // Add input panel and calculate button to the frame
            inputFrame.add(inputPanel, BorderLayout.CENTER);
            inputFrame.add(calculateButton, BorderLayout.SOUTH);
        
            // Make the window visible
            inputFrame.setVisible(true);

        }
        



        

    }






}
