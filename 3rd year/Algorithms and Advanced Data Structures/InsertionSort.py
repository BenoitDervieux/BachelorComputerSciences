test = [54, 15, 23,9, 99]

for j in range(1, len(test)):
    # On prend l'indice à j. Example 3
    key = test[j]
    # On instantie une variable à j - 1. Example donc 2
    i = j - 1
    while (i > -1) and (test[i] > key): # tant que i est égal 0 ou supérieur, et que liste à l'indice i est supérieur à la clé à l'indice 3
        test[i + 1] = test[i] # On fait remonter la valeur pour aller à la fin
        i = i - 1
    test[i + 1] = key

print(test)