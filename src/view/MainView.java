package view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import presentation.MainPresenter;

public class MainView extends BaseView {

    private BorderPane basePane;
    private VBox contentVBox;
    private GridPane searchGridPane;
    private VBox buttonsVBox;

    private MainPresenter mainViewPresenter;

    public MainView(MainPresenter mainViewPresenter) {
        this.mainViewPresenter = mainViewPresenter;
    }


}
