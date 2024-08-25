# --------------------------------------------------------------------------
# Sentence builder
# File:       sentence_builder.py
# Description:
#   Create 10 random sentences
#
# History:     15 Sept 2022
#
# ---------------------------------------------------------------------------

import random
import sys


def pronoun():
    # function pronouns that coontains 5 pronouns
    pronouns_list = ['I', 'You', 'It', 'We', 'They']
    pronoun_for_sentence = pronouns_list[random.randint(0, 4)]
    # choose randomly among those 5 prmouns
    return pronoun_for_sentence


def verb():
    verbs_list = ["will see", "will eat", "will pull",
                  "will touch", "will call"]
    # function verb that contains 5 verbs
    verbs_for_sentence = verbs_list[random.randint(0, 4)]
    # one can choose among those 5 verbs
    return verbs_for_sentence


def noun():
    nouns_list = ["a house.", "a car.", "a computer.", "a tree.", "a bottle."]
    # function noun that contains 5 nouns
    nouns_for_sentence = nouns_list[random.randint(0, 4)]
    # one can choose randomly
    return nouns_for_sentence


def generate_a_sentence():
    generate = input("Would you like to generate 10 sentences?(Y/N) ")
    # ask the user if she/he wants to generate 10 sentences
    print()
    if (generate.lower() == "n"):
        sys.exit(0)
    elif (generate.lower() != "y"):
        print("I didn't understand the answer")
        generate_a_sentence()
    elif (generate.lower() == 'y'):
        for i in range(10):
            print(pronoun(), verb(), noun())
            # if yes, we print it by calling those
            # 3 functions in a print statement


generate_a_sentence()
