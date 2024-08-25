from dataclasses import dataclass
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
        # if new key bigger than key and if no left child
        if self.key > key:
            if self.left is None:   # add a new Node
                self.left = Node(key, value)
            elif self.left is not None:     # if not empty
                # recursive call, go to left side of tree
                self.left.put(key, value)
        elif self.key < key:
            if self.right is None:
                self.right = Node(key, value)
            elif self.right is not None:
                self.right.put(key, value)
        elif self.key == key:               # if new key = key, change value
            self.value = value
            return

    def bst_put(self, key, value):  # mainly for part 3 BST
        # same as put, only change value +1 for word if same
        if self.key > key:
            if self.left is None:
                self.left = Node(key, value)
            elif self.left is not None:
                self.left.bst_put(key, value)
        elif self.key < key:
            if self.right is None:
                self.right = Node(key, value)
            elif self.right is not None:
                self.right.bst_put(key, value)
        elif self.key == key:
            self.value += 1
            # main change, instead of change value add +1 count words
            return

    def to_string(self):
        # empty string
        s = ''
        # Goes to most left Node through 'return s', reiterates func
        # if self.left has a node go into it
        if self.left is not None:
            s += self.left.to_string()  # adds node to string with recur call
        # adds key and value to string if most left Node = None
        s += f'({str(self.key)} {str(self.value)})'
        # right side
        if self.right is not None:
            s += self.right.to_string()
        return s

    def count(self):
        # similar to 'to_string', pass node (left) and add to counter
        counter = 0
        if self.left is not None:
            counter += self.left.count()
        counter += 1
        if self.right is not None:
            counter += self.right.count()
        return counter

    def get(self, key):
        # get input key value if it exists, else false
        if key is None:
            return None
        elif key == self.key:
            # iterates until key either is or isnt self.kek
            return self.value

        elif key < self.key:
            # input less asci value than key
            if self.left is None:
                return None
            elif self.left is not None:
                return self.left.get(key)
            # recursive call with key, into left side

        elif key > self.key:
            if self.right is None:
                return None
            elif self.right is not None:
                return self.right.get(key)
            # recursive call with key, into right side

    def max_depth(self):    # iterate through tree
        # as a default parameter,
        maxleft = 1    # if no value as parameter, uses default
        maxright = 1    # aka:for every Node adds +1
        if self.left is not None:
            maxleft += self.left.max_depth()
        if self.right is not None:
            maxright += self.right.max_depth()
        if maxleft > maxright:
            return maxleft
        else:
            return maxright

    def count_leafs(self):
        # leaf = Node with no children
        leafs = 0
        # iterate to bottom nodes
        if self.left is not None:
            leafs += self.left.count_leafs()
        if self.right is not None:
            leafs += self.right.count_leafs()
        # if no left and right child = leaf
        if self.left is None and self.right is None:
            leafs += 1
        return leafs

    # We do a left-to-right in-order traversal of the tree
    # to get the key-value pairs sorted base on their keys
    def as_list(self, lst):
        lst = []        # empty list
        if self.left is not None:   # recursive call, iterate through tree
            lst += self.left.as_list(lst)
        tup = (self.key, self.value)    # assign Node key n value as tupl
        lst.append(tup)                 # add tuple to list
        if self.right is not None:
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

    def bst_put(self, key, value):
        if self.root is None:    # Empty, add first node
            self.root = Node(key, value, None, None)
        else:
            self.root.bst_put(key, value)

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
