size = int(input('Enter a number: '))

print("        ", end='')

for column in range(1, size + 1):
    print('{0:4}'.format(column), end='')

print()
print("       +", end='')

for column in range(1, size + 1):
    print('----', end='')
print()

for column in range(1, size + 1):
    print("Row {0:2} |".format(column), end='')
    for row in range(1, size + 1):
        print("{0:4}".format(column*row), end='')
    print()
