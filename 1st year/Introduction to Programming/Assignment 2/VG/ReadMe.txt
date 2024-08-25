Here is the ReadMe.txt folder that comes with the VG exercises.

Student: Benoit Dervieux

Files:
*abcd.py
*birthday_candles.py
*countdigits.py
*pi_approx.py
*salary_revision.py
*tic_tac_toe.py

Name of those programs (in order):
-ABCD
-Birthday
-Counting Digits
-Calculating Pi
-Salary
-Shall we play a game


Decription of those programs:

ABCD:-
This program searches for a number of 4 different digits ABCD from 0 to 9999 which has a number 4 times higher in the reverse order (DCBA).
To do so, I made a quadruple nested loop so that the program would compute the conditions expressed in the problem's description.
I also made a function that transform a 4 chars number into a plain number to valid the condition and find the 4 number digit.
Note that I started by a double nested loop (range(10 000) and range(10 000)) that took a minute to compute and figured out the hints
provided helped improve the execution's speed.


Birthday Candles:-
In this exercise, I mainly had 3 values: age, the number of candles and the number of boxes to buy.
From a range of 0 to 100 for the age, I was comparing if i had enough candles compared to the age. 
If not, I would add boxes of candles up until I would have enough. Then adding +24 candles accordingly to the number of boxes.
Then, everytime there would be a positive number of boxes of candles bought, I would print it, the birthday, and substract the 
candles consumed compared to the age. If no box was bought I wouldn't print it.
We then printed the total number of boxes by adding a "total_boxes" variable and the remaining at the end.


Counting Digits:-
In this exercise I ask the user to enter a positive integer. The integer is a string form, I keep one version
in an int form in the variable "the_int_version"
If this integer is negative, I exit the program (comparing with "the_int_version")
Then I declared 3 variables to store the digits of the number: 0,odd anf even.
I then create a loop that will analyze the string input which correspond to the number, digit by digit, and will add 
+1 when those numbers are odd, even or 0. 
To analyse them, I change them into int and then compare them in the condition by using a modulus or an equality.
I then print the 3 variables.


Calculating Pi:-
The concept of calculating Pi is to generate coordinate of points in the square of length and height 2 and see if those 
points are inside the circle of R 1 or not. Then we compare the percentage of points that are inside and when we 
apply this percentage to the total area of the square we would get close to Pi.
Here we generate points randomly in the square with the help of random.uniform(-1.0, 1.0) which returns float values.
We then compute the distance from the center to the point. If the distance is <= 1, this means the points is on or inside
the circle, otherwise if it's greater than one, it means it isn't. We then calculate the frequency/ratio of points inside/
outside. We calculate the ratio of area occupied by the perfect circle inside the square. We compare those two values.
We print out all those values and the whole area of the square times the percentage of frequency/ratio of points.
We then simulate the operation with 100 generated points, 10000 generated points, 1000000 generated points and see
that the more generated points leads to a more precise approximation (which leads to a better approximation of Pi).


Salary:-
In this exercise we ask the user to input the salaries separated by only white space.
The then create a list named salaries with the function split() and the input 's'.
We sort the list from the smallest value to the highest. This could be done with the function sort()
but I was kind of proud of it so I kept it as it doesn't affect the performances here.
Then we calculate the median by taking the two middle values divided by two if the number of salaries entered
is even or the middle value if the number of salaries entered is odd. We print.
Then we calculate the average by taking the sum of all the salaries divided by the number of
salaries entered.
Then we calculate the gap by calculating the difference between the highest and the lowest.
Note that here I could use max() or min() functions but as the list is sorted, I knew those
were the biggest and lowest values of the list.


Shall we play a game:-
In this game we simulate the game TicTacToe.
First I declared the game as a list of signs to analyze it.
Then I declared the players as a class so that it can hold the values name and True/False to know which turn it is.
Then we define the players (def_players). It's a custom function that just ask personnalized name and assign the 'X' and the 'O'.
Then we have a function (clean_the_grid) that is activated when we want to restart the game. This function will 
basically replace every character in the grid by a '-'.
Then we have a function (print_grid) that prints the grid accordingly with the rows and column numbers.
Then we have a function (check_if_win) that checks if there is a combination of 3 same char in one row, 3 same char in one column
of 3 same char in a diagonal. In that case it would mean that one has won.
The next function checks (check_case) if the grid is full. Technically, if the grid is full and no one has won, it
means the game is over.
Then we have the principal function (ask_player) that manages the turn of the players by asking which row and column
one wants to play, calling all of the other functions and changing the turn order. This function also notifies if one case is already
taken.
Then we have a custom function that asks if one wants to continue to play and also if one wants to keep the name or not.