def reverse(S, start, stop):
    if start < stop - 1:
        S[start], S[stop - 1] = S[stop -1], S[start]
        reverse(S, start + 1, stop - 1)
    return S


test = [1,2,3,4,5,6,7,8,9,10]
test = [4, 3, 6, 2, 6]

print(reverse(test, 0, 5))