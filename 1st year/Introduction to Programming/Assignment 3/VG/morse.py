# --------------------------------------------------------------------------
# Morse Code
# File:       morse.py
# Description:
#   This program is able to translate to and from Morse code
#
# History:     9 Oct 2022
#
# ---------------------------------------------------------------------------


from_letter_to_morse = {
    'a': '.-',     'b': '-...',     'c': '-.-.',     'd': '-..',
    'e': '.',     'f': '..-.',     'g': '--.',     'h': '....',
    'i': '..',     'j': '.---',     'k': '-.-',     'l': '.-..',
    'm': '--',     'n': '-.',     'o': '---',     'p': '.--.',
    'q': '--.-',     'r': '.-.',     's': '...',     't': '-',
    'u': '..-',     'v': '...-',     'w': '.--',     'x': '-..-',
    'y': '-.--',     'z': '--..',    'å': '.--.-',   'ä': '.-.-',
    'ö': '---.'
}
# Dictionary from letter to morse

from_morse_to_letter = {}
# Initialize dictionary from morse to letter

for i in from_letter_to_morse:
    from_morse_to_letter.update({from_letter_to_morse[i]: i})
    # Fill the new dictionary by inverting the keys of
    # The first one

text_to_transcribe = input('Write a message: ')
# Ask the user to enter an input
text_to_transcribe = text_to_transcribe.lower()
# Transform the text to lowercase
new_text = ''
# Initialize string to store value
for i in range(len(text_to_transcribe)):
    # Iterate inside the string
    if 97 <= ord(text_to_transcribe[i]) <= 122:
        # If the letter is between a and z, we add it to the new string
        new_text += from_letter_to_morse[text_to_transcribe[i]] + ' '
    elif text_to_transcribe[i] in 'äåö':
        # If the text is a special swedish character, we add it
        new_text += from_letter_to_morse[text_to_transcribe[i]] + ' '
    elif ord(text_to_transcribe[i]) == 32:
        # If there is a space, we add double space in the morse code
        new_text += '  '

print('Message in Morse code:')
print(new_text)
# Print the new message

morse_to_transcribe = input('Write in Morse code: ')
# Ask a morse code to the user
lst = []
# Initialize a new list
new_text_morse = ''
# Initialize a new string to store it
lst += morse_to_transcribe.split(' ')
# Add all the letter into a list
for i in range(len(lst)):
    # Iterate into the list
    if lst[i] != '':
        # If the character is not empty, we add to the new
        # string by finding the key in the dictionary
        new_text_morse += from_morse_to_letter[lst[i]] + ' '

print('Message in plain language:')
print(new_text_morse)
# Print the new normal text
