""" 
Isabel has an interesting way of summing up the values in a sequence A of
n integers, where n is a power of two. She creates a new sequence B of half
the size of A and sets B[i] = A[2i]+A[2i+1], for i = 0,1,...,(n/2) - 1. If
B has size 1, then she outputs B[0]. Otherwise, she replaces A with B, and
repeats the process. What is the running time of her algorithm?
"""

# I don't manage to make sense of this algorithm
# anyway I think it's n^2
# And I was wrong as it's O(n), let's see later for this problem


def LetsTry(A):
    B = [0 for x in range(int(len(A)/2))]
    for i in range(int(len(A)/2) - 1): # In the example here it's 8
        B[i] += A[2*i + j]
    if len(B) == 1:
        return B[0]
    else:
        return B

test = [1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8] # here it's 2^4
summation = 0
for i in test:
    summation += i
print(summation)
# print(LetsTry(test))

