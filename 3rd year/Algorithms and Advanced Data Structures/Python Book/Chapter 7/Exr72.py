from LinkedList import LinkedList

def concatenate_linked_list(L, M):
    Lprime = LinkedList()
    Lprime.append(L.lst)
    Lprime.append(M.lst)
    return Lprime

test = LinkedList()
test2 = LinkedList()

for i in range(10):
    test.append(i)

for i in range(30, 40):
    test2.append(i)

print(test.lst)
print(test2.lst)

print(concatenate_linked_list(test, test2).lst)