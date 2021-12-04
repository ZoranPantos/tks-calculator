package exceptions;

/**
 * <h2>Exception caused by number not being in expected range</h2>
 * This exception is thrown when a number out of set range is provided.
 * 
 * @author zoran pantos
 * @version 1.0
 * @since 2021-02-12
 */
public class NumberNotInAreaException extends Exception {
	/**
	 * Instantiates exception object of type DivisionByZeroException.
	 */
	public NumberNotInAreaException() {
		super();
	}
	
	/**
	 * Instantiates exception object of type DivisionByZeroException.
	 * @param message exception message
	 */
	public NumberNotInAreaException(String message) {
		super(message);
	}
}