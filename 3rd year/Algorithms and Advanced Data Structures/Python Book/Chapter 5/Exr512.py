
n = 5
data = [[(x + i) for x in range(n)] for i in range(n)]

for i in range(5):
    for j in range(5):
        print(data[i][j], end=' ')
    print()