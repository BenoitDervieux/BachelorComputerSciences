from random import randint as rd
import time

liste100 = []
liste1000 = []
liste10000 = []
liste100000 = []
liste1000000 = []

for i in range(100):
    liste100.append(rd(1, 100))
    
for i in range(1000):
    liste1000.append(rd(1, 1000))

for i in range(10000):
    liste10000.append(rd(1, 10000))

for i in range(100000):
    liste100000.append(rd(1, 100000))

for i in range(1000000):
    liste1000000.append(rd(1, 1000000))

data = [[0] * 5 for j in range(3)]

liste_of_liste = [liste100, liste1000, liste10000, liste100000, liste1000000]

for i in range(5):
    start_time = time.time() * 10000
    liste_of_liste[i].pop()
    end_time = time.time()
    data[0][i] = start_time - end_time
    
    start_time = time.time() * 10000
    liste_of_liste[i].pop(len(liste_of_liste[i]) // 2)
    end_time = time.time()
    data[1][i] = start_time - end_time

    start_time = time.time() * 10000
    liste_of_liste[i].pop(len(liste_of_liste[i])-1)
    end_time = time.time()
    data[2][i] = start_time - end_time

for i in range(3):
    for j in range(5):
        print(data[i][j], end=" ")
    print()

""" No solution here but i feel as if the pop is constant no matter what is the 
"""