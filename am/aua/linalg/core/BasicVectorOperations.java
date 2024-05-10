package am.aua.linalg.core;



/**
 * The BasicVectorOperations interface defines basic vector operations.
 * These operations include vector addition, subtraction, and multiplication with a scalar.
 */
public interface BasicVectorOperations {

    /**
     * Adds another vector to this vector.
     *
     * @param other the vector to be added
     * @return the result of adding the other vector to this vector
     */
    Vector add(Vector other);

    /**
     * Subtracts another vector from this vector.
     *
     * @param other the vector to be subtracted
     * @return the result of subtracting the other vector from this vector
     */
    Vector subtract(Vector other);

    /**
     * Multiplies this vector by a scalar.
     *
     * @param scalar the scalar to multiply with
     * @return the result of multiplying this vector by the scalar
     */
    Vector multiplyWithAScalar(Scalar scalar);

    
}


