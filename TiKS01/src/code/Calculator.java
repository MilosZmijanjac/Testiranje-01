package code;
import code.exceptions.NotSupportedOperationException;
import code.exceptions.DivisionByZeroException;

/**
 * <h1>Calculator</h1>
 * Class for the basic four function calculator.
 *
 * @author Miloš
 * @version 1.0
 * @since 1.0
 *
 */
public class Calculator {
    /**
     * Class member of type Double that is used in calculations
     */
    private Double currentValue;

    /**
     * Constructs a Calculator with zero as its currentValue.
     */
    public Calculator(){
        currentValue=0.0;
    }


    /**
     * Returns currentValue
     * @return  a Double that represents the value of currentValue
     */
    public Double getCurrentValue() {
        return currentValue;
    }

    /**
     * Sets the value of currentValue
     * @param currentValue new value of the currentValue
     */
    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * Calculates the value of currentValue after applying the given operation with value
     * @param value second operand of operation
     * @param operator char value for the operation
     * @throws DivisionByZeroException thrown when division by zero is made
     * @throws NotSupportedOperationException thrown when unsupported operation is used
     */
    public void calculate(Double value,char operator) throws DivisionByZeroException, NotSupportedOperationException {
        switch (operator) {
            case '+' :
            	currentValue += value;
            	break;
            case '-' :
            	currentValue -= value;
            	break;
            case '/' : {
                if (value == 0)
                    throw new DivisionByZeroException("Division by zero is undefined");
                currentValue /= value;
            }
            break;
            case '*':
            	currentValue *= value;
            	break;
            default :
            	throw new NotSupportedOperationException("Operation " + operator + " isn't supported");
        }
    }
}
