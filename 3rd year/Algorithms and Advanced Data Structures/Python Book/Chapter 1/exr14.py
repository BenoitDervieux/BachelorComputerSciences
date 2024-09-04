def sum_of_positive_square(n):
    sum = 0
    for i in range(0, n+1):
        sum += i*i
    return sum


print(sum_of_positive_square(5))
