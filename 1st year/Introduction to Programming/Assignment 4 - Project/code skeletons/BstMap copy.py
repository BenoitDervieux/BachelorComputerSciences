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
        mini_iter = len(key) if len(key) < len(self.key) else len(self.key)
        i = 0
        if ord(self.key[i]) > ord(key[i]):
            if self.left == None:
                self.left = Node(key, value)
            elif self.left != None:
                self.left.put(key, value)
        elif ord(self.key[i]) < ord(key[i]):
            if self.right == None:
                self.right = Node(key, value)
            elif self.right != None:
                self.right.put(key, value)
        elif ord(self.key[i]) == ord(key[i]):
            for i in range(mini_iter):
                if i > 0:
                    already_entry = self.key[i].lower()
                    new_entry = key[i].lower()
                else:
                    already_entry = self.key[i]
                    new_entry = key[i]
                if ord(already_entry) == ord(new_entry):
                    if self.key == key:
                        self.value = value
                        return
                    elif key in self.key:
                        if self.left == None:
                            self.left = Node(key, value)
                            pass
                        elif self.left != None:
                            self.left.put(key, value)
                    elif self.key in key:
                        if self.right == None:
                            self.right = Node(key, value)
                            pass
                        elif self.right != None:
                            self.right.put(key, value)
                #if the words are still smaller than the i
                elif ord(already_entry) > ord(new_entry):
                        if self.left == None:
                            self.left = Node(key, value)
                            pass
                        elif self.left != None:
                            self.left.put(key, value)
                elif ord(already_entry) < ord(new_entry):
                    if self.right == None:
                        self.right = Node(key, value)
                        pass
                    elif self.right != None:
                        self.right.put(key, value)
            if self.get(key) == None:
                if len(self.key) > i:
                    if self.right == None:
                            self.right = Node(key, value)
                            pass
                    elif self.right != None:
                        self.right.put(key, value)
                elif len(key) > i:
                    if self.left == None:
                        self.left = Node(key, value)
                        pass
                    elif self.left != None:
                        self.left.put(key, value)
                
        

    def to_string(self):
        s = ''
        if self.left != None:
            s += self.left.to_string()
        s += '(' + str(self.key) + ',' + str(self.value) + ') '
        if self.right != None:
            s += self.right.to_string()
        return s  # Placeholder code to avoid crash in demo program. To be replaced

    def count(self):
        countage = 0
        if self.left != None:
            countage += self.left.count()
        countage += 1
        if self.right != None:
            countage += self.right.count()
        return countage  # Placeholder code to avoid crash in demo program. To be replaced

    def get(self, key):
        mini_iter = len(key) if len(key) < len(self.key) else len(self.key)
        i = 0
        valeur = None
        if ord(self.key[i]) > ord(key[i]):
            if self.left == None:
                return valeur
            elif self.left != None:
                return self.left.get(key)
        elif ord(self.key[i]) < ord(key[i]):
            if self.right == None:
                return valeur
            elif self.right != None:
                return self.right.get(key)
        elif ord(self.key[i]) == ord(key[i]):
            for i in range(mini_iter):
                if ord(self.key[i]) == ord(key[i]):
                    if self.key == key:
                        valeur = self.value
                        return valeur
                    elif key in self.key:
                        if self.left == None:
                            return valeur
                        elif self.left != None:
                            return self.left.get(key)
                    elif self.key in key:
                        if self.right == None:
                            return valeur
                        elif self.right != None:
                            return self.right.get(key)
                elif len(self.key) > i:
                    if self.right == None:
                            return valeur
                    elif self.right != None:
                        return self.right.get(key)
                elif len(key) > i:
                    if self.left == None:
                        return valeur
                    elif self.left != None:
                        return self.left.get(key)
        

    def max_depth(self):
        dl = 0
        dr = 0
        d = 0
        if self.left != None:
            dl += self.left.max_depth()
        if self.right != None:
            dr += self.right.max_depth()
        d = d + 1
        if dl > dr:
            d += dl
        else:
            d += dr
        return d
        
             
        

    def count_leafs(self):
        pass     # Placeholder code ==> to be replaced

    # We do a left-to-right in-order traversal of the tree
    # to get the key-value pairs sorted base on their keys
    def as_list(self, lst):
        return [None]    # Placeholder code to avoid crash in demo program. To be replaced


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
