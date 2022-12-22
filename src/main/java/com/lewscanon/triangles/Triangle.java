package com.lewscanon.triangles;

import java.util.Arrays;

/** Triangle implementation. */
public class Triangle implements Trisided {
    private static final String NULL_SIDES = "Forbidden null value for sides";
    private static final String WRONG_NUMBER_OF_SIDES
            = "Forbidden number of sides, must equal " + NUM_SIDES + " found %d: %s";
    private static final String WRONG_SIDE_LENGTHS
            = "Forbidden lengths for sides, too long or short: %s";

    private static void invalidate(final double[] sides) {
        if (sides == null) {
            throw new NullPointerException(NULL_SIDES);
        }
        if (sides.length != NUM_SIDES) {
            throw new IllegalArgumentException(String.format(WRONG_NUMBER_OF_SIDES,
                    sides.length, Arrays.toString(sides)));
        }
        if (! Trisided.areValid(sides)) {
            throw new IllegalArgumentException(String.format(WRONG_SIDE_LENGTHS,
                    Arrays.toString(sides)));
        }
    }

    private final double[] sides;
    private final String representation;

    /**
     * Constructor.
     * @param sides the lengths of the sides.
     */
    public Triangle(final double[] sides) {
        invalidate(sides);
        this.sides = sides;
        assert Trisided.areValid(this.sides);
        representation = "sides " + Arrays.toString(this.sides);
    }

    @Override
    public double[] getSides() {
        return Arrays.copyOf(sides, sides.length);  // avoid array mutation
    }

    @Override
    public String toString() {
        return representation;
    }

}
