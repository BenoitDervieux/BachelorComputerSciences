#--------------------------------------------------------------------------
# Volume
# File:        volume.py
# Description:
#   Calculate the volume of a sphere
#
# History:     2 Sept 2022
#
#---------------------------------------------------------------------------

import math, sys

def shouldContinue():                                                                   #ask if we want to continue
    answer = input("Do you want to continue? (Y/N)")
    if (answer.lower() == 'y' or answer.lower() == 'yes' or answer.lower() == 'ye'):
        calculateTheVolume()
    elif(answer.lower() == 'n' or answer.lower() == 'no'):
        sys.exit(1)
    else:
        print("I didn\'t understand")
        calculateTheVolume()

def calculateTheVolume():
    try: 
        radius = float(input("Provide a radius: "))                                     #ask for a radius
        volume = float(round((4/3)*math.pi*radius**3, 1))                               #calculate the volume

    except ValueError:
        print("You entered a wrong value")
        calculateTheVolume()

    print("The volume is " + str(volume))                                               #output the volume
    shouldContinue()

calculateTheVolume()