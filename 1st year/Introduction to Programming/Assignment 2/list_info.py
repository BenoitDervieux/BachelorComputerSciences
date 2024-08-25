# --------------------------------------------------------------------------
# List information
# File:       list_info.py
# Description:
#   Create a list of 100 elements with random values from1 and 10000
#
# History:     15 Sept 2022
#
# ---------------------------------------------------------------------------

import random as r

the_list = []
# declare the list
total = 0
# declare the total variable

for i in range(0, 100):
    # loop for generating 100 values
    random_value = r.randint(1, 10000)
    # generate a value between 1 and 10 000
    the_list.append(random_value)
    # add it to the list
    total += random_value
    # add it to caculate the averafge later

max_value = max(the_list)
# take the maximum value from the list
min_value = min(the_list)
# take the minimum value from the list
average_value = total/len(the_list)
# calculate the average value
the_list.remove(max_value)
# remove the highest value from the list
second_max_value = max(the_list)
# pick up the new highest (which was the second largest)

print("Largest value in list:", max_value)
# print the max, the min, the average and the second max
print("Smallest value in list:", min_value)
print('Average value in list:', average_value)
print("second largest value in list:", second_max_value)
