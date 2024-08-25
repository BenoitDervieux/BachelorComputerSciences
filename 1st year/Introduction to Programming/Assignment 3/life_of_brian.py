# --------------------------------------------------------------------------
# Life of Brian
# File:       life_of_brian.py
# Description:
#   Reads and create a list of words from the script "Life of Brian"
#
# History:     9 Oct 2022
#
# ---------------------------------------------------------------------------

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


def get_words(path, input_file):
    input_file = path + input_file
    # here we assemble to complete path to access the file
    lst = []
    # we create an empty list for all the words
    file = open(input_file, 'r')
    # We open the file
    for line in file:
        if line == '\n':
            # We jump the line where it's empty
            continue
        else:
            line = line.lower()
            # we lower every line to handle the characters
            line = line.rsplit(' ')
            # We divided the words by spaces
            for i in line:
                i = i.lstrip(' \'";[].,:!?&\n')
                # We supress any strange character on the left side
                i = i.rstrip(' \'";[].,:!?&\n')
                # We supress any strange character on the right side
                if i.isalpha() == True:
                    if i != '':
                        if len(i) == 1:
                            if i != 'a' or i != 'i':
                                # we handle the case if the word is
                                # a single letter but not a or i (english language)
                                # then we continue
                                continue
                        else:
                            lst.append(i)
                            # append all the words to a list
    file.close()
    return lst
    # return the list of word


def save_words(path, output_file, words):
    file = open(output_file, 'w+')
    # Create a new file
    for i in range(len(words)):
        file.write(words[i] + '\n')
        # write those words in the file
    file.close()


def main():
    path = get_real_path()
    # Ask for the real path
    os.chdir(get_real_path())
    # go to the real path
    if windows is True:
        input_file = '\\data\\life_of_brian.txt'
    else:
        input_file = '/data/life_of_brian.txt'
    # define the input file
    words = []
    words = get_words(path, input_file)
    # Get all the words into a list
    print('Number of words in the text:', len(words))
    # print the number of words in the list
    if windows is True:
        output_file = path + f'\\data\\brian_{len(words)}_words.txt'
    else:
        output_file = path + f'/data/brian_{len(words)}_words.txt'
    # Create the name of the output list
    save_words(path, output_file, words)
    # Save the words into a new file
    print('Saved', len(words), 'words in the file', output_file)
    # print the number of words saved
    print()


main()
