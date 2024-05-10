package am.aua.linalg.core; 

/**
 * The Vector3D class represents a three-dimensional vector in space.
 * It extends the Vector class and implements the Cloneable interface.
 */
public class Vector3D extends Vector implements  Cloneable {
    
    // Instance variables
    private double x = this.getElements()[0];
    private double y = this.getElements()[1];
    private double z = this.getElements()[2];



    // Constructors
    /**
     * Constructs a new Vector3D with default values for its components.
     * The default values are (0, 0, 0).
     */
    public Vector3D() { 
        super(0,0,0); 
    }


    /**
     * Constructs a new Vector3D with the specified values.
     * The values must contain exactly three elements representing the x, y, and z components of the vector.
     *
     * @param values an array of doubles representing the x, y, and z components of the vector
     * @throws IllegalArgumentException if the number of elements in the values array is not equal to three
    */
    public Vector3D(double... values) {
        super(values);
        if (values.length != 3) {
            throw new IllegalArgumentException("Vector3D must have exactly 3 elements.");
        }

    }

    /**
     * Constructs a new Vector3D that is a copy of the specified Vector3D.
     *
     * @param other the Vector3D to be copied
    */   
    public Vector3D(Vector3D other) {
        super(other); 
    }



    // Accessors
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    
    public Object clone() {
        try {
            Vector3D copy = (Vector3D)super.clone();
                return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    


    // Methods

    // CrossProduct
    /**
     * Computes the cross product of this Vector3D with another Vector3D.
     * The cross product of two vectors results in a vector perpendicular to both input vectors.
     *
     * @param other the Vector3D to compute the cross product with
     * @return a new Vector3D representing the cross product of this vector and the other vector
     * @throws LengthsNotEqualException if the two vectors have different dimensions
    */
    public Vector3D crossProduct(Vector3D other) throws LengthsNotEqualException {
        if (this.getElements().length != other.getElements().length){
            throw new LengthsNotEqualException();
        }

        double x = (this.getY() * other.getZ()) - (this.getZ() * other.getY());
        double y = (this.getZ() * other.getX()) - (this.getX() * other.getZ());
        double z = (this.getX() * other.getY()) - (this.getY() * other.getX());
        return new Vector3D(x, y, z); 
    }

    public static Vector3D crossProduct(Vector3D vector1, Vector3D vector2) throws LengthsNotEqualException {
        return vector1.crossProduct(vector2);
    }



    // areaOfTriangle
    /**
     * Computes the area of the triangle formed by this Vector3D and another Vector3D.
     * The area is calculated as half the magnitude of the cross product of the two vectors.
     *
     * @param vector2 the second Vector3D forming the triangle with this Vector3D
     * @return the area of the triangle formed by the two vectors
     * @throws LengthsNotEqualException if the two vectors have different dimensions
    */
    public double areaOfTriangle(Vector3D vector2) throws LengthsNotEqualException {

            Vector perpendicularVector = this.crossProduct(vector2);
            double areaOfAParrallelogram = perpendicularVector.calculateMagnitude();
            double area = 0.5 * areaOfAParrallelogram;
            return area;      

    }


    



}