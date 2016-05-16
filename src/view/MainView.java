package view;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import presentation.Presenter;

public class MainView extends BaseView {

    private BorderPane basePane;
    private VBox contentVBox;
    private GridPane searchGridPane;
    private VBox buttonsVBox;

    private Presenter mainViewPresenter;

    public MainView(Presenter mainViewPresenter) {
        super(mainViewPresenter);
        this.mainViewPresenter = mainViewPresenter;
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();

    }

    private void initializePanes() {
        basePane = new BorderPane();
        basePane.setPadding(new Insets(0, 292, 0, 292));
    }

    private void initializeViews() {

    }

    private void buildPanes() {

    }

    private void setListeners() {

    }


}
