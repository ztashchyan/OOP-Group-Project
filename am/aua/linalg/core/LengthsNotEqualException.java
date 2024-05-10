package am.aua.linalg.core;

public class LengthsNotEqualException extends Exception{

    public LengthsNotEqualException(){
        super("The lengths of the vectors are nor equal");

    }
    public LengthsNotEqualException(String message){
        super(message);
    }
}
