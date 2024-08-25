#--------------------------------------------------------------------------
# Monthly income
# File:        tax.py
# Description:
#   Calculate the amount of tax you might pay depending of your annual salary
#
# History:     2 Sept 2022
#
#---------------------------------------------------------------------------

import sys

def shouldContinue():                                                                   #ask if we want to continue
    answer = input("Do you want to continue? (Y/N)")
    if (answer.lower() == 'y' or answer.lower() == 'yes' or answer.lower() == 'ye'):
        taxToPay()
    elif(answer.lower() == 'n' or answer.lower() == 'no'):
        sys.exit(1)
    else:
        print("I didn\'t understand")
        taxToPay()

def taxToPay():
    try:
        monthly_income = int(input('Please provide monthtly income: '))

        first_type = 38000
        second_type = 50000

        impostion_one = 0.3
        imposition_two = 0.35
        imposition_three = 0.40

        tax = 0

        if (monthly_income >= first_type):
            tax += first_type*impostion_one
            monthly_income = monthly_income - first_type
            if (monthly_income > (second_type - first_type)):
                tax += (second_type - first_type)*imposition_two
                monthly_income = monthly_income -(second_type - first_type)
                if (monthly_income > 0):
                    tax += monthly_income*imposition_three
            else:
                tax += monthly_income*imposition_two
        else:
            tax += monthly_income*impostion_one
            
        print('Corresponding income tax: ', round(tax))
        shouldContinue()
        
    except ValueError:
        print("You entered a wrong value")
        taxToPay()

taxToPay()