# Same as 1.7
def sum_of_square(n):
    return sum(k*k for k in range(1, n+1) if k % 2 == 1)

print(sum_of_square(5))