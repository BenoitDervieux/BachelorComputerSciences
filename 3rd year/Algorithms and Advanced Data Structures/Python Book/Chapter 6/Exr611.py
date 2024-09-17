from collections import deque

class Queue:
    
    def __init__(self):
        self._lst = deque()
        self._size = 0
    
    def enqueue(self, e):
        self._lst.append(e)
        self._size += 1
    
    def dequeue(self):
        toReturn = self._lst.popleft()
        self._size -= 1
        return toReturn

    def first(self):
        if len(self._lst) == 0:
            raise Empty('Queue is empty')
        return self._lst[0]
    
    def is_empty(self):
        return self._size == 0
    
    def __len__(self):
        return self._size
    
    def print(self):
        for i in range(self._size):
            print(self._lst[i], end=' - ')

# test = Queue()

# for i in range(10):
#     test.enqueue(i)

# for i in range(5):
#     test.dequeue()

# test.print()
# print()
# print(test.first())
# print(test.is_empty())
# print(test.__len__())