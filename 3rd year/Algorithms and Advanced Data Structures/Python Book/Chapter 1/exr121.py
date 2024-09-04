test = []
n = 0
Continue = True
while Continue :
    n = input()
    if n == 'non':
        Continue = False
    else :
        test.append(n)

for i in range(len(test)-1, -1, -1):
    print(test[i])