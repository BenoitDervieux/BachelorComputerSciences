## Mini-Project Report

Program: Software Technology (Swedish Speaking) & Software Technology (English Speaking)
Course: 1DV501
Date of submission: 2022-11-06

## Introduction

The mini-project is the last assignment of course 1DV501. We needed to implement two different and slightly advanced data structures: a Hash Table (renamed HT) and a Binary Search Tree (renamed BST). To do so, we will use the techniques we saw during the courses and the documents produced during assignment 3.

These documents are: 
The count of different words in the script of the movie “Life of Brian”
The count of different words in the script named “Swedish News”

The first part is the treatment of those two documents using data structures we already used during the previous assignments. Those data structures are Set and Dictionaries (renamed Dict).

The second part in this assignment consists of implementing the two data structures in a premade code skeleton written by the teaching team of this course. The goal of those documents was to help us understand how to implement the solution. By comparing the teacher's results to ours, we could compare how well our programs function.

The third part consists in implementing both methods to the previously cited documents. The goal was to implement the two newly seen data structures in our code to make an analysis of them and hence store their data. The BST and the HT have the same behavior as the Dict and the Set. We compared results to assess the quality of our implementation.

Visual examples of the data structures:

<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Binary_tree_v2.svg/1200px-Binary_tree_v2.svg.png" height= "300" width="300"/> <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/Hash_table_3_1_1_0_1_0_0_SP.svg/1200px-Hash_table_3_1_1_0_1_0_0_SP.svg.png" height= "250" width="300"/>

## Part 1 - Counting unique words

In this part, the goal of the program was to count the unique words in each of the files using two different techniques seen in the lectures. The first was the Set class, and the second was the Dictionary. As well as proceeding with the data using a dictionary, we had to print the ten most frequently used words. 

``1.1 - The Set``
Recalling Lecture 8: Data Structures - Tuples, Set and Dictionaries by Jonas Lundberg, a set is a non-sequential data structure with no duplicate elements. 

In other words the set will sort the terms automatically when we add them. Hence, we will have a precise number of the unique words in our texts. 

To do so in our program, we needed to:
1) Open our document
2) Initialize the set to add words to it
3) Add all the words up until the end of the document
4) Close the document

```python
file_set = open(input_file, 'r', encoding='utf-8')
set = set()
dict_for_word = {}
for line in file_set:
    line = line.rstrip('\n')
    set.add(line)
    if len(line) <= 4:
        continue
    else:
        if line in dict_for_word:
            dict_for_word[line] += 1
        else:
            dict_for_word[line] = 1
file_set.close()
```

In this set, we then had a list of unique words. To discover their number, a simple print(len(set)) allowed us to know how many words were present inside this set.

```python
print(f'\nThere are {len(set)} unique words in this file')
print(f'\nThere are {len(dict_for_word)} words ' +
      'that are longer than 4 letters in this text')
```

Hence, the size of the documents using the set are:
Life of Brian: 2 068 words
Swedish News: 409 951 words

``1.2 The Dictionary``

According to the same Lecture 8 document, a Dictionary is a set of key-value pairs. Every entry is associated with a value. It becomes handy when one wants to count the frequency or apparition of an element in a text. 

To implement a dictionary, one needs to assess the value 1 to every key (every word in this case). A word is created with the value one if it is not present in the dictionary. If the word is present, the value '1' will be added to the existing value. That is the way to acknowledge the frequency of the apparition of a term. 

Here is the piece of code for implementing the Dictionary. We first set up an empty Dictionary. Then we crop all the ‘\n’ from each word and added only the words which were longer than four letters.

```python
file_set = open(input_file, 'r', encoding='utf-8')
set = set()
dict_for_word = {}
for line in file_set:
    line = line.rstrip('\n')
    set.add(line)
    if len(line) <= 4:
        continue
    else:
        if line in dict_for_word:
            dict_for_word[line] += 1
        else:
            dict_for_word[line] = 1
file_set.close()
```

``1.3 Top 10 implementation``
For the top 10 implementation we had a dictionary which we converted into a list. We sorted the list and by using the anonymous function lambda,  reversing the list with ‘-x’ and printing the top 10 values.

```python
items = list(dict_for_word.items())
# Transform the dictionnary in a list to be sorted
dict_for_word = sorted(dict_for_word.items(), key=lambda x: -x[1])[:10]
```

``1.4 Results``
For the file ‘brian_12903_words.txt’ containing 12 903 words, we obtain:

2 068 different words

1 458 words which are longer than four letters


| Nr| Word			| Amount |
|:--|:-------------:| ------:|
| 1 | brian			|     368|
| 2 | crowd       	|    161 |
| 3 | centurion	    |    121 |
| 4 | mother 		|    104 |
| 5 | right         |     99 |
| 6 | crucifixion	|     78 |
| 7 | pilate		|     68 |
| 8 | pontius 		|     64 |
| 9 | don't 		|     59 |
| 10 | rogers       |     52 |

For the file ‘swe_news_15073169_words.txt’ containing 15 073 169 words, we obtain:

409 951 different words

395 694 words which are longer than 4 letters

| Nr| Word			| Amount |
|:--|:-------------:| ------:|
| 1 | under			|     368|
| 2 | säger       	|    161 |
| 3 | efter	 	    |    121 |
| 4 | kommer 		|    104 |
| 5 | eller		    |     99 |
| 6 | också			|     78 |
| 7 | sedan			|     68 |
| 8 | andra 		|     64 |
| 9 | finns 		|     59 |
| 10 | många        |     52 |

## Part 2 - Implementing Data Structures

``2.1 - Presentation of the given requirements``
In this second part, we needed to implement two distinctive data structures: A hash table and a binary search tree.

Both code structures (or data structure’s structure) were given, and we needed to implement the methods inside them. It consisted in adding, finding, counting elements, or returning them as a string.

To help us, we had 2 main programs that imported their relative data structures. They output data that shows us how our programs should respond.

``2.2 - The hash table``
Implementation of the function add:
The ‘add’ function had three responsibilities. The first one was to verify how many entries we had in the table. In case we would have more entries than buckets, we would rehash the table to expand its size. The second function was to generate a hash number to know which bucket the word belongs to. The third was to append the word to the corresponding bucket.

```python
def add(self, word):
        if self.size >= self.bucket_list_size():
            self.rehash()
        hash_number = self.get_hash(word)
        if bool(self.buckets[hash_number]) is False:
 
            self.buckets[hash_number].append(word)
            self.size += 1
        elif bool(self.buckets[hash_number]) is True:
            if word in self.buckets[hash_number]:
                pass
            else:
                self.buckets[hash_number].append(word)
                self.size += 1
```

How to compute the hash value:
To compute our hash values, we define a constant of 17. We begins with a hash number of 0, and for every letter of the word, we would add the ASCII number of the letter and multiply the previous hash_number by the constant. That way, we could easily avoid collisions between two diverse words with the same letters such as ‘stop’ and ‘post’. Also, we could have a greater value than just by adding the ASCII number of each letter of a word. Hence we would avoid collisions when the table exceeds a certain size. 

Once we generate this hash number, we would apply a modulo equivalent to the size of the hash table to place our word into the hash table. Otherwise, the value would be too excessive.

```python
  def get_hash(self, word):
        # source: https://www.youtube.com/watch?v=jtMwp0FqEcg&t=88s
        g = 17
        hash_number = 0
        for i in range(len(word)):
            hash_number += g * hash_number + (ord(word[i]))
        hash_number = hash_number % self.bucket_list_size()
        return hash_number
```

How we implemented the rehash function:
The rehash function happens when the number of entries equals the number of buckets and when we add a new key to the program. 

We initialize a buffer. It is a list that will store all of our current values temporarily. Once done, we will clear up all the buckets one by one and double the size of the bucket list. 

```python
   def rehash(self):
        buffer = []
        for i in range(len(self.buckets)):
            if bool(self.buckets[i]) is True:
                for j in range(len(self.buckets[i])):
                    if j != 0:
                        j = j - j
                    buffer.append(self.buckets[i][j])
                    self.buckets[i].remove(self.buckets[i][j])
                    self.size -= 1
        self.buckets = [[] for i in range(2*len(self.buckets))]
        for i in range(len(buffer)):
            self.add(buffer[i])

    def add(self, word):
        if self.size >= self.bucket_list_size():
```

Then we will re-add all of our values from the buffer/list to the new-sized hash table.

(We note that the new entries will not be at the same place because the value of the modulo has doubled.)

Difference between the hash_output and our results
We note two considerable differences between our results and the results from the hash_output.txt. The max buckets and the zero bucket ratio. From the hash_output.txt, they are respectively 2 and 0.38. From our implementation, they are 3 and 0.50.

It means we have more empty buckets and more collisions.

We choose our variable in the hash function to be fast for dealing with the document: 'Swedish News'. It is the reason that explains these differences. Hence, after several tests, the number 17 gave us better results than any other number. But the Swedish news document has many more entries than the hash_main, hence the differences in the results.


``2.2 - The Binary Search Tree``

``Implementation of the function 'put':``
The put function is responsible for adding an input, in our case key and value, into the BST. The first entry into the BST would be equal to the root. From this root we start building the so-called tree.
We used string comparison to sort whether the input should go into left or right subtree, by comparing the ASCII value of the words and seeing if it is greater or less than the given root. Depending on if it is less or greater than it will go into the left or right subtree.

```python
def put(self, key, value):
        # if new key bigger than key and if no left child
        if self.key > key:
            if self.left is None:   # add a new Node
                self.left = Node(key, value)
            elif self.left is not None:     # if not empty
                # recursive call, go to left side of tree
                self.left.put(key, value)
        elif self.key < key:
            if self.right is None:
                self.right = Node(key, value)
            elif self.right is not None:
                self.right.put(key, value)
        elif self.key == key:               # if new key = key, change value
            self.value = value
            return
```

Upon entering the subtree it checks if there already is a node there, if there is no node it will then add the input as a node with the given key and value. If there already is a node it will go into that nodes left or right subtree by doing the string comparison again by a recursive call of the function with the given input. This process is repeated for every input and subtree.

By the end of the function we also compare whether the given input key is equal to a node key in the BST. If that is the case, it would only replace the value of the node.

``Implementation of the function 'max_depth':``
For the ‘max_depth’ function we first put two variables,  ‘maxleft’ and ‘maxright as equal to one to be used as a default parameter. Setting the default parameter as one would add one to the given variable every time you pass a node. This is done by recursion where we go into the most left and respectively most right node of the subtrees by adding the node to the variables with a recursive call via the function return. If there is no node to the left it will check the right side. If either side has a node it will add one to the variable sums.
By the end we compare the two variables and return the greater of the two as that would be our max depth of the BST.

```python
def max_depth(self):    # iterate through tree
        # as a default parameter,
        maxleft = 1    # if no value as parameter, uses default
        maxright = 1    # aka:for every Node adds +1
        if self.left is not None:
            maxleft += self.left.max_depth()
        if self.right is not None:
            maxright += self.right.max_depth()
        if maxleft > maxright:
            return maxleft
        else:
            return maxright
```


## Part 3 - Count unique words
Similarly as in part 1, but here we used the function method to return the given words as a list. The function appends through a left to right traversal which already adds them in a sorted order. Then we reverse the list and print the top 10 values.

```python
lst = map.as_list()
# Sort the element from the BST to list them
lst = sorted(lst, key=lambda x: x[1], reverse=True)[:10]
```

Here are the results for the hash table and BST for each of the two files:
``Life of Brian:``
Number of words in the document: 2 068
Maximum number of elements in one bucket: 4
Number of buckets in the list: 4 096
Zero bucket ratio: 0.6
Number of Nodes in the tree: 1 458
Max depth of the tree: 24
Number of leaves: 476
Time of execution: 0.070 seconds


``Swedish News:``
Number of words in the document: 409 951
Maximum elements in one bucket: 19
Number buckets in the list: 524 288
Zero ratio bucket: 0.49
Number of Nodes in the tree: 395 694
Max depth of the tree: 112
Number of leaves: 130 721
Time of execution: 83.502 seconds (can vary)

``How to evaluate the quality of a hash function:``
In essence, a perfect hash function would have the size of the number of entries, and every bucket should have only one key. However, in practice, the more we implement data, the harder it tends to be. Hence, we have a big chance to obtain collisions as it is easy to assign a immense hash number to any entry but hard to predict which bucket they will be directed to after using the modulo. 
Hence, a large max bucket size would mean that most entries are directed to the same bucket. It also means that the speed of execution goes from O(1) to O(n). However, if the 'max bucket''s size is relatively small compared to the number of words, the implementation can be considered successful.

In our case, and after several tests, we considered the implementation reasonable in terms of max buckets. It is not 1, but it is not too high. We tried other methods, such as linear probing to have optimal results.
An intriguing ratio that would be great to analyze is the number of buckets having more than one entry.

Another criterion we could watch is the empty bucket ratio which calculates the number of empty buckets compared to the total number of buckets.
First, I would like to say that this ratio is a bit misleading. It should be compared to a theoretical maximum size of buckets. For instance, if there are 10 buckets and 6 entries, this ratio couldn't be lower than 40%, which seems high but would be maximal. 

The empty bucket ratio calculates the number of empty buckets compared to the total number of buckets. Here, the result shows that 49% of buckets are empty. As we have 409 951 entries for 524 288, by proportion, the lowest number would have been 22%. We then have a difference of 27%. It is high but reasonable in this case. When we know that we had an implementation with open addressing that required us to double the actual size of the hash table to have a max size of 1 but a hash table of 1 048 576 buckets and a slower execution time.
Hence a poor implementation would mean that we have too many buckets for too few entries. The ratio would then be very high. It would mean that our rehashing function is erroneosly implemented or that our hash function produces hash values that are similar (or following a cycle). 

``How to evaluate the quality of a BST:``
Max depth can be seen as how well balanced or spread out your values are. For example, if you have seven inputs and the max depth of the BST becomes seven, that would tell you that every value is added to one side only in a straight line which is not efficient for a BST.
For you to reach the max depth you would have to go through six nodes, but with an even distribution you can cut it to down to two nodes. The most optimal values for max depth would be around half of the nodes in the BST.

Similarly goes for the leaf count. A balanced BST can be seen as an exponential function since every subtree can only consist of two children. This tells us that an evenly spread BST will grow at an exponential rate (x^n).
If the max depth is equal to three, disregarding the root of a BST, every other row should grow at a rate of (2^2+n). So in a perfect BST the leaf count should not be any larger than the previous nodes times two. Whereas the optimal value for a leaf count would be two times the previous row of nodes or less.

<img src="https://www.codesdope.com/staticroot/images/ds/rb1.png" height= "200" width="300"/>

``Project conclusions and lessons learned``

``Major technical challenges:``
Hashing the correct way: 
We tried a lot of different hash functions to have the best repartition possible. We try a python implementation of the MurMur and the Fowler-Noll-Vo hash functions. Without going too deep into how they worked, we found that they did not provide better results than the one we had. We also tried to use linear probing and double hashing for having a max bucket size of one. Nothing was as efficient as the first implemented hash function. Even when we rehashed more frequently to obtain better time execution like in this graph: https://en.wikipedia.org/wiki/Hash_table#/media/File:Hash_table_average_insertion_time.png.

BST: 
We started by comparing the values in the put function which then later changed to comparing the first letter of the given word which at the end led us to the simple string comparison.
Learnings: We have learned that hashing did not need to be over complicated. Sometimes, small implementations can be more effective than big ones.

Improvements:
We think that if we were to have more time, we would implement the black hat / red hat technique for the Binary Search Tree to gain efficiency. The second improvement would be the hash table to obtain a lower ratio than we already have. We used different techniques but could not go lower. A conversation with a more experienced programmer would help us to obtain better results.

``Project issues``
To learn effectively and as the project was not extensively complicated. We both decided to learn to do part 1 on our side and discuss the results. During the second part, André took the Binary Search Tree while Benoit took the Hash Table. We then implemented our respective section for the 3rd part while sharing what we found. We also let ourselves take the freedom to implement the other part for learning purposes. 
We communicated frequently when we implemented any of our parts. We also met several times physically to discuss the project. 

``André:``
Responsible part 1, and the BST for part 2 and 3.
Similarly i would say around 10-15h a week, while some parts took way longer than others.
Working with these structures were difficult at the start since I couldn't really wrap my head around how some parts really worked and how they should work. But when i got into it, it made me look at some parts differently than before and how to make use of what we learned from our previous assignments.
For a similar project i would improve small details more, the use of pen and paper would increase, and same goes for the built in debugging tool since my use of it increased here also.

``Benoit:``
Responsible for implementing part 1, the hash table for part 2, and the implementation of the hash table for part 3.
I worked around 15 hours implementing the whole project (all the parts for learning purposes), but I spent a day, approximatively 8 hours trying to improve the hash table function.
Lessons learned: Data structures are interesting to implement as they challenge one's capacity to think abstractly. I learned that it takes time to implement a perfect solution and, somehow, one should compromise between the quality of the solution provided and the time it can take to improve it. 
Facing a similar project, I would improve the overall code more than its performance because our main feedback was about redundancy in our code.
