package view;

import domain.DomainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import presentation.LoginPresenter;
import presentation.Presenter;

public class MyApp extends Application {

    private static Stage baseStage;
    private static Presenter currentPresenter;

    @Override
    public void start(Stage primaryStage) throws Exception {
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

    @Override
    public void stop() {
        currentPresenter.saveAndExit();
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
