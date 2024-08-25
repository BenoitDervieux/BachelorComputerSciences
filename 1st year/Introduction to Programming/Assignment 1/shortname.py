#--------------------------------------------------------------------------
# Short Name
# File:        shortname.py
# Description:
#   Make a name shorter
#
# History:     2 Sept 2022
#
#---------------------------------------------------------------------------

import sys


def shouldContinue():
    answer = input("Do you want to continue? (Y/N)")
    if (answer.lower() == 'y' or answer.lower() == 'yes' or answer.lower() == 'ye'):
        shortName()
    elif(answer.lower() == 'n' or answer.lower() == 'no'):
        sys.exit(1)
    else:
        print("I didn\'t understand")
        shortName()


def shortName():
    first_name = input("First name: ")
    middle_name = input("Middle name: ")
    last_name = input("Last name: ")

    range_last_name = 4

    if (len(last_name) < 4):
        range_last_name = len(last_name)

    short_name = ''

    for i in range(range_last_name):
        short_name += last_name[i]
            
            
    print(f"Short name: {first_name[0]}. {middle_name[0]}. {short_name}")
    shouldContinue()
    

shortName()
