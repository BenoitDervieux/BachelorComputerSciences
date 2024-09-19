from LinkedList import LinkedList

def second_last_node(L): 
    p = L.lst 
    while p.nxt.nxt is not None:
        p = p.nxt 
    return p.val

test = LinkedList()

for i in range(10):
    test.append(i)
    

print(test.lst)
print("second last node", second_last_node(test))