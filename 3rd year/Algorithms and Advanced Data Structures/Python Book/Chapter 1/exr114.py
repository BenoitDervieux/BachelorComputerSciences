def odd_product(n):
    number = 0
    for i in n:
        if (i % 2 == 1):
            number += 1
            if number > 1:
                return True
    return False

test = [15, 9, 7, 26, 22]
print(odd_product(test))