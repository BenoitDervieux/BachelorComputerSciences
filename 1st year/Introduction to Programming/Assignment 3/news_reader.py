# --------------------------------------------------------------------------
# Swedish News
# File:       news_reader.py
# Description:
#   Reads and create a list of words from the Swedish News from 2020.
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


def has_number(n):
    """This function handles if a word
    has number in it as it wasn't considered
    as a word during the lecture"""
    for i in range(len(n)):
        if n[i] in '0123456789':
            return True


def is_one_letter_word(n):
    """This function handles swedish
    one letter words"""
    if n in 'ioåö':
        return True


def get_words(path, input_file):
    input_file = path + input_file
    # generate the real path to the file
    lst = []
    file = open(input_file, 'r', encoding='utf-8')
    # open the file
    for line in file:
        if line == '\n':
            # if the line is empty, the functions ignores it
            continue
        else:
            line = line.lower()
            # changes every line into lower character
            line = line.rsplit(' ')
            # divides all the lines with spaces
            for i in line:
                i = i.lstrip(' #¤=£@$}{+¨^~*_;:|%§▪()/' +
                             '[].,:!?\t&1234567890-½"”•–\n')
                # Delete all special characters on the left side of the word
                i = i.rstrip(' #¤=£@$}{+¨^~*_;:|%§▪()/' +
                             '[].,:!?\t&1234567890-½"”•–\n')
                # Delete all special characters on the right side of the word
                if i.isalpha() == True:
                    if len(i) == 1:
                        # check if it is a one letter word
                        if is_one_letter_word(i) is True:
                            # if yes it is added to the list of words
                            lst.append(i)
                        else:
                            continue
                    elif i != '':
                        if has_number(i) is True:
                            # Check if the words contain numbers in it
                            # if yes it is not a word
                            continue
                        else:
                            lst.append(i)
    file.close()
    return lst


def save_words(path, output_file, words):
    file = open(output_file, 'w+', encoding='utf-8')
    # Create a new file
    for i in range(len(words)):
        # Write all the words from a list to the new file
        file.write(words[i] + '\n')
    file.close()


def main():
    path = get_real_path()
    # Get the real path where the program executes
    os.chdir(get_real_path())
    # Go to the directory
    if windows is True:
        input_file = '\\data\\swe_news.txt'
    else:
        input_file = '/data/swe_news.txt'
    # Get the place where the file to examine is
    words = []
    words = get_words(path, input_file)
    print('Number of words in the text:', len(words))
    # Print the number of words
    if windows is True:
        output_file = path + f'\\data\\swe_news_{len(words)}_words.txt'
    else:
        output_file = path + f'/data/swe_news_{len(words)}_words.txt'
    # Save the file with the number of words returned
    save_words(path, output_file, words)
    # save the file
    print('Saved', len(words), 'words in the file', output_file)
    # Print the number of words saved and the location
    print()


main()
