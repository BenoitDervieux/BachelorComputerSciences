test = ['c', 'a', 't', 'd', 'o']

# number of combinations possible of the words : 5 * 4 * 3 * 2 or 5!
# number of word on a line : ca, cat, catd, catdo, at, atd, atdo, td, tdo, do and reverse

# def combinations(n):
#     liste_de_mots = []
#     for a in range(len(n)):
#         buff = []
#         liste_de_mots.append(add_letter(a, n, buff))
#     return liste_de_mots

# def add_letter(index, liste_base, liste_mots):
#     liste_mots.append(liste_base[index])
#     liste_base.remove(liste_base[index])
#     if (len(liste_base) != 0):
#         add_letter(index, liste_base, liste_mots)
#     return liste_mots
test = ['a', 'b', 'c', 'd']
def zeubi(n, liste_de_mots, stri='', size=0):
    if (size == 0):
        size = len(n)
    for i in range(len(n)):
        stri += n[i]
        print(n[i], end=' - ')
        print('length:', len(n))
        copy = []
        for p in range(len(n)):
            if n[p] != n[i]:
                copy.append(n[p])
        if (len(n)) == 1:
            liste_de_mots.append(stri)
            stri = stri[:-1]
            return
        else:
            zeubi(copy, liste_de_mots, stri, size)
    return liste_de_mots
        
liste_de_mots = []
print(zeubi(test, liste_de_mots))