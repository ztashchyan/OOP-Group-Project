package am.aua.linalg.core;

/**
 * Represents a vector and implements basic vector operations.
 */ 
public class Vector implements BasicVectorOperations, Comparable<Vector> {

    // Instance variables
    private double[] values;


    // Constructors
    /**
    * Constructs an empty vector.
    */
    public Vector() {
        this.values = new double[0];
    }
    
    /**
     * Constructs a vector with the specified values.
     *
     * @param values the values to initialize the vector
     */
    public Vector(double... values) {
        this.values = Vector.copyElements(values);
    }

    // Copy constructor
    /**
     * Constructs a vector that is a copy of the specified vector.
     *
     * @param other the vector to copy
    */
    public Vector(Vector other) {
        this.values = Vector.copyElements(other.values);
    }   
    
    

    // Accessors, modifiers, helpfull methods
    /**
     * Returns a copy of the elements of this vector.
     *
     * @return a copy of the elements of this vector
    */
    public double[] getElements() {
        return copyElements(values);
    }

    /**
     * Creates a copy of the given array of elements.
     *
     * @param elements the array of elements to be copied
     * @return a copy of the array of elements
     */
    public static double[] copyElements(double[] elements) {
        double[] copy = new double[elements.length];
        for (int i = 0; i < elements.length; i++) {
            copy[i] = elements[i];
        }
        return copy;
    }


    public boolean equals(Object object) {
        if (object == null || !(object instanceof Vector))
            return false;
        else {
            Vector otherVector = (Vector) object;
            return this.values.equals(otherVector.values);
        }
    }




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < values.length; i++) {
            sb.append(values[i]);
            sb.append(", ");
        }
        sb.append(" ]\n"); 
        return sb.toString();
    }
    
    

    public boolean isZero() {
        for (int i = 0; i < values.length; i++) {
            if(getElements()[i] != 0) {
                return false;
            }
        }
        return true;
    }


    public int compareTo(Vector other) {
        double thisMagnitude = this.calculateMagnitude();
        double otherMagnitude = other.calculateMagnitude();

        if (thisMagnitude < otherMagnitude) {
            return -1;
        } else if (thisMagnitude > otherMagnitude) {
            return 1;
        } else {
            return 0;
        }
    }

    
    public static Vector3D makeA3DVector(Vector v) throws Exception{
        if (v.getElements().length != 3){
            throw new Exception("Cannot downcast to Vector3D");
        }
        double[] vector = v.getElements();
        return new Vector3D(vector);
    }

    
    // Methods

    // Add
    /**
     * Adds the elements of the given vector to this vector element-wise and returns the result as a new vector.
     *
     * @param other the vector to be added to this vector
     * @return a new vector containing the element-wise sum of this vector and the given vector
     * @throws IllegalArgumentException if the lengths of the vectors are different
     */
    public Vector add(Vector other) {
        double[] newVector = new double[other.values.length];
        for (int i = 0; i < other.values.length; i++) {
            newVector[i] = other.values[i] + this.values[i];
        }
        return new Vector(newVector);
    }


    // Subtruct
    /**
     * Subtracts the elements of the given vector from this vector element-wise and returns the result as a new vector.
     *
     * @param other the vector to be subtracted from this vector
     * @return a new vector containing the element-wise difference of this vector and the given vector
     * @throws IllegalArgumentException if the lengths of the vectors are different
    */
    public Vector subtract(Vector other) {
        double[] newVector = new double[other.values.length];
        for (int i = 0; i < other.values.length; i++) {
            newVector[i] = this.values[i] - other.values[i];
        }
        return new Vector(newVector);
    }


    // multiplyWithAScalar
    /**
     * Multiplies each element of this vector by the scalar value and returns the result as a new vector.
     *
     * @param scalar the scalar value to multiply with
     * @return a new vector containing the elements of this vector multiplied by the scalar value
    */
    public Vector multiplyWithAScalar(Scalar scalar) {
        double[] newVector = new double[this.values.length];
        for (int i = 0; i < this.values.length; i++) {
            newVector[i] = this.values[i] * scalar.getValue();
        }
        return new Vector(newVector);
    }


    public static Vector add(Vector vector1, Vector vector2) {
        return vector1.add(vector2);
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        return vector1.subtract(vector2);
    }

    public static Vector multiplyWithAScalar(Scalar scalar, Vector vector) {
        return vector.multiplyWithAScalar(scalar);
    }



    // Dot product
    /**
     * Computes the dot product of this vector with another vector.
     *
     * @param other the vector to compute the dot product with
     * @return the dot product of this vector with the other vector
     * @throws LengthsNotEqualException if the lengths of the two vectors are not equal
    */
    public double dot(Vector other) throws LengthsNotEqualException {
        if (other.values.length != this.values.length) {
            throw new LengthsNotEqualException();
        }
        double sum = 0;
        for (int i = 0; i < other.values.length; i++) {
            sum += other.values[i] * getElements()[i];
        }
        return sum;

    }

    public static double dot(Vector vector1, Vector vector2) throws LengthsNotEqualException{
        return vector1.dot(vector2);
    }


    // Magnitude
    /**
     * Calculates the magnitude (length) of this vector.
     *
     * @return the magnitude of this vector
     */
    public double calculateMagnitude() {
        double sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += Math.pow(values[i], 2);
        }
        return Math.sqrt(sum);
    }
    

    public static double calculateMagnitude(Vector other) {
        return other.calculateMagnitude();
    }


    // Angle between two vectors
    /**
     * Calculates the angle between this vector and another vector.
     *
     * @param other the other vector
     * @return the angle between this vector and the other vector in degrees
     * @throws LengthsNotEqualException if the lengths of the two vectors are not equal
     */
    public double calculateAngle(Vector other) throws LengthsNotEqualException{
        double cos = dot(other) / (calculateMagnitude() * other.calculateMagnitude());
        double angle = Math.acos(cos);
        return Math.toDegrees(angle);
    }

    public static double calculateAngle(Vector vector1, Vector vector2) throws LengthsNotEqualException{
        return vector1.calculateAngle(vector2);
    }


    // Unit vector
    /**
     * Calculates the unit vector of this vector.
     *
     * @return the unit vector of this vector
    */
    public Vector calculateUnitVector() {
        double[] unitVector = new double[this.values.length];
        for (int i = 0; i < this.values.length; i++) {
            unitVector[i] = getElements()[i] / calculateMagnitude();
        }
        return new Vector(copyElements(unitVector));
    }

    public static Vector calculateUnitVector(Vector vector) {
        return vector.calculateUnitVector();
    }



    //Scalar Projection
    /**
     * Calculates the scalar projection of this vector onto another vector.
     *
     * @param other the vector onto which the projection is calculated
     * @return the scalar projection of this vector onto the other vector
     * @throws LengthsNotEqualException if the lengths of the two vectors are not equal
    */
    public double calculateScalarProjection(Vector other) throws LengthsNotEqualException {
        return dot(other) / calculateMagnitude();

    }

    public static double calculateScalarProjection(Vector vector1, Vector vector2) throws LengthsNotEqualException {
        return vector1.calculateScalarProjection(vector2);//Vector1 onto Vector2
    }


    
    // Vector Projection
    /**
     * Calculates the vector projection of this vector onto another vector.
     *
     * @param other the vector onto which the projection is calculated
     * @return the vector projection of this vector onto the other vector
     * @throws LengthsNotEqualException if the lengths of the two vectors are not equal
    */
    public Vector calculateVectorProjection(Vector other) throws LengthsNotEqualException {
        double calculation = dot(other) / Math.pow(calculateMagnitude(), 2);
        double[] vector = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            vector[i] = getElements()[i] * calculation;
        }
        return new Vector(copyElements(vector));
    }

    public static Vector calculateVectorProjection(Vector vector1, Vector vector2) throws LengthsNotEqualException {
        return vector1.calculateVectorProjection(vector2);
    }


    // Vector rejection
    /**
     * Calculates the vector rejection of this vector from another vector.
     *
     * @param other the vector from which the rejection is calculated
     * @return the vector rejection of this vector from the other vector
     * @throws LengthsNotEqualException if the lengths of the two vectors are not equal
    */
    public Vector calculateVectorRejection(Vector other) throws LengthsNotEqualException {
        Vector projection = calculateVectorProjection(other);
        Vector projectionVector = new Vector(projection);
        Vector rejectionVector = subtract(projectionVector);
        return new Vector(copyElements(rejectionVector.values));
    }
    public static Vector calculateVectorRejection(Vector vector1, Vector vector2) throws LengthsNotEqualException{
        return vector1.calculateVectorRejection(vector2);
    }




    // Is parallel
    /**
     * Checks if this vector is parallel to another vector.
     *
     * @param other the vector to compare with
     * @return true if this vector is parallel to the other vector, false otherwise
     * @throws LengthsNotEqualException if the lengths of the two vectors are not equal
    */
    public boolean isParallelTo(Vector other) throws LengthsNotEqualException {
        return calculateAngle(other) == Math.toDegrees(0);
    }
    public static boolean isParallelTo(Vector vector1, Vector vector2)throws LengthsNotEqualException{
        return vector1.isParallelTo(vector2);
    }



    // Is orthogonal
    /**
     * Checks if this vector is orthogonal (perpendicular) to another vector.
     *
     * @param other the vector to compare with
     * @return true if this vector is orthogonal to the other vector, false otherwise
     * @throws LengthsNotEqualException if the lengths of the two vectors are not equal
    */
    public boolean isOrthogonal(Vector other) throws LengthsNotEqualException{
        return calculateAngle(other) == Math.toDegrees(90);
    }
    public static boolean isOrthogonal(Vector vector1, Vector vector2) throws LengthsNotEqualException{
        return vector1.isOrthogonal(vector2);
    }
 
    
    




}