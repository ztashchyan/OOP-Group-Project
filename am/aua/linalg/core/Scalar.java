package am.aua.linalg.core;

/**
 * The Scalar class represents a scalar value.
 * Scalars are single-dimensional quantities without direction.
 */
public class Scalar {

    // Instance variable
    private double value;

    // Constructor
    public Scalar(){
    }

    /**
     * Constructs a Scalar object with the specified value.
     *
     * @param value the value of the scalar
    */
    public Scalar(double value){
        this.value = value;
    }

    /**
     * Gets the value of this scalar.
     *
     * @return the value of this scalar
    */
    public double getValue(){
        return this.value ;
    }


}
