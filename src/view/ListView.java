package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import presentation.BasePresenter;
import presentation.ListPresenter;
import presentation.Presenter;
import presentation.RelevanceTypeSelectorPresenter;

import java.util.ArrayList;

public abstract class ListView extends BaseView {

    protected VBox contentVBox;

    protected HBox titlesHBox;
    protected Label nameLabel;
    protected Label idLabel;

    protected Pane separacionSuperioPane;

    protected ArrayList<HBox> results;
    protected ArrayList<Label> numbers;
    protected ArrayList<Label> names;
    protected ArrayList<Label> ids;

    protected Pane separacionInferiorPane;

    protected HBox pagingButtonsHbox;
    protected ImageButton nextPageButton;
    protected ImageButton prevPageButton;

    //public ListView(Presenter viewPresenter) {
    //    presenter = (BasePresenter) viewPresenter;
    //}

    public ListView() {}

    protected void initializePanes() {
        contentVBox = new VBox();
        contentVBox.setPadding(new Insets(5, 40, 5, 40));
        contentVBox.setSpacing(4);

        titlesHBox = new HBox();
        titlesHBox.setPadding(new Insets(0, 10, 0, 60));

        results = new ArrayList<>(Config.LISTS_SIZE);
        for (int i = 0 ; i < Config.LISTS_SIZE; ++i) {
            results.add(new HBox());
            results.get(i).setPadding(new Insets(0, 10, 0, 10));
        }
        pagingButtonsHbox = new HBox();
        pagingButtonsHbox.setPadding(new Insets(0, 0, 0, 345));
        pagingButtonsHbox.setSpacing(10);

    }

    protected void initializeViews() {
        nameLabel = new Label("Name");
        idLabel = new Label("ID");
        initializeTitleLabels();

        separacionSuperioPane = new Pane();

        numbers = new ArrayList<>(Config.LISTS_SIZE);
        names = new ArrayList<>(Config.LISTS_SIZE);
        ids = new ArrayList<>(Config.LISTS_SIZE);
        initializeArrayLabel();

        separacionInferiorPane = new Pane();

        prevPageButton = new ImageButton("../images/", "prevButton", 60, 30);
        nextPageButton = new ImageButton("../images/", "nextButton", 60, 30);
    }

    protected void buildPanes() {
        titlesHBox.getChildren().addAll(
                nameLabel,
                idLabel
        );

        pagingButtonsHbox.getChildren().add(prevPageButton);
        pagingButtonsHbox.getChildren().add(nextPageButton);

        separacionSuperioPane.setMinSize(800, 1);
        separacionSuperioPane.setMaxSize(800, 1);
        separacionSuperioPane.setStyle("-fx-background-color: #ffffff");

        separacionInferiorPane.setMinSize(800, 1);
        separacionInferiorPane.setMaxSize(800, 1);
        separacionInferiorPane.setStyle("-fx-background-color: #ffffff");

        contentVBox.getChildren().add(titlesHBox);
        contentVBox.getChildren().add(separacionSuperioPane);
        contentVBox.getChildren().addAll(results);
        contentVBox.getChildren().add(separacionInferiorPane);
        contentVBox.getChildren().add(pagingButtonsHbox);

    }

    protected void setListeners() {

        prevPageButton.setOnMousePressed(event -> prevPageButton.press());
        prevPageButton.setOnMouseReleased(
                event -> {
                    prevPageButton.release();
                    ((ListPresenter) presenter).showLess();
                }
        );

        nextPageButton.setOnMousePressed(event -> nextPageButton.press());
        nextPageButton.setOnMouseReleased(
                event -> {
                    nextPageButton.release();
                    ((ListPresenter) presenter).showMore();
                }
        );
    }

    public void setContent(int index, String node) {
        int i = index++ % Config.LISTS_SIZE;
        String[] elements = node.split("\t");
        names.get(i).setText(elements[0]);
        ids.get(i).setText(elements[1]);

        if (elements[0].equals("")) numbers.get(i).setText("");
        else numbers.get(i).setText(index + "");
    }

    protected void initializeArrayLabel() {
        Font font = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 20);

        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            numbers.add(new Label());
            numbers.get(i).setMinSize(50, 24);
            numbers.get(i).setMaxSize(50, 24);
            numbers.get(i).setFont(font);
            numbers.get(i).setTextFill(Paint.valueOf("white"));

            names.add(new Label());
            names.get(i).setMinSize(380, 24);
            names.get(i).setMaxSize(380, 24);
            names.get(i).setFont(font);
            names.get(i).setTextFill(Paint.valueOf("white"));

            ids.add(new Label());
            ids.get(i).setMinSize(75, 24);
            ids.get(i).setMaxSize(75, 24);
            ids.get(i).setFont(font);
            ids.get(i).setTextFill(Paint.valueOf("white"));
        }
    }

    protected void initializeTitleLabels() {
        nameLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        nameLabel.setMinSize(380, 20);
        nameLabel.setMaxSize(380, 24);
        nameLabel.setFont(new Font("Arial bold", 24));

        idLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        idLabel.setMinSize(75, 20);
        idLabel.setMaxSize(75, 24);
        idLabel.setFont(new Font("Arial bold", 24));
    }

    protected abstract void completePanes();

    protected abstract void buildLine();

}
