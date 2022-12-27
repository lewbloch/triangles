package com.lewscanon.triangles;

import java.util.Arrays;

/** Triangle implementation. */
public class Triangle implements Trisided {

    private final double[] sides;
    private final String representation;

    /**
     * Constructor.
     * @param sides the lengths of the sides.
     */
    @SuppressWarnings("AssertionCanBeIf")
    public Triangle(final double[] sides) {
        invalidate(sides);
        this.sides = sides;
        assert areValid(this.sides);
        representation = "sides " + Arrays.toString(this.sides);
    }

    @Override
    public final double[] getSides() {
        return Arrays.copyOf(sides, sides.length);  // avoid array mutation
    }

    @Override
    public String toString() {
        return representation;
    }

}
