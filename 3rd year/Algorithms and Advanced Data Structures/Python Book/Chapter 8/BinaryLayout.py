from BinaryEulerTour import BinaryEulerTour

class BinatyLayout(BinaryEulerTour):
    
    def __init__(self, tree):
        super().__init__(tree)
        self._count = 0
    
    def _hook_invisit(self, p, d, path):
        p.element().setX(self._count)
        p.element().setY(d)
        self._count += 1