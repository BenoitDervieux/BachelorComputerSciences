def dot_product(a, b):
    return [a[x] * b[x] for x in range(len(a))]


test = [15, 9, 7, 26, 22]
test2 = [1, 2, 3, 4, 5]

print(dot_product(test, test2))