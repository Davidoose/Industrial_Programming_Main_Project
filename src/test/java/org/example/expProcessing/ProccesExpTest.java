package org.example.expProcessing;

import junit.framework.TestCase;
import org.junit.Assert;

import static org.example.expProcessing.ProccesExp.solvingExpression;

public class ProccesExpTest extends TestCase {

    public void testSolvingExpression1() {
        String exp = new String("(1 + 2) * (3 + 4)");
        String actual = solvingExpression(exp);
        Assert.assertEquals("21", actual);
    }


    public void testSolvingExpression2() {
        String exp1 = new String("(13 * (14 - 88)) / 37");
        String actual1 = solvingExpression(exp1);
        Assert.assertEquals("-26", actual1);
    }

    public void testSolvingExpression3() {
        String exp1 = new String("29 / 0");
        String actual1 = solvingExpression(exp1);
        Assert.assertEquals("Error due to division by zero", actual1);
    }


}