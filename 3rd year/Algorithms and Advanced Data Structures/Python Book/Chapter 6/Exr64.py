from ArrayStack import ArrayStack

def removingElementStack(S):
    if S.is_empty():
        return
    else:
        print(S.pop())
        removingElementStack(S)

S = ArrayStack()

for i in range(10):
    S.push(i)

removingElementStack(S)