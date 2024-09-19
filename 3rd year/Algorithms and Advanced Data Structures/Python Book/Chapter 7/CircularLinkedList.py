from LLNodeCircular import LLNodeCircular

class CircularLinkedList():
    
    def __init__(self):
        """ Initialize the list
        """
        self.count = 0
        self.tail = LLNodeCircular(None)
    
    def append(self, d):
        """ Add a new LLNode at the end of the linked list of nodes.
        Args:
            d (Node): LLNode (Implementation from the course).
        """
        if self.count == 0:
            self.tail = LLNodeCircular(d)
        elif self.count == 1:
            last = LLNodeCircular(d)
            first = self.tail
            self.tail = last
            last.nxt = first
            first.nxt = last
        else:
            first = self.tail.nxt
            p = self.tail.nxt
            while p is not self.tail:
                p = p.nxt
            # here on a self.tail and self.tail.nxt
            p.nxt = LLNodeCircular(d)
            p = p.nxt
            p.nxt = first
            self.tail = p
        self.count += 1
    
    def empty(self) -> bool:
        """_summary_
        Check if the list is empty.
        Returns:
            bool: Return True or False.
        """
        return self.count == 0
    
    def __len__(self) -> int:
        """_summary_
        Compute the length of the list.
        Returns:
            int: Return the number of elements in the list.
        """
        return self.count
    
    def delete_last(self) -> bool:
        """Delete last element of the list-

        Returns:
            bool: True if delete otherwise False.
        """
        if self.count == 1:
            self.tail.nxt = None
            self.count = 0
        elif not self.empty():
            p = self.tail.nxt
            prev = p
            while p.nxt != None:
                prev = p
                p = p.nxt
            prev.nxt = None
            self.count -= 1
            return True
        else:
            return False
    
    def delete_first(self) -> bool:
        """Delete first element of the list-

        Returns:
            bool: True if delete otherwise False.
        """
        if not self.empty():
            self.tail.nxt = self.tail.nxt.nxt
            self.count -= 1
            return True
        else:
            return False

    def delete_by_index(self, index:int) -> bool:
        """_summary_
        Delete an element of the list using a given index.
        Args:
            index (int): Index of the list to delete.

        Returns:
            bool: True if the element was in the list and delete, False otherwise.
        """
        if index > self.__len__() - 1:
            return False
        elif index != 0:
            number = 0
            p = self.tail.nxt
            prev = p
            while number is not index:
                prev = p
                p = p.nxt
                number += 1
            p = p.nxt
            prev.nxt = p
            self.count -= 1
            return True
        else:
            p = self.tail.nxt.nxt
            self.tail.nxt = p
            self.count -= 1
            return True
    def delete_by_value(self, _val) -> bool:
        """Delete the first node that has the value registered.

        Args:
            _val (Value): Value of a Node.

        Returns:
            bool: True if deleted else False.
        """
        p = self.tail.nxt
        number = 0
        if self.count != 0:
            if p.val == _val:
                self.delete_first()
                return True
            else:
                prev = p
                while p.val is not _val:
                    prev = p
                    p = p.nxt
                    number += 1
                    if number == self.__len__():
                        return False
                p = p.nxt
                prev.nxt = p
                self.count -= 1
                return True
        else:
            return False



    