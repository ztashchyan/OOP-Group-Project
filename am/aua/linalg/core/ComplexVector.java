package am.aua.linalg.core; 

/**
 * Represents a vector of complex numbers.
 */
public class ComplexVector { 

    // Instance variable
    private ComplexNumber[] elements;


    //Constructor
    public ComplexVector(ComplexNumber... values){
        this.elements= values.clone();
    }

    /**
     * Retrieves the array of complex numbers.
     *
     * @return The array of complex numbers.
     */
    public ComplexNumber[] getElements() {
        return this.elements;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < elements.length; i++) {
            sb.append(elements[i]);
            if (i < elements.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }




    // Methods
    /**
     * Adds another ComplexVector to this ComplexVector element-wise.
     *
     * @param other The ComplexVector to be added to this ComplexVector.
     * @return A new ComplexVector representing the sum of this ComplexVector and the other ComplexVector.
     * @throws IllegalArgumentException if the other ComplexVector has a different length than this ComplexVector.
    */
    public ComplexVector add(ComplexVector other){
        ComplexNumber[] newVector = new ComplexNumber[other.elements.length];
        for (int i = 0; i < other.elements.length; i++) {
            newVector[i] = other.elements[i].add(this.elements[i]);
        }
        return new ComplexVector(newVector);
    }

    /**
     * Subtracts another ComplexVector from this ComplexVector element-wise.
     *
     * @param other The ComplexVector to be subtracted from this ComplexVector.
     * @return A new ComplexVector representing the result of subtracting the other ComplexVector from this ComplexVector.
     * @throws IllegalArgumentException if the other ComplexVector has a different length than this ComplexVector.
     */
    public ComplexVector subtract(ComplexVector other) {
        ComplexNumber[] newVector = new ComplexNumber[other.elements.length];
        for (int i = 0; i < other.elements.length; i++) {
            newVector[i] = this.elements[i].subtract(other.elements[i]);
        }
        return new ComplexVector(newVector);
    }

    /**
     * Multiplies each element of this ComplexVector by a ComplexNumber scalar.
     *
     * @param scalar The ComplexNumber scalar to multiply each element by.
     * @return A new ComplexVector representing the result of multiplying each element of this ComplexVector by the scalar.
     */    
    public ComplexVector multiplyWithAComplexScalar(ComplexNumber scalar) {
        
        ComplexNumber[] newVector = new ComplexNumber[this.elements.length];
        for (int i = 0; i < this.elements.length; i++) {
            newVector[i] = this.elements[i].multiply(scalar);
        }
        return new ComplexVector(newVector);
    }


    /**
     * Returns a new ComplexVector containing the conjugate of each element in this ComplexVector.
     *
     * @return A new ComplexVector representing the conjugate of each element in this ComplexVector.
     */
    public ComplexVector conjugate() {
        ComplexNumber[] conjugates = new ComplexNumber[this.elements.length];
        for (int i = 0; i < this.elements.length; i++) {
            conjugates[i] = this.elements[i].conjugate();
        }
        return new ComplexVector(conjugates);
    }

    /**
     * Computes the magnitude (Euclidean norm) of this ComplexVector.
     *
     * @return The magnitude of this ComplexVector.
    */
    public double magnitude() {
        double sum = 0;
        for (ComplexNumber el : elements) {
            sum += el.modulus() * el.modulus();
        }
        return Math.sqrt(sum);
    }

    /**
     * Computes the dot product of this ComplexVector with another ComplexVector.
     *
     * @param other The other ComplexVector to compute the dot product with.
     * @return The dot product of this ComplexVector with the other ComplexVector.
    */
    public ComplexNumber dotProduct(ComplexVector other) {
        ComplexNumber sum = new ComplexNumber(0, 0);
        for (int i = 0; i < this.elements.length; i++) {
            sum = sum.add(this.elements[i].multiply(other.elements[i].conjugate()));
        }
        return sum;
    }

    /**
     * Multiplies each element of this ComplexVector by a scalar ComplexNumber.
     *
     * @param scalar The scalar ComplexNumber to multiply each element by.
     * @return A new ComplexVector resulting from the scalar multiplication.
    */
    public ComplexVector scalarMultiplication(ComplexNumber scalar) {
        ComplexNumber[] result = new ComplexNumber[this.elements.length];
        for (int i = 0; i < this.elements.length; i++) {
            result[i] = this.elements[i].multiply(scalar);
        }
        return new ComplexVector(result);
    }

}
