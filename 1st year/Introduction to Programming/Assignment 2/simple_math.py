# --------------------------------------------------------------------------
# Simple maths
# File:       simple_maths.py
# Description:
#   Implement 8 math function without calling any library
#
# History:     15 Sept 2022
#
# ---------------------------------------------------------------------------


def inc(n):
    # Increments n with one
    n += 1
    return n


def inc_with(n, t):
    # Increments n with the value of t
    n += t
    return n


def dec(n):
    # Decrements n with one
    n -= 1
    return n


def dec_with(n, t):
    # Decrements n with the value of t
    n -= t
    return n


def greatest(n1, n2):
    # Returns the largest of the values n1 and n2
    largest = n1 if n1 > n2 else n2
    return largest


def is_even(n):
    #  Returns True if n is even, otherwise false
    return n % 2 == 0


def power(x, n):
    #  Returns x to the power of n
    x **= n
    return x


def factorial(n):
    #  Returns the factorial of n
    total = 1
    for i in range(1, n+1):
        total *= i
    return total


def main():
    print('22 plus 1:', inc(22))
    # print all those function in the print statement
    print('45 plus 6:', inc_with(45, 6))

    print('655 minus 1:', dec(655))

    print('584 minus 84:', dec_with(584, 84))

    print('Which one is the largest number, 584769 or 26?',
          greatest(584769, 26))

    print('Is 55 even?', is_even(54))

    print('9 to the power of 2:', power(9, 2))

    print('Factorial of 10 is :', factorial(10))


main()
