package am.aua.linalg.core;

public class InappropriateLengthException extends Exception{

    public InappropriateLengthException(){
        super("Please enter the correct length, this operation does not work with vectors of this size");
    }
    public InappropriateLengthException(String message){
        super(message);
    }
}
