package view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import presentation.CategoryResultPresenter;
import util.ProjectConstants;

import java.util.ArrayList;

public class CategoryResultView extends ListView {

    private ArrayList<Label> relevance;

    private Label relevanceLabel;

    private ImageButton orderNamesImageButton;
    private ImageButton orderIdsImageButton;
    private ImageButton orderRelevanceImageButton;

    private int ascendingNames;
    private int ascendingIds;
    private int ascendingRelevance;

//    private Pane spaceNamesIds;
//    private Pane spaceIdsRelevance;


    public CategoryResultView(CategoryResultPresenter presenter) {
        this.presenter = presenter;

        ascendingNames = -1;
        ascendingIds = -1;
        ascendingRelevance = 1;

        initializePanes();
        initializeViews();
        setListeners();
        completePanes();
        topBarPane.setCenter(contentVBox);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        relevanceLabel = new Label("Relevance ▼");
        relevanceLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
//        relevanceLabel.setMinSize(130, 20);
//        relevanceLabel.setMaxSize(130, 24);
        relevanceLabel.setMinSize(200, 20);
        relevanceLabel.setMaxSize(200, 24);
        relevanceLabel.setFont(new Font("Arial bold", 24));

//        nameLabel.setMinSize(72, 20);
//        nameLabel.setMaxSize(72, 24);
//
//        idLabel.setMinSize(30, 20);
//        idLabel.setMaxSize(30, 24);

        relevance = new ArrayList<>(Config.LISTS_SIZE);

//        orderNamesImageButton = new ImageButton("../images/ascending.png", 20, 20); // ▲ & ▼
//        orderIdsImageButton = new ImageButton("../images/ascending.png", 20, 20);
//        orderRelevanceImageButton = new ImageButton("../images/descending.png", 20, 20);

//        spaceNamesIds = new Pane();
//        spaceNamesIds.setMinSize(288, 20);
//        spaceNamesIds.setMaxSize(288, 20);
//
//        spaceIdsRelevance = new Pane();
//        spaceIdsRelevance.setMinSize(50, 20);
//        spaceIdsRelevance.setMinSize(50, 20);


        setCorrectOrder();

        Font font = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 18);

        for (int i = 0; i < 10; ++i) {
            relevance.add(new Label());
            relevance.get(i).setMinSize(100, 20);
            relevance.get(i).setMaxSize(100, 24);
            relevance.get(i).setFont(font);
            relevance.get(i).setTextFill(Paint.valueOf("white"));

            ids.get(i).setMinSize(100, 24); //100
            ids.get(i).setMaxSize(100, 24);
        }
    }

    @Override
    public void setContent(int index, String node) {
        super.setContent(index, node);

        int i = index++ % Config.LISTS_SIZE;
        String[] elements = node.split("\t");
        relevance.get(i).setText(elements[2]);
    }

    @Override
    protected void completePanes() {
        buildLine();
        buildPanes();

//        titlesHBox.getChildren().remove(0, titlesHBox.getChildren().size());
//        titlesHBox.getChildren().addAll(
//                nameLabel,
//                orderNamesImageButton,
//                spaceNamesIds,
//                idLabel,
//                orderIdsImageButton,
//                spaceIdsRelevance,
//                relevanceLabel,
//                orderRelevanceImageButton
//        );
        titlesHBox.getChildren().add(relevanceLabel);
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

    @Override
    protected void setListeners() {
        super.setListeners();

        nameLabel.setOnMouseReleased(
                event -> {
                    ascendingIds = -1;
                    ascendingRelevance = -1;
                    ((CategoryResultPresenter) presenter).reorder(
                            ProjectConstants.NAME_ORDER,
                            (ascendingNames != 1)
                    );
                    ascendingNames = (ascendingNames != 1) ? 1 : 0;
                    setCorrectOrder();
                }
        );

        idLabel.setOnMouseReleased(
                event -> {
                    ascendingNames = -1;
                    ascendingRelevance = -1;
                    ((CategoryResultPresenter) presenter).reorder(
                            ProjectConstants.ID_ORDER,
                            (ascendingIds != 1)
                    );
                    ascendingIds = (ascendingIds != 1) ? 1 : 0;
                    setCorrectOrder();
                }
        );

        relevanceLabel.setOnMouseReleased(
                event -> {
                    ascendingNames = -1;
                    ascendingIds = -1;
                    ((CategoryResultPresenter) presenter).reorder(
                            ProjectConstants.RELEVANCE_ORDER,
                            (ascendingRelevance != 1)
                    );
                    ascendingRelevance = (ascendingRelevance != 1) ? 1 : 0;
                    setCorrectOrder();
                }
        );
    }

    public void askSimilarOp() {

    }

    private void setCorrectOrder() {
//        setCorrectOrder(ascendingNames, orderNamesImageButton, spaceNamesIds);
//        setCorrectOrder(ascendingIds, orderIdsImageButton, spaceIdsRelevance);
//        setCorrectOrder(ascendingRelevance, orderRelevanceImageButton, null);
        setCorrectOrder2(ascendingNames, nameLabel, "Name");
        setCorrectOrder2(ascendingIds, idLabel, "ID");
        setCorrectOrder2(ascendingRelevance, relevanceLabel, "Relevance");
    }

    private void setCorrectOrder2(int orderStatus, Label label, String name) {
        switch (orderStatus) {
            case -1:
                label.setText(name);
                break;

            case 0:
                label.setText(name + " ▼");
                break;

            case 1:
                label.setText(name + " ▲");
                break;
        }
    }

    private void setCorrectOrder(int orderStatus, ImageButton imageButton, Pane space) {
        switch (orderStatus) {
            case -1:
                imageButton.setVisible(false);
                imageButton.setDisable(true);

                if (space != null) {
                    space.setVisible(false);
                    space.setDisable(true);
                }

                break;

            case 0:
                imageButton.changeButtonImage("../images/descending.png");

                imageButton.setVisible(true);
                imageButton.setDisable(false);

                if (space != null) {
                    space.setVisible(true);
                    space.setDisable(false);
                }

                break;

            case 1:
                imageButton.changeButtonImage("../images/ascending.png");

                imageButton.setVisible(true);
                imageButton.setDisable(false);

                if (space != null) {
                    space.setVisible(true);
                    space.setDisable(false);
                }

                break;
        }
    }

}
