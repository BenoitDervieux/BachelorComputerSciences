# --------------------------------------------------------------------------
# Change to for
# File:        change_to_for.py
# Description:
#   Change a program using a while loop to a for loop
#
# History:     8 Sept 2022
#
# ---------------------------------------------------------------------------

count = 0
# Declare the variable count
sum = 0
# Declare the variable sum

for count in range(100):
    # for range is easier to use, it still goes from 0 to 99
    sum = sum + count
    # we sum all the number from 0 to 99

print(sum)
# it prints the total sum
