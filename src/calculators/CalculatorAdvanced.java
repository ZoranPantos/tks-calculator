package calculators;
import exceptions.*;

/**
 * <h2>Advanced calculator</h2>
 * This class inherits Calculator class which is described in its own section.
 * Important thing to notice is that it provides end-user abilities to calculate factorial or power of a number,
 * and to test if number is Armstrong or perfect.
 * 
 * @author zoran pantos
 * @version 1.0
 * @since 2021-2-12
 */
public class CalculatorAdvanced extends Calculator {
	/**
	 * Instantiates object of type CalculatorAdvanced after object of parent class is formed.
	 */
	public CalculatorAdvanced() {
		super();
	}
	
	/**
	 * Uses helper methods to calculate factorial or a power of inherited value.
	 * Can throw different exceptions based upon provided character for argument.
	 * @param action represents sign for factorial or digit for calculating power of a <strong>currentValue</strong>
	 * @throws NumberNotInAreaException thrown when <strong>action</strong> is digit which is less than 0 or greater than 10
	 * @throws NotSupportedOperationException thrown when <strong>action</strong> is neither digit nor factorial sign
	 */
	public void calculateAdvanced(char action) throws NumberNotInAreaException, NotSupportedOperationException {
		if (action == '!') {
			if (currentValue < 0.0 || currentValue > 10.0)
				throw new NumberNotInAreaException("Number not in area");
			else
				currentValue = (double)calculateFactorial(currentValue.intValue());
		}
		else {
			int numericValue = Character.getNumericValue(action);
			//if (numericValue < 0.0 || numericValue > 9.0)
			//Manja vrijednost od 0 se nece moci ni poslati kao argument metodi jer je parametar tipa char pa ne bi proslo npr. '-1'
			if (numericValue > 9.0)
				throw new NotSupportedOperationException("Operation not supported");
			else
				currentValue = calculatePower(currentValue, numericValue);
		}
	}
	
	/**
	 * Uses helper methods to examine if inherited value is Armstrong or perfect number.
	 * @param value tells this method what kind of test to execute
	 * @return true if <strong>currentValue</strong> has tested characteristic, otherwise false
	 * @throws NotSupportedOperationException thrown when argument is different from 'A' (Armstrong) and 'P' (perfect)
	 * @throws NumberNotInAreaException thrown when <strong>currentValue</strong> is less than 1
	 */
	public Boolean hasCharacteristic(char value) throws NotSupportedOperationException, NumberNotInAreaException {
		if (value != 'A' && value != 'P')
			throw new NotSupportedOperationException("Operation not supported");
		else {
			if (currentValue.intValue() < 1)
				throw new NumberNotInAreaException("Number not in area");
			else {
				if (value == 'A')
					return isArmstrong(currentValue.intValue());
				else
					return isPerfect(currentValue.intValue());
			}
		}
	}
	
	/**
	 * Helper method which calculates power of a number.
	 * @param value base number
	 * @param power degree
	 * @return power of a number
	 */
	private Double calculatePower(Double value, Integer power) {
		if (power == 0)
			return 1.0;
		else {
			Double result = 1.0;
		
			while (power > 0) {
				result *= value;
				power--;
			}
		
			return result;
		}
	}
	
	/**
	 * Helper method which calculates factorial of a number.
	 * @param value base number
	 * @return factorial of a number
	 */
	private int calculateFactorial(int value) {
		int result = 1;
		
		while (value > 0) {
			result *= value;
			value--;
		}
		
		return result;
	}
	
	/**
	 * Helper method which tests if provided number is perfect.
	 * @param number test number
	 * @return true if number is perfect, otherwise false
	 */
	private Boolean isPerfect(int number) {
		int sum = 0;
		
		for (int i = 1; i <= number / 2; i++)
			if (number % i == 0)
				sum += i;
		
		return sum == number ? true : false;
	}
	
	/**
	 * Helper method which tests if provided number is Armstrong number.
	 * @param number test number
	 * @return true if number is Armstrong, otherwise false
	 */
	private Boolean isArmstrong(int number) {
		int numberOfDigits = 0, tmp1 = number, tmp2 = number, sum = 0;
		
		while (tmp1 > 0) {
			tmp1 /= 10;
			numberOfDigits++;
		}
		
		while (tmp2 > 0) {
			int digit = tmp2 % 10;
			tmp2 /= 10;
			sum += calculatePower((double)digit, numberOfDigits);
		}
		
		return sum == number ? true : false;
	}
}