package sh.surge.albert.lantern;

import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GradientDescentPolynomialRegression {

    public GradientDescentPolynomialRegression(int degree, int decimalPlacePrecision){
        if (decimalPlacePrecision < 3) {
            throw new IllegalArgumentException("Precision must be at least 3!");
        }
        this.decimalPlacePrecision = decimalPlacePrecision;

        numberOfParameters = degree + 1;
        parameters = new double[numberOfParameters];

        for (int i=0; i<numberOfParameters; i++){
            parameters[i] = 0;
        }
    }

    private int numberOfParameters;
    private double[] parameters;
    private ArrayList<Point> points = new ArrayList<>();

    private final double learningRate = 0.01;
    private int decimalPlacePrecision;

    public void addPoints(ArrayList<Point> newPoints){
        points.addAll(newPoints);
    }

    public void train() {
        for (int i=0; true; i++) {

            double partialDerivatives[] = getPartialDerivatives();

            if (isConverged(partialDerivatives)) {
                finish();
                System.out.println("");
                System.out.println("Took " + i + " tries!");
                break;
            }

            double[] tempParameters = parameters.clone();

            for (int j=0; j<tempParameters.length; j++) {
                tempParameters[j] = parameters[j] - learningRate*partialDerivatives[j];
            }

            parameters = tempParameters;
        }
    }

    public double[] getPartialDerivatives() {
        double[] sums = new double[numberOfParameters];
        for (int i=0; i<sums.length; i++) { // Sets all original values of sums to 0
            sums[i] = 0;
        }

        for (int i=0; i<points.size(); i++) { // Loops through all points
            double currentX = points.get(i).getX();
            double currentY = points.get(i).getY();

            double delta = hypothesis(currentX) - currentY;

            for (int j=0; j<sums.length; j++) {  // Calculate sums for each partial derivative, from j=0
                sums[j] += (delta * Math.pow(currentX, j));
            }
        }

        for (int i=0; i<sums.length; i++) { // Multiplies each partial derivative by 1/m
            sums[i] /= points.size();
        }

        return sums;
    }

    private double hypothesis(double x) {  // Returns hypothesis prediction
        double hypothesis = 0;
        for (int i=0; i<numberOfParameters; i++) {
            hypothesis += parameters[i]*Math.pow(x, i);
        }
        return hypothesis;
    }

    private void show() {
        for (int i=0; i<parameters.length; i++) {
            System.out.print(i + ": " + parameters[i] + ", ");
        }
    }

    private boolean isConverged(double[] numbers) {
        double absolutePrecision = 1/Math.pow(10, decimalPlacePrecision);

        for (int i=0; i<numbers.length; i++) {
            if (Math.abs(numbers[i]) > absolutePrecision) {
                return false;
            }
        }
        return true;
    }

    private void finish(){ // Rounds parameters and prints results
        for (int i=0; i<parameters.length; i++){
            parameters[i] = round(parameters[i], decimalPlacePrecision-2);
        }
        show();
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
}
