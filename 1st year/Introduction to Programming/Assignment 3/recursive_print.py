# --------------------------------------------------------------------------
# Recursive Print
# File:       recursive_print.py
# Description:
#   The program prints the name of the directories
#
# History:     9 Oct 2022
#
# ---------------------------------------------------------------------------


import os

path = os.getcwd()

print(path)


def count_dir(path):
    # initialize the number of files
    with os.scandir(path) as it:
        # method found on https://docs.python.org/3/library/os.html#os.scandir
        # Help counting the exact number of files
        # and not files and folders
        for entry in it:
            if entry.is_dir():
                # with this method is_file()
                os.chdir(entry.name)
                # Get the name of each directory
                print(entry.name)
                # Print every directory
                count_dir(os.getcwd())
                # Apply the function recursively
        os.chdir('..')
        # Goes back in the previous directory


count_dir(path)
