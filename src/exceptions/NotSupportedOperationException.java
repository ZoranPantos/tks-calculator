package exceptions;

/**
 * <h2>Exception caused by using unsupported operation</h2>
 * This exception is thrown when unsupported operation is provided.
 * 
 * @author zoran pantos
 * @version 1.0
 * @since 2021-02-12
 */
public class NotSupportedOperationException extends Exception {
	/**
	 * Instantiates exception object of type DivisionByZeroException.
	 */
	public NotSupportedOperationException() {
		super();
	}
	
	/**
	 * Instantiates exception object of type DivisionByZeroException.
	 * @param message exception message
	 */
	public NotSupportedOperationException(String message) {
		super(message);
	}
}