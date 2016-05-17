package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.TestView;
import presentation.LoginPresenter;
import presentation.Presenter;

public class MyApp extends Application {

    private static Stage baseStage;
    //public static Group root;
    private static Presenter currentPresenter;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //root = new Group();
        baseStage = primaryStage;

        baseStage.setTitle(Config.APPLICATION_NAME);
        baseStage.setWidth(Config.WINDOW_WIDTH);
        baseStage.setHeight(Config.WINDOW_HEIGHT);
        baseStage.setResizable(false);

        //batsign();
        testView();
    }

    public static void startScene(Pane pane) {
        baseStage.setScene(new Scene(pane));
        baseStage.show();
    }

    public static void changePresenter(Presenter nextPresenter) {
        currentPresenter = nextPresenter;
    }

    public void batsign() {
        currentPresenter = new LoginPresenter();
    }

    public static void changeButtonImage(Button button, String imageName, String width, String height, Class clas) {
        String loginButtonImage = clas.getResource("../images/"+imageName+".png").toExternalForm();
        button.setStyle(
                "-fx-background-image: url('"+ loginButtonImage + "');" +
                        "-fx-background-size: "+width+" "+height+";" +
                        "-fx-background-color: transparent");
    }

    // THIS IS A TESTING METHOD //
    private void testView() {
        currentPresenter = new TestView();
    }
    //THIS TOO
    public static void prueba() {
        Platform.exit();
    }
}
