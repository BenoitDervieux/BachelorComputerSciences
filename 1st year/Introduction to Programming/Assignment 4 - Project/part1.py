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

def get_value(tpl):
    return tpl[1]


# input_file = get_real_path() + 'swe_news_15073169_words.txt'
# input_file = get_real_path() + '/brian_12903_words.txt'
input_file = get_real_path() + 'brian_short.txt'
# Path to get access to the different files rapidly

file_set = open(input_file, 'r', encoding='utf-8')
# open the file to be treated as a set
set = set()
# set up the set
for line in file_set:
    # Read through the file
    set.add(line.rstrip(' \n'))
    # Add every word without the line back

print('The length of the set is: ', len(set))
# Print the length of the set
    
file_set.close()
# Close the document by security



file_dict = open(input_file, 'r', encoding='utf-8')
# Re-open the document for being treated as a dict
dict_for_word = {}
# Initialize the dictionnary
for line in file_dict:
    # Read through the dictionary
    line = line.rstrip(' \n')
    # Remove the line back for every word
    if len(line) <= 4:
        # If the word is longer than 4 characters 
        # Do not count it
        continue
    else:
        if line in dict_for_word:
            # Else add it to the dictionary
            dict_for_word[line] += 1
        else:
            # Or increase its number of appartion
            dict_for_word[line] = 1
file_dict.close()
# close the file

print('The lenght of the dict is:', len(dict_for_word))
# print the length of the dictionnary

items = list(dict_for_word.items())
# Transform the dictionnary in a list to be sorted
dict_for_word = sorted(dict_for_word.items(), key=get_value)
# Bring back the sorted dictionary ordered by values 
# with the key using the function get_value

for i in range(1, 11):
    # print the 10 most used words
    # by printing from the last number of apparition minus 1
    # to the last minus 10
    print('{0:12}'.format(dict_for_word[int(len(dict_for_word)) - i][0]), dict_for_word[int(len(dict_for_word)) - i][1])


hash_list = []

#def own_hash_function(word, len(set)):

sum = 0
for i in 'un':
    sum += ord(i)

print(int(sum*100/len(set)))