# --------------------------------------------------------------------------
# Bit string
# File:       bit_string.py
# Description:
#   Produce the bit representation of a string
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
