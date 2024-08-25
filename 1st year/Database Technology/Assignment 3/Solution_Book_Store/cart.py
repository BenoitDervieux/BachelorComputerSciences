from books import Books
from mysql.connector import connect, Error
from env_variables import user_database, password_database


class Cart:

    def __init__(self):
        self._books = []
        pass

    def addBook(self, isbn, quantity, email, connected):
        ''' This method adds a book in the cart.
        It takes the isbn, the quantity, the email and
        the state of connection
        '''
        if (not connected):
            print("You are not connected")
            return
        try:
            with connect(
                host="localhost",
                user=user_database,
                password=password_database,
                database="book_store",
            ) as connection:
                connect_query = ("select author, title, isbn, price, "
                                 + "subject from books  "
                                 + f"where isbn=\'{isbn}\'")
                user_id = f"select userid from members where email=\'{email}\'"
                with connection.cursor() as cursor:
                    cursor.execute(connect_query)
                    result = cursor.fetchall()
                    if result == []:
                        print("This isbn does not exist")
                    else:
                        book = Books()
                        book.setBook(result[0][0],
                                     result[0][1],
                                     result[0][2],
                                     result[0][3],
                                     result[0][4],
                                     quantity)
                        self._books.append(book)
                with connection.cursor() as cursor:
                    cursor.execute(user_id)
                    user_id = cursor.fetchall()
                    if result == []:
                        print("No user id")
                add_in_cart = ("insert into book_store.cart "
                               + "(userid, isbn, qty) select "
                               + f"\'{user_id[0][0]}\', "
                               + f"\'{isbn}\', \'{quantity}\'")
                with connection.cursor() as cursor:
                    cursor.execute(add_in_cart)
                    connection.commit()

        except Error as e:
            print(e)

    def getBooks(self):
        ''' This method return the books in the cart
        as a list
        '''
        list_of_books = []
        for i in self._books:
            list_of_books.append(i.getBookFromBooks())
        return list_of_books

    def confirm_cart(self, email, connected):
        ''' This method ask confirmation
        of the command by inserting the books
        from the cart into the database
        '''
        if (not connected):
            print("You are not connected")
            return
        if (self._books == []):
            print("You have no books selected")
            return None
        try:
            with connect(
                host="localhost",
                user=user_database,
                password=password_database,
                database="book_store",
            ) as connection:
                connect_query = ("insert into book_store.orders "
                                 + "(userid, received, shipped, "
                                 + "shipAddress, shipCity, "
                                 + "shipState, shipZip) select "
                                 + "members.userid, "
                                 + "date(CURRENT_TIMESTAMP + "
                                 + "interval '7' day), "
                                 + "null, "
                                 + "members.address, members.city, "
                                 + "members.state, members.zip "
                                 + "from members where "
                                 + f"members.email=\'{email}\'")
                with connection.cursor() as cursor:
                    cursor.execute(connect_query)
                    connection.commit()
                ono_number = "select ono from orders order by ono desc limit 1"
                with connection.cursor() as cursor:
                    cursor.execute(ono_number)
                    ono_number = cursor.fetchall()
                for i in self._books:
                    book = i.getBookFromBooks()
                    insert_order = ("insert into book_store.odetails "
                                    + "(ono, isbn, qty, price) select "
                                    + f"\'{ono_number[0][0]}\', \'{book[2]}\',"
                                    + f" \'{book[5]}\', \'{book[3]}\'")
                    with connection.cursor() as cursor:
                        cursor.execute(insert_order)
                        connection.commit()
                return "michel"
        except Error as e:
            print(e)

    def generate_invoice_cart(self, email, connected):
        ''' This method generates the invoice
        after the cart comfirmation
        '''
        if (not connected):
            print("You are not connected")
            return
        try:
            with connect(
                host="localhost",
                user=user_database,
                password=password_database,
                database="book_store",
            ) as connection:
                # Query to retrive the userid
                get_user_id = ("select userid "
                               + "from members where "
                               + f"email=\'{email}\'")
                with connection.cursor() as cursor:
                    cursor.execute(get_user_id)
                    get_user_id = cursor.fetchall()
                    get_user_id = get_user_id[0][0]
                    # Query to retrieve the order number
                ono_number = ("select ono from orders "
                              + f"where userid=\'{get_user_id}\' "
                              + "order by ono desc limit 1")
                with connection.cursor() as cursor:
                    cursor.execute(ono_number)
                    ono_number = cursor.fetchall()
                    ono_number = ono_number[0][0]
                print()
                print("Invoice for Order no.", ono_number)
                print()
                print("Shipping address")
                print("Name:        John Smith")
                print("Adress:      123 Elm Street")
                print("             Atlanta")
                print("             GA 11111")
                print()
                print("Billing adress")
                # Query to retrieve information of the customer
                billing_address = ("select fname, lname, address, "
                                   + "city, state, "
                                   + "zip from members where "
                                   + f"email=\'{email}\';")
                with connection.cursor() as cursor:
                    cursor.execute(billing_address)
                    billing_address = cursor.fetchall()
                print("Name:        ", billing_address[0][0],
                      billing_address[0][1])
                print("Address:     ", billing_address[0][2])
                print("             ", billing_address[0][3])
                print("             ", billing_address[0][4],
                      billing_address[0][5])
                print("-"*138)
                print("ISBN        Title" + " "*100
                      + "$" + "  Qty  " + "Total")
                print("-"*138)
                # Query to retrieve the isbn from the ono number
                list_of_isbn = ("select isbn from "
                                + f"odetails where ono=\'{ono_number}\' ;")
                with connection.cursor() as cursor:
                    cursor.execute(list_of_isbn)
                    list_of_isbn = cursor.fetchall()
                total = 0
                for i in list_of_isbn:
                    # Query to retrieve book information
                    get_book = ("select isbn, title, price "
                                + f"from books where isbn=\'{i[0]}\';")
                    with connection.cursor() as cursor:
                        cursor.execute(get_book)
                        get_book = cursor.fetchall()
                        # Query to retrieve the quantity of book
                    get_quantity = ("select qty, isbn from odetails where "
                                    + f"ono=\'{ono_number}\' "
                                    + f"and isbn=\'{i[0]}\';")
                    with connection.cursor() as cursor:
                        cursor.execute(get_quantity)
                        get_quantity = cursor.fetchall()
                    print(f"{get_book[0][0]:12}" +
                          f"{get_book[0][1]:102}" +
                          f"{str(get_book[0][2]):<5}" +
                          f"  {str(get_quantity[0][0]):4}"
                          + str(round(float(get_book[0][2]) *
                                      float(get_quantity[0][0]), 2)) + '$')
                    total += float(get_book[0][2])*float(get_quantity[0][0])
                print("-"*138)
                print("Total = ", f"{total:>120}$")
                print("-"*138)
                print("Estimated delivery date:")
                # query to retrieve the delivery date
                estimated_delivery_date = ("select received from "
                                           + "orders where "
                                           + f"ono=\'{ono_number}\' ;")
                with connection.cursor() as cursor:
                    cursor.execute(estimated_delivery_date)
                    estimated_delivery_date = cursor.fetchall()
                print(estimated_delivery_date[0][0])
                self._books.clear()

        except Error as e:
            print(e)
