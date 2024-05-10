package am.aua.linalg.core; 

/**
 * Represents a complex number with real and imaginary parts.
 */
public class ComplexNumber { 

        // Instance variables
        private double real;
        private double imaginary;

        
        // Constructors
        /**
         * Constructs a complex number with the given real and imaginary parts.
         *
         * @param real      The real part of the complex number.
         * @param imaginary The imaginary part of the complex number.
        */
        public ComplexNumber(double real, double imaginary) {
            this.real = real;
            this.imaginary = imaginary;
        }


        // Getters and Setters
        /**
         * Gets the real part of the complex number.
         *
         * @return The real part of the complex number.
        */
        public double getReal() {
            return real;
        }

        /**
         * Gets the imaginary part of the complex number.
         *
         * @return The imaginary part of the complex number.
         */
        public double getImaginary() {
            return imaginary;
        }


        @Override
        public String toString() {
            String sign;
            if (imaginary>0){
                sign = " + ";
            }
            else{
                sign = " - ";
            }
            return "(" + real + sign + Math.abs(imaginary) + "i)";
        }
        
        
        // Methods
        /**
         * Adds another complex number to this complex number and returns the result.
         *
         * @param other The complex number to add.
         * @return The result of adding the other complex number to this complex number.
        */
        public ComplexNumber add(ComplexNumber other) {
            return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
        }


        /**
         * Subtracts another complex number from this complex number and returns the result.
         *
         * @param other The complex number to subtract.
         * @return The result of subtracting the other complex number from this complex number.
        */       
        public ComplexNumber subtract(ComplexNumber other) {
            return new ComplexNumber(this.real - other.real, this.imaginary - other.imaginary);
        }


        /**
         * Multiplies this complex number by another complex number and returns the result.
         *
         * @param other The complex number to multiply by.
         * @return The result of multiplying this complex number by the other complex number.
         */        
        public ComplexNumber multiply(ComplexNumber other) {
            double newReal = this.real * other.real - this.imaginary * other.imaginary;
            double newImaginary = this.real * other.imaginary + this.imaginary * other.real;
            return new ComplexNumber(newReal, newImaginary);
        }

        
        /**
         * Computes the conjugate of this complex number.
         *
         * @return The conjugate of this complex number.
         */        
        public ComplexNumber conjugate() {
            return new ComplexNumber(this.real, -this.imaginary);
        }


        /**
         * Computes the modulus of this complex number.
         *
         * @return The modulus of this complex number.
        */
        public double modulus() {
            return Math.sqrt(real * real + imaginary * imaginary);
        }


}
