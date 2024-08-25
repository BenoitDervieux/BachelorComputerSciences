import BstMap_test as bst
import os


def get_value(tpl):
    return tpl[1]

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

# input_file = get_real_path() + 'swe_news_15073169_words.txt'
input_file = get_real_path() + 'brian_12903_words.txt'
# input_file = get_real_path() + 'brian_short.txt'
# Path to get access to the different files rapidly

words = bst.BstMap()

file = open(input_file, 'r', encoding='utf-8')
# open the file to be treated as a set
for line in file:
    # Read through the file
    line = line.rstrip(' \n')
    if len(line) > 4:
        words.put_bst(line, 1)
    # Add every word without the line back
file.close()

# Print the name of the file
print(input_file)

# print(words.to_string())
lst = words.as_list()
lst = sorted(lst, key=get_value, reverse=True)

for i in range(10):
    print('{0:12} - {1:12}'.format(lst[i][0], lst[i][1]))


    
print("\nSize:", words.size())
print("Max depth:", words.max_depth())
print("Count leafs:", words.count_leafs())