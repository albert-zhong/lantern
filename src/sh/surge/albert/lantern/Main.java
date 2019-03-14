package sh.surge.albert.lantern;

import java.awt.Point;
import java.util.ArrayList;

public class Main {
    public static void main (String args[]) {

        Point p1 = new Point(8, 3);
        Point p2 = new Point(2, 10);
        Point p3 = new Point(11, 3);
        Point p4 = new Point(6, 6);
        Point p5 = new Point(5, 8);
        Point p6 = new Point(4, 12);
        Point p7 = new Point(12, 1);
        Point p8 = new Point(9, 4);
        Point p9= new Point(6, 9);
        Point p10 = new Point(1, 14);

        ArrayList<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        points.add(p7);
        points.add(p8);
        points.add(p9);
        points.add(p10);

        GradientDescent line = new GradientDescent();
        line.addPoints(points);
        line.train();

    }
}
