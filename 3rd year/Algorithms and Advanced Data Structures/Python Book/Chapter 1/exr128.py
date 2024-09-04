def norm(v, p=2):
    result = 0
    for x in v:
        result += x**p
    return result ** (1/p)

test = [23, 5, 49, 99, 56]
test2 = [4,3]
print(norm(test2))
print(norm(test2,3))