def binary_search(data, target, low, high):
    if low > high:
        return False
    else:
        mid = (low + high) // 2
        if target == data[mid]:
            return True
        elif target < data[mid]:
            return binary_search(data, target, low, mid - 1)
        else:
            return binary_search(data, target, mid + 1, high)


test = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19]

print(binary_search(test, 22, 0, 18))