package org.example.expProcessing;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class ProccesExpByLib {
    public static String solvingExpressionByLib(String expression) {
        Expression libExp = new ExpressionBuilder(expression).build();
        try {
            String result = String.valueOf(libExp.evaluate());
            return String.valueOf(result);
        } catch (ArithmeticException e) {
            return "Error due to division by zero";
        }
    }
}
