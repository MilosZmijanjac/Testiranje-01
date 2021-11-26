package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.stream.Stream;

import code.Calculator;
import code.exceptions.*;

class CalculatorTest {

    private Calculator calculator=new Calculator();
    
    @BeforeAll
    static void setUpBeforeClass() throws Exception { }

    @AfterAll
    static void tearDownAfterClass() throws Exception { }

    @BeforeEach
    void setUp() {
        calculator.setCurrentValue(0.0);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCalculator(){
        assertThat(calculator, notNullValue());
    }

    @Test
    void testGetCurrentValue() {
        assertThat(calculator.getCurrentValue(),is(0.0));
    }

    @Test
    void testSetCurrentValue() {
        calculator.setCurrentValue(5.0);
        assertThat(calculator.getCurrentValue(),is(5.0));
    }


    @ParameterizedTest
    @DisplayName("Calculate method test")
    @MethodSource("parameters")
    void testCalculate(Double currentValue,Double value,char operation,Double result) throws DivisionByZeroException, NotSupportedOperationException {
        calculator.setCurrentValue(currentValue);
        calculator.calculate(value, operation);
        assertThat(calculator.getCurrentValue(),is(result));
    }

    private static Stream<Arguments> parameters()
    {
        return Stream.of(
                Arguments.of(2.331,0.5,'*',1.1655),
                Arguments.of(16.23,3.1,'-',13.13),
                Arguments.of(-9.631,3.2,'/',-3.0096875),
                Arguments.of(4.2,1.16,'+',5.36)
        );
    }
    @Test
    @DisplayName("Test for DivisionByZeroException")
    void testDivisionByZeroException() {
        DivisionByZeroException exc=assertThrows(DivisionByZeroException.class, ()-> calculator.calculate(0.0, '/'));

        assertThat(exc.getMessage(),is("Division by zero is undefined"));
    }

    @ParameterizedTest
    @DisplayName("Test for NotSupportedOperationException")
    @MethodSource("errors")
    void testNotSupportedOperation(char operator) {
        NotSupportedOperationException exc=assertThrows(NotSupportedOperationException.class, ()-> calculator.calculate(9.9, operator));

        assertThat(exc.getMessage(),is("Operation " + operator + " isn't supported"));
    }

    private static Stream<Arguments> errors()
    {
        return Stream.of(
                Arguments.of('?'),
                Arguments.of('L'),
                Arguments.of('4'),
                Arguments.of('#')
        );
    }
}
