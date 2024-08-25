# --------------------------------------------------------------------------
# Task3 assignment 1 of the course of computer security
# File:       Task3_ass1_CS.py
# Description:
#   Allows the user to decrypt and encrypt txt files using substitution
#   of transposition techniques
#
# History:     27 November 2022
#
# ---------------------------------------------------------------------------

from math import sqrt


def encryption_length(key):
    sum = 0
    for i in key:
        sum += ord(i)
        # Add all the ascii values of the key
    sum = sum % 256
    # apply the module to get a key of 256 bits

    return sum


def encrypt(phrase, key):
    gap = encryption_length(key)
    # get the key, the number to add
    encrypted_string = ''
    # create an empty string
    for i in range(len(phrase)):
        encrypted_string += chr(ord(phrase[i]) + gap + 161 - 33)
        # Moves the ascii value according to the key
        # obtained. Add it to the new string

    return encrypted_string


def decrypt(encrypted_phrase, key):
    gap = encryption_length(key)
    # Get the key
    decrypted_string = ''
    for i in range(len(encrypted_phrase)):
        decrypted_string += chr(ord(encrypted_phrase[i]) - gap - 161 + 33)
        # Substract the ascii value of the encoded character

    return decrypted_string


def get_swap_number(key):
    swap_number = 0

    for i in key:
        swap_number += ord(i)
        # Sum the character inside the key
    swap_number = swap_number % 256
    # Apply module to have a length of 256

    return swap_number


def transposition_encoding(sentence, grid_horizontal,
                           grid_vertical, swap_number):
    iteration = 0
    list_to_transpose = [[0 for q in range(grid_vertical)]
                         for p in range(grid_horizontal)]
    # Declare a two dimensions list
    for i in range(grid_horizontal):
        for j in range(grid_vertical):
            # iterate through the list
            if iteration == len(sentence):
                list_to_transpose[i][j] = chr(126)
                # Add a mark to localize the end of
                # sentence
                iteration += 1
            elif iteration > len(sentence):
                # Add 'a' to fill the grid
                list_to_transpose[i][j] = 'a'
            else:
                list_to_transpose[i][j] = sentence[iteration]
                iteration += 1
                # Add the sentence character

    string_to_return = ''
    for j in range(grid_vertical):
        for i in range(grid_horizontal):
            # Iterate through the list in vertical
            # manner
            string_to_return += list_to_transpose[i][j]
            # Add the characters from the list
            # To a new string

    swap_string = ''
    for i in range(len(string_to_return)):
        swap_string += string_to_return[(i + swap_number)
                                        % len(string_to_return)]
        # Shift the beginning of the string
        # with the swap number

    return swap_string


def transposition_decoding(sentence, grid_horizontal,
                           grid_vertical, swap_number):

    unswap_string = ''
    for i in range(len(sentence)):
        unswap_string += sentence[(i - swap_number) % len(sentence)]
        # Unswap the beginning of the string

    iteration = 0
    list_to_transpose = [[0 for q in range(grid_vertical)]
                         for p in range(grid_horizontal)]
    # Create a two dimension list
    for i in range(grid_horizontal):
        for j in range(grid_vertical):
            # Add the charcters inside it
            list_to_transpose[i][j] = unswap_string[iteration]
            iteration += 1

    string_to_return = ''
    for j in range(grid_vertical):
        for i in range(grid_horizontal):
            # Place the untranspose character
            # In a string
            string_to_return += list_to_transpose[i][j]

    return string_to_return


def transpose(phrase_to_transpose, key):

    grid_horizontal = int(sqrt(len(phrase_to_transpose))) + 1
    # The key horizontal is the square root of the length of the message
    grid_vertical = int(sqrt(len(phrase_to_transpose))) + 1
    # The key horizontal is the square root of the length of the message

    swap_number = get_swap_number(key)

    phrase_to_transpose = transposition_encoding(phrase_to_transpose,
                                                 grid_horizontal,
                                                 grid_vertical, swap_number)

    return phrase_to_transpose


def un_tranpose(phrase_to_transpose, key):

    grid_horizontal = int(sqrt(len(phrase_to_transpose)))
    # The key horizontal is the square root of the length of the message
    grid_vertical = int(sqrt(len(phrase_to_transpose)))
    # The key horizontal is the square root of the length of the message
    swap_number = get_swap_number(key)

    phrase_to_transpose = transposition_decoding(phrase_to_transpose,
                                                 grid_horizontal,
                                                 grid_vertical, swap_number)

    clean_phrase = ''
    for i in range(len(phrase_to_transpose)):
        # Copy the character from the original string
        if phrase_to_transpose[i] == chr(126):
            if (i > int(((sqrt(len(phrase_to_transpose))) - 1)**2)):
                break
            # break when the special character is found
            # only at a certain place
        else:
            clean_phrase += phrase_to_transpose[i]
            # Otherwise add any character

    return clean_phrase


def main():

    print('What do you want to do?')
    print('1) Encrypt a file using substitution')
    print('2) Decrypt a file using substitution')
    print('3) Encrypt a file using transposition')
    print('4) Decrypt a file using transposition')
    # Ask 4 options for running the program
    response = int(input('Enter your answer: '))
    # Ask for an answer
    name_file = input('Provide a file: ')
    # Ask for a file
    key = input('Provide a key: ')
    # Ask for a key
    if response == 1:
        lst = []
        file = open(name_file, 'r', encoding="utf-8")
        # open the provided file
        for line in file:
            line = line.rstrip('\n')
            lst.append(line)
            # append each line into a list
        file.close
        file_to_encode = open('encoded_sub_' + name_file,
                              'w+', encoding="utf-8")
        # Create the new file
        for line in lst:
            line = line.rstrip('\n')
            line_to_write = encrypt(line, key)
            # Encrypt every line
            file_to_encode.write(line_to_write + '\n')
            # Write the line in the new document
        file_to_encode.close
    elif response == 2:
        lst = []
        file = open(name_file, 'r', encoding="utf-8")
        # open the provided file
        for line in file:
            line = line.rstrip('\n')
            lst.append(line)
            # append each line into a list
        file.close
        file_to_encode = open('decoded_sub_' + name_file,
                              'w+', encoding="utf-8")
        # Create the new file
        for line in lst:
            line = line.rstrip('\n')
            line_to_write = decrypt(line, key)
            # Decrypt every line
            file_to_encode.write(line_to_write + '\n')
            # Write the line in the new document
        file_to_encode.close
    elif response == 3:
        lst = []
        file = open(name_file, 'r', encoding="utf-8")
        # open the provided file
        for line in file:
            line.rstrip('\n')
            lst.append(line)
            # append each line into a list
        file.close
        file_to_encode = open('encoded_tran_' + name_file,
                              'w+', encoding="utf-8")
        # Create the new file
        for line in lst:
            line = line.rstrip('\n')
            line_to_write = transpose(line, key)
            # Encrypt every line
            file_to_encode.write(line_to_write + '\n')
            # Write the line in the new document
        file_to_encode.close
    elif response == 4:
        lst = []
        file = open(name_file, 'r', encoding="utf-8")
        # open the provided file
        for line in file:
            line = line.rstrip('\n')
            lst.append(line)
            # append each line into a list
        file.close
        file_to_encode = open('decoded_tran_' + name_file,
                              'w+', encoding="utf-8")
        # Create the new file
        for line in lst:
            line = line.rstrip('\n')
            line_to_write = un_tranpose(line, key)
            # Decrypt every line
            file_to_encode.write(line_to_write + '\n')
            # Write the line in the new document
        file_to_encode.close


main()
