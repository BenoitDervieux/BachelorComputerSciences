import sys

data = []
n = 26

for k in range(27):
    a = len(data)
    b = sys.getsizeof(data)
    print('Length: {0:3d}; Size in bytes: {1:4d}'.format(a, b))
    data.append(None)
    
    
    
# On my system : 312 bytes for 26
# On the code fragment 352