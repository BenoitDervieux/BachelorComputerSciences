import numpy as np 

class SimpleList():
    
    def __init__(self):
        self.lst = np.empty(10, dtype=np.int64)
        self.cnt = 0
        self.sz = 10
    def empty(self):
        return self.cnt == 0
    def count(self):
        return self.cnt
    def append(self, d):
        if self.cnt == self.sz:
            self.sz *= 2
            tmp = np.empty(self.sz, dtype=np.int64)
            for i in range(self.cnt):
                tmp[i] = self.lst[i]
            self.lst = tmp
                 
        self.lst[self.cnt] = d
        self.cnt += 1
    def delete(self, d):
        found = False
        for i in range(0, self.cnt):
            if self.lst[i] == d:
                found = True
                break
            if found:
                for j in range(i+1, self.cnt):
                    self.lst[j-1] = self.lst[j]
                self.cnt -= 1

sl = SimpleList()

print(sl.empty())
print(sl.count())
sl.append(1)
sl.append(2)
sl.append(3)
print(sl.count())
assert sl.count() == 3
sl.delete()