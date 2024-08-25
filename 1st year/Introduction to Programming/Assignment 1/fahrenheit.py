#--------------------------------------------------------------------------
# Fahrenheit
# File:        fahrenheit.py
# Description:
#   Convert a temperature farenheit in a celcius temperature
#
# History:     2 Sept 2022
#
#---------------------------------------------------------------------------

def getCelcius():
    try:
        F = float(input("Provide a temperature in Fahrenheit: "))
        C = (F - 32)*(5/9)           #(9/5)*C +32
        floated_c = "{:.5f}".format(C)
        print("Corresponding temperature in Celsius is " + floated_c)
        
    except ValueError:
        print("This is not a valid temperature")
        getCelcius()

    except TypeError:
        print("This is not a valid temperature")
        getCelcius()

getCelcius()