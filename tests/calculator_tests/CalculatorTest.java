package calculator_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.core.IsNull.*;

import calculators.Calculator;
import exceptions.DivisionByZeroException;
import exceptions.NotSupportedOperationException;

/**
 * <h2>CalculatorTest</h2>
 * This class is used for testing Calculator class and all of its methods.
 * 
 * @author zoran pantos
 * @version 1.0
 * @since 2021-2-12
 */
@DisplayName("Calculator test class")
public class CalculatorTest {
	/**
	 * This (new) instance will be used in every test.
	 */
	private Calculator calculator = new Calculator();

	/**
	 * Tests if calculator instance is not null.
	 */
	@Test
	@DisplayName("Null reference test")
	public void testCalculator() {
		assertThat(calculator, notNullValue());
	}

	/**
	 * Tests if starting value of a Calculator class is 0.
	 */
	@Test
	@DisplayName("Get value test")
	public void testGetCurrentValue() {
		assertThat(calculator.getCurrentValue(), is(Double.valueOf(0.0)));
	}

	/**
	 * Tests the correct setting up starting value of Calculator class.
	 */
	@Test
	@DisplayName("Set value test")
	public void testSetCurrentValue() {
		calculator.setCurrentValue(1.0);
		assertThat(calculator.getCurrentValue(), is(Double.valueOf(1.0)));
	}
	
	/**
	 * Used for parameterized tests.
	 * Tests when exceptions should be thrown.
	 * @param exception will be of type DivisionByZeroException or NotSupportedOperationException
	 * @param value value
	 * @param operation operation for execution
	 * @param message message for exception
	 */
	@DisplayName("Exceptions test")
	@ParameterizedTest
	@MethodSource("methodExceptionWithParameters")
	public void testExceptionsParameterized(Exception exception, Double value, char operation, String message) {
		Exception exc = assertThrows(exception.getClass(), () -> calculator.calculate(value, operation));
		assertThat(exc.getMessage(), containsString(message));
	}
	
	/**
	 * Used for parameterized tests.
	 * @return stream of Arguments
	 */
	private static Stream<Arguments> methodExceptionWithParameters() {
		return Stream.of(
				Arguments.of(new DivisionByZeroException(), 0.0, '/', "Division with 0 attempted"),
				Arguments.of(new NotSupportedOperationException(), 1.0, 'x', "Operation not supported")
				);
	}
	
	/**
	 * Used for parameterized tests.
	 * Tests execution of arithmetic operations.
	 * @param baseValue base value for arithmetic operation
	 * @param factor factor
	 * @param operation arithmetic operation
	 * @param result result of arithmetic operation
	 * @throws DivisionByZeroException thrown when factor is 0 and operation is division
	 * @throws NotSupportedOperationException thrown when operation is not from { +, -, *, / }
	 */
	@DisplayName("Standard arithmetic operations test")
	@ParameterizedTest
	@MethodSource("methodOperationsWithParameters")
	public void testCalculateParameterized(Double baseValue, Double factor, char operation, Double result) throws DivisionByZeroException, NotSupportedOperationException {
		calculator.setCurrentValue(baseValue);
		calculator.calculate(factor, operation);
		assertThat(calculator.getCurrentValue(), is(result));
	}
	
	/**
	 * Used for parameterized tests.
	 * @return stream of Arguments
	 */
	private static Stream<Arguments> methodOperationsWithParameters() {
		return Stream.of(
				Arguments.of(1.0, 9.0, '+', 10.0),
				Arguments.of(0.0, -5.0, '+', -5.0),
				Arguments.of(-1.0, 1.0, '+', 0.0),
				Arguments.of(-4.0, -4.0, '+', -8.0),
				Arguments.of(1.0, 1.0, '-', 0.0),
				Arguments.of(0.0, -5.0, '-', 5.0),
				Arguments.of(-5.0, 5.0, '-', -10.0),
				Arguments.of(-5.0, -5.0, '-', 0.0),
				Arguments.of(2.0, 2.0, '*', 4.0),
				Arguments.of(1.0, -1.0, '*', -1.0),
				Arguments.of(-1.0, 1.0, '*', -1.0),
				Arguments.of(-1.0, -2.0, '*', 2.0),
				Arguments.of(6.0, 2.0, '/', 3.0),
				Arguments.of(25.0, -5.0, '/', -5.0),
				Arguments.of(-25.0, 5.0, '/', -5.0),
				Arguments.of(25.0, 5.0, '/', 5.0)
				);
	}
}