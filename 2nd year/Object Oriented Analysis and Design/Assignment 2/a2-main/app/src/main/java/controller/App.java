package controller;

import model.SystemModel;
import model.interfaces.SystemModelOperations;
import view.Ui;

/**
 * Responsible for staring the application.
 */
public class App {
  /**
   * Application starting point.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {

    SystemModelOperations sys = new SystemModel();
    view.Ui ui = new Ui();
    MainController mc = new MainController();
    
    mc.startSystem(sys, ui);
  }
}
