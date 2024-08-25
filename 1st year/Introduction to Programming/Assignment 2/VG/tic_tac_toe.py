# --------------------------------------------------------------------------
# Shall we play a game?
# File:        tic_tac_toe.py
# Description:
#   This program is the game of Tic Tac Toe
#
# History:     17 Sept 2022
#
# ---------------------------------------------------------------------------

import sys

# Here we have a list of X and O to determine who
# has played
game = [['-', '-', '-'], ['-', '-', '-'], ['-', '-', '-']]


# We define the two player as classes
# with 2 values: name and boolean
class Player1:
    def __init__(self, name, up_to_player):
        self.name = name
        self.up_to_player = up_to_player


class Player2:
    def __init__(self, name, up_to_player):
        self.name = name
        self.up_to_player = up_to_player


def def_players(player1, player2):
    """This function defines the two players by assigning them the classe
    and by defining who starts or not. It also prints out
    the name of the player"""
    player1 = Player1(input('Enter the name of the first player: '), True)
    print(f'{player1.name} you\'re the "X"')
    player2 = Player2(input('Enter the name of the second player: '), False)
    print(f'{player2.name} you\'re the "O"')
    return player1, player2


def clean_the_grid(game):
    """This function restart the grid when the
    players decide to start again"""
    for i in range(3):
        for j in range(3):
            game[i][j] = '-'


def print_grid(game):
    """This function prints the grid by printing
    123 on horizontally on top and 123 vertically
    of the left side"""
    print('{0:4} {1:4} {2:4}'.format(1, 2, 3))

    for i in range(3):
        print(i + 1, end='  ')
        for j in range(3):
            print('{0:4}'.format(game[i][j]), end=' ')
        print()
    print()


def check_if_win(player_one, player_two, game):
    """This functions detects if there is a combination
    of same signs that forms a win in the Tic Tac Toe game.
    Basically 3 same signs vertically, horizontally or
    in diagonal"""
    if game[0][0] == game[0][1] == game[0][2] and game[0][0] != '-':
        print_grid(game)
        if player_one.up_to_player is True:
            print(f'{player_one.name} you won!')
        else:
            print(f'{player_two.name} you won')
        start_again(player_one, player_two)
    elif game[1][0] == game[1][1] == game[1][2] and game[1][0] != '-':
        print_grid(game)
        if player_one.up_to_player is True:
            print(f'{player_one.name} you won!')
        else:
            print(f'{player_two.name} you won')
        start_again(player_one, player_two)
    elif game[2][0] == game[2][1] == game[2][2] and game[2][0] != '-':
        print_grid(game)
        if player_one.up_to_player is True:
            print(f'{player_one.name} you won!')
        else:
            print(f'{player_two.name} you won')
        start_again(player_one, player_two)
    elif game[0][0] == game[1][0] == game[2][0] and game[0][0] != '-':
        print_grid(game)
        if player_one.up_to_player is True:
            print(f'{player_one.name} you won!')
        else:
            print(f'{player_two.name} you won')
        start_again(player_one, player_two)
    elif game[0][1] == game[1][1] == game[2][1] and game[0][1] != '-':
        print_grid(game)
        if player_one.up_to_player is True:
            print(f'{player_one.name} you won!')
        else:
            print(f'{player_two.name} you won')
        start_again(player_one, player_two)
    elif game[0][2] == game[1][2] == game[2][2] and game[0][2] != '-':
        print_grid(game)
        if player_one.up_to_player is True:
            print(f'{player_one.name} you won!')
        else:
            print(f'{player_two.name} you won')
        start_again(player_one, player_two)
    elif game[0][0] == game[1][1] == game[2][2] and game[0][0] != '-':
        print_grid(game)
        if player_one.up_to_player is True:
            print(f'{player_one.name} you won!')
        else:
            print(f'{player_two.name} you won')
        start_again(player_one, player_two)
    elif game[2][0] == game[1][1] == game[0][2] and game[2][0] != '-':
        print_grid(game)
        if player_one.up_to_player is True:
            print(f'{player_one.name} you won!')
        else:
            print(f'{player_two.name} you won')
        start_again(player_one, player_two)
    else:
        print()


def check_case(player_one, player_two, game):
    """This function checks if all the cases
    are filled by something else than the
    original char in the game list. If
    yes, then no winner has been detected, which
    mean no one won"""
    count = 0
    for i in range(3):
        for j in range(3):
            if game[i][j] != '-':
                count += 1
    if count == 9:
        print('The game is over, no one won')
        start_again(player_one, player_two)


def ask_player(player_one, player_two, game):
    """This function asks the players to enter
    a row and a column. It notifies if those are
    already taken. It checks if there is a
    winner. It also prints the updated grid
    with the sign a player has choosen"""
    # Let the first player play if it's true
    if player_one.up_to_player is True:
        # ask for a number of row the first player wants
        # to choose
        answer_row = int(input(
            f'{player_one.name}, which row do you want to play? ')) - 1
        while answer_row < 0 or answer_row > 2:
            # if the input is outide (1 - 3 ) it loops
            # over
            answer_row = int(input(
                f'{player_one.name}, which row do you want to play? ')) - 1
        answer_column = int(input(
            f'{player_one.name}, which column do you want to play? ')) - 1
        while answer_column < 0 or answer_column > 2:
            answer_column = int(input(
                f'{player_one.name}, which column do you want to play? ')) - 1
        if game[answer_row][answer_column] != '-':
            print('This is already taken')
            # If the row is already taken, we ask again
            ask_player(player_one, player_two, game)
        elif game[answer_row][answer_column] == '-':
            # Otherwise we print the sign where the
            # player has asked for
            game[answer_row][answer_column] = 'X'
            # We check if the player has won
            check_if_win(player_one, player_two, game)
            # We check if all the cases are filled
            check_case(player_one, player_two, game)
            # We change the order of who should play
            player_one.up_to_player = False
            player_two.up_to_player = True
            # We print the new grid
            print_grid(game)
            # It's the turn to the other player
            ask_player(player_one, player_two, game)
    if player_two.up_to_player is True:
        # Let the second player play if it's true
        # ask for a number of row and column the
        # second player wants to choose
        answer_row = int(input(
            f'{player_two.name}, which row do you want to play? ')) - 1
        while answer_row < 0 or answer_row > 2:
            answer_row = int(input(
                f'{player_two.name}, which row do you want to play? ')) - 1
            # if the input is outide (1 - 3 ) it loops
            # over
        answer_column = int(input(
            f'{player_two.name}, which column do you want to play? ')) - 1
        while answer_column < 0 or answer_column > 2:
            answer_column = int(input(
                f'{player_two.name}, which column do you want to play? ')) - 1
        if game[answer_row][answer_column] != '-':
            print('This is already taken')
            # If the row is already taken, we ask again
            ask_player(player_one, player_two, game)
        elif game[answer_row][answer_column] == '-':
            # Otherwise we print the sign where the
            # player has asked for
            game[answer_row][answer_column] = 'O'
            # We check if the player has won
            check_if_win(player_one, player_two, game)
            # We check if all the cases are filled
            check_case(player_one, player_two, game)
            # We change the order of who should play
            player_one.up_to_player = True
            player_two.up_to_player = False
            # We print the new grid
            print_grid(game)
            # It's the turn to the other player
            ask_player(player_one, player_two, game)


def main(player1='', player2=''):
    # We clean the grid for any new game
    clean_the_grid(game)
    # if we don't have players,  or if someone erased them
    # we create them
    if player1 == '' and player2 == '':
        player1, player2 = def_players(player1, player2)
    # if we have players already, we redefine them
    # as objects
    elif player1 != '' and player2 != '':
        player1 = Player1(player1, True)
        player2 = Player2(player2, False)
    # we print the grid
    print_grid(game)
    # We start the game again
    ask_player(player1, player2, game)


def start_again(player1, player2):
    """This programs asks if we want to play again
    and if we want to keep the same players
    because no one does only 1 game of Tic Tac Toe"""
    start_again = input('Do you want to start again?(Y/N) ')
    start_again = start_again.lower()
    while start_again != 'n' and start_again != 'y':
        # We ask if we want to keep the same players
        start_again = input('Do you want to use the same players?(Y/N) ')
        start_again = start_again.lower()
    if start_again == 'y':
        same_player = input('Do you want to use the same players?(Y/N) ')
        same_player = same_player.lower()
        while same_player != 'n' and same_player != 'y':
            same_player = input('Do you want to use the same players?(Y/N) ')
            same_player = same_player.lower()
        if same_player == 'y':
            # If one wants to keep the same players
            # we send the names we already have
            main(player1.name, player2.name)
        elif same_player == 'n':
            # Otherwise we don't send anything
            main()
    elif start_again == 'n':
        # We exit the system if the players
        # don't want to play anymore
        sys.exit(0)


main()
