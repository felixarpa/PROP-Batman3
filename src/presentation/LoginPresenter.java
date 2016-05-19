package presentation;

import domain.UserController;
import exceptions.ExistingUser;
import exceptions.IncorrectPassword;
import exceptions.NonExistentUser;
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
        loginView.startProgress();
        Thread thread = new Thread(() -> {
            try {
                UserController.logIn(username, password);
                System.out.println("Success login");
                loginView.destroy();
                loginView = null;
                MyApp.changePresenter(new MainPresenter());
            } catch (IncorrectPassword | NonExistentUser exception) {
                System.out.println(exception.getMessage());
                loginView.stopProgress();
                loginView.displayErrorMessage("Invalid password or username");
            }
        });
        thread.start();
    }

    public void register() {
        String username = loginView.getUsername();
        if (username.length() < 1) {
            loginView.displayErrorMessage("Username must have at least 1 character");
            return;
        }
        String password = loginView.getPassword();
        if (password.length() < 1) {
            loginView.displayErrorMessage("Password must have at least 1 character");
            return;
        }
        loginView.startProgress();
        Thread thread = new Thread(() -> {
            try {
                UserController.register(username, password);
                System.out.println("Success register");
            } catch (ExistingUser existingUser) {
                System.out.println(existingUser.getMessage());
                loginView.displayErrorMessage("User "+username+" already exists");
            }
            finally {
                loginView.stopProgress();
            }
        });
        thread.start();
    }
}
