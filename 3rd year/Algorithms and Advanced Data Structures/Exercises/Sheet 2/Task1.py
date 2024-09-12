test = [5,4,1,9,6,8,62,2,54,41]


def mergeSort(A, start, stop):
    print("A en début", A[start:stop], " le start", start, " le stop", stop)
    if stop - start > 1:
        mid = round(int((start + stop) / 2))
        # print(A[start:mid])
        # print(A[mid:stop])
        print("mid", mid)
        mergeSort(A, start, mid)
        mergeSort(A, mid + 1, stop)
        merge2(A, start, stop, mid)
        return

def merge2(A, start, stop, mid):
    print("Debut de merge", A[start:stop+1])
    print("Start", start, " Stop", stop)
    if stop - start == 1:
        if A[start] > A[stop]:
            A[stop], A[start] = A[start], A[stop]
    else:
        for i in range(0, mid):
            
    print("Fin de merge", A[start:stop+1])
    
# mergeSort(test, 0, 10)

test2 = [1, 5, 3, 6]
merge2(test2, 0, 3, 2)