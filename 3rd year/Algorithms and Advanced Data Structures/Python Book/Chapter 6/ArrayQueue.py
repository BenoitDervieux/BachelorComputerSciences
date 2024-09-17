class ArrayQueue:
    
    DEFAULT_CAPACITY = 10
    
    def __init__(self):
        self._data = [None] * ArrayQueue.DEFAULT_CAPACITY
        self._size = 0
        self._front = 0
        
    def __len__(self):
        return self._size
    
    def is_empty(self):
        return self._size == 0
    
    def first(self):
        if self.is_empty():
            raise Empty('Queue is empty')
        return self._data[self._front]
    
    def dequeue(self):
        if self.is_empty():
            raise Empty('Queue is empty')
        answer = self._data[self._front]
        self._data[self._front] = None
        self._front = (self._front + 1 ) % len(self._data)
        self._size -= 1
        return answer
    
    def enqueue(self, e):
        if self._size == len(self._data):
            self._resize(2 * len(self._data))
        avail = (self._front + self._size) % len(self._data)
        self._data[avail] = e
        self._size += 1
    
    def _resize(self, cap):
        old = self._data  #Buffer for the data
        self._data = [None] * cap # Create new empty slots
        walk = self._front # Buff for the self._front
        for k in range(self._size):
            self._data[k] = old[walk] # Replaced data where it was, up to
            walk = (1 + walk) % len(old)
        self._front = 0
    
    