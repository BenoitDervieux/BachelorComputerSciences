def linear_sum(S, n):
    if n == 0:
        return 0
    else:
        return linear_sum(S, n - 1) + S[n - 1]


test = [2,3,4,5,6,7,8,9,10,11]
print(linear_sum(test, 10))