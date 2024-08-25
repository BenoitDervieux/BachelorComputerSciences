package controller;

import model.HardCode;
import model.interfaces.SystemModelOperations;
import model.interfaces.UserOperations;
import view.LoginData;
import view.UserData;
import view.enums.MainMenu;

/**
 * Handles the main menu options and guides the operation.
 */
public class MainController {
  private UserOperations userOp;

  /**
   * This starts the system.
   */
  public void startSystem(SystemModelOperations sys, view.Ui ui) {
    loadData(sys);
    // Starting app with main menu
    MainMenu mainMenuSelection = ui.displayStartingMenu();

    while (mainMenuSelection != MainMenu.EXIT) {
      try {

        switch (mainMenuSelection) {

          case LOGIN: // Login as an existing user
            LoginData loginData = ui.getLoginData();
            userOp = sys.authenticateUser(loginData.getEmail(), loginData.getPassword());

            if (userOp != null) {
              UserController uc = new UserController(userOp, ui);
              // This is a dependency injection
              uc.login(sys);
            } else {
              ui.printInvalidCredentialsMessage();
            }

            break;

          case REGISTER: // Add's a new user
            UserData regData = ui.getUserData();
            UserOperations newUser = sys.addUser(regData.getName(), regData.getEmail(),
                regData.getPhoneNumber(), regData.getPassword());
            ui.viewRegStatus(newUser);
            break;

          case ADMIN_PANEL: // external admin panel
            AdminPanelController admin = new AdminPanelController();
            admin.startAdminPage(sys, ui);
            break;

          default:
            break;
        }

      } catch (Exception e) {
        ui.printError(e);
      }

      mainMenuSelection = ui.displayStartingMenu();
    }

  }

  // Change this
  private void loadData(SystemModelOperations sys) {
    HardCode pm = new HardCode();
    pm.loadData(sys);
  }

}
