# Pseudo code
# Take the length of the list
# iterate from the range of this list by starting by n-1 to 0
# copy the content of this list inside a new list
# return the list

test = [15, 5, 10, 26, 22]

def reverse_list(n):
    return [n[x-1] for x in range(len(n), 0, -1)]

print(reverse_list(test))
