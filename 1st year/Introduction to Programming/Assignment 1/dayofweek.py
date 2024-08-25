#--------------------------------------------------------------------------
# Day of the Week
# File:        dayofweek.py
# Description:
#   Traduce an input english day of the week to a french one
#
# History:     2 Sept 2022
#
#---------------------------------------------------------------------------


def traduceTheDay():
    day_in_english = input('What day would you like to translate in French? ')                  #input a day in English


    #this is a lit of alrady traduced days, suitable for now
    day_traduction = [['Monday','Lundi'],['Tuesday', 'Mardi'], ['Wednesday','Mercredi'],['Thursday', 'Jeudi'], ['Friday', 'Vendredi'], ['Saturday', 'Samedi'], ['Sunday','Dimanche']]

    #variable that changes is True if the day is in the list
    found = False

    for i in day_traduction:                                        #interation in the list to find the corresponding day
        if (day_in_english.lower() == i[0].lower()):                #all the words are lowered to not make any distinction of characters when entered
            traduced_day = i[1]
            found = True                                            #change the variable found in True if the entered day is found
            
    if (found == True):
        print("Traduced day is:", traduced_day)
    else:
        print('Couldn\'t find the day you mentionned, try again')   #asks the user to enter a day again
        traduceTheDay()

traduceTheDay()

