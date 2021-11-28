package code.exceptions;

/**
 * Signals that an attempt to use the number from the specified area failed.
 *
 * @author Miloš
 * @version 1.0
 * @since 1.0
 */
public class NumberNotInAreaException extends Exception {

    /**
     * Constructs a NumberNotInAreaException with null as its error detail message.
     */
    public NumberNotInAreaException(){
        super();
    }

    /**
     * Constructs a NumberNotInAreaException with the specified detail message.
     * @param message Represents a detailed message.
     */
    public NumberNotInAreaException(String message){
        super(message);
    }
}
