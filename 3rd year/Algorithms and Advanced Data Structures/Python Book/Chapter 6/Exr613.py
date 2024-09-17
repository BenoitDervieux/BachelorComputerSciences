from Exr611 import Queue
from Exr612 import Deque

D = Deque()
Q = Queue()

for i in range(9):
    D.add_last(i)

D.print()
print()

for i in range(D.__len__()):
    Q.enqueue(D.delete_first())

Q.print()
print()

for i in range(Q.__len__()):
    D.add_last(Q.dequeue())

D.print()
    