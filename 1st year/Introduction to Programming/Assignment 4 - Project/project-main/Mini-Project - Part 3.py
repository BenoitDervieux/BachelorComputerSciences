import HashSet as hset
import BstMap as bst
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

# Initialize word set
words = hset.HashSet()   # Create new empty HashSet
words.init()             # Initialize with eight empty buckets
map = bst.BstMap()

file = open(input_file, 'r', encoding='utf-8')
# open the file to create hash function
# open file to print depth, fize and leafs
for line in file:
    # Read through the file
    line = line.rstrip('\n')
    words.add(line)
    if len(line) > 4:
            map.bst_put(line, 1)
    # Add every word without the line back
file.close()

# print the size of the hash table
print("\nNumber of words in the document:", words.get_size())

# Hash specific data
mx = words.max_bucket_size()
print("Maximum number of elements in one bucket:", mx)
buckets = words.bucket_list_size()
print("Number of buckets in the list:", buckets)
zero_buckets_ratio = words.zero_bucket_ratio()
print("Zero bucket ratio:", round(zero_buckets_ratio, 2))


lst = map.as_list()
# Sort the element from the BST to list them
lst = sorted(lst, key=lambda x: x[1], reverse=True)[:10]

print('\n10 most recurrent words in the text:')
for i in range(10):
    print('|{0:3} '.format(i + 1), end='')
    print('|{0:14}'.format(lst[i][0]), end='')
    print('|{0:4}'.format(lst[i][1]))


print("\nNumber of Nodes in the tree:", map.size())           # Nr of Nodes
print("Max depth of the tree:", map.max_depth())            # Depth of tree
print("Number of leaves:", map.count_leafs())    # Leafs


print("\n--- %s seconds ---" % (time.time() - start_time))
