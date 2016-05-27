package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presentation.FavoriteTopicsPresenter;

import java.util.ArrayList;

public class FavoriteTopicsView extends ListView {

    private ArrayList<ImageButton> deleteButtons;
    private ArrayList<Label> relevance;
    private ImageButton addFavorites;

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
        contentVBox.setPadding(new Insets(5, 40, 5, 40));
        //contentVBox.setSpacing(4);

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

    @Override
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

        contentVBox.getChildren().add(addFavorites);
        contentVBox.getChildren().add(titlesHBox);
        contentVBox.getChildren().add(separacionSuperioPane);
        contentVBox.getChildren().addAll(results);
        contentVBox.getChildren().add(separacionInferiorPane);
        contentVBox.getChildren().add(pagingButtonsHbox);
    }

    protected void initializeViews(){
        super.initializeViews();
        deleteButtons = new ArrayList<>(Config.LISTS_SIZE);
        relevance = new ArrayList<>(Config.LISTS_SIZE);
        addFavorites = new ImageButton("../images", "addEdgeButton", 225, 50);

        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            Label label = new Label();
            relevance.add(label);

            ImageButton imageButton = new ImageButton("../images", "xButton", 20, 20);
            deleteButtons.add(imageButton);
        }
    }

    protected void setListeners() {
        super.setListeners();

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

        if (elements[0].equals("")) numbers.get(i).setText("");
        else numbers.get(i).setText(index + "");
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
                    relevance.get(i)
            );
            ++i;
        }
    }

}

