from mysql.connector import connect, Error
from library import Library
from env_variables import user_database, password_database

lib = Library()


class Books:

    def __init__(self):
        pass

    def browse_subjects(self):
        ''' This method browse the list of different
        subjects in the library and ske the user
        to choose amount the available subjects.
        Loop until the answer correspond to the normal range.
        '''
        list_of_subject = []
        try:
            # Connection to the database
            with connect(
                host="localhost",
                user=user_database,
                password=password_database,
                database="book_store",
            ) as connection:
                # Query to select books' subjects
                fetch_subject = "select distinct(books.subject) from books;"
                with connection.cursor() as cursor:
                    cursor.execute(fetch_subject)
                    result = cursor.fetchall()
                    for subject in result:
                        list_of_subject.append(subject[0])
                    list_of_subject.sort()
                    choice = True
                    while(choice):
                        rangeAnswer = ''
                        for i in range(len(list_of_subject)):
                            print(i, end=' - ')
                            print(list_of_subject[i])
                            rangeAnswer += str(i)
                        print("q to exit")
                        answer = input("What is your choice: ")
                        print("Rangeanswer" + rangeAnswer)
                        if answer in rangeAnswer:
                            return list_of_subject[int(answer)]
                        elif (answer.lower() == 'q'):
                            return None
                        else:
                            print("Please enter a valid subject number, "
                                  + "it should be less than ",
                                  len(list_of_subject) - 1)
        except Error as e:
            print(e)

    def check_books_by_subject(self, index):
        ''' This method return a list of books
        from a same subject. It implements the previous
        method of browse_subject by getting all the book
        from the previously displayed genre
        '''
        list_of_book = []
        try:
            with connect(
                host="localhost",
                user=user_database,
                password=password_database,
                database="book_store",
            ) as connection:
                # Quert to select a book of a certain subject
                fetch_subject = ("select author, title, isbn, price, "
                                 + "subject from books "
                                 + f"where subject=\'{index}\';")
                with connection.cursor() as cursor:
                    cursor.execute(fetch_subject)
                    result = cursor.fetchall()
                    for i in result:
                        list_of_book.append(i)
                    return list_of_book
        except Error as e:
            print(e)

    def check_books_by_author(self):
        ''' This method return a list of books
        having the same author. If no author is found
        it returns None
        '''
        author = input("Which author are you looking for? ")
        list_of_book = []
        try:
            with connect(
                host="localhost",
                user=user_database,
                password=password_database,
                database="book_store",
            ) as connection:
                # Query to select a book from a certain author
                fetch_author = ("select author, title, isbn, price, "
                                + "subject from books where "
                                + f"author like \'%{author}%\';")
                with connection.cursor() as cursor:
                    cursor.execute(fetch_author)
                    fetch_author = cursor.fetchall()
                    if (fetch_author == []):
                        print("0 book found")
                        return None
                    else:
                        for i in fetch_author:
                            list_of_book.append(i)
                        return list_of_book
        except Error as e:
            print(e)

    def check_books_by_title(self):
        ''' This method return a list of books
        from an entered term. The term needs to be included
        in the title of the book. If no book is found
        it returns None
        '''
        title = input("Enter title or part of the title: ")
        list_of_book = []
        try:
            with connect(
                host="localhost",
                user=user_database,
                password=password_database,
                database="book_store",
            ) as connection:
                # Query to select a book containing a title
                fetch_subject = ("select author, title, isbn, price, subject "
                                 + "from books where title "
                                 + f"like \'%{title}%\';")
                with connection.cursor() as cursor:
                    cursor.execute(fetch_subject)
                    result = cursor.fetchall()
                    if (result == []):
                        print("0 book found")
                        return None
                    else:
                        for i in result:
                            list_of_book.append(i)
                        return list_of_book
        except Error as e:
            print(e)

    def setBook(self, author, title, isbn, price, subject, quantity):
        ''' This method sets a book to add in the cart
        '''
        self.author = author
        self.title = title
        self.isbn = isbn
        self.price = price
        self.subject = subject
        self.quantity = quantity

    def getBookFromBooks(self):
        ''' This method returns a book amd its attributes.
        '''
        return (self.author, self.title,
                self.isbn, self.price,
                self.subject, self.quantity)
