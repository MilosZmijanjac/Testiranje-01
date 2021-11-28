package code.exceptions;

/**
 * Signals that an attempt to use the operation  failed.
 *
 * @author Miloš
 * @version 1.0
 * @since 1.0
 */
public class NotSupportedOperationException extends Exception{
    /**
     * Constructs a NotSupportedOperationException with null as its error detail message.
     */
    public NotSupportedOperationException(){
        super();
    }

    /**
     * Constructs a NotSupportedOperationException with the specified detail message.
     * @param message Represents a detailed message.
     */
    public NotSupportedOperationException(String message){
        super(message);
    }
}
