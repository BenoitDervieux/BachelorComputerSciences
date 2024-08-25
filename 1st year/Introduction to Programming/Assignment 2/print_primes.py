# --------------------------------------------------------------------------
# Print primes
# File:       print_primes.py
# Description:
#   Display a number of prime numbers asked by the user
#
# History:     15 Sept 2022
#
# ---------------------------------------------------------------------------


list_of_primes = []
# list where we will stock our prime numbers

prime = 1
# the prime we are testing, it increases with time
prime_buffer = 0
# a variable that will help us to recognize of
# not if a number is a prime

number_of_prime = int(input('How many primes? '))
# We ask the user to enter a number of prime to display

print_position = 0
# This variables helps us to print 10 by 10


while len(list_of_primes) < number_of_prime:
    # Calculate the length of the list of prime and compare
    # if it is as big as the input entered by the user
    prime_buffer = 0
    # Help to compare if the number has been divided or
    # not, starts at 0 for every new prime
    prime += 1
    # Increase the number to test and to find new prime numbers
    if (prime == 2):
        # exception for the number 2
        list_of_primes.append(prime)
        # which is added directly as it was easier
    for n in range(2, prime, 1):
        # We check here if the number can be divisible by any other number
        if (prime/n) - int(prime/n) == 0.0:
            # We do that by checking if the result of
            # itself by the troncature is equal to 0.0
            break
        # if so, we break the loop and move to the next number
        else:
            prime_buffer += 1
            # otherwise we had one in our "buffer"
    if (prime_buffer + 1 == prime - 1 and prime != 2):
        # if no number can divide our number, it means it's prime,
        # it means it has enough "buffer" to be considered as prime
        list_of_primes.append(prime)
        # we compare this and if it's the case we add the number into our list

for i in range(0, len(list_of_primes)):
    print('{0:4}'.format(list_of_primes[i]), end=' ')
    # we print the list in a formated way where we have
    # 10 prime numbers per line
    print_position += 1
    if (print_position % 10) == 0:
        print()
