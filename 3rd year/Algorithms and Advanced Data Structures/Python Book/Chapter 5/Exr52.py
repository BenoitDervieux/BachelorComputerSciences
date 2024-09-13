import sys

data = []
n = 26

# print(data.__dir__())
# print(data)
initialSize = sys.getsizeof(data)
print(initialSize)
print(sys.getsizeof(None))

for k in range(100):
    a = len(data)
    b = sys.getsizeof(data)
    sizePointers = len(data) * 8
    if (initialSize + sizePointers) == b:
        print('Length: {0:3d}; Size in bytes: {1:4d}'.format(a, b))
    data.append(None)
    
# The list keeps 8 bytes per data elements
# We see that it grows 4/8/12/16 rather than doubling every time it's full