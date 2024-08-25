#--------------------------------------------------------------------------
# Sum of three
# File:        sumofthree.py
# Description:
#   Add every digit from a three digit number
#
# History:     2 Sept 2022
#
#---------------------------------------------------------------------------

import sys;

def shouldContinue():                                                                   #ask if we want to continue
    answer = input("Do you want to continue? (Y/N)")
    if (answer.lower() == 'y' or answer.lower() == 'yes' or answer.lower() == 'ye'):
        addThreeDigit()
    elif(answer.lower() == 'n' or answer.lower() == 'no'):
        sys.exit(1)
    else:
        print("I didn\'t understand")
        shouldContinue()


def addThreeDigit():
    try:
        threedigit = input("Provide a three digit number: ")
        zero_check = str(threedigit)
        threedigit = int(threedigit)
        
        if(threedigit < 0 ):                                            #handle negative numbers 
            print('You entered a negative number')
            addThreeDigit()            
            
        is_it_three_digit = len(str(threedigit))

        if (threedigit == 000):                                         #handle 000
            print("That's not even a number")
            addThreeDigit()
        
        if (zero_check[0] == '0'):                                      #handles a 3 digit in the form 0XX or 00x by adding +1 
            is_it_three_digit += 1                                      # to the variable that checks if it has 3 digits59
            if (zero_check[1] == '0'):
                is_it_three_digit += 1
                
                
            

        if (is_it_three_digit != 3):                                    #says that it's not a 3 digit number
            print("You did not enter a 3 digit number")
            addThreeDigit()



        total = 0

        for i in range(3):                                  
            total += threedigit % 10                            #I am adding the last digit of the number
            threedigit = threedigit//10                         #I am diving by 10 and throwing the last digit
            
        print('Sum of digit', total)
        shouldContinue()
        
    except ValueError:
        print("You did not enter a number")                                     #handle an entered string
        addThreeDigit()

addThreeDigit()