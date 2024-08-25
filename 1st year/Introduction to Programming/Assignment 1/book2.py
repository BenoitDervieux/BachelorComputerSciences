word = input("Enter a word (no x please): ")
vowel_count = 0
for c in word:
    if c == "A" or c == 'a' or c == 'E' or c == 'e' \
        or c == 'I' or c == 'i' or c == 'O' or c == 'o':
            print(c, ', ', sep='', end='')
            vowel_count += 1
    elif c == 'X' or c == 'x':
        break
print('(', vowel_count, ' vowels)', sep='')