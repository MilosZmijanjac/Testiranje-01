package code.exceptions;


/**
 * Signals that an attempt to divide a number with 0 is made. Division by zero is undefined.
 *
 * @author Miloš
 * @version 1.0
 * @since 1.0
 */
public class DivisionByZeroException extends Exception{
    /**
     * Constructs a DivisionByZeroException with null as its error detail message.
     */
    public DivisionByZeroException(){
        super();
    }

    /**
     * Constructs a DivisionByZeroException with the specified detail message.
     * @param message Represents a detailed message.
     */
    public DivisionByZeroException(String message){
        super(message);
    }

}
