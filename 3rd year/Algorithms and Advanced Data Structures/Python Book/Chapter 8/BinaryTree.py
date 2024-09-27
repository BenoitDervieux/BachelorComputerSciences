from Tree import Tree

class BinaryTree(Tree):
    
    def left(self, p):
        raise NotImplementedError('Must be implemented')
    
    def right(self, p):
        raise NotImplementedError('Must be implemented')
    
    def sibling(self, p):
        parent = self.parent(p)
        if parent is None:
            None
        else:
            if p == self.left(parent):
                return self.right(parent)
            else:
                return self.left(parent)
    
    def children(self, p):
        if self.left(p) is not None:
            yield self.left(p)
        if self.right(p) is not None:
            yield self.right(p)
    
    def num_children(self, p):
        numChildren = 0
        if self.right(p) is not None:
            numChildren += 1
        if self.left(p) is not None:
            numChildren += 1
        return numChildren