package view;


import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import presentation.FilteredRelevanceResultPresenter;
import presentation.FilteredSearchResultPresenter;
import util.ProjectConstants;

import java.util.ArrayList;

public class FilteredSearchResultView extends BaseView {


    private VBox contentVBox;
    private ArrayList<VBox> contents;

    private ArrayList<ArrayList<HBox>> results;
    private ArrayList<ArrayList<Label>> numbers;
    private ArrayList<ArrayList<Label>> names;
    private ArrayList<ArrayList<Label>> labels;
    private ArrayList<ArrayList<Label>> ids;

    private Label authorText;
    private Label conferenceText;
    private Label paperText;
    private Label termText;


    public FilteredSearchResultView(FilteredSearchResultPresenter filteredSearchResultPresenter) {
        presenter = filteredSearchResultPresenter;
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
        topBarPane.setCenter(contentVBox);

    }

    private void initializePanes() {
        contentVBox = new VBox();
        contents = new ArrayList<>(4);
        results = new ArrayList<>(4);
    }

    private void initializeViews() {
        authorText = new Label("AUTHOR");
        conferenceText = new Label("CONFERENCE");
        paperText = new Label("PAPER");
        termText = new Label("TERM");
        numbers = new ArrayList<>(4);
        names = new ArrayList<>(4);
        labels = new ArrayList<>(4);
        ids = new ArrayList<>(4);
    }

    private void buildPanes() {
        for (int i = 0; i < 4; ++i) {
            VBox vaux = new VBox();
            HBox haux = new HBox();
            switch (i) {
                case ProjectConstants.AUTHOR_TYPE:
                    haux.getChildren().add(authorText);
                    break;
                case ProjectConstants.CONFERENCE_TYPE:
                    haux.getChildren().add(conferenceText);
                    break;
                case ProjectConstants.PAPER_TYPE:
                    haux.getChildren().add(paperText);
                    break;
                case ProjectConstants.TERM_TYPE:
                    haux.getChildren().add(termText);
                    break;
            }
            vaux.getChildren().add(haux);
            vaux.getChildren().addAll(results.get(i));
            vaux.setAlignment(Pos.CENTER);
            contents.add(vaux);
            contentVBox.getChildren().add(contents.get(i));
        }
        contentVBox.setAlignment(Pos.CENTER);


    }

    private void setListeners() {
    }

    public void setContent(int index, String node, int type) {
        String[] elements = node.split("\t");
        numbers.get(type).add(index, new Label(Integer.toString(index+1)));
        names.get(type).add(index, new Label(elements[0]));
        labels.get(type).add(index, new Label(elements[1]));
        ids.get(type).add(index, new Label(elements[2]));
        HBox aux = new HBox();
        aux.getChildren().add(numbers.get(type).get(index));
        aux.getChildren().add(names.get(type).get(index));
        aux.getChildren().add(labels.get(type).get(index));
        aux.getChildren().add(ids.get(type).get(index));
        results.get(type).add(index,aux);
    }

}
