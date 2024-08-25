#--------------------------------------------------------------------------
# Quote
# File:        quote.py
# Description:
#   Change a input into a quote
#
# History:     2 Sept 2022
#
#---------------------------------------------------------------------------

import sys


def shouldContinue():
    answer = input("Do you want to continue? (Y/N)")
    if (answer.lower() == 'y' or answer.lower() == 'yes' or answer.lower() == 'ye'):
        quoteThat()
    elif(answer.lower() == 'n' or answer.lower() == 'no'):
        sys.exit(1)
    else:
        print("I didn\'t understand")
        quoteThat()

def quoteThat():
    quote = input("Write a line of text: ")
    print('Quote: "' + quote + '"')
    shouldContinue()

quoteThat()