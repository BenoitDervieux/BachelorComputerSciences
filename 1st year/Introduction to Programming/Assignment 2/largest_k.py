# --------------------------------------------------------------------------
# Largest K
# File:       largest_k.py
# Description:
#   Compute the largest k number such as 0 + 2 + 4 + 6 + 8 ... + k
#
# History:     8 Sept 2022
#
# ---------------------------------------------------------------------------


def k_number():
    # A fonction is defined to restart after someone
    # entered a negative integer
    number = int(input('Enter a positive integer: '))

    if (number <= 0):
        print("You did not enter a positive integer")
        # if someone doesn't enter a positive integer
        # he/she is informed
        return k_number()
    # we return the function

    comparison = 0
    # this variable is the sum of numbers
    k = 0
    # this one is the highest k that will before the sum
    # will be higher tham the entered value

    while comparison < number:
        # We're adding 2 up until the sum is higher than
        # the number entered
        k += 2
        # We're adding +2 to ke eveytime
        comparison += k
        # we're summing it up

    k -= 2
    # We're taking off 2 because we were noticed when k
    # was higher than the number but we want the previous integer

    print("{0} is the largest k such as 0+2+4+6+...+k < {1}".format(k, number))
    # we print out the number


k_number()
# we call the function
