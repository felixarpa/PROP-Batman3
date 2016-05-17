package domain;

import exceptions.*;

public abstract class UserController {

    public static void logIn(String username, String password) throws IncorrectPassword, NonExistentUser {
        User user = new User(username, password);
        DataBaseController.logIn(user);
    }

    public static void register(String username, String password) throws ExistingUser {
        User user = new User(username, password);
        DataBaseController.registerUser(user);
    }
    
    public static void addNewFav(int term) {
    	DomainController.currentUser.addFavorite(term);
    }
    
    public static void deleteFav(int term) {
    	DomainController.currentUser.deleteFavorite(term);
    }

    public static void changePassword(String currentPassword, String newPassword, String confirmedPassword) throws IncorrectPassword, PasswordMissMatch {
        User currentUser = DomainController.currentUser;
        if (!currentPassword.equals(currentUser.getPassword())) throw new IncorrectPassword(currentUser, newPassword);
        if (!newPassword.equals(confirmedPassword)) throw new PasswordMissMatch(newPassword, confirmedPassword);
        currentUser.setPassword(newPassword);
    }

 }
