""" 
Describe a recursive function for converting a string of digits into the 
integer it represents. For example, 13531 represents the integer 13,531
"""
""" 
Let's consider that it's always 2 digits, and x decimals
"""

def convertStringDigit(s, length):
    if len(s) == 1:
        return float(s)*10**(length-len(s))
    else:
        result = convertStringDigit(s[:-1], length)
        michel = float(s[len(s) - 1])
        toAdd = michel*10**(length-len(s))
        result += toAdd
        return result
    
test = '13531'

print(convertStringDigit(test, 4))