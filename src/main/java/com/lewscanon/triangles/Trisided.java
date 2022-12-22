package com.lewscanon.triangles;

import java.util.Objects;

/** The characteristics and behavior of a triangle. */
public interface Trisided {
    /** The number of sides. */
    int NUM_SIDES = 3;

    /**
     * Validate a triangle's sides' values.
     * @param sidesTest the sides to validate.
     * @return whether the specified sides are valid.
     */
    static boolean areValid(final double [] sidesTest) {
        final double [] sides = Objects.requireNonNull(sidesTest);
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
            isValid = (sideA > 0.0)
                    && (sideA < (sideX + sideY));
        }
        return isValid;
    }

    /**
     * Get the sides' lengths.
     * @return an array of the sides' lengths.
     */
    double [] getSides();

    /**
     * Get the number of sides.
     * @return the number of sides.
     */
    @SuppressWarnings("unused")
    default int getNumSides() {
        return getSides().length;
    }

    /**
     * Evaluate the triangle type.
     * @return the triangle type.
     */
    default TriangleType getTriangleType() {
        double [] sides = getSides();
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
