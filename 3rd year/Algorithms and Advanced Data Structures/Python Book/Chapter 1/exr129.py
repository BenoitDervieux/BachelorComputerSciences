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
test = ['c', 'a', 't', 'd', 'o']

def output_strings(liste, tmp=""):
    if len(liste) == 1:
        tmp += liste[0]
        print(tmp)
        if len(tmp) == 1:
            return ""
        return tmp[:-1]
    for i in range(len(liste)):
        tmp += liste[i]
        copy=[]
        for p in range(len(liste)):
            if liste[p] != liste[i]:
                copy.append(liste[p])
        tmp = output_strings(copy, tmp)
        if len(tmp) == 1:
            tmp = ""
        else:
            tmp = tmp[:-1]
    return tmp

output_strings(test)