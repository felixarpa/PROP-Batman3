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
    private ArrayList<Label> names2;

    private Label relevanceLabel;
    private Label name2Label;

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
        relevanceLabel.setMinSize(125, 20);
        relevanceLabel.setMaxSize(125, 24);
        relevanceLabel.setFont(new Font("Arial bold", 24));

        name2Label = new Label("name2");
        name2Label.setTextFill(Config.LABEL_CLEAR_COLOR);
        name2Label.setMinSize(380, 20);
        name2Label.setMaxSize(380, 24);
        name2Label.setFont(new Font("Arial bold", 24));

        relevance = new ArrayList<>(Config.LISTS_SIZE);
        names2 = new ArrayList<>(Config.LISTS_SIZE);

        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            relevance.add(new ProgressBar());
            relevance.get(i).setMinSize(125, 20);
            relevance.get(i).setMaxSize(125, 24);

            names2.add(new Label());
            names2.get(i).setMinSize(380, 24);
            names2.get(i).setMaxSize(380, 24);
        }
    }

    public void setContent(int index, String nodeSrc, String nodeDst, double weight) {
        int i = index % Config.LISTS_SIZE;
        String[] srcElements = nodeSrc.split("\t");
        String[] dstElements = nodeDst.split("\t");
        names.get(i).setText(srcElements[0]);
        names2.get(i).setText(dstElements[0]);
        relevance.get(i).setProgress(weight);
    }

    @Override
    protected void completePanes() {
        buildLine();
        buildPanes();

        titlesHBox.getChildren().clear();
        titlesHBox.getChildren().addAll(
                nameLabel,
                relevanceLabel,
                name2Label
        );
    }

    @Override
    protected void buildLine() {
        int i = 0;
        for (HBox line : results) {
            line.getChildren().addAll(
                    numbers.get(i),
                    names.get(i),
                    relevance.get(i),
                    names2.get(i)
            );
            ++i;
        }
    }

    public void setNode(String nodeSrc, String nodeDst, double weight) {
        String[] srcElements = nodeSrc.split("\t");
        String[] dstElements = nodeDst.split("\t");
        nameLabel.setText(srcElements[0]);
        name2Label.setText(dstElements[0]);
        relevanceLabel.setText(weight + "");
    }
}
