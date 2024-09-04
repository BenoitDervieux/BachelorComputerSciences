def scale(data, factor):
    for j in range(len(data)):
        data[j] *= factor
    return data

test = [15, 9, 7, 26, 22]

print(scale(test, 10))

## Answer
## Maybe it's because there is a new assignment in the data