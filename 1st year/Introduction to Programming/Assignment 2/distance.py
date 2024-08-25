# --------------------------------------------------------------------------
# Distance
# File:       distance.py
# Description:
#   Calcualte the distance between 2 points
#
# History:     15 Sept 2022
#
# ---------------------------------------------------------------------------

from math import sqrt


def enter_coordinates():
    x1 = float(input('Enter x1: '))
    y1 = float(input('Enter y1: '))
    x2 = float(input('Enter x2: '))
    y2 = float(input('Enter y2: '))
    return x1, y1, x2, y2


def distance(x1, y1, x2, y2):
    distance = sqrt((x1 - x2)**2 + (y1 - y2)**2)
    return distance


def print_the_distance(x1, y1, x2, y2, distance):
    print("The distance between ({0},{1}) and ({2},{3}) is {4}".format
          (x1, y1, x2, y2, round(distance, 3)))


def main():
    x1, y1, x2, y2 = enter_coordinates()
    distance_value = distance(x1, y1, x2, y2)
    print_the_distance(x1, y1, x2, y2, distance_value)


main()
