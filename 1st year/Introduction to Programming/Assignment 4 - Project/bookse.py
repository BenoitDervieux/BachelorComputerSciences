def hash_this(word):
    # source: https://www.youtube.com/watch?v=jtMwp0FqEcg&t=88s
    g = 31
    hasheuh = 0
    for i in range(len(word)):
        hasheuh += g * hasheuh +  ord(word[i])
    
    return hasheuh

print(hash_this('test'))
print(hash_this('sett'))