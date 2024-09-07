test = [22, 4, 89, 6, 71, 55]


# def merge(list1, beginning, middle, end):
    

def merge(liste, beginning, middle, end):
    print("Middle", middle)
    if len(liste) == 2:
        if liste[0] > liste[1]:
            liste[0], liste[1] = liste[1], liste[0]
        return
    if len(liste) == 1:
        return
    else:
        biglist = []
        liste1 = liste[beginning:middle]
        liste2 = liste[middle:end]
        biglist.append(liste1)
        biglist.append(liste2)
        for i in biglist:
            merge(i, 0, len(i)//2, len(i))
    return liste

print(merge(test, 0, len(test)//2, len(test)))