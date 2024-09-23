
def merge(A, start, mid, end):
    first = A[start:mid]
    second = A[mid:end]
    index_first = 0
    index_second = 0
    for i in range(start, end):
        if index_first == len(first):
            A[i] = second[index_second]
            index_second += 1
        elif index_second == len(second):
            A[i] = first[index_first]
            index_first += 1
        elif first[index_first] <= second[index_second]:
            A[i] = first[index_first]
            index_first += 1
        else:
            A[i] = second[index_second]
            index_second += 1


def merge_sort(A, start, end):
    if len(A[start:end]) <= 1:
        # print(A[start:end])
        return
    else :
        mid = (end + start) // 2
        merge_sort(A, start, mid)
        merge_sort(A, mid, end)
        merge(A, start, mid, end)
        return A



test = [71,54,36,1,48,75,22,49,94,85,39,4,25, 999]
print(merge_sort(test, 0, 13))