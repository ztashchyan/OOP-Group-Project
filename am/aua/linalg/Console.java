package am.aua.linalg;

import am.aua.linalg.core.*;

public class Console { 
    public static void main(String[] args) {
        
        // TEST OF MATRICES 
        Matrix A1 = new Matrix(3,3,1,2,3,4,5,6,7,8,9);
        Matrix B1 = new Matrix(3,3,4,5,4,5,6,3,4,5,6);
        Matrix C = new Matrix(3,2,1,1,1,2,2,2);
        SquareMatrix A = new SquareMatrix(3,1,2,3,4,5,6,7,8,9);
        SquareMatrix B = new SquareMatrix(3,4,5,4,5,6,3,4,5,6);
        SquareMatrix S = new SquareMatrix(3);
        DiagonalMatrix D = new DiagonalMatrix(3,1,2,3);
        IdentityMatrix I = new IdentityMatrix(4);
        LowerTriangularMatrix L = new LowerTriangularMatrix(3, 1,2,3,4,5,6);
        UpperTriangularMatrix U = new UpperTriangularMatrix(3, 5,2,3,4,7,6);               
        Scalar a = new Scalar(3);
 
            
            System.out.println(A);
            System.out.println(A.equals(B));
            System.out.println(Matrix.add(A,B));
            System.out.println(A.add(B));
            System.out.println(Matrix.subtract(A,B));
            System.out.println(A.subtract(B));
            System.out.println(Matrix.scalarMultiply(A,a));
            System.out.println(A.scalarMultiply(a));
            System.out.println(Matrix.multiply(A,B));
            System.out.println(A.multiply(B));
            System.out.println(C.transpose());
            System.out.println(SquareMatrix.ref(B));
            System.out.println(Matrix.ref(B1));
            System.out.println(SquareMatrix.rref(B)); 
            System.out.println(Matrix.rref(B1));           
            System.out.println(B.inverse());
            System.out.println(SquareMatrix.divide(A,B));
            System.out.println(SquareMatrix.power(B,-2));
            System.out.println(B.det());





        
        // TEST OF VECTORS              
        Vector vector1 = new Vector(1,2,3);
        Vector vector2 = new Vector(4,5,6);
        Vector vector1b = new Vector(8,0);
        Vector vector2b = new Vector(0,7);
            System.out.println(vector1.add(vector2));
            System.out.println(vector1.subtract(vector2));
            System.out.println(vector1.multiplyWithAScalar(a));
            System.out.println(vector1b.calculateMagnitude());
            System.out.println(vector1.calculateUnitVector());

            try{
                System.out.println(vector1.dot(vector2));
                System.out.println(vector1b.calculateAngle(vector2b));
                System.out.println(vector1.calculateScalarProjection(vector2));
                System.err.println(vector1.calculateVectorProjection(vector2));
                System.err.println(vector1.calculateVectorRejection(vector2));
                System.err.println(vector1.isParallelTo(vector2));
                System.err.println(vector1.isOrthogonal(vector2));
            }catch( LengthsNotEqualException e){
                System.err.println(e.getMessage());

            }





        Vector3D vector3 = new Vector3D(7, 8, 9);
        Vector3D vector4 = new Vector3D(10, 11, 12);   
            try{

                System.out.println(vector3.crossProduct(vector4));
                System.out.println(vector3.areaOfTriangle(vector4));
            }catch(LengthsNotEqualException e){
                System.out.println(e.getMessage());

            }




            
        ComplexNumber b = new ComplexNumber(1, 3);
        ComplexNumber d = new ComplexNumber(5, 5);
            System.out.println(b.add(d));
            System.out.println(b.subtract(d));
            System.out.println(b.multiply(d));
            System.out.println(b.modulus());
            System.out.println(d.conjugate());




        ComplexVector vector5 = new ComplexVector(b,d);
        ComplexVector vector6 = new ComplexVector(d,b);   
            System.out.println(vector5.add(vector6));
            System.out.println(vector5.subtract(vector6));
            System.out.println(vector5.scalarMultiplication((d)));
            System.out.println(vector5.dotProduct(vector6));
            System.out.println(vector5.magnitude());
            System.out.println(vector5.conjugate());


    }
    
}
