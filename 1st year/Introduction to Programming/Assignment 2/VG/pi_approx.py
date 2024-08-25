# --------------------------------------------------------------------------
# Calculating Pi
# File:        calculating.py
# Description:
#   This program calculates an approximation of Pi
#
# History:     19 Sept 2022
#
# ---------------------------------------------------------------------------

import random
import math as ma

in_the_circle = 0
# declare the variable that will stock the number
# of points in the circle

out_of_the_circle = 0
# declare the variable that will stock the number
# of points out of the circle


def simulation(times, variable_of_in, variable_of_out):
    for n in range(0, times):
        # A loop inside the number of times we
        # wish to try
        px = random.uniform(-1.0, 1.0)
        py = random.uniform(-1.0, 1.0)
        # we generate the coordinate of a points inside
        # the square of length = 2, height = 2
        # and center 0, 0

        distance = ma.sqrt(px**2 + py**2)
        # we calculate the distance from the
        # center to the point
        if distance <= 1:
            # if the distance = if the point is in the
            # circle as R = 1 we add it into the
            # variable
            variable_of_in += 1
        elif distance > 1:
            # otherwise if it's superior it means it's
            # outside of the circle
            variable_of_out += 1

    frequency = (variable_of_in * 100)/(variable_of_out + variable_of_in)
    # Here we are calculating the frequency of times we have
    # had points inside

    area_percentage = (ma.pi * 100)/4
    # here we are calculating the are the circle takes
    # inside the square

    area_filled = frequency * 4 / 100
    # the area filled is the ratio of points generated
    # inside the circle compared tp the area of the whole
    # square which is 4. That gives us an approximation of Pi

    # We calculate the frequency depending on the number of tests
    # the percentage of occupation of the square by the circle
    # and the difference between the real area and our simulation
    # We print also the percentage of area filled according
    # to the ratio we calculated previously
    print('Frequency of apparition in the circle for {0} points: {1} %'
          .format(times, frequency))
    print("Ration between a square area and a circle of R of 1: {0} %"
          .format(area_percentage))
    print("Difference between the pi area and the square area formula:",
          ma.sqrt((area_percentage - frequency)**2))
    print(f"{frequency} percent of area filled if equal to: {area_filled}")
    print()


# We compute the simulation for 100, 10000 and 1000000
# times
simulation(100, in_the_circle, out_of_the_circle)
simulation(10000, in_the_circle, out_of_the_circle)
simulation(1000000, in_the_circle, out_of_the_circle)
