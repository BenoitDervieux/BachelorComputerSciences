from dataclasses import dataclass
from typing import List

def fnv1a_64(string, seed=9):
        """
        Returns: The FNV-1a (alternate) hash of a given string
        source: https://github.com/sup/pyhash/blob/master/pyhash/pyhash.py
        """
        #Constants
        FNV_prime = 1099511628211
        offset_basis = 14695981039346656037

        #FNV-1a Hash Function
        hash = offset_basis + seed
        for char in string:
            hash = hash ^ ord(char)
            hash = hash * FNV_prime
        return hash

@dataclass
class HashSet:
    buckets: List[List] = None
    size: int = 0

    def init(self):
        self.size = 0
        self.buckets = [[] for i in range(8)]

    # Computes hash value for a word (a string)    
    def get_hash(self, word):
        # source: https://www.youtube.com/watch?v=jtMwp0FqEcg&t=88s
        g = 17
        # random constant for the hashing
        hash_number = 0
        # initialize the hash number
        for i in range(len(word)):
            hash_number += g * hash_number +  (ord(word[i]))
            # Hash formula for strings
            # Avoid to have words with
            # the same letters in the same
            # bucket
        
        #hash_number2 = fnv1a_64(word)
        
        hash_number = hash_number % self.bucket_list_size()
        # Number is hashed modulo the ratio of
        # the number of buckets in the hash table
        return hash_number

    # Doubles size of bucket list
    def rehash(self):
        buffer = []
        # Initialize a buffer for stocking the
        # current table
        for i in range(len(self.buckets)):
            if bool(self.buckets[i]) is True:
                # If the bucket is not empty
                for j in range(len(self.buckets[i])):
                    # Iterate inside it
                    if j != 0:
                        # This is a method to acces the elements
                        # come back to the position 0
                        j = j - j
                    buffer.append(self.buckets[i][j])
                    # Each element is added to the buffer
                    self.buckets[i].remove(self.buckets[i][j])
                    # Then each element is removed
                    self.size -= 1
                    # The sized is reduced by one
        self.buckets = [[] for i in range(2*len(self.buckets))]
        # Now that the bucket is empty, reintialize by
        # doubling the initial size
        for i in range(len(buffer)):
            # Add each element placed in the buffer
            # in the new hash table
            self.add(buffer[i])

    # Adds a word to set if not already added
    def add(self, word):
        if self.size >= self.bucket_list_size()  :
            # double the size if the number
            # of words excess the number of buckets
            self.rehash()
        hash_number = self.get_hash(word)
        # Get the hash number for the specific word
        if bool(self.buckets[hash_number]) is False:
            # if the bucket doesn't have any word
            # append it and increase the size
            self.buckets[hash_number].append(word)
            self.size += 1
        elif bool(self.buckets[hash_number]) is True:
            # If the bucket is not empty, check if
            # the word is in the bucket
            """add_number = hash_number
            while bool(self.buckets[add_number % self.bucket_list_size()]) is True:
                if word in self.buckets[add_number % self.bucket_list_size()]:
                    # if yes pass
                    break
                add_number += 1"""
                
            if word in self.buckets[hash_number]:
            # if word in self.buckets[add_number % self.bucket_list_size()]:
                # if yes pass
                pass
            else:
                self.buckets[hash_number].append(word)
                #self.buckets[add_number % self.bucket_list_size()].append(word)
                # otherwise add the word
                self.size += 1

    # Returns a string representation of the set content
    def to_string(self):
        s = '{ '
        for i in range(len(self.buckets)):
            # Check all the buckets
            if bool(self.buckets[i]) is True:
                # Check if the bucket contains something
                for j in self.buckets[i]:
                    # print what the bucket contains
                    s += j + ' '
        s += '}'
        return s

    # Returns current number of elements in set
    def get_size(self):
        return self.size

    # Returns True if word in set, otherwise False
    def contains(self, word):
        hash_number = self.get_hash(word)
        # Hash the word
        if bool(self.buckets[hash_number]) is False:
            # if the list is empty, return false
            return False
        elif bool(self.buckets[hash_number]) is True:
            # if the list is not empty, check the list
            # Return true if word otherwise false
            if word in self.buckets[hash_number]:
                return True
            else:
                return False

    # Returns current size of bucket list
    def bucket_list_size(self):
        return len(self.buckets)

    # Removes word from set if there, does nothing
    # if word not in set
    def remove(self, word):
        hash_number = self.get_hash(word)
        if bool(self.buckets[hash_number]) is False:
            # if the bucket is empty return
            return
        elif bool(self.buckets[hash_number]) is True:
            if word in self.buckets[hash_number]:
                # If the bucket has words
                # check the bucket and remove
                # otherwise return 
                self.buckets[hash_number].remove(word)
                self.size -= 1
            else:
                # print('The element is not in the list')
                return

    # Returns the size of the bucket with most elements
    def max_bucket_size(self):
        maximum_buckets = 0
        # Initialize a bucket size of 0
        for i in range(self.bucket_list_size()):
            # iterate in the bucket to get the
            # highest size
            if len(self.buckets[i]) > maximum_buckets:
                maximum_buckets = len(self.buckets[i])
                # return highest size
        return maximum_buckets

    # Returns the ratio of buckets of lenght zero.
    # That is: number of zero buckets divided by number of buckets
    def zero_bucket_ratio(self):
        number_zero_buckets = 0
        ratio_zero_bucket = 0
        for i in range(self.bucket_list_size()):
            # iterate in all the buckets
            if len(self.buckets[i]) == 0:
                # if bucket is empty, add 1 to the variable
                number_zero_buckets += 1
        ratio_zero_bucket = number_zero_buckets / self.bucket_list_size()
        # calculate the ratio
        return ratio_zero_bucket

    def print_buckets(self):
        for i in range(self.bucket_list_size()):
            if len(self.buckets[i]) > 4:
                print(self.buckets[i])
                for j in self.buckets[i]:
                    lst = []
                    lst.append(j)
                    lst.append(self.get_hash(j))
                    print(lst, end='')
