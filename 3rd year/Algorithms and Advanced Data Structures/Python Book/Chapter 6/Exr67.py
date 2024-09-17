from ArrayQueue import ArrayQueue


q = ArrayQueue()

print(q.enqueue(5), end=' - ')
print(q.enqueue(3), end=' - ')
print(q.dequeue(), end=' - ')
print(q.enqueue(2), end=' - ')
print(q.enqueue(8), end=' - ')
print(q.dequeue(), end=' - ')
print(q.dequeue(), end=' - ')
print(q.enqueue(9), end=' - ')
print(q.enqueue(1), end=' - ')
print(q.dequeue(), end=' - ')
print(q.enqueue(7), end=' - ')
print(q.enqueue(6), end=' - ')
print(q.dequeue(), end=' - ')
print(q.dequeue(), end=' - ')
print(q.enqueue(4), end=' - ')
print(q.dequeue(), end=' - ')
print(q.dequeue(), end=' - ')