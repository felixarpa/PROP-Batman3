package exceptions;

import domain.User;

public class ExistingUser extends Exception {

    private User user;

    public ExistingUser(User user) {
        this.user = user;
    }

    @Override
    public String getMessage() {
        return "User " + user.getUsername() + " already exists";
    }

    public User getUser() {
        return user;
    }

    public String getUsername() { return user.getUsername(); }
    public String getPassword() { return user.getPassword(); }
}
