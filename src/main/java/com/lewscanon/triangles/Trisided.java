package com.lewscanon.triangles;

import java.util.Arrays;
import java.util.Objects;

/** The characteristics and behavior of a triangle. */
public interface Trisided {
    /** The number of sides. */
    int NUM_SIDES = 3;

    /** Error message: null sides value. */
    String NULL_SIDES = "Forbidden null value for sides";
    /** Error message: wrong number of sides. */
    String WRONG_NUMBER_OF_SIDES
            = "Forbidden number of sides, must equal " + NUM_SIDES
                + " found %d: %s";
    /** Error message: invalid side length values. */
    String WRONG_SIDE_LENGTHS
            = "Forbidden lengths for sides, too long or short: %s";

    /**
     * Validate a trisided figure's sides' values.
     * @param sides the sides to validate.
     * @return whether the specified sides are valid.
     */
    default boolean areValid(final double[] sides) {
        Objects.requireNonNull(sides);
        boolean isValid = (sides.length == NUM_SIDES);  // confirm the number of sides
        /* for each side (indexed modulo NUM_SIDES) check that its length is:
         *  - positive
         *  - greater than the difference between the other two sides' lengths
         *  - less than the sum of the other two sides' lengths
         */
        for (int ix = 0; isValid && ix < sides.length; ++ix) {
            final double sideA = sides[ix];
            final double sideX = sides[(ix + 1) % NUM_SIDES];
            final double sideY = sides[(ix + 2) % NUM_SIDES];
            isValid = sideA < (sideX + sideY);
        }
        return isValid;
    }

    /**
     * Get the sides' lengths.
     * @return an array of the sides' lengths.
     */
    double[] getSides();

    /**
     * Get the number of sides.
     * @return the number of sides.
     */
    @SuppressWarnings("unused")
    default int getNumberOfSides() {
        return getSides().length;
    }

    /**
     * Throw exceptions for any rules the sides violate.
     * @param sides The sides to invalidate if possible.
     */
    default void invalidate(final double[] sides) {
        if (sides == null) {
            throw new NullPointerException(NULL_SIDES);
        }
        if (sides.length != NUM_SIDES) {
            throw new IllegalArgumentException(String.format(WRONG_NUMBER_OF_SIDES,
                    sides.length, Arrays.toString(sides)));
        }
        if (! areValid(sides)) {
            throw new IllegalArgumentException(String.format(WRONG_SIDE_LENGTHS,
                    Arrays.toString(sides)));
        }
    }

    /**
     * Evaluate the triangle type.
     * @return the triangle type.
     */
    default TriangleType getTriangleType() {
        double[] sides = getSides();
        assert areValid(sides);

        if (sides[0] == sides[1] || sides[0] == sides[2]) {
            if (sides[1] == sides[2]) {
                return TriangleType.EQUILATERAL;
            } else {
                return TriangleType.ISOSCELES;
            }
        } else if (sides[1] == sides[2]) {
            return TriangleType.ISOSCELES;
        } else {
            return TriangleType.SCALENE;
        }
    }

    /* The triangle type. */
    enum TriangleType {
        /** Scalene: no two sides are of equal length. */
        SCALENE("Scalene"),
        /** Isosceles: exactly two sides are of equal length. */
        ISOSCELES("Isosceles"),
        /** Equilateral: all sides are of equal length. */
        EQUILATERAL("Equilateral");

        private final String representation;

        /**
         * Construct with a friendly name.
         * @param represent the friendly name.
         */
        TriangleType(String represent) {
            this.representation = represent;
            assert this.representation != null;
        }

        @Override
        public String toString() {
            return representation;
        }

        /**
         * Obtain the enum instance from the friendly string, or {@code null} if not found.
         * @param represent the friendly string.
         */
        @SuppressWarnings("unused")
        public static TriangleType fromString(final String represent) {
            for (TriangleType triangleType : values()) {
                if (triangleType.toString().equals(represent)) {
                    return triangleType;
                }
            }
            return TriangleType.valueOf(represent);
        }
    }
}
