package model.rules;


/**
 * Creates concrete rules.
 */
public class RulesFactory {

  /**
   * Creates the rule to use for the dealer's hit behavior.

   * @return The rule to use
   */
  public HitStrategy getHitRule() {
    return new BasicHitStrategy();
  }

  /**
   * Crates the rule to use when starting a new game.

   * @return The rule to use.
   */
  public NewGameStrategy getNewGameRule() {
    return new AmericanNewGameStrategy();
  }
  /**
   * Crates a strategy for who should win the game depending on the scores.

   * @return the startegy to use.
   */
  public WinGameStrategy getWinGameStrategy() {
    return new PlayerWinGameStrategy();
  }
}