package view;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import presentation.SimilarRelationRelevancePresenter;
import view.auxiliarViews.Config;
import view.auxiliarViews.ListView;

import java.util.ArrayList;

public class SimilarRelationRelevanceView extends ListView {

    private ArrayList<ProgressBar> relevance;

    private Label relevanceLabel;


    public SimilarRelationRelevanceView(SimilarRelationRelevancePresenter presenter) {
        this.presenter = presenter;
        initializePanes();
        initializeViews();
        setListeners();
        completePanes();
        topBarPane.setCenter(contentVBox);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        relevanceLabel = new Label("relevance");
        relevanceLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        relevanceLabel.setMinSize(200, 20);
        relevanceLabel.setMaxSize(200, 24);
        relevanceLabel.setFont(new Font("Arial bold", 24));

        relevance = new ArrayList<>(Config.LISTS_SIZE);

        for (int i = 0; i < 10; ++i) {
            relevance.add(new ProgressBar());
            relevance.get(i).setMinSize(120, 20);
            relevance.get(i).setMaxSize(120, 24);

            ids.get(i).setMinSize(100, 24);
            ids.get(i).setMaxSize(100, 24);
        }

        idLabel.setMinSize(100, 20);
        idLabel.setMaxSize(100, 24);
    }

    @Override
    public void setContent(int index, String node) {
        super.setContent(index, node);

        int i = index % Config.LISTS_SIZE;
        String[] elements = node.split("\t");
//        relevance.get(i).setText(elements[2]);
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

    public void setNode(String node) {
        String[] elements = node.split("\t");
        nameLabel.setText(elements[0]);
        idLabel.setText(elements[1]);
//        relevanceLabel.setText(elements[2]);
    }
}
