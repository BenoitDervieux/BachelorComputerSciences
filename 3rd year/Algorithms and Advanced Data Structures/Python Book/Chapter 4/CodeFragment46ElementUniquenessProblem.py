def unique(S, start, stop):
    if stop - start <= 1: return True
    elif not unique(S, start, stop - 1): return False
    elif not unique(S, start + 1, stop): return False
    else: return S[start] != S[stop - 1]


test = [1,2,3,4,5,6,7,9,9]

print(unique(test, 0, 8))