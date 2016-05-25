package view;

import domain.DomainController;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import presentation.MainPresenter;

public class MainView extends BaseView {

    private VBox contentVBox;

    private TextField searchText;
    private HBox searchTextHBox;

    private ProgressIndicator progressIndicator;

    private ImageButton searchButton;

    public MainView(MainPresenter mainPresenter) {
        presenter = mainPresenter;
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
        topBarPane.setCenter(contentVBox);
    }

    private void initializePanes() {
        contentVBox = new VBox();
        searchTextHBox = new HBox();
        searchTextHBox.setPadding(new Insets(0,0,10,0));
    }

    private void initializeViews() {
        searchButton = new ImageButton("../images/searchButton.png", 143, 51);
        searchText = new TextField();
        progressIndicator = new ProgressIndicator();
        progressIndicator.setProgress(-1);
    }

    private void buildPanes() {

        searchTextHBox.getChildren().add(searchText);
        searchTextHBox.setMinWidth(500);
        searchTextHBox.setMaxWidth(500);
        searchTextHBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(searchText, Priority.ALWAYS);

        Predictor predictor = new Predictor(DomainController.allNames(), 10);
        predictor.setMaxSize(600,200);
        predictor.setMinSize(600,200);
        contentVBox.getChildren().add(predictor);
        //contentVBox.getChildren().add(searchTextHBox);
        //contentVBox.getChildren().add(searchButton);
        contentVBox.setAlignment(Pos.CENTER);
    }

    private void setListeners() {

        searchText.setOnAction(
                event -> ((MainPresenter)presenter).clickSearchButton()
        );

        searchButton.setOnMousePressed(
                event -> searchButton.changeButtonImage("../images/searchButtonPressed.png")
        );

        searchButton.setOnMouseReleased(
                event -> {
                    searchButton.changeButtonImage("../images/searchButton.png");
                    ((MainPresenter)presenter).clickSearchButton();
                }
        );
    }

    public void startProgress() {
        searchButton.setDisable(true);
        if (Platform.isFxApplicationThread()) {
            searchTextHBox.getChildren().set(0, progressIndicator);
        }
        else {
            Platform.runLater(() -> searchTextHBox.getChildren().set(0, progressIndicator));
        }
    }

    public void stopProgress() {
        searchButton.setDisable(false);
        if (Platform.isFxApplicationThread()) {
            searchTextHBox.getChildren().set(0, searchText);
        }
        else {
            Platform.runLater(() -> searchTextHBox.getChildren().set(0, searchText));
        }
    }
    public String getSearchText() { return searchText.getText();}


}
