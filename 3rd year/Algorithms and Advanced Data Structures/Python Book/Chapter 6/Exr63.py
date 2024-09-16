from ArrayStack import ArrayStack

def transfer(S, T): 
    while S.is_empty() is not True:
        T.push(S.pop())
    

S = ArrayStack()
T = ArrayStack()

S.push(11)
S.push(12)
S.push(13)
S.push(14)
S.push(15)
S.push(16)

T.push(1)
T.push(2)
T.push(3)
T.push(4)
T.push(5)
T.push(6)
        
transfer(S, T)

T.print()