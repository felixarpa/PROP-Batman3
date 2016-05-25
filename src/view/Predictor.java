package view;

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

    private Stack<LinkedList<SearchString>>  resultStack;

    private int lastLength;
    private int selected;

    public Predictor(Collection<String> data, int resultsToShow) {
        this.resultsToShow = resultsToShow;
        this.data = data;
        textToPredict = new TextField();
        resultStack = new Stack<>();
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
                }
                else if (length != lastLength) {
                    slowStep();
                }
                lastLength = length;
            }
        });

    }

    private void slowStep() {
        int count = 0;
        selected = -1;
        resultBox.getChildren().remove(0,resultBox.getChildren().size());
        for (String word : data) {
            if (word.contains(textToPredict.getText())) {
                if (count < resultsToShow) {
                    Text predicted = new Text(word);
                    resultBox.getChildren().add(predicted);
                    ++count;
                }
            }
        }
    }

    private void nextStep() {
        selected = -1;
        LinkedList<SearchString> result = new LinkedList<>();
        Text predicted;
        resultBox.getChildren().remove(0,resultBox.getChildren().size());
        int count = 0;
        if (resultStack.isEmpty()) {
            for (String word : data) {
                if (word.contains(textToPredict.getText())) {
                    if (count < resultsToShow) {
                        predicted = new Text(word);
                        resultBox.getChildren().add(predicted);
                        ++count;
                    }
                    result.add(new SearchString(word, 0));
                }
            }
        }
        else {
            LinkedList<SearchString> lastResult = resultStack.peek();
            for (SearchString searchString : lastResult) {
                int index = searchString.getIndex();
                String word = searchString.getString();
                if (word.contains(textToPredict.getText())) {
                    if (count < resultsToShow) {
                        predicted = new Text(word);
                        resultBox.getChildren().add(predicted);
                        ++count;
                    }
                    result.add(new SearchString(word, 0));
                }
            }
        }

        /*for (; count < resultsToShow; ++count) {
            ((Text)resultBox.getChildren().get(count)).setText("");
        }*/
        resultStack.push(result);
    }

    private void previousStep() {
        selected = -1;
        resultBox.getChildren().remove(0,resultBox.getChildren().size());
        if (resultStack.size() <= 1) {
            /*for (int count = 0; count < resultsToShow; ++count) {
                ((Text)resultBox.getChildren().get(count)).setText("");
            }*/
            return;
        }
        resultStack.pop();
        LinkedList<SearchString> previousResult = resultStack.peek();
        ListIterator<SearchString> it = previousResult.listIterator();
        int count = 0;
        for (; count < resultsToShow && it.hasNext(); ++count) {
            Text predicted = new Text(it.next().getString());
            resultBox.getChildren().add(predicted);
        }

        /*for (; count < resultsToShow; ++count) {
            ((Text)resultBox.getChildren().get(count)).setText("");
        }*/
    }


}
