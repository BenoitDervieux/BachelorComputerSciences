from getpass import getpass
from mysql.connector import connect, Error
from cart import Cart
from env_variables import user_database, password_database
import re
import bcrypt


class User:

    def __init__(self):
        self._obj_cart = Cart()
        self.connected = False

    def validate_email(self, email):
        ''' This method validates an email
        source: https://www.youtube.com/shorts/Ihd7ZTHRVsY
        '''
        patt = r'[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}'
        pattern = re.compile(patt)
        return re.match(pattern, email) is not None

    def login(self):
        ''' This method allows user to login.
        Password is verified through encryption using bcrypt
        '''
        try:
            with connect(
                host="localhost",
                user=user_database,
                password=password_database,
                database="book_store",
            ) as connection:
                email = input("Enter email : ")
                self.email = email
                password = getpass("Enter password: ")
                password = password.encode('UTF-8')
                # Query to retrieve the encoded password
                retrieve_password = ("select password from members "
                                     + f"where email=\'{email}\'")
                with connection.cursor() as cursor:
                    cursor.execute(retrieve_password)
                    retrieve_password = cursor.fetchall()
                    retrieve_password = retrieve_password[0][0]
                if (retrieve_password == []):
                    print("Invalid unsername/login credentials")
                if bcrypt.checkpw(password, retrieve_password.encode("utf-8")):
                    # Comparision of the password given using bcrypt method
                    self.password = password
                    self.connected = True
                    print("You are now connected")
                else:
                    print("Invalid password")
                    return False
        except Error as e:
            print(e)

    def register(self):
        """This method allows an user to register.
        The password is encrypted"""
        print("Welcome to the Online Book Store")
        print("New Member Registration")
        fname = input("Enter first name: ")
        lname = input("Enter last name: ")
        address = input("Enter street address: ")
        city = input("Enter city: ")
        state = input("Enter state: ")
        zip = input("Enter zip: ")
        phone = input("Enter phone: ")
        email = input("Enter email address: ")
        if self.validate_email(email):
            # Check if the email is valid
            pass
        else:
            print("Email is invalid, please enter a valid email")
        password = getpass("Enter password: ")
        password = password.encode('UTF-8')
        # Encoding the email using bcrypt
        hashed_password = bcrypt.hashpw(password, bcrypt.gensalt())
        hashed_password = hashed_password.decode("utf-8")
        try:
            # Query to check if the user already exists
            with connect(
                host="localhost",
                user=user_database,
                password=password_database,
                database="book_store",
            ) as connection:
                # Query to retrieve a member using an email
                existing_user = ("select * from members where"
                                 + f" email=\'{email}\'")
                with connection.cursor() as cursor:
                    cursor.execute(existing_user)
                    result = cursor.fetchall()
                    if (result == []):
                        pass
                    elif (result != []):
                        print("The user with such email exist,"
                              + "please use another email")
                        return

        except Error as e:
            print(e)

        try:
            with connect(
                host="localhost",
                user=user_database,
                password=password_database,
                database="book_store",
            ) as connection:
                # Query that inserts the new user
                db_query = ("insert into members (fname, lname, address, city,"
                            + " state, zip, phone, email, password) values "
                            + f"(\'{fname}\',\'{lname}\', "
                            + f"\'{address}\',\'{city}\',"
                            + f"\'{state}\',\'{zip}\',\'{phone}\',"
                            + f"\'{email}\',\'{hashed_password}\');")
                with connection.cursor() as cursor:
                    cursor.execute(db_query)
                    connection.commit()
                    print("You have registered successfully!")
                    input("Press enter to go back to the Menu")

        except Error as e:
            print(e)

    def addBookIsbn(self, isbn):
        ''' Method to specify the quantity of
        books to add'''
        quantity = input("Enter quantity: ")
        self._obj_cart.addBook(isbn, int(quantity), self.email, self.connected)

    def getBooksInCart(self, connected):
        """Method that adds a book if
        the user is connected"""
        if (not connected):
            print("You're not connected")
            return
        list_of_books = self._obj_cart.getBooks()
        return list_of_books

    def passOrder(self):
        """Method that passes and order. Return None if the
        cart is empty otherwise it confirms the order"""
        answer = self._obj_cart.confirm_cart(self.email, self.connected)
        if answer is None:
            return None
        else:
            return 'michel'

    def generate_invoice(self):
        """Method that generates the invoice"""
        self._obj_cart.generate_invoice_cart(self.email, self.connected)

    def unconnect(self):
        self.connected = False
