#--------------------------------------------------------------------------
# Change
# File:        change.py
# Description:
#   Input an amount you need to pay, the bill you're using to pay and the change you'll get
#
# History:     2 Sept 2022
#
#---------------------------------------------------------------------------
import sys

def shouldContinue():                                                                   #ask if we want to continue
    answer = input("Do you want to continue? (Y/N)")
    if (answer.lower() == 'y' or answer.lower() == 'yes' or answer.lower() == 'ye'):
        giveMyChange()
    elif(answer.lower() == 'n' or answer.lower() == 'no'):
        sys.exit(1)
    else:
        print("I didn\'t understand")
        giveMyChange()

def giveMyChange():
    try:
        price = float(input("Price: "))                                         #input the price you need to pay
        if (price < 0):                                                         #Alert that you entered a negative price
            print('Price doesn\'t correspond')                                  #Start the program again
            giveMyChange()
        payment = float(input("Payment: "))                                     #input the amount you're giving in cash
        
        if (payment < 0):                                                       #alerts that you entered a negative value, start the program again
            print("You can\'t pay something negatively")
            giveMyChange()
        if (payment < price):                                                   #alerts that you don't have enough to pay
            print("You don't have enough money to pay?")
            giveMyChange()

        change = payment - price                                                #the change is the amount you gave minus the price
        change = round(change)                               

        print("\nChange:", round(change),"kr")                                  #print the change you should get

        bills = [ ['bills',1000], ['bills',500], ['bills',200], ['bills',100], ['bills',50],['bills',20], ['coins',10], ['coins',5], ['coins',2], ['coins',1] ]    


        for i in bills:
            print_change = change/i[1]                                          #return 0 if you can't get the closest bill, one or more if you can
            if (print_change == 0):                                             #print a bill and how many times you can get it
                print((4-len(str(i[1])))*' ', end='')
                print(i[1], 'kr', i[0], ':', int(print_change))
            elif (print_change > 0):
                change = change - int(print_change)*i[1]                        #As we have a float, we need to round to the lowest by calling troncate. We can also use int() function
                print((4-len(str(i[1])))*' ', end='')
                print(i[1], 'kr', i[0], ':', int(print_change))                 #print the number of bill you can get
        
        shouldContinue()                                                             #Asks the user to continue
                
    except ValueError:                                                          #if entered a wrong value like a string, then it tells you
        print("You entered a wrong value")                                      #that you entered a wrong value and it starts again
        giveMyChange()

giveMyChange()
                