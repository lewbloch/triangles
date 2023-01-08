# Copyright © 2023, Lewis S. Bloch. All rights reserved.
import abc
from enum import Enum


class Polygon(metaclass=abc.ABCMeta):
    @classmethod
    def __subclasshook__(cls, subclass):
        return (hasattr(subclass, 'num_sides')
                and callable(subclass.num_sides)
                and hasattr(subclass, 'is_valid')
                and callable(subclass.is_valid)
                )
