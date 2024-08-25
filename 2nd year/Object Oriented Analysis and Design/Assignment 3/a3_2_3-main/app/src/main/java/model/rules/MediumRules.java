package model.rules;

import model.Visiteur;

/**
 * Rules with Medium Difficulties.
 */
public class MediumRules extends AbstractRulesFactory {

  private HitStrategy hitStrategy = new BasicHitStrategy();
  private WinGameStrategy winStrategy = new NormalWinStrategy();
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
