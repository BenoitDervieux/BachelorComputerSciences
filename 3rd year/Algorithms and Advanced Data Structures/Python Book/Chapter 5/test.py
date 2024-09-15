# encoder = [None] * 26 # temp array for encryption
# decoder = [None] * 26 # temp array for decryption
# for k in range(26):
#     encoder[k] = chr((k + shift) % 26 + ord('A'))
#     decoder[k] = chr((k - shift) % 26 + ord('A'))
# self._forward = ''.join(encoder) # will store as string
# self._backward = ''.join(decoder) # since fixed

shift = 5
testforward = ''.join([chr((x + shift) % 26 + ord('A')) for x in range(26)])
testbackward = ''.join([chr((x - shift) % 26 + ord('A')) for x in range(26)])

print("Forward", testforward)
print("Backward", testbackward)
