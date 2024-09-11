"""Describe a recursive algorithm for finding the 
maximum element in a sequence, S, of n elements. 
What is your running time and space usag"""

def find_max(arr, index = 0):
    if len(arr) == 1: #O(1)
        return arr[0], index #O(1)
    else:
        result = find_max(arr[:-1], index) #O(n)
    print(arr)
    index = result[1] # O(1)
    if arr[result[1]] < arr[len(arr) - 1]: #O(1)
        index = len(arr) - 1 #O(1)
    return arr[0], index

test = [0,1,2,3,4,54,6,7,8,9]

print(find_max(test)[1])