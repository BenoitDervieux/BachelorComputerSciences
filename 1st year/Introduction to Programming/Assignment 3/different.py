# --------------------------------------------------------------------------
# Different Values
# File:       different.py
# Description:
#   Returns a sorted list of the unique elements in the list
#
# History:     9 Oct 2022
#
# ---------------------------------------------------------------------------

import random as rd

lst = []
# initialize a list


def create_list(lst):
    for i in range(100):
        lst.append(rd.randrange(1, 200))
        # Add 100 random numbers in the list (they are from 0 to 200)


def different(lst):
    set_to_store = set(lst)
    # Transform the values of the list into a set
    lst.clear()
    # Clear the list
    for i in set_to_store:
        lst.append(i)
    # Add all the values back into the list
    return lst
    # Returns the list


create_list(lst)
print('Differents integers:', different(lst), sep='\n')
# Print the list with no double
