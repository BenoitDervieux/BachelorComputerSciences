from random import randint
def my_shuffle(n):
    for i in range(10000):
        one = randint(0, len(n)-1)
        two = randint(0, len(n)-1)
        n[one], n[two] = n[two], n[one]
    return n

test = [15, 9, 7, 26, 22]

print(my_shuffle(test))