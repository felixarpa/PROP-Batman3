package view;

import javafx.scene.layout.HBox;
import presentation.RelevanceTypeSelectorPresenter;
import view.auxiliarViews.Config;
import view.auxiliarViews.ImageButton;
import view.auxiliarViews.ListView;

import java.util.ArrayList;

public class RelevanceTypeSelectorView extends ListView {

    private ArrayList<ImageButton> entityButtons;
    private ArrayList<ImageButton> relationshipButtons;

    public RelevanceTypeSelectorView(RelevanceTypeSelectorPresenter relevanceTypeSelectorPresenter) {
        presenter = relevanceTypeSelectorPresenter;

        initializePanes();
        initializeViews();
        setListeners();
        completePanes();
        topBarPane.setCenter(contentVBox);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        nameLabel.setMinSize(360, 20);
        nameLabel.setMaxSize(360, 24);

        entityButtons = new ArrayList<>(Config.LISTS_SIZE);
        relationshipButtons = new ArrayList<>(Config.LISTS_SIZE);

        for (int i = 0; i < 10; ++i) {
            names.get(i).setMinSize(360, 24);
            names.get(i).setMaxSize(360, 24);
            entityButtons.add(new ImageButton("entityRelevanceButton", 140, 24));
            relationshipButtons.add(new ImageButton("relationshipRelevanceButton", 140, 24));
        }
    }

    protected void setListeners() {
        super.setListeners();

        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            final int index = i;
            entityButtons.get(i).setOnMousePressed(
                event -> entityButtons.get(index).press()
            );
            entityButtons.get(i).setOnMouseReleased(
                event -> {
                    entityButtons.get(index).release();
                    ((RelevanceTypeSelectorPresenter) presenter).onClickEntityRelevance(index);
                }
            );

            relationshipButtons.get(i).setOnMousePressed(
                event -> relationshipButtons.get(index).press()
            );
            relationshipButtons.get(i).setOnMouseReleased(
                event -> {
                    relationshipButtons.get(index).release();
                    ((RelevanceTypeSelectorPresenter) presenter).onClickRelationshipRelevance(index);
                }
            );
        }
    }

    protected void completePanes() {
        buildLine();
        buildPanes();
    }

    @Override
    protected void buildLine() {
        int i = 0;
        for (HBox line : lineHBox) {
            line.getChildren().addAll(
                    numbers.get(i),
                    names.get(i),
                    ids.get(i),
                    entityButtons.get(i),
                    relationshipButtons.get(i)
            );
            ++i;
        }
    }

    @Override
    public void setContent(int index, String node) {
        super.setContent(index, node);

        int i = index % Config.LISTS_SIZE;
        String[] elements = node.split("\t");

        if (elements[0].equals("")) {
            entityButtons.get(i).setDisable(true);
            relationshipButtons.get(i).setDisable(true);

            entityButtons.get(i).setVisible(false);
            relationshipButtons.get(i).setVisible(false);
        }
        else {
            entityButtons.get(i).setDisable(false);
            relationshipButtons.get(i).setDisable(false);

            entityButtons.get(i).setVisible(true);
            relationshipButtons.get(i).setVisible(true);
        }
    }
}
