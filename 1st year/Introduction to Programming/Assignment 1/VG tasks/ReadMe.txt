Benoit Dervieux

Student in the Software Technology program.

VG's submission of the following exercises :
-Sum of three
-Change
-Taxes
-Quadric Equation

Before analyzing the code: I implemented all my code with extra functions in order to have better feedback than if I would have done the strict minimum. 

Explanations:

Sum of three :- 
The first thing which is implemented into this function is the capacity to handle the bad inputs. Hence, I use try/except in case the user wouldn't enter a number. 
Then, I handle the negative numbers by notifying it to the user. Then, it analyzes the lenght of the input (if it is a x-digit number) by converting it into a string and use len().
That way, the programme knows if the user has entered a 3-digit number or not. If not, I would notice her/him and restart the program. It also handles the case of an user entering 000 by noticing
him/her that it's not even a number even if it contains 3 digits. It also handles the case of a number being 00X or 0XX.
If the user has entered a 3-digit number which is superior to 000, then I had in a variable named total applies the modulus of 10 to the number I then divide the number by 10. 
I do that only 3 times (a loop in a range of 3) in that case as we only focus on 3-digit numbers.
I then print the total and call a function name "should continue" that ask the user if he/she wants to continue to use the program.
The whole program is under a function is order to redirect the user to the program in case this one doesn't enter right values.
There is also a small function that asks the user if she/he wants to continue to use the program or exit it.


Change :-
The program asks to input an amount of money one needs to pay. It uses a try/except technique to avoid the case of a person entering a string instead of a number.
The program converts the input into a float to round the exact sum the program should give back.
First of all, we handle the cases when the user input a negative numbers or an amount to pay which is superior than the amount of money one has.
Once everything is right, we substract the amount paid minus the price and round the change. 
Then we print the change.
I created a list of lists in order to have all the swedish bills and coins. As the number of bills and coins was finished, it was an easy way to iterate through them later. This might not
be the most optimal solution but that was the only one I came up with.
I then iterated through the list of bills and coins by dividing the number of time each bills would be given depending on how much change we had left. I then substracted the number of bills to give back to the change
and I continued until the end of the list. Every step was printed.
To finish, I made it into a function so that when a bad input would be given, the program could restart by itself and continue until all the input would fit.
There is also a small function that asks the user if he/she wishes to continue to use the program.


Taxes:-
The program asks the monthly income to the user. It handles the problem of inputing a string of a negative value.
The programs asks an income that it transfers into a float. 
As the amount of taxation and income are fixed, I entered them into variables.
To describe the program we can use 3 paths:
 - Someone has an income under 38 000, then this person will be charge at 30%. It adds (+=) the amount to a variable tax and print the tax variables.
 - Someone has an income greater than 38 000 and lower than 50 000. Then it adds (+=) to a variable tax 38 000 at 30% and the income minus 38 000 at 35%. It adds (+=) both results to the variable
tax and prints the tax.
 - Someone has an income greater than 50 000. Then it adds (+=) 30% of 38 000, 35% of 12 000 (between 38 000 and 50 000) and 40% of income minus 50 0000 to the variable tax. It then prints tax.
The program is inside a function so that it returns to the program when the user inputs wrongly. There is also a small function that asks the user if he/she wishes to continue to use
the program.


Quadric Equation:-
The program asks for 3 different inputs A,B,C. It handles if the user inputs string instead of integers or floats.
First, we handle if a = 0 or if a and b = 0. If a and b are 0, we consider it's not an equation of this type. If a = 0 we calculate -c/b as instructed in the description.
If delta is superior to 0, then it means there are 2 solutions, we calculate them and output them. If delta is = 0, then there is only one solution. We calculate it and print it.
If delta is inferior to 0, then it means there is no solution. We print it.
The program is inside a function to start over when the user enter a bad input. Then we display a function "should continue" so that the user can decide to shut down
the program or not.