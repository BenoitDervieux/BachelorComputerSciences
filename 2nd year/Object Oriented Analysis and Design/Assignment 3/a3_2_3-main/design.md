# BlackJack OO-Design
This document describes the current design. Note that some dependencies have been left out for readability reasons. For example there are a lot of dependencies to the Card class.

## Class Diagram
The application uses the model-view-controller (MVC) architectural pattern. The view is passive and gets called from the controller. The design has been updated.

![class diagram](img/BlackJackUpdated.jpg)

## Stand - Sequence Diagram
This is the detailed sequence diagram for the `Game.stand` method. This is what should be implemented.

![Stand Sequence diagram](img/stand_seq.jpg)

## Variations

There are different types of variations that you could find in the folder "rules":

### Hit Strategy

There are two different hit strategies implemented that will modify the behavior of the dealer (Basic hit strategy and Soft 17 strategy).

### Who win the game strategy

There are two differents rules for who is said to win the game. Either the dealer when scores are equals (NormalWinStrategy) either the player (Player Win Strategy).

### Cards distribution

There are two differents rules for the distribution of cards. Either the american way or the international way.

### Languages

There are two available languages: English and Sweidsh. They all implement the observer pattern.

### Abstract factories

An abstract factory pattern has been implemented. The abstract factory is AbstractRulesFactory. It contains 3 abstract methods which return 1. The hitstrategy, 2. The wingamestrategy and 3. the createNewGameStrategy.
3 differents abstract strategies have been implemented: Easy rules, Medium Rules and Hard rules. Those are implemented in the Game class when initialized.

## Fix for A2

I had as a fix those two comments : 
<i>Refactoring: okish - but why send the deck to the dealer? It already has it as it is the Information Expert. Then you could also have removed the Deck from the interfaces an increased encapsulation.
Observer: no - Your design has introduced a violation of the MVC pattern.<i>

After some research, I understood that my mistake was to implement some logic in the view. This is why I was violating the principles of the MVC architecture.

To fix it, I made the controller the observer (instead of the view) and I implemented a series of print methods in the view.

I also put the view as an attribute of the class controller.Player to be able to call those new print methods whenever I needed and I passed the view in the App class as well.

Instead of having one method for updating the card, I had two so that I could made the difference between printing a dealer playing or a player playing (in a game sense of the game, not a logical one). That way I would avoid to have any logic in the controller or the view.

I also deleted all the presence of deck in the game strategies and in the method for processing a card as it was suggested.

### Class Diagram updated

Here the update of the class diagram after the fix:

![class diagram](img/BlackJackFix.jpg)


