package sh.surge.albert.lantern;

import java.awt.*;
import java.util.ArrayList;

public class GradientDescent {

    private ArrayList<Point> points = new ArrayList<>();
    private double bias = 0;
    private double slope = 0;
    private double learningRate = 0.02;

    public void addPoints(ArrayList<Point> newPoints){
        points.addAll(newPoints);
    }

    public void train(){
        for (int i=0; i<6000; i++){
            double[] partialDerivatives = getPartialDerivatives();
            double tempBias = bias - learningRate*partialDerivatives[0];
            double tempSlope = slope - learningRate*partialDerivatives[1];
            bias = tempBias;
            slope = tempSlope;
            System.out.println("Slope = " + slope + ", Bias = " + bias);
        }
    }

    private double getLoss(){
        double loss = 0;
        for (int i=0; i<points.size(); i++) {
            loss += Math.pow((predict(points.get(i).getX()) - points.get(i).getY()), 2);
        }
        return loss / (2 * points.size());
    }

    private double[] getPartialDerivatives(){
        double sum0 = 0;
        double sum1 = 0;
        for (int i=0; i<points.size(); i++){
            double delta = predict(points.get(i).getX()) - points.get(i).getY();
            sum0 += delta;
            sum1 += delta*points.get(i).getX();
        }
        sum0 /= points.size();
        sum1 /= points.size();
        return new double[]{sum0, sum1};
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
