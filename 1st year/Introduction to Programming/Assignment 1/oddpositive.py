#--------------------------------------------------------------------------
# Oddpositive
# File:        oddpositive.py
# Description:
#   Select a random number and tells if it is positive, negative, odd and even
#
# History:     2 Sept 2022
#
#---------------------------------------------------------------------------

import random
import sys

def shouldContinue():
    answer = input("Do you want to continue? (Y/N)")
    if (answer.lower() == 'y' or answer.lower() == 'yes' or answer.lower() == 'ye'):
        play()
    elif(answer.lower() == 'n' or answer.lower() == 'no'):
        sys.exit(1)
    else:
        print("I didn\'t understand")
        shouldContinue()
        
def play():
    x = random.randint(-10, 10)
    number_odd = ''
    is_negative_or_not = ''

    def isOdd(n):
        n = n % 2
        if (n == 1):
            return 'odd'
        else:
            return 'even'

    def isNegative(m):
        if (m == 0):
            return 'neither positive nor negative'
        elif (m < 0):
            return 'negative'
        else:
            return 'positive'
        
    number_odd = isOdd(x)
    is_negative_or_not = isNegative(x)

    print("The generated number is", x)
    print(x, 'is', number_odd, 'and', is_negative_or_not)
    shouldContinue()
    
play()

