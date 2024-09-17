from Exr612 import Deque
from ArrayStack import ArrayStack

D = Deque()
S = ArrayStack()

for i in range(9):
    D.add_last(i)

D.print()
print()

for i in range(D.__len__()):
    S.push(D.delete_first())

S.print()
print()

for i in range(S.__len__()):
    D.add_first(S.pop())

D.print()
print()