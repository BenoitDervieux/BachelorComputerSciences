from LinkedList import LinkedList

def count_nodes(L):
    if L.nxt == None:
        return 1
    else:
        return count_nodes(L.nxt) + 1
    

test = LinkedList()

for i in range(50):
    test.append(i)

# print(test.lst.nxt.nxt)
print(count_nodes(test.lst))