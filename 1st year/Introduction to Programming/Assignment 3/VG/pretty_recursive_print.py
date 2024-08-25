# --------------------------------------------------------------------------
# Pretty Recursive Print
# File:       pretty_recursive_print.py
# Description:
#   This program makes the output indented based on
#   the depth of directories
#
# History:     9 Oct 2022
#
# ---------------------------------------------------------------------------

import os

path = os.getcwd()
# Get the path of the directory
count = -1
# Starts the count as -1 as the last '\'
# Of the folder is not counted
for i in range(len(path)):
    if path[i] == '\\' or path[i] == '/':
        count -= 1
# Count the number of '\' (folders) there
# are to print accordingly

print(path)
# Print the path the program will start


def pretty_print(path, count):
    # initialize the number of files
    with os.scandir(path) as it:
        # method found on https://docs.python.org/3/library/os.html#os.scandir
        # Help counting the exact number of files
        # and not files and folders
        for entry in it:
            if entry.is_dir():
                # with this method is_dir()
                os.chdir(entry.path)
                # Get the name of each directory
                path = os.getcwd()
                # Get the direct path
                count_path = count
                # This count helps to start
                # The tree of folders where we are in the
                # directory, not in the computer
                for i in range(len(path)):
                    if ord(path[i]) == 47 or ord(path[i]) == 92:
                        count_path += 1
                        # This checks the number of times a '\' appears
                        # To print accordingly
                print('  '*count_path, entry.name)
                # Prints depending how deep we are in the folder.
                # Prints each directory
                pretty_print(os.getcwd(), count)
                # Apply the function recursively
        os.chdir('..')
        # Goes back in the previous directory


pretty_print(path, count)
