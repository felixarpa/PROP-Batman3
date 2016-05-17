package main;

import presentation.*;
import view.MyApp;

public abstract class BatmanChiliPepper {

    private static Presenter currentPresenter;
    public static int exitStatus = 0;

    public static void main(String[] args) {

        MyApp.launch(MyApp.class, args);

        System.exit(exitStatus);
    }

    public static void changePresenter(Presenter nextPresenter) {
        currentPresenter = nextPresenter;
   }
}
