#--------------------------------------------------------------------------
# Positive
# File:        positive.py
# Description:
#   Tells is an integer is positive, negative or zero
#
# History:     2 Sept 2022
#
#---------------------------------------------------------------------------
import sys

def shouldContinue():
    answer = input("Do you want to continue? (Y/N)")
    if (answer.lower() == 'y' or answer.lower() == 'yes' or answer.lower() == 'ye'):
        isPositive()
    elif(answer.lower() == 'n' or answer.lower() == 'no'):
        sys.exit(1)
    else:
        print("I didn\'t understand")
        isPositive()


def isPositive():
    try:
        number = int(input("Please provide an integer: "))                      #Ask for a number

        if (number == 0):
            print(number, "is zero")                                            #look if the number is equal to zero
        elif (number < 0):
            print(number, "is negative")                                        #look if the number is negative
        else:
            print(number, "is positive")                                        #look if the number is positive
            
    except ValueError:
        print("You entered an wrong value")                                     #handle an entered string
        isPositive()
        
    shouldContinue()

isPositive()
    
    
    
    