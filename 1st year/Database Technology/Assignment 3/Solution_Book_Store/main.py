from library import Library
from user import User
from books import Books
import sys
sys.path.insert(0, "..")

# Instantiate the classes
library = Library()
user = User()
books = Books()

choice = True


def main():
    # Menu register
    while(choice):
        # Print the menu
        library.menuRegister()
        answerMenuRegister = library.get_answer()

        if (answerMenuRegister == '1'):
            # Handle the login and loop if the login is incorrect
            login = user.login()
            if login is False:
                continue
            # Principal menu
            menuPrincipalBool = True
            while(menuPrincipalBool and user.connected):
                # Display the principal menu
                library.menuPrincipal()
                # Ask for an approriate answer
                answerMenuPrincipal = library.get_answer()
                if (answerMenuPrincipal == '1'):
                    sub = True
                    while(sub):
                        subject = books.browse_subjects()
                        if (subject is None):
                            sub = False
                        else:
                            answer = library.print_inter_books(
                                books.check_books_by_subject(subject))
                            if not answer:
                                sub = False
                            else:
                                user.addBookIsbn(answer)
                                sub = False
                elif (answerMenuPrincipal == '2'):
                    sub = True
                    while(sub):
                        # Menu to browse by author
                        a_t = library.author_or_title()
                        if (a_t == 'author'):
                            auth = True
                            while(auth):
                                answer = library.print_inter_books(
                                        books.check_books_by_author())
                                if not answer:
                                    auth = False
                                else:
                                    user.addBookIsbn(answer)
                                    auth = False
                        elif (a_t == 'title'):
                            # Menu to browse by title
                            tit = True
                            while(tit):
                                answer = library.print_inter_books(
                                    books.check_books_by_title())
                                if not answer:
                                    tit = False
                                else:
                                    user.addBookIsbn(answer)
                                    tit = False
                        elif (a_t is None):
                            sub = False
                elif (answerMenuPrincipal == '3'):
                    # Menu to check the cart
                    booksInCart = user.getBooksInCart(user.connected)
                    library.display_order(booksInCart)
                    check = True
                    while(check):
                        checkout = input("Do you want to proceed?(Y/N) ")
                        if (checkout.lower() == 'y'):
                            answer = user.passOrder()
                            if answer is None:
                                continue
                            elif answer == 'michel':
                                user.getBooksInCart(user.connected)
                                user.generate_invoice()
                                check = False
                        elif (checkout.lower() == 'n'):
                            check = False
                        else:
                            print("I did not understand")

                elif(answerMenuPrincipal == '4'):
                    # Exit the menu and disconnect the user
                    user.unconnect()
                    menuPrincipalBool = False
                else:
                    print("I did not understand")
            continue
        elif (answerMenuRegister == '2'):
            # Register a new user
            user.register()
            continue
        elif(answerMenuRegister == 'q'):
            # Quit the application
            print("Bye!")
            sys.exit()
        else:
            print("I didn't understand")


main()
