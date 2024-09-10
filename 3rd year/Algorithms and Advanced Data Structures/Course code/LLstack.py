from LLNode import LLNode

class LLStack():
    def __init__(self):
        self.stack = None
    def empty(self):
        return self.stack is None
    def top(self):
        if self.stack is None:
            return None
        else:
            return self.stack.val
    def push(self, v):
        tmp = LLNode(v)
        tmp.nxt = self.stack
        self.stack = tmp
    def pop(self):
        if self.stack is not None:
            self.stack = self.stack.nxt

stack = LLStack()
stack.push(1)
stack.push(2)
stack.push(3)
stack.push(4)
stack.push(5)
print(stack.top())
stack.pop()
stack.pop()
stack.pop()
print(stack.top())
stack.pop()
stack.pop()
print(stack.top())
print(stack.empty())

from enum import IntEnum

class Op(IntEnum):
    ADD = 1
    DIV = 2
    MUL = 3
    SUB = 4
def doop(op:Op, d1:int, d2:int) -> int:
    match op:
        case Op.ADD: 
            return d1 + d2
        case Op.DIV:
            return d1 / d2
        case Op.MUL:
            return d1 * d2
        case Op.SUB:
            return d1 - d2

def calc(ops:LLStack, dig:LLStack) -> int:
    while not ops.empty():
        o = Op(ops.top())
        d1 = dig.top()
        dig.pop()
        d2 = dig.top()
        dig.pop()
        dig.push(doop(o, d1, d2))
        ops.pop()
    tmp = dig.top()
    dig.pop()
    return tmp

myops = LLStack()
mydigs = LLStack()

# myops.push(Op.SUB.value)
# mydigs.push(7)
# myops.push(Op.ADD.value)
# mydigs.push(1)
# myops.push(Op.MUL.value)
# mydigs.push(6)
# mydigs.push(4)

myops.push(Op.MUL.value)
mydigs.push(10)
# mydigs.push(6)
mydigs.push(4)

tmp = calc(myops, mydigs)
print("On va asserter là...")
print(tmp)

# assert tmp == 18

