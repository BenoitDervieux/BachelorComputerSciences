from ArrayStack import ArrayStack


stack = ArrayStack()

stack.push(5) # 5
stack.push(3) # 3 - 5
print(stack.pop()) #  5
stack.push(2) # 2 - 5
stack.push(8) # 8 - 2 - 5
stack.pop() # 2 - 5
stack.pop() #  5
stack.push(9) # 9 - 5
stack.push(1) # 1 - 9 - 5
stack.pop() # 9 - 5
stack.push(7) # 7 - 9 - 5
stack.push(6) # 6 - 7 - 9 - 5
stack.pop() # 7 - 9 - 5
stack.pop() # 9 - 5
stack.push(4) # 4 - 9 - 5
stack.pop() # 9 - 5
stack.pop() # 5

stack.print()