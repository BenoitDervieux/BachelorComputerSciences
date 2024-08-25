# --------------------------------------------------------------------------
# Deque Data Structure
# File:       Deque.py
# Description:
#   This program provides a Deque data structure
#
# History:     9 Oct 2022
#
# ---------------------------------------------------------------------------

from dataclasses import dataclass
from typing import Any

# A head-and-tail implementation of a deque using data classes


# Each node is an instance of class Node
@dataclass
class Node:
    value: int = None
    nxt: Any = None  # Any since Node not properly defined at this point


@dataclass
class Deque:
    head: Node = None      # First node in queue
    tail: Node = None      # Last node in queue
    size: int = 0

    # Add element n as last entry in deque
    def add_last(self, n):
        current_node = self.head
        # Assign the head to the current node
        self.size = 1
        # Consider the size as 1
        if self.head is None:
            # When there is nothing in the class
            self.head = Node(n)
            # Create the first node with the data n
            self.tail = self.head
            # The tail is the head as well
        else:
            while current_node.nxt is not None:
                # iterate inside the nodex
                current_node = current_node.nxt
                self.size += 1
                # increase the size every node
            current_node.nxt = Node(n)
            # create a new node with the data n
            self.tail = current_node.nxt
            # Assign the tail to the last node
            self.size += 1
            # Increase the size by 1

    # Returns a string representation of the current deque content
    def to_string(self):
        elements = []
        # Initialize a list
        the_string = ''
        current_node = self.head
        # Assign the head to the current node
        if self.head is None:
            # I no element return empty string
            return '{ }'
        while current_node.nxt is not None:
            elements.append(current_node.value)
            # We append each value of the node to a list
            current_node = current_node.nxt
        elements.append(current_node.value)
        # append the last value
        for i in elements:
            i = str(i)
            the_string += i + ' '
            # Insert every value in a string
        return '{ ' + the_string + '}'
    # return a string with all the elements

    # Add element n as first entry in deque
    def add_first(self, n):
        if self.size == 1:
            self.tail = self.head
        current_node = self.head
        # Adress current to the head
        new_node = Node(n)
        # Create a new node
        new_node.nxt = current_node
        # Link the new node to the current head
        self.head = new_node
        # Assign the head to the new node
        self.size += 1
        # Increase the size of the structure

    # Returns (without removing) the last entry in the deque.
    # Gives error message and returns None when accessing empty deque.
    def get_last(self):
        if self.tail is None:
            # If the structure is empty returns a message
            print('You can\'t access an empty Queue')
            return
        value_to_get = self.tail.value
        # Get the value of the tail
        return value_to_get
        # Returns it

    # Returns (without removing) the first entry in the deque
    # Gives error message and returns None when accessing empty deque.
    def get_first(self):
        if self.tail is None:
            # If the structure is empty returns a message
            print('You can\'t access an empty Queue')
            return
        value_to_get = self.head.value
        # Get the head value
        return value_to_get
        # Returns the head value

    # Removes and returns the first entry in the deque.
    # Gives error message and returns None when accessing empty deque.
    # The case size = 1 requires speciall handling
    def remove_first(self):
        if self.head is None:
            # If the structure is empty returns a message
            print('You can\'t access an empty Queue')
            return
        first_value = self.head.value
        # Get the first value to return
        first_node = self.head
        # Adress the first node
        second_node = first_node.nxt
        # Adress the second node
        self.head = second_node
        # Reassign the head to the second node
        self.size -= 1
        # Reduce the size
        return first_value
        # Returns the first value

    # Removes and returns the last entry in the deque.
    # Gives error message and returns None when accessing empty deque.
    # The case size = 1 requires speciall handling
    def remove_last(self):
        if self.head is None:
            # If the structure is empty returns a message
            print('You can\'t access an empty queue')
            return
        current_node = self.head
        # Assign head to the current_node
        if self.size == 1:
            # If size only 1, empty both the tail and the head
            # And reduce the size
            self.head = None
            self.tail = None
            self.size -= 1
            return
        else:
            while current_node.nxt is not None:
                # Iterate through the structure up to the last
                last_node = current_node
                current_node = current_node.nxt
            last_value = current_node.value
            # Get the last value
            last_node.nxt = None
            # Cut the next link of the penultimate so
            # that the last node is lost
            current_node = last_node
            # Reassign the current not to the last node
            self.tail = current_node
            # Assign the tail to the current node
            self.size -= 1
            # Reduce the size
            return last_value
            # Returns the last value
