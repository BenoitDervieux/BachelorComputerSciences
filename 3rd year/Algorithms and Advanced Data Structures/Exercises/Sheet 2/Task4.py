import numpy as np 

class SimpleList():
    
    def __init__(self):
        self.lst = np.empty(10, dtype=np.int64)
        self.cnt = 0
        self.sz = 10
    def empty(self):
        return self.cnt == 0
    def resize(self):
        bufflst = np.empty(self.sz * 2, dtype=np.int64)
        for i in range(len(self.lst)):
            bufflst[i] = self.lst[i]
        self.lst = bufflst
            
    def count(self):
        return self.cnt
    def append(self, d):
        if self.cnt == self.sz:
            self.resize() 
        self.lst[self.cnt] = d
        self.cnt += 1
    def appendArray(self, arr, index):
        if (self.sz + len(arr)) < self.sz:
            bufflst = np.empty(self.sz, dtype=np.int64)
        else:
            bufflst = np.empty((len(self.lst) + len(arr)), dtype=np.int64)
        print("buff list size", len(bufflst))
        for i in range(index):
            bufflst[i] = self.lst[i]
        for i in range(index, index + len(arr), 1):
            print("I - index:", i - index)
            print("Arr de i", arr[i - index])
            bufflst[i] = arr[i - index]
        for i in range(index + len(arr), (len(arr) + len(self.lst))):
            bufflst[i] = self.lst[i - index - 1]
        self.lst = bufflst
        self.sz = len(bufflst)
        self.cnt += len(arr)
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
    def print(self):
        for i in self.lst:
            print(i, end='-')

sl = SimpleList()

# print(sl.empty())
# print(sl.count())
# sl.append(1)
# sl.append(2)
# sl.append(3)
# print(sl.count())
# assert sl.count() == 3
# sl.delete()

for i in range(10):
    sl.append(i)

print(sl.print())

test = [11,12,13,14,15]

sl.appendArray(test, 4)
print(sl.print())
