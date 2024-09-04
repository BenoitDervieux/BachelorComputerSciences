def double_sequence(n):
    myset = set()
    for x in n:
        if (x in myset):
            return False
        else:
            myset.add(x)
    return True

test = [15, 9, 7, 26, 22]
test2 = [15, 9, 7, 22, 22]

print(double_sequence(test))
print(double_sequence(test2))