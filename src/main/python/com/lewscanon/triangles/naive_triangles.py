# Copyright © 2023, Lewis S. Bloch. All rights reserved.

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
