def scale(data, factor):
    for val in data:
        val *= factor
    return data

test = [15, 9, 7, 26, 22]

print(scale(test, 10))

## Here there isn't any allocation anymore, we just multiply each value
## but we never store it anywhere so data stays data