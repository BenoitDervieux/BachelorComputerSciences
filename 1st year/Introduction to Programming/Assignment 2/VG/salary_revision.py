# --------------------------------------------------------------------------
# Salary
# File:        salary_revision.py
# Description:
#   This program computes the median, the average and the gap
#   between the highest and the lowest salary of a serie
#   of salaries
#
# History:     17 Sept 2022
#
# ---------------------------------------------------------------------------


s = input('Provide salaries (separed with a space): ')
# we ask the user to provide a series of salarie
# separed only with a space

salaries = s.split()
# we crate a list by seperating through their
# space with the function split()

buffer = ''
# We create a buffer to sort the salaries
# this has been made before the lecture 6
# otherwise we could use sort()

for i in range(len(salaries)):
    for j in range(len(salaries) - 1):
        # we sort the list by iterating through it
        if int(salaries[j+1]) < int(salaries[j]):
            buffer = salaries[j + 1]
            salaries[j + 1] = salaries[j]
            salaries[j] = buffer

median = 0
# We declare the variable median

if len(salaries) % 2 == 0:
    # If the list if even, we calculate the 2 middles
    # values to get the media
    median = (int(salaries[int(len(salaries) / 2)]) +
              int(salaries[int(len(salaries)/2) - 1])) / 2
    median = int(round(median))
elif len(salaries) % 2 == 1:
    # if the list if odd, we take the middle value
    median = int(salaries[int(len(salaries)/2)])

print('Median:', median)
# we print the media

average = 0
# We declare the variable average which will
# help us making a sum of salaries
for i in range(len(salaries)):
    average += int(salaries[i])
    # we iterate through all the salaries
    # to get the total sum
average = average / len(salaries)
# we divide this sum by the number of salaries
# we have in the list

print("Average:", int(round(average, 0)))
# we print the rounded average transformed into an int


gap = int(salaries[int(len(salaries) - 1)]) - int(salaries[0])
# we calculate the gap using the lowet and highest salary
# we could use max or min but as our list is sorted
# the first and last term are supposed to be highest and lowest

print('Gap:', int(round(gap)))
# we print the gap, rounded and changed
# into an int
