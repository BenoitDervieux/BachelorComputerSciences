import sys

data = []
initialSize = sys.getsizeof(data)

for k in range(1000):
    a = len(data)
    b = sys.getsizeof(data)
    sizePointers = len(data) * 8
    if (initialSize + sizePointers) == b:
        print('Length: {0:3d}; Size in bytes: {1:4d}'.format(a, b))
    data.append(None)

for p in range(1000, 1, -1):
    del data[p - 1]
    a = len(data)
    b = sys.getsizeof(data)
    sizePointers = len(data) * 8
    print('Length: {0:3d}; Size in bytes: {1:4d}'.format(a, b))
    

# Here we see that the size shrink when the capacity decreases