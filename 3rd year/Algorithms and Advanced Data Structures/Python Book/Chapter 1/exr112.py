import random
test = [15, 5, 10, 26, 22]
print(random.choice(test))

def my_choice(n):
    return n[random.randrange(0, len(n))]

print(my_choice(test))