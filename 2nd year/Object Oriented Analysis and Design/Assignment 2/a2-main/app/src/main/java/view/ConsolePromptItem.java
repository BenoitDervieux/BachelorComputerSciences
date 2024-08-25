package view;

import model.Item;

/**
 * Class to get console input.
 */
public class ConsolePromptItem {

  interface Output {
    void print(String s);
  }

  interface Input {
    String prompt(String s);
  }

  interface StringConstructor<T> {
    T construct(String str);
  }

  private final Output out;
  private final Input in;

  ConsolePromptItem(Output output, Input input) {
    out = output;
    in = input;
  }

  Item.ItemName getItemName() {
    return getT("Enter item name:", str -> new Item.ItemName(str));
  }

  Item.ItemShortDescription getItemShortDescription() {
    return getT("Enter item short description:", str -> new Item.ItemShortDescription(str));
  }

  Item.ItemCostPerDay getItemCostPerDay() {
    return getT("Enter item cost per day:", str -> new Item.ItemCostPerDay(str));
  }

  Item.Category getItemCategory() {
    return getT("Enter item category among Tool, Vehicle, Game, Toy, Sport and Other:", str -> {
      String[] choices = {"tool", "vehicle", "game", "toy", "sport", "other"};
      String input = str.toLowerCase();
      while (true) {
        for (String i : choices) {
          if (i.equals(input)) {
            switch (input) {
              case "tool":
                return Item.Category.Tool;
              case "vehicle":
                return Item.Category.Vehicle;
              case "game":
                return Item.Category.Game;
              case "toy":
                return Item.Category.Toy;
              case "sport":
                return Item.Category.Sport;
              case "other":
                return Item.Category.Other;
              default:
                return Item.Category.None;
            }
          }
        }
        System.out.println("Invalid category. Please enter one of the predefined categories.");
        str = in.prompt("Enter item category among Tool, Vehicle, Game, Toy, Sport, and Other:");
        input = str.toLowerCase();
      }
    });
  }

  private <T> T getT(String prompt, StringConstructor<T> constructor) {
    T entered = null;
    while (entered == null) {
      String str = in.prompt(prompt);
      if (str.isEmpty()) {
        return null;
      }
      try {
        entered = constructor.construct(str);
      } catch (Exception e) {
        out.print("Invalid input. Try again.");
      }
    }
    return entered;
  }


  

}
