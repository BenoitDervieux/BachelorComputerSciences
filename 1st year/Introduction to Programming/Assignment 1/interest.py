#--------------------------------------------------------------------------
# Interest
# File:        interest.py
# Description:
#   Calculate a certain interest on a sum of money
#
# History:     2 Sept 2022
#
#---------------------------------------------------------------------------

import sys

def calculateInterest():
    try:
        savings = int(input("Initial savings: "))                               #ask the number of savings
        
        if (savings <= 0):                                                      #handle a negative number
            print("You entered a wrong number")
            savings=0
            calculateInterest()
                                       
        percentage = int(input("Interest rate (in percentages): "))             #ask the percentage
        
        if (percentage <= 0):
            print("You entered a wrong number")                                 #handle a negative number
            percentage =0
            calculateInterest()
            
        number_of_years = int(input("Number of years: "))                       #ask for the number of year
        
        if (number_of_years <= 0):
            print("You entered a wrong number")                                 #handle a negative number
            number_of_years =0
            calculateInterest()

    except ValueError:
        print("You entered an wrong value")                                     #handle an entered string
        calculateInterest()
        
    try:
        for i in range(number_of_years):                                        #iterate through the years
            savings = savings + savings*(percentage/100)                        #calculte the savings through the years
        
    except NameError:                                                           #handle a name error
        calculateInterest()
        
    print("\nThe value of your savings after " + str(number_of_years) + " years is: " + str(round(savings)))
    sys.exit(1)                                                                 #print and exit the program
    
calculateInterest()