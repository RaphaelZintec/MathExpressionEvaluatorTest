package be.intecbrussel.MathExpressionEvaluator.service;

import java.math.BigDecimal;

public class BasicMathServiceImpl implements BasicMathService{
    @Override
    public double add(double firstNumber, double secondNumber) {
        String firstNumberAsString = String.valueOf(firstNumber);
        String secondNumberAsString = String.valueOf(secondNumber);

        BigDecimal firstDecimal = new BigDecimal(firstNumberAsString);
        BigDecimal secondDecimal = new BigDecimal(secondNumberAsString);

        BigDecimal result = firstDecimal.add(secondDecimal);

        return result.doubleValue();
    }

    @Override
    public double subtract(double firstNumber, double secondNumber) {
        return  firstNumber - secondNumber;
    }

    @Override
    public double multiply(double firstNumber, double secondNumber) {
        return firstNumber * secondNumber;
    }

    @Override
    public double divide(double firstNumber, double secondNumber) {
        return firstNumber / secondNumber;
    }
}