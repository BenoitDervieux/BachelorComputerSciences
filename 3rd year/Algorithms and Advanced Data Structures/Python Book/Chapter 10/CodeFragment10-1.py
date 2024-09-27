freq = {}
filename = "3rd year//Algorithms and Advanced Data Structures//Python Book//Chapter 10//paroles-lunatic.txt"
for piece in open(filename).read().lower().split():
    word = ''.join(c for c in piece if c.isalpha())
    if word:
        freq[word] = 1 + freq.get(word, 0)
        
max_word = ''
max_count = 0
for (w, c) in freq.items():
    if c > max_count:
        max_count = c
        max_word = w

print("The most frequent word is '", max_word, "' with a frequency of", max_count)