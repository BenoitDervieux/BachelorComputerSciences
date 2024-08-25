# --------------------------------------------------------------------------
# My Place
# File:       my_place.py
# Description:
#   The program prints out the current absolute path (directly)
#   as well as the number of directories (folder)
#
# History:     9 Oct 2022
#
# ---------------------------------------------------------------------------

import os

dir_path = ''


def get_real_path():
    # Function to get the real directory
    filename = os.path.basename(__file__)
    # Get the filename
    foldersname_complete = os.path.abspath(__file__)
    # Get the whole path

    folderspath = foldersname_complete.rstrip(filename)
    # Substract the name from the whole path
    folderspath = folderspath.rstrip('/\\')
    # Substract the '/' from the path
    return folderspath


def count_directories_to_file(dir_path):
    # Returns the number of directories
    count = 0
    for i in range(len(dir_path)):
        if ord(dir_path[i]) == 47 or ord(dir_path[i]) == 92:
            count += 1
    print(f'There are {count} directories to' +
          'acces this file from this computer')


def count_dir(dir_path):
    # Returns the number of files
    count = 0
    # initialize the number of files
    with os.scandir(dir_path) as it:
        # method found on https://docs.python.org/3/library/os.html#os.scandir
        # Help counting the exact number of files
        # and not files and folders
        for entry in it:
            if entry.is_dir():
                # with this method is_file()
                count += 1
    if count < 2:
        print(f'Below me I have {count} directory')
    else:
        print(f'Below me I have {count} directories')


def count_files(dir_path):
    # Returns the number of files
    count = 0
    # initialize the number of files
    with os.scandir(dir_path) as it:
        # method found on https://docs.python.org/3/library/os.html#os.scandir
        # Help counting the exact number of files
        # and not files and folders
        for entry in it:
            if entry.is_file():
                # with this method is_file()
                count += 1
    if count < 2:
        print(f'This directory contains {count} file')
        # differentiate between plural and singular
        # in terms of number of directories
    else:
        print(f'This directory contains {count} files')


def main():
    dir_path = get_real_path()
    # first we get the real path where we are
    print('I am right now at:', dir_path)
    # then we print where we are
    # count_directories_to_file(dir_path)
    # Here we count the number of directories to come
    # to the one we're currently in
    count_dir(dir_path)
    # here we count how many directories there are
    # in the directory we're in
    count_files(dir_path)
    # here we count how many files there are


main()
