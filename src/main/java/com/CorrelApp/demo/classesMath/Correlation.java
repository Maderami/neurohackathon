package com.CorrelApp.demo.classesMath;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Correlation {
    public static void main(String[] args) {
        //double [] x = new double[10];
        //double [] y = new double[10];

        double[] x = { 2, 4, 5, 6, 4, 7, 8, 5, 6, 7 };
        double[] y = { 3, 2, 6, 5, 3, 6, 5, 4, 4, 5 };
        double r = correlateByPirson(x, y);
        System.out.println("MyMath.Correlation between arrays x and y is " + r);

        List<Double> x1 = new ArrayList<>(), y1 = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            x1.add(x[i]);
            y1.add(y[i]);
        }
        r = correlateByPirson(fromListToArray(x1), fromListToArray(y1));
        System.out.println("List's correlation between arrays x and y is " + r);
    }

    public static double correlateByPirson(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new java.lang.NumberFormatException();
        }

        int len = x.length;
        double xSum = 0, ySum = 0, xAvg, yAvg;

        for (int i = 0; i < len; i++) {
            xSum += x[i];
            ySum += y[i];
        }

        xAvg = xSum / len;
        yAvg = ySum / len;

        double upperSum = 0, lowerSum1 = 0, lowerSum2 = 0;

        for (int i = 0; i < len; i++) {
            upperSum += (x[i] - xAvg) * (y[i] - yAvg);
            lowerSum1 += pow(x[i] - xAvg, 2);
            lowerSum2 += pow(y[i] - yAvg, 2);
        }

        return upperSum / sqrt(lowerSum1 * lowerSum2);
    }

    public static double correlateByPirson(List<Double> x, List<Double> y) {
        if (x.size() != y.size()) {
            throw new java.lang.NumberFormatException();
        }
        return correlateByPirson(fromListToArray(x), fromListToArray(y));
    }

    private static double[] fromListToArray(List<Double> list) {
        int len = list.size();
        double[] array = new double[len];
        for (int i = 0; i < len; i++)
            array[i] = list.get(i);

        return array;
    }
}
