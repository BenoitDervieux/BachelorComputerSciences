from dataclasses import dataclass
from re import S
from typing import Any

# The BstMap class is a binary search tree based implementation of
# a map (or dictionary). It works for any type of values and for
# all types keys that are comparable ==> we can compare keys using
# the operators < and >.


# The Node class is responsible for most of the work.
# Each call to the BstMap class is just delegated to the
# root node which starts a recursive sequence of calls to
# handle the request. Notice: All Node methods are recursive.
@dataclass
class Node:
    key: Any = None         # the key
    value: Any = None       # the value
    left: Any = None        # left child (a Node)
    right: Any = None       # right child (a Node)
    

    def put(self, key, value):
        if self.key > key:
            # Use string comparison
            # The entry goes to the left if
            # it is lower than the key or to
            # the right if it is higher
            if self.left == None:
                self.left = Node(key, value)
                # create a new node if not existing
            elif self.left != None:
                # use recursion to dive into the tree
                self.left.put(key, value)
        elif self.key < key:
            if self.right == None:
                self.right = Node(key, value)
            elif self.right != None:
                self.right.put(key, value)
        elif self.key == key:
            # change the value if the 
            # entry already exists
            self.value = value
            return
    
    def put_bst(self, key, value):
        if self.key > key:
            # Use string comparison
            # The entry goes to the left if
            # it is lower than the key or to
            # the right if it is higher
            if self.left == None:
                self.left = Node(key, value)
                # create a new node if not existing
            elif self.left != None:
                # use recursion to dive into the tree
                self.left.put_bst(key, value)
        elif self.key < key:
            if self.right == None:
                self.right = Node(key, value)
            elif self.right != None:
                self.right.put_bst(key, value)
        elif self.key == key:
            # change the value if the 
            # entry already exists
            self.value += 1
            return


    def to_string(self):
        s = ''
        # start a string
        if self.left != None:
            s += self.left.to_string()
            # Goes to the left node to gather the leftest value
            # through the return statement
        s += '(' + str(self.key) + ',' + str(self.value) + ') '
        # if leftest value, then add the key and value to the string
        if self.right != None:
            s += self.right.to_string()
            # then goes to the right side of the tree
            # return the string
        return s

    def count(self):
        countage = 0
        # Goes to the leftest node and count
        # Same principle as to_string
        if self.left != None:
            countage += self.left.count()
            # Return the number after visiting the other nodes
        countage += 1
        if self.right != None:
            countage += self.right.count()
        return countage 

    def get(self, key):
        # same principle as the put function
        if self.key > key:
            if self.left == None:
                # return none if no entry
                return None
            elif self.left != None:
                return self.left.get(key)
        elif self.key < key:
            if self.right == None:
                return None
            elif self.right != None:
                return self.right.get(key)
        elif self.key == key:
            # return the value if the corresponding value
            # exists
            return self.value
        

    def max_depth(self):
        # initialized 3 variables, 2 (dl, dr) that 
        # will compare both node depth
        # and choose the bigger one (d)
        dl = 0
        dr = 0
        d = 0
        if self.left != None:
            # access the nodes and return their depth
            dl += self.left.max_depth()
        if self.right != None:
            dr += self.right.max_depth()
        d = d + 1
        # increment the depth by one
        if dl > dr:
            # choose the biggest depth of node
            d += dl
        else:
            d += dr
            # return the biggest depth of node
        return d


    def count_leafs(self):
        """When a node has no other node then it means it
        is a leaf. Iterate into the tree and return the
        final number """
        number_leaf = 0
        if self.left != None:
            number_leaf += self.left.count_leafs()
        if self.right != None:
            number_leaf += self.right.count_leafs()
        if self.right == None and self.left == None:
            # When no node then the number of leaf increase
            number_leaf += 1
        return number_leaf

    # We do a left-to-right in-order traversal of the tree
    # to get the key-value pairs sorted base on their keys
    def as_list(self, lst):
        """Create a list, iterate through the whole tree to create tuples
        of key-values, append it to the list and returns the whole list"""
        lst = []
        if self.left != None:
            lst += self.left.as_list(lst)
        tpl = (self.key, self.value)
        lst.append(tpl)
        if self.right != None:
            lst += self.right.as_list(lst)
        return lst


# The BstMap class is rather simple. It basically just takes care
# of the case when the map is empty. All other cases are delegated
# to the root node ==> the Node class.
#
# The class below is complete ==> not to be changed
@dataclass
class BstMap:
    root: Node = None

    # Adds a key-value pair to the map
    def put(self, key, value):
        if self.root is None:    # Empty, add first node
            self.root = Node(key, value, None, None)
        else:
            self.root.put(key, value)
    
    def put_bst(self, key, value):
        if self.root is None:    # Empty, add first node
            self.root = Node(key, value, None, None)
        else:
            self.root.put_bst(key, value)

    # Returns a string representation of all the key-value pairs
    def to_string(self):
        if self.root is None:     # Empty, return empty brackets
            return "{ }"
        else:
            res = "{ "
            res += self.root.to_string()
            res += "}"
            return res

    # Returns the current number of key-value pairs in the map
    def size(self):
        if self.root is None:
            return 0
        else:
            return self.root.count()

    # Returns the value for a given key. Returns None
    # if key doesn't exist (or map is empty)
    def get(self, key):
        if self.root is None:
            return None
        else:
            return self.root.get(key)

    # Returns the maximum tree depth. That is, the length
    # (counted in nodes) of the longest root-to-leaf path
    def max_depth(self):
        if self.root is None:
            return 0
        else:
            return self.root.max_depth()

    # Returns a leaf node count. That is, the number of nodes 
    # with no children
    def count_leafs(self):
        if self.root is None:
            return 0
        else:
            return self.root.count_leafs()

    # Returns a sorted list of all key-value pairs in the map.
    # Each key-value pair is represented as a tuple and the
    # list is sorted on the keys ==> left-to-right in-order
    def as_list(self):
        lst = []
        if self.root is None:
            return lst
        else:
            return self.root.as_list(lst)
