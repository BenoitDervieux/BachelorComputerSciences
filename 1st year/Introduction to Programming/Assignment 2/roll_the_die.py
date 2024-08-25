# --------------------------------------------------------------------------
# Rolling the die
# File:       rill_the_die.py
# Description:
#   Calculate the difference in percentage between the
#   face that turned up the most and the one
#   that turned up the least
#
# History:     15 Sept 2022
#
# ---------------------------------------------------------------------------

import random

rolls = 10
# describe the number of rolls we start with


# store the information to count the number of rolls
# per face, here we have the faces: 1, 2, 3, 4, 5 and 6
un = 0
deux = 0
trois = 0
quatre = 0
cinq = 0
six = 0

for n in range(20):
    # We repeat the experiment 20 times
    for n in range(rolls):
        # We have a number of rolls and iterate into it
        n = random.randint(1, 6)
        # we generate a random number between 1 and 6
        if (n == 1):
            # we add in each variable the number
            # corresponding to the face we had
            un += 1
        elif (n == 2):
            deux += 1
        elif (n == 3):
            trois += 1
        elif (n == 4):
            quatre += 1
        elif (n == 5):
            cinq += 1
        elif (n == 6):
            six += 1
    minimum_value = min(un, deux, trois, quatre, cinq, six)
    # we get the minimum value among all the variables
    maximum_value = max(un, deux, trois, quatre, cinq, six)
    # we get the maximum value among all the variables
    difference = ((maximum_value - minimum_value)/maximum_value)*100
    # We calculate the difference as explained in the description
    print("For {0} rolls, the difference is {1}%".format(rolls,
                                                         round(difference, 2)))
    # we round the result after 2 digits
    rolls *= 2
    # we duplicate the number of rolls for the next round
