package view;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.*;

public class Predictor extends VBox {

    private class SearchString {

        private String string;
        private int index;

        public SearchString(String string, int index) {
            this.string = string;
            this.index = index;
        }

        public String getString() {
            return string;
        }

        public int getIndex() {
            return index;
        }
    }

    private TextField textToPredict;
    private Collection<String> data;
    private VBox resultBox;
    private int resultsToShow;

    private int lastLength;
    private int selected;

    public Predictor(Collection<String> data, int resultsToShow) {
        this.resultsToShow = resultsToShow;
        this.data = data;
        textToPredict = new TextField();
        resultBox = new VBox();
        resultBox.setStyle("-fx-background-color: #FFFFFF;");
        lastLength = -1;
        selected = -1;
        getChildren().add(textToPredict);
        getChildren().add(resultBox);
        textToPredict.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.UP) {
                if (selected > 0) {
                    ((Text)resultBox.getChildren().get(selected)).setFill(Color.BLACK);
                    --selected;
                    ((Text)resultBox.getChildren().get(selected)).setFill(Color.GRAY);
                }
            }
            else if (event.getCode() == KeyCode.DOWN) {
                if (selected < resultBox.getChildren().size()-1) {
                    if (selected >= 0)((Text)resultBox.getChildren().get(selected)).setFill(Color.BLACK);
                    ++selected;
                    ((Text)resultBox.getChildren().get(selected)).setFill(Color.GRAY);
                }
            }
            else if (event.getCode() == KeyCode.ENTER) {
                if (selected >= 0 && selected < resultBox.getChildren().size()) {
                    textToPredict.setText(((Text)resultBox.getChildren().get(selected)).getText());
                    if (selected >= 0 && selected < resultBox.getChildren().size()) {
                        textToPredict.setText(((Text)resultBox.getChildren().get(selected)).getText());
                        resultBox.getChildren().remove(0,resultBox.getChildren().size());
                    }
                }
            }
            else {
                int length = textToPredict.getText().length();
                if (length == 0) {
                    resultBox.getChildren().remove(0, resultBox.getChildren().size());
                } else if (length != lastLength) {
                    slowStep();
                }
                lastLength = length;
                selected = -1;
            }
        });

    }

    private void slowStep() {
        int count = 0;
        resultBox.getChildren().remove(0,resultBox.getChildren().size());
        for (String word : data) {
            if (word.contains(textToPredict.getText())) {
                if (count < resultsToShow) {
                    Text predicted = new Text(word);
                    resultBox.getChildren().add(predicted);
                    ++count;
                }
                else return;
            }
        }
    }

}
