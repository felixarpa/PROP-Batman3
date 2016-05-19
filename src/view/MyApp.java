package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
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

        batsign();
    }

    public static void startScene(Pane pane) {
        if (Platform.isFxApplicationThread()) {
            baseStage.setScene(new Scene(pane));
            baseStage.show();
        }
        else {
            Platform.runLater(() -> {
                baseStage.setScene(new Scene(pane));
                baseStage.show();
            });
        }
    }

    public static void changePresenter(Presenter nextPresenter) {
        currentPresenter = nextPresenter;
    }

    private void batsign() {
        currentPresenter = new LoginPresenter();
    }

    public static void exit() {
        Platform.exit();
    }
}
