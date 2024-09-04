def arithmetic(a, b, c):
    if (((a + b) == c) or ((b - c) == a) or ((a*b) == c)) :
        return True
    return False
    

print(arithmetic(4,4,16))