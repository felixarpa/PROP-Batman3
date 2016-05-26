package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import presentation.RelevanceTypeSelectorPresenter;

import java.util.ArrayList;

public class RelevanceTypeSelectorView extends ListResult {

    private ArrayList<ImageButton> entityButtons;
    private ArrayList<ImageButton> relationshipButtons;

    public RelevanceTypeSelectorView(RelevanceTypeSelectorPresenter relevanceTypeSelectorPresenter) {
        super(relevanceTypeSelectorPresenter);
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

        entityButtons = new ArrayList<>(Config.LISTS_SIZE);
        relationshipButtons = new ArrayList<>(Config.LISTS_SIZE);

        for (int i = 0; i < 10; ++i) {
            entityButtons.add(new ImageButton("../images", "entityRelevanceButton", 140, 24));
            relationshipButtons.add(new ImageButton("../images", "relationshipRelevanceButton", 140, 24));
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

    protected void buildLine() {
        int i = 0;
        for (HBox line : results) {
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
        int i = index++ % Config.LISTS_SIZE;
        String[] elements = node.split("\t");
        names.get(i).setText(elements[0]);
        ids.get(i).setText(elements[1]);

        if (elements[0].equals("")) {
            numbers.get(i).setText("");

            entityButtons.get(i).setDisable(true);
            relationshipButtons.get(i).setDisable(true);

            entityButtons.get(i).setVisible(false);
            relationshipButtons.get(i).setVisible(false);
        }
        else {
            numbers.get(i).setText(index + "");

            entityButtons.get(i).setDisable(false);
            relationshipButtons.get(i).setDisable(false);

            entityButtons.get(i).setVisible(true);
            relationshipButtons.get(i).setVisible(true);
        }
    }
}
