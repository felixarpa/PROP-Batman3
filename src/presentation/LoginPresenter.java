package presentation;

import domain.UserController;
import exceptions.ExistingUser;
import exceptions.IncorrectPassword;
import exceptions.NonExistentUser;
import main.BatmanChiliPepper;
import view.LoginView;
import view.MyApp;

public class LoginPresenter extends Presenter {

    private LoginView loginView;

    public LoginPresenter() {
        loginView = new LoginView(this);
        MyApp.startScene(loginView.getContent());
    }

    public void login() {
        String username = loginView.getUsername();
        String password = loginView.getPassword();
        try {
            UserController.logIn(username, password);
            System.out.println("Successz");
            loginView.displaySuccessMessage();
            //loginView.destroy();
            loginView = null;
            BatmanChiliPepper.changePresenter(new MainPresenter());
        } catch (IncorrectPassword | NonExistentUser exception) {
            System.out.println(exception.getMessage());
            loginView.displayErrorMessage();
        }
    }

    public void register() {
        String username = loginView.getUsername();
        String password = loginView.getPassword();
        try {
            UserController.register(username, password);
            System.out.println("Success");
            loginView.displaySuccessMessage();
        } catch (ExistingUser existingUser) {
            System.out.println(existingUser.getMessage());
            loginView.displayErrorMessage();
        }
    }
}
