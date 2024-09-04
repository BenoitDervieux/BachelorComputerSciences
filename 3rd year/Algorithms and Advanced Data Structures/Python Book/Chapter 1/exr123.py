test = [15, 9, 7, 26, 22]
try:
    print(test[50])
except IndexError:
    print("Don't try to overflow") 