# --------------------------------------------------------------------------
# Subtle Strings producer
# File:       subtle_string_producer.py
# Description:
#   Produce 1000 different strings varying of one bit. Prints their bit value.
#
# History:     27 November 2022
#
# ---------------------------------------------------------------------------

import bitarray
ba = bitarray.bitarray()

ba.frombytes('hi'.encode('utf-8'))


def s_to_bitlist(s):
    ords = (ord(c) for c in s)
    shifts = (7, 6, 5, 4, 3, 2, 1, 0)
    return [(o >> shift) & 1 for o in ords for shift in shifts]


def string_to_bits(s):
    string = ''
    lst = s_to_bitlist(s)
    for i in lst:
        string += str(i)
    return string


file_to_create = open('subtle_strings.txt', 'w+', encoding="utf-8")

words_to_change = 'comput'

word_to_add = ''

for i in range(1000):
    word_to_add = words_to_change + chr(161 + i)
    bitversion = string_to_bits(word_to_add)
    file_to_create.write(word_to_add + ' - ' + bitversion + '\n')
words_to_change = word_to_add

file_to_create.close
