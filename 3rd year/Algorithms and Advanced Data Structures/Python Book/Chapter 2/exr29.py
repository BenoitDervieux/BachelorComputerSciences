class Vector:
# ””Represent a vector in a multidimensional space.”””

    def __init__(self, other = None):
        "””Create d-dimensional vector of zeros.”””"
        if isinstance(other, list):
            self.coords = [0] * len(other)
            for i in range(len(other)):
                self.coords[i] = other[i]
        elif isinstance(other, int):
            if other == 0:
                raise ValueError('This is 0')
            self.coords = [0]*other

    def __len__(self):
        "””Return the dimension of the vector.”””"
        return len(self.coords)

    def __getitem__(self, j):
        "”””Return jth coordinate of vector.”””"
        return self.coords[j]

    def __setitem__(self, j, val):
        "”””Set jth coordinate of vector to given value.”””"
        self.coords[j] = val

    def __add__(self, other):
        "”””Return sum of two vectors.”””"
        if len(self) != len(other): # relies on len method
            raise ValueError('dimensions must agree')
        result = Vector(len(self)) # start with vector of zeros
        for j in range(len(self)):
            result[j] = self[j] + other[j]
        return result

    def __radd__(self, other):
        if len(self) != len(other): # relies on len method
            raise ValueError('dimensions must agree')
        result = Vector(len(self)) # start with vector of zeros
        for j in range(len(self)):
            result[j] = self[j] + other[j]
        return result
    
    def __sub__(self, other):
        if len(self) != len(other):
            raise ValueError('dimensions must agree')
        result = Vector(len(self)) # start with vector of zeros
        for j in range(len(self)):
            result[j] = self[j] - other[j]
        return result

    def __eq__(self, other):
        "”””Return True if vector has same coordinates as other.”””"
        return self.coords == other.coords

    def __ne__(self, other):
        "”””Return True if vector differs from other.”””"
        return not self == other # rely on existing eq definition

    def __str__(self):
        "”””Produce string representation of vector.”””"
        return '<' + str(self.coords)[1:-1] + '>' # adapt list representation
    def __neg__(self):
        result = Vector(len(self)) # start with vector of zeros
        for j in range(len(self)):
            result[j] -= self[j] 
        return result
        
    def __mul__(self, other = None, multiple = 1):
        if isinstance(other, (Vector)):
            if len(self) != len(other): # relies on len method
                raise ValueError('dimensions must agree')
            else:
                summation = 0
                for i in range(len(self)):
                    summation += self[i] * other[i]
            return summation
        result = Vector(len(self)) # start with vector of zeros
        for j in range(len(self)):
            result[j] = self[j] * multiple
        return result
    def __rmul__(self, multiple = 1):
        result = Vector(len(self)) # start with vector of zeros
        for j in range(len(self)):
            result[j] = self[j] * multiple
        return result
        
    
testvec1 = [10, 4, 6]
testvec2 = [5, 2, 3]
test1 = Vector(3)
test2 = Vector(3)
test1 = test1.__add__(testvec1)
test2 = test2.__add__(testvec2)
print(test1.__str__())
print(test1.__sub__(testvec2))
test1 = test1.__neg__()
print(test1.__str__())

v = Vector(5) # construct five-dimensional <0, 0, 0, 0, 0>
v[1] = 23 # <0, 23, 0, 0, 0> (based on use of setitem )
v[-1] = 45 # <0, 23, 0, 0, 45> (also via setitem )
print(v[4]) # print 45 (via getitem )
u=v+v # <0, 46, 0, 0, 90> (via add )
print(u) # print <0, 46, 0, 0, 90>
total = 0
for entry in v: # implicit iteration via len and getitem
    total += entry
print("V avant add", v)
v = u + [5, 3, 10, -2, 1]
print("V apres add",v)
v = [5, 3, 10, -2, 1] + u
print("V après radd", v)
v = v * 3
print("V after multiplication droite", v)
v = 3 * v
print("V after multiplication rmul", v)
v = v * v 
print("V after multiplication vector mul", v)

qq = [4, 7, 5]
q = Vector(qq)

print(q.__str__())