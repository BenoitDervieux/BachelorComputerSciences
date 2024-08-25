package model;

import model.rules.HitStrategy;
import model.rules.NewGameStrategy;
import model.rules.WinGameStrategy;

/**
 * Implements a visitor strategy to display the rules.
 */
public interface Visiteur {

  void visit(HitStrategy hitStrategy);

  void visit(WinGameStrategy winGameStrategy);

  void visit(NewGameStrategy newGameStrategy);
    
}
