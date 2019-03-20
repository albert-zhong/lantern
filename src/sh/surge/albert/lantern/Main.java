package sh.surge.albert.lantern;

import java.awt.Point;
import java.util.ArrayList;

public class Main {
    public static void main (String args[]) {

        Point q1 = new Point(0,-6);
        Point q2 = new Point(1,1);
        Point q3 = new Point(2,18);
        Point q4 = new Point(-2, -14);
        Point q5 = new Point(-1, -9);
        ArrayList<Point> curve = new ArrayList<>();
        curve.add(q1);
        curve.add(q2);
        curve.add(q3);
        curve.add(q4);
        curve.add(q5);

        GradientDescentPolynomialRegression testForCurve = new GradientDescentPolynomialRegression(3, 3);
        testForCurve.addPoints(curve);
        testForCurve.train();

    }
}
