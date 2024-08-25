# --------------------------------------------------------------------------
# Types of characters
# File:       simple_strings.py
# Description:
#   Returns the first and last character of a string
#   Returns the number of vowel and consonants
#   Returns the number of character, symbols (including spaces) and numbers
#
# History:     15 Sept 2022
#
# ---------------------------------------------------------------------------


def first_last(s):
    # Returns the first and last character in the string s
    first = s[0]
    # Get the first character at position 0 of the sentence
    last = s[len(s)-1]
    # returns the last character at position number of character minus 1
    print("First and last in \"{0}\": {1} and {2}".format(s, first, last))
    # print it


def char_types(s):
    # Returns the number of vowels and consonants in string s
    s = s.lower()
    # change all the letter into lowercase
    vowels = 0
    # declare the variables consonants and vowels
    consonant = 0
    for i in range(0, len(s)):
        # It iterates in the sentence
        if (s[i] == 'a' or s[i] == 'e' or s[i] == 'i' or
           s[i] == 'o' or s[i] == 'u' or s[i] == 'y'):
            # if the letter is a,e,i,o,u,y it is a vowel
            vowels += 1
            # then we had it in our counter
        elif (65 <= ord(s[i]) < 91 or 97 <= ord(s[i]) < 123):
            # if it is a consonant, we add to the counter
            consonant += 1
    print(f"In that sentence, the number of vowels is {vowels}"
          + f" and the number of consonants is {consonant}")
    # we print everything


def char_symbol_number(s):
    # Returns the number of characters, symbols (including spaces)
    # and numbers in string s
    letters = 0
    # we declare the variables letters,
    # symbols and numbers
    symbols = 0
    numbers = 0
    for i in range(0, len(s)):
        # we iterate through the string
        if (65 <= ord(s[i]) < 91 or 97 <= ord(s[i]) < 123):
            # we check the ascii code if this is a letter,
            # if yes we add it into our count
            letters += 1
        elif (48 <= ord(s[i]) <= 57):
            # we check the ascii code if this is a number,
            # if yes we add to the counter
            numbers += 1
        else:
            symbols += 1
            # else we consider it's a symbol
    print(f'In the sentence "{s}" the number of letters is {letters},'
          + f' symbols is {symbols} and numbers is {numbers}')
    # we print everything


def main():
    sentence1 = 'May the Force be with you!'
    sentence2 = 'I am 1 with the Force, not 2...'
    first_last(sentence1)
    char_types(sentence1)
    char_symbol_number(sentence2)


main()
