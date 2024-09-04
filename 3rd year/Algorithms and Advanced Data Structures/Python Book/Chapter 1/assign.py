michel = 55
pedro = michel
print("Michel : {0}".format(michel))
print("Pedro : {0}".format(pedro))
pedro += 5
print("Michel : {0}".format(michel))
print("Pedro : {0}".format(pedro))
michel += 10
print("Michel : {0}".format(michel))
print("Pedro : {0}".format(pedro))

alpha = [1,2,3]
beta = alpha
beta += [4, 5]
beta = beta + [6, 7]
print(alpha)
print('marron', 5)
print(michel, pedro, sep=':', end="-\n")

def sqrttt(x):
    if not isinstance(x,(int, float)):
        raise TypeError('x must be a numeric')
    elif x < 0:
        raise ValueError('x cannot be negative')
    else:
        return x * x

print(sqrttt(10))
print('Check if program continues')
data = [22,23,24,25]
i = iter(data)
for p in i:
    print(p)
    
def factors1(n):
    results = []
    for k in range(1, n+1):
        if n % k == 0:
            results.append(k)
    return results

def factors2(n):
    for k in range(1, n+1):
        if n % k == 0:
            yield k

def factors3(n):
    k = 1
    while k * k < n:
        if n % k == 0:
            yield k
            yield n // k
        k += 1
    if k*k == n:
        yield k
            
print('Factor 1')
print(factors1(100))
print('Factor 2')
for n in factors2(100):
    print(n, end=", ")
print('Factor 3')
for n in factors3(100):
    print(n, end=", ")
print()
print([n for n in factors3(100)])