# Copyright © 2023, Lewis S. Bloch. All rights reserved.
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
