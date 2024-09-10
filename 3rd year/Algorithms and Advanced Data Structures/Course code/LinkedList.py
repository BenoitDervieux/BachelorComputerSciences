from LLNode import LLNode

class LinkedList():
    
    def __init__(self):
        self.lst = None
    
    def append(self, d):
        if self.lst is None:
            self.lst = LLNode(d)
        else:
            p = self.lst
            while p.nxt is not None:
                p = p.nxt
            p.nxt = LLNode(d)
            
    def empty(self) -> bool:
        return self.lst == None
    
    def __len__(self) -> int:
        if self.empty() == True :
            return 0
        else :
            number = 0
            p = self.lst
            while p.nxt is not None:
                p = p.nxt
                number += 1
            return number + 1

    def deleteByIndex(self, index:int) -> bool:
        if index > self.__len__() - 1:
            return False
        elif index != 0:
            number = 0
            p = self.lst
            prev = p
            while number is not index:
                prev = p
                p = p.nxt
                number += 1
            p = p.nxt
            prev.nxt = p
            return True
        else:
            p = self.lst.nxt
            self.lst = p
            return True
            
            
            
        
        

ll = LinkedList()
print("Is it empty?", ll.empty())
print("what is the lenght?", ll.__len__())
ll.append(1)
ll.append(2)
ll.append(3)
ll.append(4)
ll.append(5)
print(ll.lst)
print("what is the lenght?", ll.__len__())
ll.deleteByIndex(0)
print(ll.lst)
ll.deleteByIndex(1000)
print("Is it empty?", ll.empty())
print("what is the lenght?", ll.__len__())