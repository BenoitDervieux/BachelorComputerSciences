# --------------------------------------------------------------------------
# Reading Star Wars Character
# File:       reading_star_wars.py
# Description:
#   This program read and creates a list of
#   Star Wars characters (as data class objects)
#
# History:     9 Oct 2022
#
# ---------------------------------------------------------------------------

import Character
import os

path = os.getcwd()
windows = False
linux = True
if '/' in path:
    linux = True
else:
    windows = True
# Detects if os is Linux of Windows


def get_real_path():
    # Function to get the real directory
    filename = os.path.basename(__file__)
    # Get the filename
    foldersname_complete = os.path.abspath(__file__)
    # Get the whole path

    folderspath = foldersname_complete.rstrip(filename)
    # Substract the name from the whole path
    folderspath = folderspath.rstrip('/')
    # Substract the '/' from the path
    return folderspath


def main():
    path = get_real_path()
    # Look for the path where the file is
    os.chdir(get_real_path())
    # Move to the path
    if windows is True:
        input_file = 'starwars.txt'
    else:
        input_file = '/starwars.txt'
    lst = []
    file = open(path + input_file, 'r')
    # Open the file with the characters
    for line in file:
        name, kind, planet = line.split(', ')
        # Take the attributes of the character for each line
        if '\n' in planet:
            planet = planet.rstrip('\n')
            # Cut the \n for every word
        lst.append(Character.Character(name, kind, planet))
        # append to a list in order to store the characters and their
        # Characteristics
    file.close()
    print('A Collection of Star Wars Characters:')
    for i in range(len(lst)):
        print(lst[i])
        # Print all the characters


main()
