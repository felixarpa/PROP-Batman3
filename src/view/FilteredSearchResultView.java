package view;


import javafx.geometry.*;
import javafx.scene.Node;
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

    private Label authorText;
    private Label conferenceText;
    private Label paperText;
    private Label termText;

    public final int numToShow = 10;


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
        for (int i = 0; i < 4; ++i) {
            ArrayList<HBox> aux = new ArrayList<>(numToShow);
            for (int j = 0; j < numToShow; ++j) {
                aux.add(new HBox());
            }
            results.add(aux);
        }
    }

    private void initializeViews() {
        authorText = new Label("RELATED AUTHORS");
        conferenceText = new Label("RELATED CONFERENCES");
        paperText = new Label("RELATED PAPERS");
        termText = new Label("RELATED TERMS");
    }

    private void buildPanes() {
        for (int i = 0; i < 1; ++i) {
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
            /*for (int j = 0; j < 10; ++j) {
                Label label = new Label(Integer.toString(j));
                HBox aux = new HBox();
                aux.getChildren().add(label);
                aux.setAlignment(Pos.CENTER);
                vaux.getChildren().add(aux);
            }*/
            for (Node n : vaux.getChildren()) {
                System.out.println(n);
            }
            contents.add(vaux);
        }
        contentVBox.getChildren().add(contents.get(0));
    }

    private void setListeners() {
    }

    public void setContent(int index, String node, int type) {
        System.out.println(node);
        String[] elements = node.split("\t");
        Label number = new Label(Integer.toString(index+1));
        Label name = new Label(elements[0]);
        Label id = new Label(elements[1]);
        Label relevance =  new Label(elements[2]);
        Label label = new Label("");
        if (elements.length == 5) {
            label = new Label(elements[3]);
        }
        HBox aux = new HBox();
        aux.getChildren().add(number);
        aux.getChildren().add(name);
        aux.getChildren().add(label);
        aux.getChildren().add(id);
        aux.getChildren().add(relevance);
        aux.getChildren().add(new Label("dafsdf"));
        results.get(type).set(index,aux);
    }

}
