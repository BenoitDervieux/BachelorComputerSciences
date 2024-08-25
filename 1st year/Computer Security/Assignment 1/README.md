# Decription of the files

## 1) Task3_ass1_CS.py

This file allows you to encrypt and decrypt text of your choice using the key of your choice.
To do so, you need to run the program. It will ask you for a .txt file and a key to encode.
You will have 2 choices of encoding. Either by substitution or by transposition.
For the substitution method, the sum of the ascii letters % 256 will determine the size of the encryption.
For the transposition method, each line will be 'shuffled' and swaped according to the length of the key provided.
The file allows you to decrypt and untranspose at the same time.
The files 'matrix.txt', 'encoded_sub_matrix.txt' and 'decoded_sub_encoded_sub_matrix.txt' are an example of substitution using the key 'matrix'.
The files 'matrix.txt', 'encoded_tran_matrix.txt' and 'decoded_tran_encoded_tran_matrix.txt' are an example of transposition using the key 'matrix'.

## 2) Decoding files

Decode_text_ks222ur.py and decode_text_nc222hk.py are two small programs used to decoded two encoded texts. (ks223ur.txt and nc222hk.txt)


## 3) Task6_hash_function_CS.py

This file is the file relative to the task 6. 
It tests the implementation of a hash function and assess its uniformity.
While running the code, you have two options. The first one is to test the uniformity on 10 000 different words.
The second one is to test the uniformity and the avalanche concept on 1000 different strings that differs on only 1 bit each.
The first test output a graph representing the collision and how spread they are among the number of different buckets (256).
It also calculates the chi-squared value of those inputs.
The second shows the hash value from the string that differs only by one bit. The x axis represents the number of the x-th string.
The y value represent the hash value it gets.

## 4) Other files

- my_text.txt : Submitted text for the assignment
- bit_string.py : Programme allowing to have a bit representation of a string
- subtle_string_producer.py : Programme allowing to produce 1000 different strings with only one bit of difference. Produce the bit representation as well.
- subtle_string.txt : PRogramme containing 1000 different strings being one bit different
