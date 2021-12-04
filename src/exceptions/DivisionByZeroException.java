package exceptions;

/**
 * <h2>Exception caused by dividing with zero</h2>
 * This exception is thrown when division by zero is attempted.
 * 
 * @author zoran pantos
 * @version 1.0
 * @since 2021-02-12
 */
public class DivisionByZeroException extends Exception {
	/**
	 * Instantiates exception object of type DivisionByZeroException.
	 */
	public DivisionByZeroException() {
		super();
	}
	
	/**
	 * Instantiates exception object of type DivisionByZeroException.
	 * @param message exception message
	 */
	public DivisionByZeroException(String message) {
		super(message);
	}
}