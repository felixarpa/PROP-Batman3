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

    /*
     * ascending =  1   ->      Ordre ascendent
     * ascending =  0   ->      Ordre descendent
     * ascending = -1   ->      L'ordre no depen d'aquest parametre
     */
    private int ascendingNames;
    private int ascendingIds;
    private int ascendingRelevance;



    public CategoryResultView(CategoryResultPresenter presenter) {
        this.presenter = presenter;

        ascendingNames = -1;
        ascendingIds = -1;
        ascendingRelevance = 0;

        initializePanes();
        initializeViews();
        setListeners();
        completePanes();
        topBarPane.setCenter(contentVBox);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        relevanceLabel = new Label("Relevance");
        relevanceLabel.setTextFill(Config.LABEL_CLEAR_COLOR);

        relevanceLabel.setMinSize(200, 20);
        relevanceLabel.setMaxSize(200, 24);
        relevanceLabel.setFont(new Font("Arial bold", 24));


        relevance = new ArrayList<>(Config.LISTS_SIZE);

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
        setCorrectOrder(ascendingNames, nameLabel, "Name");
        setCorrectOrder(ascendingIds, idLabel, "ID");
        setCorrectOrder(ascendingRelevance, relevanceLabel, "Relevance");
    }

    private void setCorrectOrder(int orderStatus, Label label, String name) {
        switch (orderStatus) {
            case -1:
                label.setText(name + " ▬");
                break;

            case 0:
                label.setText(name + " ▼");
                break;

            case 1:
                label.setText(name + " ▲");
                break;
        }
    }

}
