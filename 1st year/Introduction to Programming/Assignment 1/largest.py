#--------------------------------------------------------------------------
# Largest
# File:        largest.py
# Description:
#   Calculate the largest input
#
# History:     2 Sept 2022
#
#---------------------------------------------------------------------------


def enterThreIntegers():
    try:
        print("Please provide three integers A, B, C.")                     #asks to enter 3 different integers
        int_a = int(input("Enter A: "))
        int_b = int(input("Enter B: "))
        int_c = int(input("Enter C: "))

        largest = int_a                                                     #declare directly that a is the largest

        if (int_a > int_b):
            if (int_a > int_c):                                             
                largest = int_a
            else:
                largest = int_c
        elif (int_b > int_c):
            largest = int_b
        else:
            largest = int_c
            

        print("\nThe largest number is", largest)
        
    except ValueError:
        print("You entered an wrong value")                             #handle strings and float
        enterThreIntegers()
        
enterThreIntegers()