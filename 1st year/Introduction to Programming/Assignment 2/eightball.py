# --------------------------------------------------------------------------
# Magic 8-ball
# File:       eightball.py
# Description:
#   Answer randomly to question
#
# History:     15 Sept 2022
#
# ---------------------------------------------------------------------------

import random
import sys


def ask_the_magic():
    question = input("Ask the magic 8-ball your question: ")
    # we ask the user to input a question

    answers = ['I don\'t want to answer you',
               'I honestly don\'t know', 'Great, what else?',
               'Is this what I think it is?', 'Hummmmm, maybe',
               'Seriously? I don\'t think so',
               'Yeah but have you seen my friend?',
               'He said he can\'t come tonight',
               'I honestly just want to eat a fish and chips',
               'Oh my god are you serious?!']
    # We have some stored answer in a list

    the_answer = answers[random.randint(0, len(answers)-1)]
    # we take any answer from this list by randmly generating it

    if question == 'stop':
        # if the input is "stop", we stop the programme
        sys.exit(0)
    else:
        print('The magic 8-ball says: ', the_answer)
        # else we answer randomly to the question
        ask_the_magic()


ask_the_magic()
