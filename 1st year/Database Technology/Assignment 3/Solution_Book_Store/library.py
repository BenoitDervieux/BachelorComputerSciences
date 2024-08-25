from mysql.connector import connect, Error
from env_variables import user_database, password_database
import re


class Library:

    def __init__(self):
        pass

    def menuRegister(self):
        ''' This method prints the first menu
        '''
        print("************************************************************")
        print("***                                                      ***")
        print("***          Welcome to the Online Book Store            ***")
        print("***                                                      ***")
        print("************************************************************")
        print("1. Member Login")
        print("2. New Member Registration")
        print("q. Quit")
        print()

    def get_answer(self):
        ''' This method asks for an answer
        '''
        answer = input("Type in your option: ")
        return answer

    def menuPrincipal(self):
        ''' This method prints the user registered menu
        '''
        print("************************************************************")
        print("***                                                      ***")
        print("***          Welcome to the Online Book Store            ***")
        print("***                     Member Menu                      ***")
        print("***                                                      ***")
        print("************************************************************")
        print("1. Browse by Subject")
        print("2. Search by Author/Title")
        print("3. Check Out")
        print("4. Logout")
        print()

    def print_book_list(self, list):
        ''' This method prints a lit of books
        '''
        for i in range(len(list)):
            print(i, end=" -- ")
            print(list[i])

    def print_inter_books(self, list):
        ''' This method displays a book according to a selection.
        It allows to print the books and ask the user to enter an
        ISBN to add it to a list.
        '''
        if (list is None):
            return False
        else:
            print(str(len(list)) + " books available on this subject")
            print()
            x = 0
            scroll = True
            while(scroll):
                if (len(list) > 1):
                    x = x % len(list)
                    # Print a list of book
                    for i in range(x, x + 2 if x + 2 < len(list) else x + 1):
                        print("Author: " + list[i][0])
                        print("Title: " + list[i][1])
                        print("ISBN: " + list[i][2])
                        print("Price: " + str(list[i][3]))
                        print("Subject: " + list[i][4])
                        print()
                else:
                    print("Author: " + list[0][0])
                    print("Title: " + list[0][1])
                    print("ISBN: " + list[0][2])
                    print("Price: " + str(list[0][3]))
                    print("Subject: " + list[0][4])
                    print()
                print("1 - Enter ISBN to add to Cart")
                print("2 - Continue to browse")
                print("q - Go back to the menu")
                answer = input("Type in your option ")
                if (answer == '1'):
                    rightISBN = True
                    while(rightISBN):
                        checkisbn = True
                        while(checkisbn):
                            isbn = input("Enter ISBN: ")
                            isbn.rstrip()
                            pattern = re.compile(r'\D+')
                            matches = pattern.findall(isbn)
                            if matches == []:
                                checkisbn = False
                            else:
                                continue
                        try:
                            with connect(
                                host="localhost",
                                user=user_database,
                                password=password_database,
                                database="book_store",
                            ) as connection:
                                # Query to check if a book exists
                                test_isbn = ("select isbn from books "
                                             + f"where isbn=\'{isbn}\'")
                                with connection.cursor() as cursor:
                                    cursor.execute(test_isbn)
                                    test_isbn = cursor.fetchall()
                                    test_isbn = test_isbn[0][0]
                                    if (test_isbn == []):
                                        print("Invalid book's ISBN")
                                    else:
                                        rightISBN = False
                        except Error as e:
                            print(e)
                    return isbn
                elif (answer == '2'):
                    x += 2
                elif (answer.lower() == 'q'):
                    return False
                else:
                    print("I did not understand")

    def author_or_title(self):
        ''' This method asks the user to choose between
        a research by author or by title
        '''
        while(True):
            print("Do you want to search:")
            print("1 - By author")
            print("2 - By Title")
            print("q - Go back")
            answer = input("Your choice: ")
            if (answer == '1'):
                return 'author'
            elif (answer == '2'):
                return 'title'
            elif (answer.lower() == 'q'):
                return None
            else:
                print("I did not understand")

    def display_order(self, list):
        ''' This method display the cart of an user
        using a list.
        '''
        print("current cart contents:")
        print("ISBN" + " " * 11 + "Title" + " " * 100 +
              " " * 4 + "$" + "  Qty  " + "Total")
        print("-"*138)
        total = 0
        for i in list:
            print("{0:15}{1:105}{2:5}{3:5} ${4:5.2f}".
                  format(i[2], i[1], i[3], i[5],
                         int(i[5])*float(i[3])))
            total += int(i[5])*float(i[3])
        print("-"*138)
        print("Total" + " "*126 + "$" + str(round(total, 2)))
        print("-"*138)

    def printInvoice(self):
        pass
