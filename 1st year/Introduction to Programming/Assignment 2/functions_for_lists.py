# --------------------------------------------------------------------------
# List Functions
# File:       functions_for_lists.py
# Description:
#   Generate list of n integers
#   Take a list and returns only odd values
#   Takes a list as input and creates a new list with all the values squared
#   Returns a new list with only the values between start and stop in the list
#
# History:     15 Sept 2022
#
# ---------------------------------------------------------------------------

import random

new_list = []
odd_list = []
squared_list = []


def random_num_list(n):
    # Generates a list of n integers
    list_of_n = [random.randint(0, 100) for number in range(n)]
    return list_of_n


new_list = random_num_list(5)
print('Here is the list:', new_list)


def only_odd(lst):
    # Takes a list as input and returns a list with only the odd values
    lst = [numbers for numbers in lst if numbers % 2 == 1]
    return lst


odd_list = only_odd(new_list)
# Generate a new list with only odd number
print('Odd in it are:', odd_list)


def square(lst):
    # Takes a list as input and creates a new list with all the values squared
    lst = [numbers*numbers for numbers in lst]
    return lst


squared_list = square(new_list)
# take the main list and square all its values
print('Let\'s square each number:', squared_list)


def sublist(lst, start, stop):
    # Returns a new list with only the values
    # between start and stop in the list
    lst = lst[start:stop]
    return lst


sub_list = sublist(new_list, 1, 4)
# returns the 3 middle value
print('Only the three middle value:', sub_list)
