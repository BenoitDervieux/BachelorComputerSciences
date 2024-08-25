# --------------------------------------------------------------------------
# Reading a File
# File:       file_reader.py
# Description:
#   Read a file by taking it's path name as a parameter
#
# History:     9 Oct 2022
#
# ---------------------------------------------------------------------------

import os


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


def reading(path, name):
    os.chdir(path)
    # Go to the path where the file is
    lst = []
    count_line = 0
    file = open(name, 'r')
    # Open the file
    for line in file:
        # Count the number of line and append
        # them to a list
        count_line += 1
        lst.append(line)
    print(f'Lines in file: {count_line}')
    # Print the number of lines
    file.close
    return lst


def main():
    name_of_the_file = input('What is the name of the file to read? ')
    # Ask the user which file to read/to open
    lst = []
    path = get_real_path()
    with os.scandir(path) as it:
        for entry in it:
            if entry.is_file():
                if name_of_the_file == entry.name:
                    # Look for all the files in the directory
                    # And look for a matching name
                    lst = reading(path, name_of_the_file)
                    # Call the function reading and get
                    # line
                    break
                # Finish the process when this is done
        else:
            print('We did not find the file you\'re looking for.')
    lst = [print(i.strip()) for i in lst]
    # Print all the lines once it's finished


main()
