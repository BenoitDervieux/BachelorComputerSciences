package model.rules;

/**
 * Interface for the vistor pattern.
 */
public interface Visitable {

  public void accept(model.Visiteur visitor);
    
}

