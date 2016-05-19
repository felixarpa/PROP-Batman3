package view;

import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import presentation.MainPresenter;

public class MainView extends BaseView {

    private BorderPane basePane;
    private VBox contentVBox;

    private EditText searchEditText;

    private ImageButton searchButton;

    private MainPresenter mainPresenter;

    public MainView(MainPresenter mainPresenter) {
        super(mainPresenter);
        this.mainPresenter = mainPresenter;
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
    }

    public void destroy() {
        mainPresenter = null;
    }

    private void initializePanes() {
        basePane = new BorderPane();
        basePane.setPadding(new Insets(133, 292, 0, 292));

        contentVBox = new VBox();

        searchEditText = new EditText("Search");
    }

    private void initializeViews() {
        searchButton = new ImageButton("", 1, 1);

        HBox.setHgrow(searchButton, Priority.ALWAYS);
    }

    private void buildPanes() {
        contentVBox.getChildren().add(searchEditText);
        contentVBox.getChildren().add(searchButton);
        contentVBox.setAlignment(Pos.CENTER);
    }

    private void setListeners() {

        searchButton.setOnMousePressed(
            new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event) {
                   // TODO change image
               }
            }
        );

        searchButton.setOnMouseReleased(
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    // TODO change image
                    mainPresenter.search();
                }
            }
        );
    }

    @Override
    public Pane getContent() {
        BorderPane baseViewBasePane = (BorderPane) super.getContent();
        baseViewBasePane.setCenter(basePane);
        return baseViewBasePane;
    }


}
