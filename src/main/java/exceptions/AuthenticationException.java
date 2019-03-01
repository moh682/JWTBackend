package exceptions;

/**
 *
 * @author RasmusFriis
 */
public class AuthenticationException extends Exception{

    /**
     *
     * @param message
     */
    public AuthenticationException(String message) {
        super(message);
    }

    /**
     *
     */
    public AuthenticationException() {
        super("Could not be Authenticated");
    }  
}
