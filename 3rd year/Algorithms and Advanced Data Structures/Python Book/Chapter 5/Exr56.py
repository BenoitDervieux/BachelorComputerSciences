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
        # if self._n == self._capacity: # not enough room
        #     self._resize(2 * self._capacity) # so double capacity
        # for j in range(self._n, k, -1): # shift rightmost first
        #     self._A[j] = self._A[j-1]
        #     self._A[k] = value # store newest element
        #     self._n += 1
        
        #_n is the number of elements and _capacity is the capacity
        # So bascially when the _n is equal to the _capacity, we can create a new array directly
        # otherwise we just add depending on the order
        if self._n == self._capacity:
            subarray = self._make_array(2*self._capacity)
            self._capacity = 2 * self._capacity
            for i in range(0, self._n + 1):
                if i is not k:
                    subarray[i] = self._A[i] if i < k else self._A[i - 1]
            subarray[k] = value
        else:
            subarray = self._make_array(self._capacity)
            for i in range(0, self._n + 1):
                if i is not k:
                    subarray[i] = self._A[i] if i < k else self._A[i - 1]
            subarray[k] = value
        self._A = subarray
        self._n += 1  
            
            
            

array = DynamicArray()
print(array._n)
print("First")
array.insert(0, 10)
print("Second")
array.insert(0, 11)
print("Third")
array.insert(0, 12)
print("Fourth")
array.insert(0, 13)
print("Fifth")
array.insert(0, 14)
print(array._n)
for i in range(10):
    array.insert(i, 20 + i)

print(array._n)
print(array._capacity)