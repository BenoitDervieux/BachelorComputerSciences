"""
Describe a recursive function for computing the nth Harmonic number,
Hn = ∑n i=1 1/i.
"""

def harmonic(n):
    if n > 1:
        return harmonic(n - 1) + 1/n
    elif n == 1:
        return 1/n

print(harmonic(4))