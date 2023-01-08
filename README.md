# triangles

They asked me to code up a simple problem -

## The Triangle Identification Exercise

Given the lengths of the three sides of a triangle, identify whether it is
`equilateral`, `isosceles`, or `scalene`.

### The Simple Approach

First we just get the core logic down, more or less.

#### Code sample

##### Java

```java
/**
 * Evaluate the type of a triangle.
 * @param side1 Side one length.
 * @param side2 Side two length.
 * @param side3 Side three length.
 * @return the triangle type.
 */
public static TriangleType evaluateTriangleType(
        double side1, double side2, double side3) {
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
```

##### Python

```python
from enum import Enum


class TriangleType(Enum):
    SCALENE = 'SCALENE'
    ISOSCELES = 'ISOSCELES'
    EQUILATERAL = 'EQUILATERAL'


def evaluate_triangle_type(side1, side2, side3):
    if side1 == side2 or side1 == side3:
        if side2 == side3:
            return TriangleType.EQUILATERAL
        else:
            return TriangleType.ISOSCELES
    elif side2 == side3:
        return TriangleType.ISOSCELES
    else:
        return TriangleType.SCALENE


def obtain_sides():
    test_data = [
        [1.0, 1.0, 1.0],
        [1.0, 1.5, 1.5],
        [1.0, 1.5, 2.0],
        [1.0, 1.0, 2.0],
        [0.0, 0.0, 0.0],
        [-1.0, 0.0, 0.0],
    ]
    return test_data


if __name__ == '__main__':
    for sides in obtain_sides():
        triangle_type = evaluate_triangle_type(sides[0], sides[1], sides[2])
        print(f"The triangle with sides {sides} is {triangle_type}")

```

#### This is the beginner's level

All we've done here is the bare minimum. We have the logic but no use of it,
no error handling, no room to grow. Yet.

It doesn't really capture much of the model of a triangle. Three sides.

### Risk Analysis Leads to Growth

There's more to a triangle than having three sides. 
Okay, sure, there are also three angles, but we can derive those from the sides.
Later.

But there are other important facts about triangles. 
If one side is shorter than the difference between the other two, 
or if it's longer than the sum of the others' lengths,
you don't have a triangle. You have a line segment.

A more professional solution will display results and handle things like
sides of zero length (or less), or sides that cannot form a triangle.

For the exercise, therefore, we need a driver routine that will test
the logic and demonstrate not only correct identification of triangle types,
but correct error handling.

As well as correct doc comments.

In short, we're going to up our game.

### A Professional Approach

Here you have a professional approach to a simple problem, one on which
we can build more features, like trigonometry or graphics.

We demonstrate object orientation, exception handling, and a few idiomatic 
Java tricks. Nothing fancy, and we haven't gone functional yet.  

#### Important techniques:

* Interface driven
* Domain modeling
* Test-forward approach
* Enum pattern including friendly names
* Maven project structure
* Doc comments
* Controlled mutability
* Straightforward code
* Component structure

Java works well with a type-oriented approach focused on interfaces.

In future, if we want to implement a `RightTriangle` we could use 
multiple inheritance to combine `Trisided` with `RightSided`.

#### Code sample

##### Java

```java
package com.lewscanon.triangles;

/**
 * Exercise the logic around triangles.
 * For each test case determine the {@code Trisided} type
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
```

##### Python

```python
import math
from enum import Enum


class Triangle:
    class TriangleType(Enum):
        SCALENE = 'SCALENE'
        ISOSCELES = 'ISOSCELES'
        EQUILATERAL = 'EQUILATERAL'

    @property
    def num_sides(self):
        return 3

    def is_valid(self) -> (bool, str):
        """
            for each side (indexed modulo num_sides) check that
            its length is less than the sum of the other two sides' lengths
        """
        # if not self.sides:
        #     return False, f"Illegal sides, must not be None: {self.sides}"

        if not isinstance(self.sides, list):
            return False, f"Illegal sides, must be a list: {self.sides}"

        if len(self.sides) != self.num_sides:
            return False, f"Illegal sides, expected {self.num_sides} got {len(self.sides)}: {self.sides}"

        for side in self.sides:
            if not (isinstance(side, float) or isinstance(side, int)):
                return False, f"Illegal side lengths: {self.sides}"

        for ix in range(self.num_sides):
            side_a = self.sides[ix]
            side_x = self.sides[(ix + 1) % self.num_sides]
            side_y = self.sides[(ix + 2) % self.num_sides]

            if side_a <= abs(side_x - side_y):
                return False, f"Illegal side lengths: {self.sides}"

        return True, ""

    def __init__(self, sidez):
        self.sides = sidez
        valid, message = self.is_valid()
        assert valid, message

    @property
    def triangle_type(self) -> str:
        if self.sides[0] == self.sides[1] or self.sides[0] == self.sides[2]:
            if self.sides[1] == self.sides[2]:
                tri_type = self.TriangleType.EQUILATERAL
            else:
                tri_type = self.TriangleType.ISOSCELES
        elif self.sides[1] == self.sides[2]:
            tri_type = self.TriangleType.ISOSCELES
        else:
            tri_type = self.TriangleType.SCALENE

        assert tri_type is not None
        return str(tri_type).split('.')[1]


def obtain_sides():
    test_data = [
        [1.0, 1.0, 1.0],
        [1.0, 1.5, 1.5],
        [1.0, 1.5, 2.0],
        [1.5, 1.5, 1.0],
        [1.5, 2.0, 1.0],
        [1.0, 0.7, 0.5],
        [math.e, math.pi, math.e],
        [math.e, math.pi, math.sqrt(math.e) * math.sqrt(math.e)],
        [math.e, math.pi, math.e * 1.5],
        [1, 0.7, 0.5],
        [1.0, 0.7, 0.5, 0.6],
        [1.0, 0.7, ],
        [1.0, 1.0, 2.0],
        [1.0, 2.0, 1.0],
        [1.0, 2.0, 1.0],
        [0.0, 0.0, 0.0],
        [1.0, 0.0, 0.5],
        [-1.0, 0.0, 0.0],
        [-1.0, 0.0, 0.5],
        [-1.0, -1.5, -2.0],
        [-1.0, None, -2.0],
        [1.0, 2.0, "unicorn"],
        None,
        1.0,
        "unicorn",
    ]
    yield from test_data.__iter__()


if __name__ == '__main__':
    for sides in obtain_sides():
        try:
            triangle = Triangle(sides)
            print(f"The triangle with sides {sides} is {triangle.triangle_type}")
        except AssertionError as err:
            print(f"{err}")
```

#### This is the professional's level

We hear it's good to start with a _Minimum Viable Product_ (MVP), but 
most of the time we start instead with a Minimum Product, and skimp on 
the "viable" part.

Here we have a minimum viable product, which actually is viable despite 
its clearly initial nature.

This is how we stay nimble in the face of near-infinite demand for software.
Not only do we make sure even simple things are useful, that is, viable,
but we build them to link up with other viable parts to make a truly viable
whole.

### A Way Forward

If you know TestNG, you probably recognize that the `obtainSides()` method 
looks a lot like a `DataProvider`, a method that feeds rows of test data 
to a test scenario.

It also looks something like a Gherkin data table with different punctuation.

This implementation omits a logging aspect. Given that a real use of something 
like the `Trisided` type here would be in core code, most likely, it probably 
wouldn't warrant a logging aspect. The driver routine in `Triangulation` might.

I haven't thought it all the way through, but I don't see any advantage in 
inheriting a triangle type from some sort of polygon or other overarching 
geometric concept. Inheritance is to be used sparingly, other than almost always 
from interface to implementation. Implementation inheritance is not the first 
thing one should reach for.

Have fun delving into the code.
