def binary_sum(S, start, stop):
    if start >= stop:
        return 0
    elif start == stop - 1:
        return S[start]
    else:
        mid = (start + stop) //2
        return binary_sum(S, start, mid) + binary_sum(S, mid, stop)

test = [1,2,3,4,5,6,7,8,9,10]

print(binary_sum(test, 0, 10))