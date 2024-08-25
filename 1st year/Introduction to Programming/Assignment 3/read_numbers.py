# --------------------------------------------------------------------------
# Statistics
# File:       read_numbers.py
# Description:
#   This program computes and presents the average
#   (mean) value and the standard deviation
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


def mean(lst):
    """This function computes the mean(average)
    of a list and returns the number"""
    sum = 0
    for i in lst:
        # add all terms in a list
        sum += i
    mean = sum/len(lst)
    # divide all added terms by number of terms
    return mean


def std(lst):
    variance = 0
    for i in lst:
        variance += (i - mean(lst))**2
    # calculate the variance by substracting the mean to
    # each number and squaring it
    variance = variance / len(lst)
    # then by dividing the sum by the numbe rof terms in
    # the list
    square_standard = 1
    # variable to calculate the square root
    iteration = 0
    while round(square_standard**2, 2) != round(variance, 2):
        """Here we are calculating the square root manually
        by adding 1, then 0.1, then 0,01 etc up until
        the square value que are looking for is close enough
        to the value of the variance"""
        # while the square standard^2 is different that the variance
        # we increase one by one
        if square_standard**2 < variance:
            square_standard += 1 / 10**iteration
        elif square_standard**2 > variance:
            # when the square standard is upper than the variance
            # we add 1 to the the iteration and subtract the square
            # a bit more to get closer
            iteration += 1
            while square_standard**2 > variance:
                square_standard -= 1 / 10 ** iteration
    return square_standard


def compute_text(name, text, term_to_split):
    print(f'Results for the file {name}:')
    os.chdir(get_real_path())
    # we get the real path to get the place
    # where the folder is
    lst = []
    buff_list = []
    file = open(text, 'r')
    # We open the forlder we add in the argument
    # in the main function
    content_file = file.read()
    # We read the file
    content_file = content_file.split(term_to_split)
    # we divide the terms of the file by the most common
    # characters they have (which  has been added into arguments
    # when we called the function)
    for s in content_file:
        if '\n' in s:
            # If there is a '\n' we handle it
            buff_list = s.splitlines()
            # it retruns 2 terms
            lst.append(int(buff_list[0]))
            # We append them to the list
            if not s.endswith('\n'):
                # except if it's the last terms and
                # there was only one term ending with '\n'
                lst.append(int(buff_list[1]))
        else:
            lst.append(int(s))
            # else we append the normal numbers
    file.close()
    mean_number = mean(lst)
    # we calculate the mean value
    standard_deviation = std(lst)
    # we calculate the standard deviation
    print(f'mean = {round(mean_number, 1)},' +
          f'standard deviation = {round(standard_deviation, 1)}')
    print()


def main():
    compute_text('A', 'file_10k_integers_A.txt', ', ')
    compute_text('B', 'file_10k_integers_B.txt', ':')


main()
