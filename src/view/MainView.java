package view;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import presentation.MainPresenter;

public class MainView extends BaseView {

    private BorderPane basePane;
    private VBox contentVBox;
    private GridPane searchGridPane;
    private VBox buttonsVBox;

    private EditText searchEditText;

    private MainPresenter mainPresenter;

    public MainView(MainPresenter mainPresenter) {
        super(mainPresenter);
        this.mainPresenter = mainPresenter;
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();

    }

    private void initializePanes() {
        basePane = new BorderPane();
        basePane.setPadding(new Insets(133, 292, 0, 292));
    }

    private void initializeViews() {

    }

    private void buildPanes() {

    }

    private void setListeners() {

    }

    @Override
    public Pane getContent() {
        BorderPane baseViewBasePane = (BorderPane) super.getContent();
        baseViewBasePane.setCenter(basePane);
        return baseViewBasePane;
    }


}
