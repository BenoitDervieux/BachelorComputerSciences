package controller;

import model.interfaces.SystemModelOperations;
import model.interfaces.UserOperations;
import view.Ui;
import view.enums.ProfilePage;

/**
 * This class handles the responsibilities related to the profile.
 * You can view, delete and update a profile
 */
public class ProfileController {

  /**
   * Handles profile operations.
   */
  public void openProfileOptions(SystemModelOperations sys, Ui ui,
      UserOperations user, UserController uc) {
    boolean run = true;

    while (run) {
      switch (ui.printProfilePageOptions()) {

        case VIEW:
          ui.viewUserProfile(user);
          ui.waitForEnterToContinue();
          break;

        case UPDATE:
          ProfilePage selection = ui.viewUpdateProfileMenu();
          try {

            if (selection == ProfilePage.UPDATE_USER_NAME) {
              String newName = ui.getNewName();
              sys.updateUserName(newName);

            } else if (selection == ProfilePage.UPDATE_EMAIL) {
              String newEmail = ui.getNewEmail();
              sys.updateUserEmail(newEmail);

            } else if (selection == ProfilePage.UPDATE_PHONE) {
              String newPhoneNumber = ui.getNewPhoneNumber();
              sys.updateUserPhoneNumber(newPhoneNumber);

            } else if (selection == ProfilePage.UPDATE_PASSWORD) {
              String newPassword = ui.getNewPassword();
              sys.updateUserPassword(newPassword);
            }

          } catch (Exception e) {
            ui.printError(e);
          }

          break;

        case DELETE:
          boolean confirmation = ui.getConfirmation();
          if (confirmation) {
            sys.removeUser();
            uc.logOut();
            return;

          }

          break;
        case EXIT:
          return;
        default:
          break;
      }
    }

  }
}
