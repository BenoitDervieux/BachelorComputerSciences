class CaesarCipherCyr():
    "”””Class for doing encryption and decryption using a Caesar cipher.”””"
    def __init__(self, shift):
        "”””Construct Caesar cipher using given integer shift for rotation.”””"
        encoder = [None] * 32 # temp array for encryption
        decoder = [None] * 32 # temp array for decryption
        for k in range(32):
            encoder[k] = chr((k + shift) % 32 + 1040)
            decoder[k] = chr((k - shift) % 32 + 1040)
        self._forward = ''.join(encoder) # will store as string
        self._backward = ''.join(decoder) # since fixed

    def encrypt(self, message):
        "”””Return string representing encripted message.”””"
        return self._transform(message, self._forward)

    def decrypt(self, secret):
        "”””Return decrypted message given encrypted secret.”””"
        return self._transform(secret, self._backward)

    def _transform(self, original, code):
        "”””Utility to perform transformation based on given code string.”””"
        msg = list(original)
        for k in range(len(msg)):
            if msg[k].isupper():
                if ord(msg[k]) >= 1040:
                    j = ord(msg[k]) - 1040 # index from 0 to 25
                else:
                    print("message de k", msg[k])
                    j = ord(msg[k]) - ord('A') # index from 0 to 25
                msg[k] = code[j] # replace this character
        return ''.join(msg)

if __name__ == '__main__ ':
    cipher = CaesarCipherCyr(3)
    message = "THE EAGLE IS IN PLAY; MEET AT JOE S."
    coded = cipher.encrypt(message)
    print('Secret:', coded)
    answer = cipher.decrypt(coded)
    print('Message:', answer)

# To adapt to other alphabet, we would need to start by something else than ord('A')
# but rather at the beginning of the other alphabet we are talking about
# We also need to change the number of letter as the cyrillic alphabet has 32 letters 

cipher = CaesarCipherCyr(3)
message = "ТРЛ РУКАМИ; ВСТРЕТИМСЯ В ДЖО С."
coded = cipher.encrypt(message)
print('Secret:', coded)
answer = cipher.decrypt(coded)
print('Message:', answer)
