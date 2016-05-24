package domain;

import domain.graph.Graph;
import domain.graph.Term;
import exceptions.*;

import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.TreeSet;

public abstract class UserController {

    public static void logIn(String username, String password) throws IncorrectPassword, NonExistentUser {
        User user = new User(username, password);
        DataBaseController.logIn(user);
    }

    public static void logOut(){
        DataBaseController.logOut(DomainController.currentUser);
        DomainController.currentUser = null;
    }

    public static void register(String username, String password) throws ExistingUser {
        User user = new User(username, password);
        DataBaseController.registerUser(user);
    }
    
    public static void addNewFav(String term) {
        Term t = DomainController.stringToNode(term).asTerm();
        DomainController.currentUser.addFavorite(t);
    }
    
    public static void deleteFav(String term) {
        Term t = DomainController.stringToNode(term).asTerm();
        DomainController.currentUser.deleteFavorite(t);
    }

    public static ArrayList<String> getFavouriteTerms() {
        TreeSet<Term> favourites = DomainController.currentUser.getFavorites();
        ArrayList<String> result = new ArrayList<>(favourites.size());
        for (Term t : favourites) {
            result.add(t.toString());
        }
        return result;
    }

    public static void changePassword(String currentPassword, String newPassword, String confirmedPassword) throws IncorrectPassword, PasswordMissMatch {
        User currentUser = DomainController.currentUser;
        if (!currentPassword.equals(currentUser.getPassword())) throw new IncorrectPassword(currentUser, newPassword);
        if (!newPassword.equals(confirmedPassword)) throw new PasswordMissMatch(newPassword, confirmedPassword);
        currentUser.setPassword(newPassword);
    }

 }
