class _DoublyLinkedBase:
    
    class _Node:
        
        __slots__ = '_element', '_prev', '_next'
        
        def __init__(self, element, prev, next):
            self._element = element
            self._prev = prev
            self._next = next
            
    def __init__(self):
        self._header = self._Node(None, None, None)
        self._trailer = self._Node(None, None, None)
        self._header._next = self._trailer
        self._trailer._prev = self._header
        self._size = 0
    
    def __len__(self):
        return self._size
    
    def is_empty(self):
        return self._size == 0
    
    def _insert_between(self, e, predecessor, successor):
        newest = self._Node(e, predecessor, successor)
        predecessor._next = newest
        successor._prev = newest
        self._size += 1
        return newest
    
    def _delete_node(self, node):
        predecessor = node._prev 
        successor = node._next 
        predecessor._next = successor
        successor._prev = predecessor
        self.size -= 1
        element = node._element 
        node._prev = node._next = node._element = None
        return element
    
    def append(self, node):
        if self._size == 0:
            self._insert_between(node, self._header, self._trailer)
        else:
            self._insert_between(node, self._trailer._prev, self._trailer)

    def printList(self):
        p = self._header
        while p._next is not self._trailer:
            p = p._next
            print(p._element)
    
    def find_middle_node(self):
        end = self._trailer
        beginning = self._header
        while end == beginning or end._prev == beginning :
            end = end._prev
            beginning = beginning._next
        return beginning._element

test = _DoublyLinkedBase()
for i in range(10):
    test.append(i)

test.printList()
print("Middle node", test.find_middle_node())