package view;

import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import presentation.MainPresenter;

public class MainView extends BaseView {

    private VBox contentVBox;

    private EditText searchEditText;
    private TextField searchText;
    private HBox searchTextHBox;

    private ImageButton searchButton;

    private MainPresenter mainPresenter;

    public MainView(MainPresenter mainPresenter) {
        super(mainPresenter);
        this.mainPresenter = mainPresenter;
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
        topBarPane.setCenter(contentVBox);
    }

    public void destroy() {
        mainPresenter = null;
    }

    private void initializePanes() {
        contentVBox = new VBox();
        searchTextHBox = new HBox();
        searchTextHBox.setPadding(new Insets(0,0,10,0));
    }

    private void initializeViews() {
        searchButton = new ImageButton("../images/searchButton.png", 143, 51);
        searchText = new TextField();
    }

    private void buildPanes() {

        searchTextHBox.getChildren().add(searchText);
        searchTextHBox.setMinWidth(500);
        searchTextHBox.setMaxWidth(500);
        searchTextHBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(searchText, Priority.ALWAYS);

        contentVBox.getChildren().add(searchTextHBox);
        contentVBox.getChildren().add(searchButton);
        contentVBox.setAlignment(Pos.CENTER);
    }

    private void setListeners() {

        searchButton.setOnMousePressed(
                event -> searchButton.changeButtonImage("../images/searchButtonPressed.png")
        );

        searchButton.setOnMouseReleased(
                event -> {
                    searchButton.changeButtonImage("../images/searchButton.png");
                    mainPresenter.clickSearchButton();
                }
        );
    }

    public String getSearchText() { return searchText.getText();}


}
