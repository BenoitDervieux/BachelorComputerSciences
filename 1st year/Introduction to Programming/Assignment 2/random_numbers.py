# --------------------------------------------------------------------------
# Random numbers
# File:       random_numbers.py
# Description:
#   Generate and prints n random numbers in the interval [1, 100]
#   Print the average value, the smallest number and the largest number
#
# History:     15 Sept 2022
#
# --------------------------------------------------------------------------

import random


def main():
    # ask the user to enter a number between 1 and 100
    numbers_of_random = int(input('Enter number of integers between' +
                                  '1 and 100 to be generated: '))
    # tell the user if the entered number is outside of [1, 100]
    if numbers_of_random < 1 or numbers_of_random > 100:
        print('You need to enter a number between 1 and 100')
        main()
        # restart the function

    actual_number = 0
    # local variables to store the numbers

    min_number = 0
    max_number = 0
    count = 0
    sum_of_everything = 0
    average = 0

    print('Generated values: ', end=' ')
    # prints all the values

    for n in range(numbers_of_random):
        # loop depending on the user's input
        actual_number = random.randint(1, 100)
        # generate a random number
        if count == 0:
            min_number = actual_number
            # set the actual number as the min otherwise
            # it will be 0 all way long
        if actual_number <= min_number:
            # change if there is a smaller number
            min_number = actual_number
        elif actual_number >= max_number:
            # change if there is a greater number number,
            # it starts at 0 so no risk
            max_number = actual_number
        sum_of_everything += actual_number
        # sum everything
        print(actual_number, end=' ')

    print()

    average = sum_of_everything / numbers_of_random
    # calculate the average

    print("Average, min and max are", round(average, 2),
          min_number, max_number)
    # print average, min and max


main()
# main function to use
