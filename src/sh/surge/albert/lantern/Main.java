package sh.surge.albert.lantern;

import java.awt.Point;
import java.util.ArrayList;

public class Main {
    public static void main (String args[]) {

        Point q1 = new Point(0,5);
        Point q2 = new Point(1,10);
        Point q3 = new Point(3,38);
        Point q4 = new Point(4,17);
        Point q5 = new Point(8,65);
        ArrayList<Point> curve = new ArrayList<>();
        curve.add(q1);
        curve.add(q2);
        curve.add(q3);

        GradientDescentPolynomialRegression testForCurve = new GradientDescentPolynomialRegression(2);
        testForCurve.addPoints(curve);
        testForCurve.train();

    }
}
