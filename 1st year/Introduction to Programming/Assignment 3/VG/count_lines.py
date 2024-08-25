# --------------------------------------------------------------------------
# Lines of Python
# File:       count_lines.py
# Description:
#   Count of all the non-empty lines in all Python files
#
# History:     9 Oct 2022
#
# ---------------------------------------------------------------------------


import os

path = input('Enter a directory you would like to analyze: ')
# ask the user which directory toanalyze


windows = False
linux = True
if '/' in path:
    linux = True
else:
    windows = True
# detect if the os is Linux or Windows

os.chdir(path)
# Goes to this directory
number_of_line = 0


def count_the_lines(path, number_of_line):
    with os.scandir(path) as it:
        # Open the directory and scan the entries inside
        for entry in it:
            if entry.is_dir():
                # if the entry is a folder
                if entry.name.startswith('_'):
                    # if the folder starts by '__' it is ignore as it
                    # mostly refers to a system folder
                    continue
                elif entry.name.startswith('.'):
                    # if the folder starts by '.' it is ignore as it
                    # mostly refers to a system folder
                    continue
                else:
                    if windows is True:
                        os.chdir(os.getcwd() + '\\' + entry.name)
                        # Then we iterate inside all the directories
                        number_of_line += count_the_lines(os.getcwd(),
                                                          number_of_line)
                        # count the number of lines inside the directories and
                        # add them to the one we alredy have
                    else:
                        os.chdir(os.getcwd() + '/' + entry.name)
                        # Then we iterate inside all the linux directories
                        number_of_line += count_the_lines(os.getcwd(),
                                                          number_of_line)
                        # count the number of lines inside the directories and
                        # add them to the one we alredy have
            elif entry.is_file():
                if entry.name.endswith('.py'):
                    # if the file finished with a .py, then we can access it
                    if windows is True:
                        # if using windows
                        file = open(os.getcwd() + '\\' +
                                    entry.name, 'r', encoding='utf-8')
                        # open the file
                        line_substitution = ''
                        # create a new string to store the line
                        for line in file:
                            line_substitution = line
                            line_substitution = line_substitution.rstrip(' \n')
                            # cut the spaces and the enter
                            # from any word to the right
                            line_substitution = line_substitution.lstrip(' \n')
                            # cut the spaces and the enter
                            # from any word to the left
                            if line_substitution != '':
                                # if the line is not empty,
                                # it means we wrote it
                                number_of_line += 1
                                # add it to our count
                    else:
                        # if using Linux
                        file = open(os.getcwd() + '/' +
                                    entry.name, 'r', encoding='utf-8')
                        # open the file
                        line_substitution = ''
                        # create a new string to store the line
                        for line in file:
                            line_substitution = line
                            line_substitution = line_substitution.rstrip(' \n')
                            # cut the spaces and the enter
                            # from any word to the right
                            line_substitution = line_substitution.lstrip(' \n')
                            # cut the spaces and the enter
                            # from any word to the left
                            if line_substitution != '':
                                # if the line is not empty,
                                # it means we wrote it
                                number_of_line += 1
                                # add it to our count
                    file.close()
        os.chdir('..')
    return number_of_line
    # returns the number of lines written


number = count_the_lines(path, number_of_line)
# Get the number of lines

print(f'You wrote {number} lines in the directory {path}')
# Prints the number of line
