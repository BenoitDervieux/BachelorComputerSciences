test = [1, 5]
test2 = [4, 6, 8]


def merge(A, start, middle, end):
    B = A[start:end]
    A_first = A[start:middle]
    B_first = A[middle:end]
    index_a = 0
    index_b = 0
    for i in range(len(B)):
        # Case when both of them are not exhausted
        if index_a < len(A_first) and index_b < len(B_first):
            B[i] = A_first[index_a] if A_first[index_a] < B_first[index_b] else B_first[index_b]
            if A_first[index_a] < B_first[index_b]:
                index_a += 1
            else:
                index_b += 1
        # Case when A is exhausted
        elif index_a == len(A_first) and len(B_first) > 0:
            B[i] = B_first[index_b]
            index_b += 1
        elif index_b == len(B_first) and len(A_first) > 0:
            B[i] = A_first[index_a]
            index_a += 1
    for i in range(len(B)):
        A[start + i] = B[i]

def mergeOptTest(A, start, middle, end):
    A_first = A[start:middle]
    B_first = A[middle:end]
    index_a = 0
    index_b = 0
    for i in range(start, len(A[start:end])):
        # Case when both of them are not exhausted
        if index_a < len(A_first) and index_b < len(B_first):
            A[start + i] = A_first[index_a] if A_first[index_a] < B_first[index_b] else B_first[index_b]
            if A_first[index_a] < B_first[index_b]:
                index_a += 1
            else:
                index_b += 1
        # Case when A is exhausted
        elif index_a == len(A_first) and len(B_first) > 0:
            A[start + i] = B_first[index_b]
            index_b += 1
        elif index_b == len(B_first) and len(A_first) > 0:
            A[start + i] = A_first[index_a]
            index_a += 1

def mergeSort(A, start, end):
    if len(A[start:end]) == 1:
        return
    if len(A[start:end]) == 2:
        if A[end-1] < A[start]:
            A[end-1], A[start] = A[start], A[end-1]
        return
    if start < end:
        middle = (start + end) // 2
        mergeSort(A, start, middle)
        mergeSort(A, middle, end)
        mergeOptTest(A, start, middle, end)
    return A

superTest = [71,54,36,1,48,75,22,49,94,85,39,4,25]
print(mergeSort(superTest, 0, 14))


# A = [1,5,4,6,8]
# merge(A, 0, 2, 4)

    