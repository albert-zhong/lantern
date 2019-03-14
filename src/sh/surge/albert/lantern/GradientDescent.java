package sh.surge.albert.lantern;

import java.awt.*;
import java.util.ArrayList;

public class GradientDescent {

    private ArrayList<Point> points = new ArrayList<>();
    private double bias = 0;
    private double slope = 0;
    private double learningRate = 0.05;

    public void addPoints(ArrayList<Point> newPoints){
        points.addAll(newPoints);
    }

    public void train(){
        int counter = 0;
        while(true) {
            double tempBias = bias - learningRate * getPartialDerivativeOfBias();
            double tempSlope = slope - learningRate * getPartialDerivativeOfSlope();
            if (tempBias == bias && tempSlope == slope) { // Reached minimum
                System.out.println("slope is " + slope + " and bias is " + bias);
                System.out.println("took " + counter + " tries");
                break;
            }
            bias = tempBias;
            slope = tempSlope;
            counter++;
        }
    }

    private double getLoss(){
        double loss = 0;
        for (int i=0; i<points.size(); i++) {
            loss += Math.pow((predict(points.get(i).getX()) - points.get(i).getY()), 2);
        }
        return loss / (2 * points.size());
    }

    private double getPartialDerivativeOfBias(){
        double sum = 0;
        for (int i=0; i<points.size(); i++) {
            sum += (predict(points.get(i).getX()) - points.get(i).getY());
        }
        return sum / points.size();
    }

    private double getPartialDerivativeOfSlope(){
        double sum = 0;
        for (int i=0; i<points.size(); i++) {
            sum += ((predict(points.get(i).getX()) - points.get(i).getY())*points.get(i).getX());
        }
        return sum / points.size();
    }

    public double predict(double x){
        return slope*x + bias;
    }

    public double getSlope(){
        return slope;
    }

    public double getYIntercept(){
        return bias;
    }

    public String toString(){
        return "y = " + slope + "x + " + bias;
    }

}
