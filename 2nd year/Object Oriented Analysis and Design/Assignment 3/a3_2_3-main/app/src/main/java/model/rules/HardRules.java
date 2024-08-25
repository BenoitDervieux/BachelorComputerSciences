package model.rules;

import model.Visiteur;

/**
 * Hard rules for playing black jack.
 */
public class HardRules extends AbstractRulesFactory  {

  private HitStrategy hitStrategy = new Soft17Strategy();
  private WinGameStrategy winStrategy = new NormalWinStrategy();
  private NewGameStrategy gameStrategy = new InternationalNewGameStrategy();

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
