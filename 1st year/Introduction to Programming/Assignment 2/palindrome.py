# --------------------------------------------------------------------------
# Palindrome
# File:       palindrome.py
# Description:
#   Check if this is a palindrome or not
#
# History:     15 Sept 2022
#
# ---------------------------------------------------------------------------

sentence = input('Check is the sentence is a palindrome: ')


def is_palindrome(s):
    s = s.lower()
    # we change all the letters into lowercase
    no_space_palin = []
    # we create a list to iterate inside
    count = 0
    # we declare a variable to keep track of the count

    for i in range(0, len(s)):
        # first we take only the letters, no space, no number
        if (97 <= ord(s[i]) <= 122):
            no_space_palin += s[i]
            # we add them to the list

    for i in range(0, int(len(no_space_palin)/2)):
        # then we divide the list in two and we compare letter by letter
        if no_space_palin[i] == no_space_palin[len(no_space_palin)-1-i]:
            # if the end is the same as the beginning
            count += 1
        else:
            print('This is not a palindrome')
            # We print the results
            break
    if count == len(no_space_palin)//2:
        print('This is a palindrome')


is_palindrome(sentence)
