# --------------------------------------------------------------------------
# Moving around
# File:       move_dir.py
# Description:
#   The program allows to user to change directory, list them
#   or list the files inside
#
# History:     9 Oct 2022
#
# ---------------------------------------------------------------------------

import sys
import os


decision = 0


def ask_user():
    print('1. List directories')
    print('2. Change directory')
    print('3. List files')
    print('4. Quit')

    decision = int(input('What do you want to do? '))
    # Ask the user what to do
    print()
    print(f'==> {decision}')
    # print the decision's taken
    return decision


def list_dir(dir_path):
    # initialize the number of files
    name_of_directories = []
    with os.scandir(dir_path) as it:
        # method found on https://docs.python.org/3/library/os.html#os.scandir
        # Help counting the exact number of files
        # and not files and folders
        for entry in it:
            if entry.is_dir():
                # Check if it is a directory
                name_of_directories.append(entry.name)
                # appends it into a list
    return name_of_directories


def change_dir(dir_path):
    new_dir = input('Name of the directory to enter: ')
    # Ask the user which directory to go to
    name_of_directories = []
    # We create a list of directories
    with os.scandir(dir_path) as it:
        for entry in it:
            if entry.is_dir():
                # check it it's a directory
                name_of_directories.append(entry.name)
                # appends it into a list
        if new_dir == '..':
            # if the user enters '..' we go back
            os.chdir(new_dir)
            print('You moved to:', os.getcwd())
            return os.getcwd()
        if new_dir not in name_of_directories:
            # if the user enters a directlory that doesn't exist
            print('The files you asked can\'t be accessed')
            return
        else:
            os.chdir(new_dir)
            # if the user enters a good directory
            print('You moved to:', os.getcwd())
            return os.getcwd()


def print_files(dir_path):
    # Returns the names of the files
    name_of_files = []
    # We create a list of files
    with os.scandir(dir_path) as it:
        # method found on https://docs.python.org/3/library/os.html#os.scandir
        # Help counting the exact number of files
        # and not files and folders
        for entry in it:
            if entry.is_file():
                # with this method is_file()
                name_of_files.append(entry.name)
                # appends to a list and returns a list
    return name_of_files


while decision != 4:
    decision = ask_user()
    # we ask the user up until she exits
    path = os.getcwd()
    # get the current path
    if decision == 1:
        lst = list_dir(path)
        lst = [print(i) for i in lst]
        # print the list that it returns
    if decision == 2:
        path = change_dir(path)
        # Change the directory
    if decision == 3:
        lst = print_files(path)
        lst = [print(i) for i in lst]
        # Print all the directories
    if decision == 4:
        # Exit the loop
        break
    print()

sys.exit()
# Stop the program
