def number_vowel(n, vowels={'a', 'e', 'i', 'o', 'u'}):
    isin = 0
    for x in n:
        if x in vowels:
            isin += 1
    return isin

test = "feneck resplendit meme dans l'ivresse je reste dandy"
print(number_vowel(test))