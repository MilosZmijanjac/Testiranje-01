package code;
import code.exceptions.NotSupportedOperationException;
import code.exceptions.NumberNotInAreaException;


/**
 * <h1>Advanced Calculator</h1>
 * Class which extends Calculator class and add new functions like calculating the factorial value and raising to the power of the number that is passed as a parameter.
 * With the use of this class user can check if the number is perfect or Armstrong.
 *
 * @author Miloš
 * @version 1.0
 * @since 1.0
 * @see Calculator
 */
public class CalculatorAdvanced extends Calculator {
    /**
     * Constructs a CalculatorAdvanced with zero as its currentValue.
     */
    public CalculatorAdvanced(){
        super();
    }


    /**
     * Calculates factorial value or power of the currentValue
     * @param action  number between [0-9] for power or "!" for factorial.
     * @throws NumberNotInAreaException thrown when number is not in the range
     * @throws NotSupportedOperationException thrown when operation is not supported
     */
    public void calculateAdvanced(char action) throws NumberNotInAreaException, NotSupportedOperationException {
        int value=getCurrentValue().intValue();

        if(action=='!'){
            if(value<0||value>10)
                throw new NumberNotInAreaException("Number is not in the [0,10] range");
            else {
                int fact=1;
                for (int i = 2; i <= value; i++)
                    fact = fact * i;

                setCurrentValue((double)fact);
            }
        }else if(action>='0'&&action<='9'){
            int pow=1;
            for(int i=0;i<action-'0';i++)
                pow*=value;
            setCurrentValue((double)pow);
        }
        else
            throw new NotSupportedOperationException("Action " + action + " isn't supported");

    }

    /**
     * Checks to see if number is perfect or Armstrong
     * @param value if value is 'P' then method checks if number is perfect, if value is 'A' checks if number is Armstrong
     * @return returns Boolean value if number is perfect or Armstrong
     * @throws NotSupportedOperationException thrown when operation is not supported
     * @throws NumberNotInAreaException thrown when number is less than 0
     */
    public Boolean hasCharacteristic(char value)throws NotSupportedOperationException,NumberNotInAreaException{
        int integerValue=getCurrentValue().intValue();
        if(integerValue<0)
            throw new NumberNotInAreaException("Number is less than 0");
        if(value=='A'){
            int sum = 0;
            String stringValue=Integer.toString(integerValue);
            for(int i=0;i<stringValue.length();i++){
                int pow=1;
                for(int j=0;j<stringValue.length();j++)
                    pow*=stringValue.charAt(i)-'0';
                sum+=pow;
            }
            return sum == integerValue;
        }
        else if(value=='P'){
            int sum=0;
            for(int i=1;i<=integerValue/2;i++)
                if(integerValue%i==0)
                    sum+=i;
                return sum==integerValue;
        }
        else
            throw new NotSupportedOperationException("Operation "+value+" is not supported");
    }

}
