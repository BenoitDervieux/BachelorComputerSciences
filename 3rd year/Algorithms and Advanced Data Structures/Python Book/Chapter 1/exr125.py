n = "Let's try, Mike."

def remove_punctuation(n):
    new_string = ""
    for x in n:
        if 65 <= ord(x) <= 90 or 97 <= ord(x) <= 122 or 48 <= ord(x) <= 57 or ord(x) == 32:
            new_string += x
    return new_string
    
print(remove_punctuation(n))