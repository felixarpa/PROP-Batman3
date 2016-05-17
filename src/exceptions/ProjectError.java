package exceptions;

import main.BatmanChiliPepper;
import view.MyApp;

public class ProjectError extends Error {

    public ProjectError(String message) {
        super(message);
        MyApp.prueba();
        BatmanChiliPepper.exitStatus = 1;
    }

}
