#--------------------------------------------------------------------------
# Quadric Equation
# File:        quadricequation.py
# Description:
#   Calculate the solutions of an equation having as form ax2 + bx + c
#
# History:     5 Sept 2022
#
#---------------------------------------------------------------------------
import math                                                                         #import the math library for the sqrt function
import sys


def shouldContinue():                                                               #asks the user if he/she wants to re-try the program
    answer = input("Do you want to continue? (Y/N)")
    if (answer.lower() == 'y' or answer.lower() == 'yes' or answer.lower() == 'ye'):
        quadraticNumbers()
    elif(answer.lower() == 'n' or answer.lower() == 'no'):
        sys.exit(1)
    else:
        print("I didn\'t understand")
        quadraticNumbers()

def quadraticNumbers():
    try:
        a_quadratic = float(input("A: "))                                               #ask the user to input the values
        b_quadratic = float(input("B: "))
        c_quadratic = float(input("C: "))


        is_there_solution = b_quadratic**2 - 4*a_quadratic*c_quadratic                 #calculate how many solutions there are for the equation ax2 + bx + c = 0

        if (a_quadratic == 0 and b_quadratic != 0):
            solution = -c_quadratic / b_quadratic                                       # handles the case a = 0

        elif (a_quadratic == 0 and b_quadratic == 0):                                   # handles the case a = 0 and b = 0
            print("I don't think we can call that a quadric equation isn\'t it?")
            
        else:
            if (is_there_solution > 0):                                                 #calculate the case of 2 solution if it's superior to 0
                solution_a = (-b_quadratic + math.sqrt(is_there_solution))/(2*a_quadratic) # calculate the two roots
                solution_b = (-b_quadratic - math.sqrt(is_there_solution))/(2*a_quadratic)
                print(f"There are two solutions, namely", solution_a, 'and', solution_b) # prints out the solutions

            elif (is_there_solution == 0):                                              #calculate the only solution
                solution = -b_quadratic/(2*a_quadratic)
                print(f"There is one solution, which is", solution)                     #prints out when there is only one solution
                    
            else:
                print('There are no solutions')                                         #informs that there is no solution
        shouldContinue()
        
    except ValueError:
        print("You entered an wrong value")                                     #handle an entered string
        quadraticNumbers()
        
quadraticNumbers()
