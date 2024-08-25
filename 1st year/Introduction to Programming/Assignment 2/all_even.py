# --------------------------------------------------------------------------
# Sum
# File:        all_even.py
# Description:
#   This program prints out the sum of all the even numbers from 0 to 100
#
# History:     8 Sept 2022
#
# ---------------------------------------------------------------------------

sum = 0
# Declare the variable which will sum the numbers

for n in range(1, 101):
    # from 2 to 100, with a step of 2
    if n % 2 == 0:
        # check if a number is even
        sum += n
        # add the number to the sum

print("Sum of the 100 firts numbers is:", sum)
