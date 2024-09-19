from CircularLinkedList import CircularLinkedList

def number_of_nodes(L):
    number = 0
    tout = L.tail.nxt
    while True: 
        number += 1
        tout = tout.nxt
        if tout == L.tail.nxt:
            break
    return number


test = CircularLinkedList()
for i in range(10):
    test.append(i)

print(number_of_nodes(test))