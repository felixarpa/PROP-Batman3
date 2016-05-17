package exceptions;

import domain.User;

public class IncorrectPassword extends Exception {
    private User user;
    private String password;

    public IncorrectPassword(User user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public String getMessage() {
        return "Invalid password for user: "+user+". Password entered: "+password;
    }

    public User getUser() {
        return user;
    }

    public String getUsername() { return user.getUsername(); }
    public String getBadPassword() { return password; }

    public String getPassword() {
        return user.getPassword();
    }
}
