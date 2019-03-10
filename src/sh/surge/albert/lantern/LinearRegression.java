package sh.surge.albert.lantern;

import java.util.ArrayList;
import java.awt.Point;

public class LinearRegression {

    private ArrayList<Point> points = new ArrayList<Point>();
    private double yIntercept;
    private double slope;

    public void train(ArrayList<Point> newPoints){
        points.addAll(newPoints); // Adds all new points to current points
    }

    public double calculateSlope(){
        // Calculate the mean of all x-values
        double meanX = 0;
        for (int i=0; i<points.size(); i++) {
            meanX += points.get(i).getX();
        }
        meanX /= points.size();

        // Calculate the mean value of all y-values
        double meanY = 0;
        for (int i=0; i<points.size(); i++) {
            meanY += points.get(i).getY();
        }
        meanY /= points.size();

        // Calculate the numerator (xi - X)(yi - Y) and denominator (xi-X)^2
        double numerator = 0;
        double denominator = 0;
        for (int i=0; i<points.size(); i++){
            numerator += (points.get(i).getX()-meanX)*(points.get(i).getY()-meanY);
            denominator += Math.pow(points.get(i).getX()-meanX, 2);
        }

        // Calculate and return slope
        return numerator / denominator;
    }



}
