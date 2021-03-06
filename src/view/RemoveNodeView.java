package view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import presentation.RemoveNodePresenter;
import view.auxiliarViews.ImageButton;
import view.auxiliarViews.Predictor;

import java.util.Collection;
import java.util.List;

public class RemoveNodeView extends MainAdminView {

    private VBox contentVBox;


    private HBox errorHBox;
    private Label errorLabel;


    private HBox searchTextHBox;

    private ProgressIndicator progressIndicator;
    private ImageButton searchButton;
    private Predictor predictor;
    protected List<String> resultToPredict;


    private Font font;

    public RemoveNodeView(RemoveNodePresenter removeNodePresenter, List<String> resultToPredict) {
        presenter = removeNodePresenter;
        this.resultToPredict = resultToPredict;
        removeNodeButton.press();
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
        topBarPane.setCenter(contentVBox);
    }

    private void initializePanes() {
        contentVBox = new VBox();

        errorHBox = new HBox();
        errorHBox.setAlignment(Pos.CENTER);
        searchTextHBox = new HBox();
        searchTextHBox.setPadding(new Insets(0,0,10,80));
        searchTextHBox.setSpacing(10);
    }

    private void initializeViews() {
        searchButton = new ImageButton("removeNodeButton", 143, 51);

        font = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 14.95);
        errorLabel = new Label();
        errorLabel.setFont(font);
        errorLabel.setTextFill(Paint.valueOf("#6E0000"));

        predictor = new Predictor(resultToPredict, 10, new Insets(8, 0, 0, 0), "Introduce the name of the node that you want to remove.");
        predictor.setMaxSize(600,200);
        predictor.setMinSize(600,200);

        progressIndicator = new ProgressIndicator();
        progressIndicator.setProgress(-1);
    }

    private void buildPanes() {

        searchTextHBox.getChildren().add(predictor);
        searchTextHBox.getChildren().add(searchButton);
        errorHBox.getChildren().add(errorLabel);

        contentVBox.getChildren().addAll(
                errorHBox,
                searchTextHBox
        );
        contentVBox.setAlignment(Pos.CENTER);
    }

    private void setListeners() {

        predictor.setTextListener(
                event -> ((RemoveNodePresenter) presenter).clickRemoveNodeButton()
        );

        searchButton.setOnMousePressed(
                event -> searchButton.changeButtonImage("../images/removeNodeButtonPressed.png")
        );

        searchButton.setOnMouseReleased(
                event -> {
                    searchButton.changeButtonImage("../images/removeNodeButton.png");
                    ((RemoveNodePresenter)presenter).clickRemoveNodeButton();
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
            searchTextHBox.getChildren().set(0, predictor);
        }
        else {
            Platform.runLater(() -> searchTextHBox.getChildren().set(0, predictor));
        }
    }
    public String getSearchText() { return predictor.getText();}

    public void showError(String s) {
        errorLabel.setText(s);
    }
}
