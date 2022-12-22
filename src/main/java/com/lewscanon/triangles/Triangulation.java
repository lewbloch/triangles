package com.lewscanon.triangles;

/**
 * Exercise the logic around triangles.
 * For each test case determine the {@code Triangle} type
 * while handling erroneous triangle specifications (illegal sides).
 */
public class Triangulation {

    /**
     * Drive a test use of the {@code Trisided} type.
     * @param args command arguments.
     */
    public static void main(String[] args) {
        for (double[] sides : obtainSides()) {  // run through the sides data
            try {
                final Trisided triangle = new Triangle(sides);
                System.out.println(
                        "The triangle " + triangle + " is " + triangle.getTriangleType());
            }
            catch (NullPointerException | IllegalArgumentException exc) {
                //noinspection ThrowablePrintedToSystemOut
                System.out.println(exc);
            }
        }
    }

    /**
     * Deliver rows of test data.
     * @return rows of test data.
     */
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
