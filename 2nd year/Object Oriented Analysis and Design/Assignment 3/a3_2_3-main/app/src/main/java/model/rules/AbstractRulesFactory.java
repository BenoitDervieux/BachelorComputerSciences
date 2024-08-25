package model.rules;

/**
 * Abstract factory for implementing rules.
 */
public abstract class AbstractRulesFactory implements Visitable {

  public abstract HitStrategy createHitStrategy();

  public abstract WinGameStrategy createWinGameStrategy();

  public abstract NewGameStrategy createNewGameStrategy();
    
}
