package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import presentation.FavoriteTopicsPresenter;

import java.util.ArrayList;

public class FavoriteTopicsView extends ListView {

    private ArrayList<ImageButton> deleteButtons;
    private ArrayList<Label> relevance;
    private ImageButton addFavorites;
    private Label relevanceLabel;

    public FavoriteTopicsView(FavoriteTopicsPresenter favoriteTopicsPresenter) {
        presenter = favoriteTopicsPresenter;
        initializePanes();
        initializeViews();
        completePanes();
        setListeners();
        topBarPane.setCenter(contentVBox);
    }

    @Override
    public void initializePanes() {
        contentVBox = new VBox();
        contentVBox.setAlignment(Pos.CENTER);
        contentVBox.setFillWidth(false);

        titlesHBox = new HBox();

        results = new ArrayList<>(Config.LISTS_SIZE);
        for (int i = 0 ; i < Config.LISTS_SIZE; ++i) {
            results.add(new HBox());
            results.get(i).setAlignment(Pos.CENTER);
            results.get(i).setStyle("-fx-border-color: #000000;" +
                                    "-fx-border-width: 0.4;");
        }
        pagingButtonsHbox = new HBox();
        pagingButtonsHbox.setPadding(new Insets(5, 0, 0, 0));
        pagingButtonsHbox.setAlignment(Pos.CENTER);
        pagingButtonsHbox.setSpacing(10);
    }

    @Override
    protected void buildPanes() {
        Label aux = new Label();
        aux.setMinSize(50, 24);
        aux.setMaxSize(50, 24);
        Label aux2 = new Label();
        aux2.setMinSize(20, 24);
        aux2.setMaxSize(20, 24);
        titlesHBox.getChildren().addAll(
                aux,
                nameLabel,
                idLabel,
                relevanceLabel,
                aux2
        );

        pagingButtonsHbox.getChildren().add(prevPageButton);
        pagingButtonsHbox.getChildren().add(nextPageButton);

        separacionSuperioPane.setMinSize(800, 1);
        separacionSuperioPane.setMaxSize(800, 1);
        separacionSuperioPane.setStyle("-fx-background-color: #ffffff");

        separacionInferiorPane.setMinSize(800, 1);
        separacionInferiorPane.setMaxSize(800, 1);
        separacionInferiorPane.setStyle("-fx-background-color: #ffffff");


        contentVBox.getChildren().add(addFavorites);
        contentVBox.getChildren().add(titlesHBox);
        contentVBox.getChildren().add(separacionSuperioPane);
        contentVBox.getChildren().addAll(results);
        contentVBox.getChildren().add(separacionInferiorPane);
        contentVBox.getChildren().add(pagingButtonsHbox);
    }

    @Override
    protected void initializeTitleLabels() {
        nameLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        nameLabel.setMinSize(500, 24);
        nameLabel.setMaxSize(500, 24);
        nameLabel.setFont(new Font("Arial bold", 24));

        idLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        idLabel.setMinSize(75, 24);
        idLabel.setMaxSize(75, 24);
        idLabel.setFont(new Font("Arial bold", 24));

        relevanceLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        relevanceLabel.setMaxSize(130, 24);
        relevanceLabel.setMinSize(130, 24);
        relevanceLabel.setFont(new Font("Arial bold", 24));
    }

    protected void initializeViews() {
        nameLabel = new Label("Name");
        idLabel = new Label("ID");
        relevanceLabel = new Label("Relevance");
        initializeTitleLabels();

        separacionSuperioPane = new Pane();

        numbers = new ArrayList<>(Config.LISTS_SIZE);
        names = new ArrayList<>(Config.LISTS_SIZE);
        ids = new ArrayList<>(Config.LISTS_SIZE);
        relevance = new ArrayList<>(Config.LISTS_SIZE);
        deleteButtons = new ArrayList<>(Config.LISTS_SIZE);
        initializeArrayLabel();

        separacionInferiorPane = new Pane();

        prevPageButton = new ImageButton("../images/", "prevButton", 60, 30);
        nextPageButton = new ImageButton("../images/", "nextButton", 60, 30);
        addFavorites = new ImageButton("../images", "addEdgeButton", 225, 50);

    }

    @Override
    protected void initializeArrayLabel() {
        Font font = Font.loadFont(getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 20);

        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            numbers.add(new Label());
            numbers.get(i).setMinSize(50, 24);
            numbers.get(i).setMaxSize(50, 24);
            numbers.get(i).setFont(font);
            numbers.get(i).setTextFill(Paint.valueOf("white"));

            names.add(new Label());
            names.get(i).setMinSize(500, 24);
            names.get(i).setMaxSize(500, 24);
            names.get(i).setFont(font);
            names.get(i).setTextFill(Paint.valueOf("white"));

            ids.add(new Label());
            ids.get(i).setMinSize(75, 24);
            ids.get(i).setMaxSize(75, 24);
            ids.get(i).setFont(font);
            ids.get(i).setTextFill(Paint.valueOf("white"));

            relevance.add(new Label());
            relevance.get(i).setMinSize(130, 24);
            relevance.get(i).setMaxSize(130, 24);
            relevance.get(i).setFont(font);
            relevance.get(i).setTextFill(Paint.valueOf("white"));

            ImageButton imageButton = new ImageButton("../images", "xButton", 20, 20);
            deleteButtons.add(imageButton);
        }
    }

    protected void setListeners() {
        super.setListeners();

        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            int lol = i;
            deleteButtons.get(i).setOnMousePressed(event -> deleteButtons.get(lol).press());
        }

        addFavorites.setOnMousePressed(event -> addFavorites.press());
        addFavorites.setOnMouseReleased(event -> {
            addFavorites.release();
            ((FavoriteTopicsPresenter)presenter).onClickAddTopicButton();
        });
    }

    @Override
    public void setContent(int index, String node) {
        int i = index++ % Config.LISTS_SIZE;
        String[] elements = node.split("\t");
        names.get(i).setText(elements[0]);
        ids.get(i).setText(elements[1]);
        relevance.get(i).setText(elements[2]);

        if (elements[0].length() == 0) {
            numbers.get(i).setText("");
            deleteButtons.get(i).setVisible(false);
        }
        else {
            numbers.get(i).setText(index + "");
            deleteButtons.get(i).setVisible(true);
            deleteButtons.get(i).setOnMouseReleased(event -> {
                deleteButtons.get(i).release();
                ((FavoriteTopicsPresenter)presenter).onClickRemoveTopicButton(i);
            });
        }
    }

    @Override
    protected void completePanes() {
        buildLine();
        buildPanes();
    }

    @Override
    protected void buildLine() {
        int i = 0;
        for (HBox line : results) {
            line.getChildren().addAll(
                   numbers.get(i),
                   names.get(i),
                   ids.get(i),
                   relevance.get(i),
                   deleteButtons.get(i)
            );
            ++i;
        }
    }

}

