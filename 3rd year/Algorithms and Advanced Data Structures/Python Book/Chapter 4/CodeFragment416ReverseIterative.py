def reverse_iterative(S):
    start, stop = 0, len(S)
    while start < stop - 1:
        S[start], S[stop - 1] = S[stop - 1], S[start]
        start, stop = start + 1, stop - 1
    return S

test = [1,2,3,4,5,6,7,8,9,10]
print(reverse_iterative(test))