import HashSet as hset
import os

import time

start_time = time.time()

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

# Initialize word set
words = hset.HashSet()   # Create new empty HashSet
words.init()             # Initialize with eight empty buckets

file = open(input_file, 'r', encoding='utf-8')
# open the file to be treated as a set
for line in file:
    # Read through the file
    words.add(line.rstrip(' \n'))
    # Add every word without the line back
file.close()

# Print the name of the file
print(input_file)


# print("\nto_string():", words.to_string())
print("get_size():", words.get_size())             # 14
print("contains(choir):", words.contains("choir"))   # True
print("contains(Bob):", words.contains("Bob"))     # False

# Hash specific data
mx = words.max_bucket_size()
print("\nmax bucket:", mx)
buckets = words.bucket_list_size()
print("bucket list size:", buckets)
zero_buckets_ratio = words.zero_bucket_ratio()
print("zero bucket ratio:", round(zero_buckets_ratio, 2))


# Initialize word set
words = hset.HashSet()   # Create new empty HashSet
words.init()             # Initialize with eight empty buckets

input_file = get_real_path() + 'swe_news_15073169_words.txt'

file = open(input_file, 'r', encoding='utf-8')
# open the file to be treated as a set
for line in file:
    # Read through the file
    words.add(line.rstrip(' \n'))
    # Add every word without the line back
file.close()

# Print the name of the file
print(input_file)


# print("\nto_string():", words.to_string())
print("get_size():", words.get_size())             # 14
print("contains(under):", words.contains("under"))   # True
print("contains(Benoit):", words.contains("Benoit"))     # False

# Hash specific data
mx = words.max_bucket_size()
print("\nmax bucket:", mx)
buckets = words.bucket_list_size()
print("bucket list size:", buckets)
zero_buckets_ratio = words.zero_bucket_ratio()
print("zero bucket ratio:", round(zero_buckets_ratio, 2))

# words.print_buckets()

print("--- %s seconds ---" % (time.time() - start_time))