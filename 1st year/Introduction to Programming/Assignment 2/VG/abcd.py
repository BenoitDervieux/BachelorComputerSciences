# --------------------------------------------------------------------------
# ABCD
# File:        abcd.py
# Description:
#   We find the 4 digit number such as
#
# History:     19 Sept 2022
#
# ---------------------------------------------------------------------------


def get_number(a, b, c, d):
    """This function takes 4 letters as a string
    and computes it into a 4 number integer"""
    number = str(a) + str(b) + str(c) + str(d)
    number = int(number)
    return number


# Here, we do a quadruple nested loop in order
# to find the integer that satisfies our condition
for a in range(0, 10):
    for b in range(0, 10):
        for c in range(0, 10):
            for d in range(0, 10):
                # Our condition here is to have an integer ABCD
                # which has DCBA which is equal 4 times its value
                # We also notified that A and B should not be equal
                # to zero
                if get_number(d, c, b, a) == 4*get_number(a, b, c, d):
                    if a != 0 and d != 0:
                        print("The 4 digit number ABCD such as ", end='')
                        print("DCBA is 4 times bigger is:",
                              get_number(a, b, c, d))
