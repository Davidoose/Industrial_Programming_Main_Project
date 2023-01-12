package org.example.expProcessing;

import java.util.Stack;

public class ProccesExp {
    public static String solvingExpression(String expression) {
        char[] tokens = expression.toCharArray();

        Stack<Integer> values = new
                Stack<Integer>();

        Stack<Character> ops = new
                Stack<Character>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ')
                continue;

            if (tokens[i] >= '0' &&
                    tokens[i] <= '9') {
                StringBuffer sbuf = new
                        StringBuffer();

                while (i < tokens.length &&
                        tokens[i] >= '0' &&
                        tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Integer.valueOf((sbuf.
                        toString())));
                i--;
            } else if (tokens[i] == '(')
                ops.push(tokens[i]);

            else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(Integer.valueOf((applyOp(ops.pop(),
                            values.pop(),
                            values.pop()))));
                ops.pop();
            } else if (tokens[i] == '+' ||
                    tokens[i] == '-' ||
                    tokens[i] == '*' ||
                    tokens[i] == '/') {
                while (!ops.empty() &&
                        hasPrecedence(tokens[i],
                                ops.peek()))
                    values.push(Integer.valueOf((applyOp(ops.pop(),
                            values.pop(),
                            values.pop()))));

                ops.push(tokens[i]);
            }
        }

        while (!ops.empty()) {
            values.push(Integer.valueOf((applyOp(ops.pop(),
                    values.pop(),
                    values.pop()))));
        }
        int temp = (values.pop());
        if (temp == 1813) {
            return "Error due to division by zero";
        } else {
            return String.valueOf(temp);
        }
    }

    public static boolean hasPrecedence(char sign1, char sign2) {
        if (sign2 == '(' || sign2 == ')')
            return false;
        if ((sign1 == '*' || sign1 == '/') &&
                (sign2 == '+' || sign2 == '-'))
            return false;
        else
            return true;
    }

    public static String applyOp(char sign, Integer b, Integer a) {
        switch (sign) {
            case '+':
                return String.valueOf(a + b);
            case '-':
                return String.valueOf(a - b);
            case '*':
                return String.valueOf(a * b);
            case '/':
                if (b != 0) {
                    return String.valueOf(a / b);
                } else {
                    return "1813";
                }
        }

        return String.valueOf(0);
    }

}
