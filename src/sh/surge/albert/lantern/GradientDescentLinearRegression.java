package sh.surge.albert.lantern;

import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GradientDescentLinearRegression {

    private ArrayList<Point> points = new ArrayList<>();
    private double bias = 0;
    private double slope = 0;
    private final double learningRate = 0.03;
    private final double precision = 0.000001;

    public void addPoints(ArrayList<Point> newPoints){
        points.addAll(newPoints);
    }

    public void train(){
        while (true){
            double[] partialDerivatives = getPartialDerivatives();
            if (Math.abs(partialDerivatives[0]) < precision && Math.abs(partialDerivatives[1]) < precision) {
                slope = round(slope, 6);
                bias = round(bias, 6);
                System.out.println("Slope = " + slope + ", Bias = " + bias);
                break;
            }
            double tempBias = bias - learningRate*partialDerivatives[0];
            double tempSlope = slope - learningRate*partialDerivatives[1];
            bias = tempBias;
            slope = tempSlope;

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

    private double round(double number, int decimalPlace) {
        if (decimalPlace < 1) {
            throw new IllegalArgumentException("Must round to at least one decimal place!");
        }

        StringBuilder pattern = new StringBuilder("#.");
        for (int i=0; i<decimalPlace; i++) {
            pattern.append("#");
        }
        DecimalFormat df = new DecimalFormat(pattern.toString());
        df.setRoundingMode(RoundingMode.HALF_UP);

        return Double.valueOf(df.format(number));
    }

    public String toString(){
        return "y = " + slope + "x + " + bias;
    }

}
