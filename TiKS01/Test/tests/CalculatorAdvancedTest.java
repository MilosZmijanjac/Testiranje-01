package tests;

import code.exceptions.NotSupportedOperationException;
import code.exceptions.NumberNotInAreaException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

import code.CalculatorAdvanced;

/**
 * <h1>CalculatorAdvancedTest</h1>
 * Class for Unit testing CalculatorAdvanced class.
 *
 * @author Miloš
 * @version 1.0
 * @since 1.0
 * @see CalculatorAdvanced
 *
 */
public class CalculatorAdvancedTest {
    private CalculatorAdvanced calculatorAdvanced=new CalculatorAdvanced();

    @BeforeAll
    static void setUpBeforeClass() throws Exception {}

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() {
        calculatorAdvanced.setCurrentValue(0.0);
    }

    @AfterEach
    void tearDown() {
    }
    /**
     * Tests a CalculatorAdvanced with zero as its currentValue.
     */
    @Test
    void testCalculatorAdvanced(){
        assertThat(calculatorAdvanced, notNullValue());
    }
    /**
     * Test calculateAdvanced method
     * @param value value of currentValue to be set
     * @param option option to be chosen
     * @param result output of the calculateAdvanced method
     * @throws NumberNotInAreaException 
     * @throws NotSupportedOperationException
     */
    @ParameterizedTest
    @DisplayName("CalculateAdvanced method test")
    @MethodSource("calculateAdvancedParameters")
    void testCalculateAdvanced(Double value,char option,Double result) throws NumberNotInAreaException, NotSupportedOperationException {
        calculatorAdvanced.setCurrentValue(value);
        calculatorAdvanced.calculateAdvanced(option);
        assertThat(result,is(calculatorAdvanced.getCurrentValue()));
    }

    private static Stream<Arguments> calculateAdvancedParameters() {
        return Stream.of(
                Arguments.of(7.0,'!',5040.0),
                Arguments.of(4.0,'4',256.0),
                Arguments.of(3.0,'!',6.0),
                Arguments.of(12.12,'2',144.0),
                Arguments.of(0.0,'!',1.0),
                Arguments.of(10.0,'!',3628800.0),
                Arguments.of(12.12,'0',1.0),
                Arguments.of(1.0,'9',1.0)
                
        );
    }
    /**
     * Test for NotSupportedOperationException in calculateAdvanced method
     * @param action action chosen
     */
    @ParameterizedTest
    @DisplayName("Test for NotSupportedOperationException in calculateAdvanced")
    @MethodSource("errorsNotSupportedOperationExceptionInCalculateAdvanced")
    void testNotSupportedOperationInCalculateAdvanced(char action) {
        calculatorAdvanced.setCurrentValue(5.0);
        NotSupportedOperationException exc=assertThrows(NotSupportedOperationException.class, ()-> calculatorAdvanced.calculateAdvanced( action));
        assertThat(exc.getMessage(),is("Action " + action + " isn't supported"));
        
    }
    private static Stream<Arguments> errorsNotSupportedOperationExceptionInCalculateAdvanced() {
        return Stream.of(
                Arguments.of('?'),
                Arguments.of('L'),
                Arguments.of('#'),
                Arguments.of('/'),
                Arguments.of(':')
                
        );
    }
    /**
     * Test for NumberNotInRangeException in calculateAdvanced method
     * @param value value to be chosen
     */
    @ParameterizedTest
    @DisplayName("Test for NumberNotInRangeException in calculateAdvanced")
    @MethodSource("errorsNumberNotInRangeExceptionInCalculateAdvanced")
    void testNumberNotInRangeExceptionInCalculateAdvanced(Double value) {
        calculatorAdvanced.setCurrentValue(value);
        NumberNotInAreaException exc=assertThrows(NumberNotInAreaException.class, ()-> calculatorAdvanced.calculateAdvanced('!'));

        assertThat(exc.getMessage(),is("Number is not in the [0,10] range"));
    }

    private static Stream<Arguments> errorsNumberNotInRangeExceptionInCalculateAdvanced() {
        return Stream.of(
                Arguments.of(11.0),
                Arguments.of(12.0),
                Arguments.of(13.0),
                Arguments.of(-1.0)
                
        );
    }
    /**
     * Test for hasCharacteristics method
     * @param result output value
     * @param value option A or P
     * @param currentValue value to be set as the currentValue
     * @throws NumberNotInAreaException
     * @throws NotSupportedOperationException
     */
    @ParameterizedTest
    @DisplayName("HasCharacteristics method test")
    @MethodSource("hasCharacteristicsParameters")
    void testHasCharacteristic(boolean result, char value, Double currentValue) throws NumberNotInAreaException, NotSupportedOperationException {
        calculatorAdvanced.setCurrentValue(currentValue);
        assertThat(result,is(calculatorAdvanced.hasCharacteristic(value)));
    }

    private static Stream<Arguments> hasCharacteristicsParameters() {
        return Stream.of(
                Arguments.of(true,'P',6.0),
                Arguments.of(false,'P',7.0),
                Arguments.of(true,'A',407.),
                Arguments.of(false,'A',408.0),
                Arguments.of(true,'A',1.0)
        );
    }
    
    /**
     * Test for NotSupportedOperationException in hasCharacteristics method
     * @param action action chosen
     */
    @ParameterizedTest
    @DisplayName("Test for NotSupportedOperationException in hasCharacteristics")
    @MethodSource("errorsNotSupportedOperationExceptionInHasCharacteristics")
    void testNotSupportedOperationInHasCharacteristics(char action) {
        calculatorAdvanced.setCurrentValue(5.0);
        NotSupportedOperationException exc=assertThrows(NotSupportedOperationException.class, ()-> calculatorAdvanced.hasCharacteristic( action));

        assertThat(exc.getMessage(),is("Operation " + action + " is not supported"));
    }
    private static Stream<Arguments> errorsNotSupportedOperationExceptionInHasCharacteristics() {
        return Stream.of(
                Arguments.of('?'),
                Arguments.of('L'),
                Arguments.of('#')
        );
    }
    /**
     * Test for NumberNotInRangeException in hasCharacteristics method
     * @param operation operation to be chosen A or P
     * @param value value to be set as currentValue
     */
    @ParameterizedTest
    @DisplayName("Test for NumberNotInRangeException in hasCharacteristics")
    @MethodSource("errorsNumberNotInRangeExceptionInHasCharacteristics")
    void testNumberNotInRangeExceptionInHasCharacteristics(char operation,Double value) {
        calculatorAdvanced.setCurrentValue(value);
        NumberNotInAreaException exc=assertThrows(NumberNotInAreaException.class, ()-> calculatorAdvanced.hasCharacteristic(operation));

        assertThat(exc.getMessage(),is("Number is less than 1"));
    }

    private static Stream<Arguments> errorsNumberNotInRangeExceptionInHasCharacteristics() {
        return Stream.of(
                Arguments.of('A',-1.0),
                Arguments.of('P',-12.0),
                Arguments.of('A',-13.0)
        );
    }
}