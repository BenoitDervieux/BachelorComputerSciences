# --------------------------------------------------------------------------
# Creating a File
# File:       file_writer.py
# Description:
#   This program creates a text file with the help of Python
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


def writing(path, name, content):
    os.chdir(path)
    # We move to the real folder
    file = open(name, 'w+', encoding="utf-8")
    # We open as w+ which means we create a new file
    print('Writing to file....')
    for i in content:
        if i != 'stop':
            file.write(i + '\n')
    # we write every line from the list into a new line
    # up until we find the word 'stop'
    file.close()


def main():
    path = get_real_path()
    # First we get the reaql path of the folder
    name_of_file = input('Name of the file: ')
    # We ask for a name of file
    text = []
    # We create a where we will append each
    # entry as a line in the text
    sentence = ''
    # We create an empty string to store the line

    print('Enter the content and end with "stop": ')
    while sentence != 'stop':
        # We loop until we have the word stop
        sentence = input('> ')
        # We ask the user to enter a text each time
        # and we stock it into our string
        text.append(sentence)
        # Then we append to our list

    writing(path, name_of_file, text)
    # We then write the list into a new file
    # As a text


main()
