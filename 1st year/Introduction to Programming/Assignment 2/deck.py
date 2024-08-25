# --------------------------------------------------------------------------
# Deck of Cards
# File:       deck.py
# Description:
#   Create a deck of cards and then shuffle it and print the 5 first value
#
# History:     15 Sept 2022
#
# ---------------------------------------------------------------------------

import random as r

type_of_card = ['Hearts', 'Spades', 'Clubs', 'Diamonds']
# List of types of cards
number_of_card = [2, 3, 4, 5, 6, 7, 8, 9, 10, 'Jack', 'Queen', 'King', 'Ace']
# list of number of cards

deck = []
# creation of the deck
card_type = ''
card_type = ''


def shuffle(list, number):
    # shuffle function that takes a card in one random place
    # and exchange with another card
    buffer1 = r.randint(0, number - 1)
    # from another random place. We iterate a random number
    # between 0 and number of card minus 1
    buffer2 = r.randint(0, number - 1)
    list[buffer2], list[buffer1] = list[buffer1], list[buffer2]
    return list


print('My hand:')
for i in range(0, len(number_of_card)):
    for j in range(0, len(type_of_card)):
        card = str(number_of_card[i]) + ' of ' + str(type_of_card[j])
        # Here we create the association of strings
        # that will corresponds to the cards
        deck.append(card)
        # we do a nested loop 1st the type then
        # the number and we add it to the list
for k in range(0, 2000):
    shuffle(deck, len(deck))
    # we shuffle 2000 times

for cards in range(0, 5):
    # we print the 5 first values/5 top cards
    print(deck[cards])
