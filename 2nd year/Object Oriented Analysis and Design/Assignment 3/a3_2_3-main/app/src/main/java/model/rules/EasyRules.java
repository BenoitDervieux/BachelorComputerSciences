package model.rules;

import model.Visiteur;

/**
 * Factory for easy rules.
 */
public class EasyRules extends AbstractRulesFactory {

  private HitStrategy hitStrategy = new BasicHitStrategy();
  private WinGameStrategy winStrategy = new PlayerWinGameStrategy();
  private NewGameStrategy gameStrategy = new AmericanNewGameStrategy();

  @Override
  public HitStrategy createHitStrategy() {
    return hitStrategy;
  }

  @Override
  public WinGameStrategy createWinGameStrategy() {
    return winStrategy;
  }

  @Override
  public NewGameStrategy createNewGameStrategy() {
    return gameStrategy;
  }

  @Override
  public void accept(Visiteur visitor) {
    visitor.visit(this.hitStrategy);
    visitor.visit(this.winStrategy);
    visitor.visit(this.gameStrategy);
  }
    
}
