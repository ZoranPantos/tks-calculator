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

import calculators.CalculatorAdvanced;
import exceptions.NotSupportedOperationException;
import exceptions.NumberNotInAreaException;

/**
 * <h2>CalculatorAdvancedTest</h2>
 * This class is used for testing CalculatorAdvanced class and all of its methods.
 * 
 * @author zoran pantos
 * @version 1.0
 * @since 2021-2-12
 */
public class CalculatorAdvancedTest {
	/**
	 * This (new) instance will be used in every test.
	 */
	private CalculatorAdvanced calculatorAdvanced = new CalculatorAdvanced();

	/**
	 * Tests if calculatorAdvanced instance is not null.
	 */
	@Test
	@DisplayName("Null reference test")
	public void testCalculatorAdvanced() {
		assertThat(calculatorAdvanced, notNullValue());
	}
	
	/**
	 * Used for parameterized tests.
	 * Tests calculation of power and factorial.
	 * @param currentValue currentValue
	 * @param action action to be performed
	 * @param expectedResult expected result
	 * @throws NumberNotInAreaException thrown when number is not in area
	 * @throws NotSupportedOperationException thrown when operation is not supported
	 */
	@DisplayName("Advanced calculation test")
	@ParameterizedTest
	@MethodSource("advancedCalculationWithParameters")
	public void testAdvancedCalculationParameterized(Double currentValue, char action, Double expectedResult) throws NumberNotInAreaException, NotSupportedOperationException {
		calculatorAdvanced.setCurrentValue(currentValue);
		calculatorAdvanced.calculateAdvanced(action);
		assertThat(calculatorAdvanced.getCurrentValue(), is(expectedResult));
	}
	
	/**
	 * Used for parameterized tests.
	 * @return stream of Arguments
	 */
	private static Stream<Arguments> advancedCalculationWithParameters() {
		return Stream.of(
				Arguments.of(0.0, '!', 1.0),
				Arguments.of(10.0, '!', 3628800.0),
				Arguments.of(0.0, '0', 1.0),
				Arguments.of(1.0, '0', 1.0),
				Arguments.of(8.0, '2', 64.0),
				Arguments.of(2.0, '3', 8.0),
				Arguments.of(2.0, '9', 512.0),
				Arguments.of(3.2, '3', 3.2 * 3.2 * 3.2),
				Arguments.of(5.5, '1', 5.5),
				Arguments.of(8.9, '2', 8.9 * 8.9)
				);
	}
	
	/**
	 * Used for parameterized tests.
	 * Tests if provided number is Armstrong or perfect.
	 * @param currentValue currentValue
	 * @param value characteristic test
	 * @param expectedResult expected result
	 * @throws NotSupportedOperationException thrown when operation is not supported
	 * @throws NumberNotInAreaException thrown when number is not in area
	 */
	@DisplayName("Characteristic test")
	@ParameterizedTest
	@MethodSource("hasCharacteristicWithParameters")
	public void testCharacteristicParameterized(Double currentValue, char value, boolean expectedResult) throws NotSupportedOperationException, NumberNotInAreaException {
		calculatorAdvanced.setCurrentValue(currentValue);
		boolean testResult = calculatorAdvanced.hasCharacteristic(value);
		assertThat(testResult, is(expectedResult));
	}
	
	/**
	 * Used for parameterized tests.
	 * @return stream of Arguments
	 */
	private static Stream<Arguments> hasCharacteristicWithParameters() {
		return Stream.of(
				Arguments.of(1.0, 'A', true),
				Arguments.of(9.0, 'A', true),
				Arguments.of(153.0, 'A', true),
				Arguments.of(370.0, 'A', true),
				Arguments.of(371.0, 'A', true),
				Arguments.of(10.0, 'A', false),
				Arguments.of(152.0, 'A', false),
				Arguments.of(154.0, 'A', false),
				Arguments.of(6.0, 'P', true),
				Arguments.of(28.0, 'P', true),
				Arguments.of(5.0, 'P', false),
				Arguments.of(7.0, 'P', false)
				);
	}
	
	/**
	 * Used for parameterized tests.
	 * Tests exceptions that can be thrown.
	 * @param exception will be of type NumberNotInAreaException or NotSupportedOperationException
	 * @param ch number or letter
	 * @param message message for exception
	 * @param currentValue currentValue
	 * @param methodFlag flag for calling calculateAdvanced or hasCharacteristic method
	 */
	@DisplayName("Exceptions test")
	@ParameterizedTest
	@MethodSource("methodExceptionsInAdvancedWithParameters")
	public void testExceptionsInAdvancedParameterized(Exception exception, char ch, String message, Double currentValue, int methodFlag) {
		Exception exc;
		calculatorAdvanced.setCurrentValue(currentValue);
		//If methodFlag is 1 call calculateAdvanced, else call hasCharacteristic
		if (methodFlag == 1)
			exc = assertThrows(exception.getClass(), () -> calculatorAdvanced.calculateAdvanced(ch));
		else
			exc = assertThrows(exception.getClass(), () -> calculatorAdvanced.hasCharacteristic(ch));
		
		assertThat(exc.getMessage(), containsString(message));
	}
	
	/**
	 * Used for parameterized tests.
	 * @return stream of Arguments
	 */
	private static Stream<Arguments> methodExceptionsInAdvancedWithParameters() {
		return Stream.of(
				Arguments.of(new NumberNotInAreaException(), '!', "Number not in area", -1.0, 1),
				Arguments.of(new NumberNotInAreaException(), '!', "Number not in area", 11.0, 1),
				Arguments.of(new NumberNotInAreaException(), 'A', "Number not in area", 0.0, 2),
				Arguments.of(new NumberNotInAreaException(), 'P', "Number not in area", 0.0, 2),
				Arguments.of(new NotSupportedOperationException(), 'a', "Operation not supported", 0.0, 1),
				Arguments.of(new NotSupportedOperationException(), 'B', "Operation not supported", 0.0, 2)
				);
	}
}