# --------------------------------------------------------------------------
# Task6 assignment 1 of the course of computer security
# File:       Task6_hash_function_CS.py
# Description:
#   Test the uniformity of a hash function by using two different text files
#
# History:     27 November 2022
#
# ---------------------------------------------------------------------------

import matplotlib.pyplot as plt
import numpy as np


def hash_function(phrase):
    g = 7477
    hash_number = 0
    for i in range(len(phrase)):
        hash_number += ord(phrase[i]) * g + hash_number
    hash_number = hash_number % 256
    return hash_number


def uniformity(file_name):
    dict_to_analyze = {}
    # initialize a dictionnary
    file = open(file_name, 'r', encoding="utf-8")
    # open the file of 10 000 different strings/words
    for line in file:
        # reads every words
        line = line.rstrip('\n')
        # cut the 'enter' character
        line = hash_function(line)
        # hash the word
        if dict_to_analyze.get(line) is None:
            # if the word is not in the dict, add it
            dict_to_analyze.update({line: 1})
        else:
            # else add 1 to its occurence
            dict_to_analyze[line] += 1
    file.close
    # Close the file
    items_analyze = list(dict_to_analyze.items())
    # Transform the dictionnary into a list
    key_sorted_analyze = sorted(items_analyze, key=lambda tpl: tpl[0])
    # Sort the list by key
    x_array = []
    # Initialize the x axis for graphical analysis
    y_array = []
    # Initialize the y axis for graphical analysis
    for i in key_sorted_analyze:
        # Append coordinates of the points for graphical analysis
        x_array.append(i[0])
        y_array.append(i[1])
    x = np.array(x_array)
    y = np.array(y_array)
    plt.xlabel("Key")
    plt.ylabel("Number of collisions")
    plt.bar(x, y, width=1)
    plt.suptitle('Hash function repartition')
    # Outputing the graph with Matplotlib
    plt.show()

    # Chi-squared test
    n = 10000
    # Number of key
    m = 256
    # Number of buckets
    sum = 0
    for i in range(len(key_sorted_analyze)):
        sum += ((key_sorted_analyze[i][1]*(key_sorted_analyze[i][1] + 1))/2)
    # addition all the values in the bucket

    chi_squared = sum / ((n / (2*m))*(n + (2*m) - 1))
    # Divide according to the formula
    print('Chi Squared:', chi_squared)


def small_changes(file_name):
    dict_to_analyze = {}
    # initialize a dictionnary
    file = open(file_name, 'r', encoding="utf-8")
    # open the file of 10 000 different strings/words
    iteration = 1
    x_array = []
    # Initialize the x axis for graphical analysis
    y_array = []
    # Initialize the y axis for graphical analysis
    for line in file:
        # reads every words
        line = line.rstrip('-10 \n')
        # cut the 'enter' character and the bits
        line = hash_function(line)
        # hash the word
        x_array.append(iteration)
        # append the value of the
        y_array.append(line)
        if dict_to_analyze.get(line) is None:
            # if the word is not in the dict, add it
            dict_to_analyze.update({line: 1})
        else:
            # else add 1 to its occurence
            dict_to_analyze[line] += 1
        iteration += 1
    file.close
    x = np.array(x_array)
    y = np.array(y_array)
    plt.xlabel("Iterations")
    plt.ylabel("Hash Number")
    plt.plot(x, y, 'o')
    plt.suptitle('Hash function value for small changes')
    # Outputing the graph with Matplotlib
    plt.show()

    items_analyze = list(dict_to_analyze.items())
    # Transform the dictionnary into a list
    key_sorted_analyze = sorted(items_analyze, key=lambda tpl: tpl[0])
    # Sort the list by key

    # Chi-squared test
    n = 1000
    # Number of key
    m = 256
    # Number of buckets
    sum = 0
    for i in range(len(key_sorted_analyze)):
        sum += ((key_sorted_analyze[i][1]*(key_sorted_analyze[i][1] + 1))/2)
    # addition all the values in the bucket
    chi_squared = sum / ((n / (2*m))*(n + (2*m) - 1))
    # Divide according to the formula
    print('Chi Squared:', chi_squared)


def main():
    print('What test do you want to run?')
    print('1) Uniformity')
    print('2) Small changes')
    answer_file = int(input('Answer: '))
    if answer_file == 1:
        uniformity('10000_different_words.txt')
    elif answer_file == 2:
        small_changes('subtle_strings.txt')


main()
