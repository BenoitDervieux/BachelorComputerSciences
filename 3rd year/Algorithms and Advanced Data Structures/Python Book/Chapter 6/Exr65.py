from ArrayStack import ArrayStack

def reverse(T):
    S = ArrayStack()
    for i in range(len(T)):
        S.push(T[0])
        del T[0]
    while S.is_empty() is not True:
        T.append(S.pop())
    return T
    
    

test = [1,2,3,4,5,6]

print(reverse(test))

