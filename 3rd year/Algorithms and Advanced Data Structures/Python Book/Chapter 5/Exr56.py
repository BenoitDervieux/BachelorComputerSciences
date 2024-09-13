import ctypes

class DynamicArray:
    
    def __init__(self):
        self._n = 0
        self._capacity = 1
        self._A = self._make_array(self._capacity)
    
    def __len__(self):
        return self._n
    
    def __getitem__(self, k):
        if k < 0:
            k = k % self._n
        if not 0 <= k < self._n:
            raise IndexError('Invalid Index')
        return self._A[k]
    
    def append(self, obj):
        if self._n == self._capacity:
            self._resize(2*self._capacity)
        self._A[self._n] = obj
        self._n += 1
    def _resize(self, c):
        B = self._make_array(c)
        for k in range(self._n):
            B[k] = self._A[k]
        self._A = B
        self._capacity = c
    
    def _make_array(self, c):
        return (c* ctypes.py_object)( )
    
    def insert(self, k, value):
        """Insert value at index k, shifting subsequent values rightward."""
        # (for simplicity, we assume 0 <= k <= n in this verion)
        if self._n == self._capacity: # not enough room
            self._resize(2 * self._capacity) # so double capacity
        for j in range(self._n, k, -1): # shift rightmost first
            self._A[j] = self._A[j-1]
            self._A[k] = value # store newest element
            self._n += 1

array = DynamicArray()
for i in range(10):
    array.append(i)

# for i in range(10):
#     print(array.__getitem__(i))
    
for i in range(-1, -11, -1):
    print(array.__getitem__(i))