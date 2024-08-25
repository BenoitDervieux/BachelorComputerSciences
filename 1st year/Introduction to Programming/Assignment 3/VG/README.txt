Here is the ReadMe.txt folder that comes with the VG exercises for the 3rd assignment in the course 1DV501

Student: Benoit Dervieux

Files:
count_lines.py
morse.py
pretty_recursive_print.py

Explanation of the solutions:

Count_lines.py
In this exercise I basically scanned all the files and directories in a specified directory with the help of the other 
programs we needed to do. When one file terminated by '.py', I considered it was one of my production and 
I analyzed it. Inside the file, I counted the line by taking line by line and excluding those which were empty.
I return the overall number of lines.

Morse.py
I first took the letter-morse dictionary provided to make it's inverse (morse-letter).
I then analyze letter by letter the input provided. Every entry correspond to a morse letter that 
I add into a new string. I returned this string with morse letters instead. Separated by two space to differentiate between words.
The same for the invert at the difference that the string returned was plain letters with spaces 
in between only.

Pretty_recursive_print.py
I reused the recursive program with the difference that for any directory I analyzed the path in it.
Every '\' would be counted and would represent two spaces on the terminal. This help having 
a tree-like structure.