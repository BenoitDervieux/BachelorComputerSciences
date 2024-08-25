#--------------------------------------------------------------------------
# Alarm
# File:        alarm.py
# Description:
#   Tells the time when the alarm will go off when you add an amount of hours to it
#
# History:     2 Sept 2022
#
#---------------------------------------------------------------------------

import sys

def whatTime():                                                 #the function ask for the time
    try: 
        time = int(input("What time is it? "))                  #Ask for the time
        if time > 24 or time < 0 :                              #if the time you entered is negative or greater than 24 then
            print("You entered a wrong value")                  #the program tells you you entered a wrong value and starts again
            whatTime()                                          
        hours = int(input("How many hours to the alarm? "))     #Input for the number of hours later

        go_off = (time + (hours % 24)) % 24                     #Compute the time and returns the extra hours in 24h format
        go_off_format = "{:.2f}".format(go_off)

    except ValueError:                                          #if entered a wrong value like a string, then it tells you
        print("You entered a wrong value")                      #that you entered a wrong value and it starts again
        whatTime()
    

    print("\nThe alarm will go off at " + str(go_off_format))   #displays the time it goes off
    sys.exit(1)

whatTime()