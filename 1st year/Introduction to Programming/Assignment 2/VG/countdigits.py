# --------------------------------------------------------------------------
# Counting Digits
# File:        countdigits.py
# Description:
#   Count the zeros, the even and odd numbers of a number
#
# History:     19 Sept 2022
#
# ---------------------------------------------------------------------------

import sys


def number_of():
    large_positive_integer = input('Enter a large positive integer: ')
    # input that asks to enter a large integer
    the_int_version = int(large_positive_integer)
    # Transform the number into an int to check if it's positive

    if the_int_version < 1:
        # check if it's a positive integer. if not exit
        print('You did not enter a large positive integer')
        sys.exit(0)

    # declare variables of 0, odd and even
    zeros = 0
    odd = 0
    even = 0

    for i in range(len(large_positive_integer)):
        # Loop in a range as big as the number of letters
        if int(large_positive_integer[i]) == 0:
            # if there is a zero, we add it in the
            # variable zero
            zeros += 1
        elif int(large_positive_integer[i]) % 2 == 0:
            # if there is an even number, we add it in the
            # variable even
            even += 1
        elif int(large_positive_integer[i]) % 2 == 1:
            # if there is an odd number, we add it in the
            # variable odd
            odd += 1

    print("\nZeros: {0}\nOdd: {1}\nEven: {2}".format(zeros, odd, even))
    # We print the 3 variables


number_of()
