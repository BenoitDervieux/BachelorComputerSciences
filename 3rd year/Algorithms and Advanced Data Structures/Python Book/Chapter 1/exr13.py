def minima(data):
    minimum = data[0]
    maximum = data[0]
    for i in range(len(data)):
        if (data[i] < minimum):
            minimum = data[i]
        if (data[i]  > maximum):
            maximum = data[i]
    return (minimum, maximum)

test = [23, 5, 49, 99, 56]
print(minima(test))