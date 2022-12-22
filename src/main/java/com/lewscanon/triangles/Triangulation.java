package com.lewscanon.triangles;

/** Logic around triangles. */
public class Triangulation {

    public static void main(String[] args) {
        for (double[] sides : obtainSides()) {
            try {
                final Triangle triangle = new Triangle(sides);
                System.out.println(
                        "The triangle " + triangle + " is " + triangle.getTriangleType());
            }
            catch (NullPointerException | IllegalArgumentException exc) {
                //noinspection ThrowablePrintedToSystemOut
                System.out.println(exc);
            }
        }
    }

    public static double[][] obtainSides() {
        @SuppressWarnings("UnnecessaryLocalVariable")
        final double[][] testData = {
            {1.0, 1.0, 1.0},
            {1.0, 1.5, 1.5},
            {1.0, 1.5, 2.0},
            {1.0, 1.0, 2.0},
            {1.0, 0.9, 2.0},
            {1.0, 3.9, 2.0},
            {0.0, 0.0, 0.0},
            {0.0, 1.0, 2.0},
            {-1.0, 0.0, 0.0},
            {1.0, 0.0, },
            {1.0, 2.0, 2.2, 2.4},
                null,
        };
        return testData;
    }
}
