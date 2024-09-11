

def PuzzleSolve(k, S, U):
    for e in U:
        S.append(e)
        U.update(S)
        if k == 1:
            print(S)
            for i in range(len(S)):
                if (S[i] in [S[x] for x in range(len(S)) if x != i]) is True:
                    return
            return "Solution found"
        else:
            PuzzleSolve(k-1, S, U)
        S.remove(e)
        U.update(S)


test = ['a', 'b', 'c', 'd']
my_set = set(test)
empty_list = []

print(PuzzleSolve(3, empty_list, my_set))

# i = 0
# test2 = ['a', 'b', 'c', 'd', 'd']
# for i in range(len(test2)):
#     print(test2[i] in [test2[x] for x in range(len(test2)) if x != i])
    