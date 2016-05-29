package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import presentation.SimilarRelationRelevancePresenter;
import view.auxiliarViews.Config;
import view.auxiliarViews.ListView;

import java.util.ArrayList;

public class SimilarRelationRelevanceView extends ListView {

    private ArrayList<ProgressBar> relevance;
    private ArrayList<Label> names2;

    private ProgressBar relevanceProgressBar;
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

        nameLabel.setMinSize(310, 20);
        nameLabel.setMaxSize(310, 24);

        relevanceProgressBar = new ProgressBar();
        relevanceProgressBar.setMinSize(120, 20);
        relevanceProgressBar.setMaxSize(120, 24);
        relevanceProgressBar.setPadding(new Insets(0, 10, 0, 10));

        name2Label = new Label("name2");
        name2Label.setTextFill(Config.LABEL_CLEAR_COLOR);
        name2Label.setMinSize(310, 20);
        name2Label.setMaxSize(310, 24);
        name2Label.setFont(new Font("Arial bold", 24));

        relevance = new ArrayList<>(Config.LISTS_SIZE);
        names2 = new ArrayList<>(Config.LISTS_SIZE);

        Font font = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 20);

        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            relevance.add(new ProgressBar());
            relevance.get(i).setMinSize(120, 20);
            relevance.get(i).setMaxSize(120, 24);
            relevance.get(i).setPadding(new Insets(0, 10, 0, 10));

            names2.add(new Label());
            names2.get(i).setMinSize(310, 24);
            names2.get(i).setMaxSize(310, 24);
            names2.get(i).setFont(font);
            names2.get(i).setTextFill(Paint.valueOf("white"));

            names.get(i).setMinSize(310, 24);
            names.get(i).setMaxSize(310, 24);
        }
    }

    public void setContent(int index, String nodeSrc, String nodeDst, double weight) {
        int i = index++ % Config.LISTS_SIZE;
        String[] srcElements = nodeSrc.split("\t");
        String[] dstElements = nodeDst.split("\t");

        numbers.get(i).setText(index + "");
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
                relevanceProgressBar,
                name2Label
        );
    }

    @Override
    protected void buildLine() {
        int i = 0;
        for (HBox line : lineHBox) {
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
        relevanceProgressBar.setProgress(weight);
    }
}
