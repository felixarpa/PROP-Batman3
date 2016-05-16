package exceptions;

import domain.User;

public class NonExistentUser extends Exception {
    private User user;

    public NonExistentUser(User user) {
        this.user = user;
    }

    public User getUser(){return user;}

    public String getUsername() { return user.getUsername(); }
    public String getPassword() { return user.getPassword(); }

    @Override
    public String getMessage() {
        return "User "+user+" not found in data base";
    }
}
