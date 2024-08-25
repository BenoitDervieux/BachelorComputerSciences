# --------------------------------------------------------------------------
# Counting Occurrences
# File:       occurrences.py
# Description:
#   Returns a sorted dictionary of the number of occurrences of
#   integers in the list sent to the function
#
# History:     9 Oct 2022
#
# ---------------------------------------------------------------------------

import random as rd

lst = []
# The list where we will store the numbers
dict_to_store = {}
# Dictionary where we will store the occurencies


for i in range(100):
    lst.append(rd.randrange(1, 11))
# First we generate the numbers here


def get_key(tpl):
    return tpl[0]
# This is the function that helps sorting


def count_occurrences(lst):
    dict_for_values = {}
    # initialize the dict
    lst = sorted(lst)
    for i in lst:
        # Iterate inside the values of the list
        if i in dict_for_values:
            # If the value already exists
            dict_for_values[i] += 1
            # Add one to the number of occurrences
        else:
            dict_for_values[i] = 1
            # Else create the new entry
    return dict_for_values
    # Return the dictionary


dict_to_store = count_occurrences(lst)
# Dictionary using the values generated in the list


for i in dict_to_store:
    print('{0:2}:{1:8}'.format(i, dict_to_store[i]))
    # Print all the values of the dictionary
