package com.lewscanon.triangles;

import java.util.Arrays;

import static com.lewscanon.triangles.Trisided.TriangleType;

public class NaiveTriangles {

    public static void main(String[] args) {
        for (double[] sides : obtainSides()) {
            System.out.println(
                    "The triangle with sides " + Arrays.toString(sides)
                    + " is " + evaluateTriangleType(sides[0], sides[1], sides[2]));
        }
    }

    /**
     * Evaluate the type of a triangle.
     * @param side1 Side one length.
     * @param side2 Side two length.
     * @param side3 Side three length.
     * @return the triangle type.
     */
    public static TriangleType evaluateTriangleType(double side1, double side2, double side3) {
        if (side1 == side2 || side1 == side3) {
            if (side2 == side3) {
                return TriangleType.EQUILATERAL;
            } else {
                return TriangleType.ISOSCELES;
            }
        } else if (side2 == side3) {
            return TriangleType.ISOSCELES;
        } else {
            return TriangleType.SCALENE;
        }
    }

    public static double[][] obtainSides() {
        @SuppressWarnings("UnnecessaryLocalVariable")
        final double[][] testData = {
            {1.0, 1.0, 1.0},
            {1.0, 1.5, 1.5},
            {1.0, 1.5, 2.0},
            {1.0, 1.0, 2.0},
            {0.0, 0.0, 0.0},
            {-1.0, 0.0, 0.0},
        };
        return testData;
    }
}
