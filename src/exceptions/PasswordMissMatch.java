package exceptions;

public class PasswordMissMatch extends Exception {

    private String newPassword;
    private String confirmedPassword;

    public PasswordMissMatch(String newPassword, String confirmedPassword) {
        this.newPassword = newPassword;
        this.confirmedPassword = confirmedPassword;
    }

    @Override
    public String getMessage() {
        return "The password "+ newPassword + " doesn't match with " + confirmedPassword;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
