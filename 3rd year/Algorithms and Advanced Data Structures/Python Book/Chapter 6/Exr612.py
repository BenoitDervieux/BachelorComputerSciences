from collections import deque

class Deque:
    
    def __init__(self):
        self._lst = deque()
        self._size = 0
    
    def add_first(self, e):
        self._lst.appendleft(e)
        self._size += 1
        
    def add_last(self, e):
        self._lst.append(e)
        self._size += 1
        
    def delete_first(self):
        toReturn = self._lst.popleft()
        self._size -= 1
        return toReturn
    
    def delete_last(self):
        toReturn = self._lst.pop()
        self._size -= 1
        return toReturn
    
    def first(self):
        return self._lst[0]
    
    def last(self):
        return self._lst[len(self._lst) - 1]
    
    def is_empty(self):
        return self._size == 0
    
    def __len__(self):
        return self._size

    def print(self):
        for i in range(self._size):
            print(self._lst[i], end=' - ')
    
    
# test = Deque()

# print(test.add_first(4))
# print(test.add_last(8))
# print(test.add_last(9))
# print(test.add_first(5))
# print(test.delete_first())
# print(test.delete_last())
# print(test.add_last(7))
# print(test.first())
# print(test.last())
# print(test.add_last(6))
# print(test.delete_first())
# print(test.delete_first())

# test.print()