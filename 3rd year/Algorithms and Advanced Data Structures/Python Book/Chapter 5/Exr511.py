data = [[0] * 5 for i in range(5)]

for i in range(5):
    for j in range(5):
        data[i][j] = i + j
        print(data[i][j], end=' ')
    print()