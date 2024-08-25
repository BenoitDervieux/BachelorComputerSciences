import os
import time


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


choice = 0

while choice != 1 or 2 or 3:
    # Small function to decide which file to
    # analyze
    print('1) Brian')
    print('2) Swedish News')
    try:
        choice = int(input('Choose a file to analyze:'))
        if choice == 1:
            input_file = get_real_path() + '/brian_12903_words.txt'
            print('You decided to analyze:',
                  input_file.lstrip(get_real_path()))
            break
        elif choice == 2:
            input_file = get_real_path() + '/swe_news_15073169_words.txt'
            print('You decided to analyze:',
                  input_file.lstrip(get_real_path()))
            break
    except ValueError:
        print('This is not a valable entry')

start_time = time.time()

file_set = open(input_file, 'r', encoding='utf-8')
# open the file to be treated as a set
set = set()
# set up the set
dict_for_word = {}
# Set up the dictionary
for line in file_set:
    # Read through the file
    line = line.rstrip('\n')
    # cut the return to line from the word
    set.add(line)
    # Add every word without the line back
    # Remove the line back for every word
    if len(line) <= 4:
        # If the word is shorter than 4 characters
        # Do not count it
        continue
    else:
        if line in dict_for_word:
            # Else add it to the dictionary
            dict_for_word[line] += 1
        else:
            # Or increase its number of appartion
            dict_for_word[line] = 1

file_set.close()
# Close the document by security

print(f'\nThere are {len(set)} unique words in this file')
# Print the length of the set

print(f'\nThere are {len(dict_for_word)} words ' +
      'that are longer than 4 letters in this text')
# print the length of the dictionnary

items = list(dict_for_word.items())
# Transform the dictionnary in a list to be sorted
dict_for_word = sorted(dict_for_word.items(), key=lambda x: -x[1])[:10]
# Bring back the sorted dictionary ordered by values
# with the key using the function get_value

print('\nHere is a top 10 of the values appearing in this text:')
for i in range(0, 10):
    # print the 10 most used words
    # by printing from the last number of apparition minus 1
    # to the last minus 10
    print('|{0:3} '.format(i + 1), end='')
    print('| {0:12}'.format(dict_for_word[i][0]), end='')
    print('|{0:4}'.format(dict_for_word[i][1]))

print("\n--- %s seconds ---" % (time.time() - start_time))
