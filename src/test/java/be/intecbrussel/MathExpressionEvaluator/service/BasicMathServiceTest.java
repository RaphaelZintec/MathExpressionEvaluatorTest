package be.intecbrussel.MathExpressionEvaluator.service;


import be.intecbrussel.MathExpressionEvaluator.service.BasicMathService;
import be.intecbrussel.MathExpressionEvaluator.service.BasicMathServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
    import org.junit.jupiter.params.provider.Arguments;
    import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)  ==> pas obligé mais alors BeforeAll 'static'
    public class BasicMathServiceTest {
        private final BasicMathService basicMathService;

        {
            this.basicMathService = new BasicMathServiceImpl();
        }

        /*
        @BeforeAll
        public void beforeAll() {
            // TODO instantiate the service
            public static void setup();
        }
        */

        @Test
        public void testBasicAdditionOfTwoIntegers() {
            int firstNumber = 7;
            int secondNumber = 6;

            int expectedValue = 13;

            double result = basicMathService.add(firstNumber, secondNumber);

            Assertions.assertEquals(expectedValue, result);
        }

        @Test
        public void testBasicAdditionOfTwoNegativeIntegers(){
            int firstNumber = -4;
            int secondNumber = -8;
            int expectedResult = -12;

            double result = basicMathService.add(firstNumber, secondNumber);

            Assertions.assertEquals(expectedResult, result);
        }

        @ParameterizedTest
        //@ValueSource(longs = {1,1,2,2,2,4}) // exemple pour savoir si pair ou non
        @MethodSource("basicAdditionFactory")
        public void testBasicAdditions(double number1, double number2, double expectedValue){
            double result = basicMathService.add(number1, number2);
            Assertions.assertEquals(expectedValue,result);
        }

        public static Stream<Arguments> basicAdditionFactory(){ //argument = een verzameling of data to test
            return Stream.of(
                    Arguments.of(5, 3, 8),
                    Arguments.of(15, 3, 18),
                    Arguments.of(-5, 3, -2),
                    Arguments.of(0, 0, 0),
                    Arguments.of(-7, -3, -10),
                    Arguments.of(5, -3, 2),
                    Arguments.of(5.5, 4.5, 10),
                    Arguments.of(2000000000, 2000000000, 4000000000L),
                    Arguments.of(-0.00001, 0.00002, 0.00001),
                    Arguments.of(0.99999, 0.000001, 0.999991)
            );
        }




        @Test
        public void testSubstraction(){
            int firstNumber = 0;
            int secondNumber = 0;
            int expectedValue = 0;
            double result = basicMathService.subtract(firstNumber, secondNumber);
            Assertions.assertEquals(firstNumber, secondNumber);
        }
        @ParameterizedTest
        @MethodSource("testSubstractionFactory")
        public void testSubstraction2(double val1, double val2, double expectedValue){
            double result = basicMathService.subtract(val1, val2);
            Assertions.assertEquals(expectedValue,result);
        }
        public static Stream<Arguments> testSubstractionFactory(){
            return Stream.of(
                    Arguments.of(5,6,-1),
                    Arguments.of(3,3,0),
                    Arguments.of(-3,4,-7),
                    Arguments.of(-3.6,-3,-.6)
            );
        }


        @Test
        public void testMultiply(){
            int n1 = 5;
            int n2 = 2;
            int expectedValue = 10;
            double result = basicMathService.multiply(n1, n2);
            Assertions.assertEquals(expectedValue, result);
        }
        @ParameterizedTest
        @MethodSource("testMultiplyFactory")
        public void testMultiply2(double n1, double n2, double expectedValue){
            double result = basicMathService.multiply(n1, n2);
            Assertions.assertEquals(expectedValue, result);
        }
        public static Stream<Arguments> testMultiplyFactory(){
            return Stream.of(
                    Arguments.of(5, 3, 15),
                    Arguments.of(10, 2, 20),
                    Arguments.of(10, 0, 0),
                    Arguments.of(0, 0, 0),
                    Arguments.of(-30, 2, -60),
                    Arguments.of(30.6, 2, 61.2),
                    Arguments.of(-60, -2, 120)
            );
        }





        @Test
        public void testDivision(){
            int n1 = 50;
            int n2 = 2;
            int excpectedValue = 25;
            double result = basicMathService.divide(n1, n2);
            Assertions.assertEquals(excpectedValue, result);
        }

        @ParameterizedTest
        @MethodSource("testDivisionFactory")
        public void testDivision2(double n1, double n2, double expectedValue){
            double result = basicMathService.divide(n1, n2);
            Assertions.assertEquals(expectedValue, result);
        }
        public static Stream<Arguments> testDivisionFactory(){
            return Stream.of(
                    Arguments.of(50,2,25),
                    Arguments.of(40,2,20),
                    Arguments.of(40,0,0),
                    Arguments.of(0,0,0),
                    Arguments.of(0,10,0.0),
                    Arguments.of(0.5,0.2,2.5),
                    Arguments.of(0.5,-2,-0.25),
                    Arguments.of(-0.5,-0.2,2.5),
                    Arguments.of(-0.5,2,-0.25),
                    Arguments.of(5.5,4.5,1.2222222222),
                    Arguments.of((10),(2-3),-10)
            );
        }


    @ParameterizedTest
    @MethodSource("testDivisionFactory_Exception")
    public void testDivision_Exception(double n1, double n2, Class<Exception> expectException){
        Assertions.assertThrows(expectException,
                ()->basicMathService.divide(n1, n2)
        );
    }
    public static Stream<Arguments> testDivisionFactory_Exception(){
        return Stream.of(
                Arguments.of(40,0,ArithmeticException.class),
                Arguments.of(0,0,ArithmeticException.class),
                Arguments.of(-40,0,ArithmeticException.class)
        );
    }



        @Test
        public void testModulo(){
            int n1 = 10;
            int n2 = 7;
            int expectedNumber = 3;
            double result = basicMathService.modulo(n1, n2);
            Assertions.assertEquals(expectedNumber, result);
        }

        @ParameterizedTest
        @MethodSource("testModuloFactory")
        public void testModulo2(double n1, double n2, double expectedResult){
            double result = basicMathService.modulo(n1, n2);
            Assertions.assertEquals(expectedResult, result);
        }
        public static Stream<Arguments> testModuloFactory(){
            return Stream.of(
                    Arguments.of(50, 3, 2.0),
                    Arguments.of(10, -4, 2),
                    Arguments.of(-5, -3, -2),
                    Arguments.of(5, -3, 2),
                    Arguments.of(-5, 3, -2),
                    Arguments.of(5.5, 3.3, 2.2),
                    Arguments.of(-5.5, -3.3, -2.2),
                    Arguments.of((-10), (-3), -1)
            );
        }



    @ParameterizedTest
    @MethodSource("testModuloFactory_Exception")
    public void testModulo_Exception(double n1, double n2, Class<Exception> expectException){
        Assertions.assertThrows(expectException,
                ()->basicMathService.modulo(n1, n2)
        );
    }
    public static Stream<Arguments> testModuloFactory_Exception(){
        return Stream.of(
                Arguments.of(40,0,IllegalArgumentException.class),
                Arguments.of(0,0,IllegalArgumentException.class),
                Arguments.of(-40,0,IllegalArgumentException.class)
        );
    }

    }
