package calculators;
import exceptions.*;

/**
 * <h2>Calculator</h2>
 * This class simulates a simple calculator which encapsulates a single variable named <strong>currentValue</strong>.
 * <strong>currentValue</strong> represents a result produced by performing various arithmetic operations.
 * Supported arithmetic operations: addition, subtraction, multiplication, division.
 * 
 * @author zoran pantos
 * @version 1.0
 * @since 2021-2-12
 */
public class Calculator {
	/**
	 * Starting value which is also used for persisting arithmetic operation result.
	 */
	protected Double currentValue;
	
	/**
	 * Instantiates object of type Calculator and sets starting value to 0.
	 */
	public Calculator() {
		currentValue = 0.0;
	}
	
	/**
	 * Used for fetching starting value of Calculator class or arithmetic operation result if one is executed.
	 * @return currentValue
	 */
	public Double getCurrentValue() {
		return currentValue;
	}
	
	/**
	 * Used for setting starting value of Calculator class.
	 * @param currentValue starting value upon which arithmetic operation will be performed
	 */
	public void setCurrentValue(Double currentValue) {
		this.currentValue = currentValue;
	}
	
	/**
	 * Used for performing given arithmetic operation with <strong>currentValue</strong> as first operand and <strong>value</strong> as second operand. 
	 * @param value second operand
	 * @param operator arithmetic operation to be executed
	 * @throws DivisionByZeroException if <strong>value</strong> is 0
	 * @throws NotSupportedOperationException if <strong>operator</strong> is not from set { +, -, *, / }
	 */
	public void calculate(Double value, char operator) throws DivisionByZeroException, NotSupportedOperationException {
		switch (operator) {
		case '+':
			currentValue += value;
			break;
		case '-':
			currentValue -= value;
			break;
		case '*':
			currentValue *= value;
			break;
		case '/':
			if (value == 0)
				throw new DivisionByZeroException("Division with 0 attempted");
			else
				currentValue /= value;
			break;
		default:
			throw new NotSupportedOperationException("Operation not supported");
		}
	}
}
