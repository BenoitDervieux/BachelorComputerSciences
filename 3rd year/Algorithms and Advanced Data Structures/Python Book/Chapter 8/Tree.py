from LinkedQueue import LinkedQueue
class Tree:
    """Abstract base class representing a tree structure"""
    
    class Position:
        
        def element(self):
            raise NotImplementedError('Must be implemented')
        
        def __eq__(self, other):
            raise NotImplementedError('Must be implemented')
        
        def __ne__(self, other):
            return not (self == other)
        
    def root(self):
        raise NotImplementedError('Must be implemented')
    
    def parent(self, p):
        raise NotImplementedError('Must be implemented')
    
    def num_children(self, p):
        raise NotImplementedError('Must be implemented')
    
    def children(self, p):
        raise NotImplementedError('Must be implemented')
    
    def __len__(self):
        raise NotImplementedError('Must be implemented')
    
    def is_root(self, p):
        return self.root() == p
    
    def is_leaf(self, p):
        return self.num_children(p) == 0
    
    def is_empty(self):
        return len(self) == 0
    
    def depth(self, p):
        if self.is_root(p):
            return 0
        else:
            return 1 + self.depth(self.parent(p))
    
    def _height1(self):
        return max(self.depth(p) for p in self.positions() if self.is_leaf(p))
    
    def _height2(self, p):
        if self.is_leaf(p):
            return 0
        else:
            return 1 + max(self._height2(c) for c in self.children(p))
    
    def height(self, p = None):
        if p is None:
            p = self.root()
        return self._height2(p)
    
    def __iter__(self):
        for p in self.positions():
            yield p.element()
        
    def preorder(self):
        if not self.is_empty():
            for p in self._subtree_preorder(self.root()):
                yield p
    
    def _subtree_preorder(self, p):
        yield p
        for c in self.children(p):
            for other in self._subtree_preorder(c):
                yield other
    
    def positions(self):
        return self.preorder()
    
    def postorder(self):
        if not self.is_empty():
            for p in self._subtree_postorder(self.root()):
                yield p
    
    def _subtree_postorder(self, p):
        for c in self.children(p):
            for other in self._subtree_postorder(c):
                yield other
        yield p
    
    def breadthfirst(self):
        if not self.is_empty():
            fringe = LinkedQueue()
            fringe.enqueue(self.root())
            while not fringe.is_empty():
                p = fringe.dequeue()
                yield p
                for c in self.children(c):
                    fringe.enqueue(c)
    
    def inorder(self):
        if not self.is_empty():
            for p in self._subtree_inorder(self.root()):
                yield p
    
    def _subtree_inorder(self, p):
        if self.left(p) is not None:
            for other in self._subtree_inorder(self.left(p)):
                yield other
        yield p
        if self.right(p) is not None:
            for other in self._subtree_inorder(self.right(p)):
                yield other
    