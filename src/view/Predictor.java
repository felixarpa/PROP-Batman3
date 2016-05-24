package view;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

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

    private int lastLenght;

    public Predictor(Collection<String> data, int resultsToShow) {
        this.resultsToShow = resultsToShow;
        this.data = data;
        textToPredict = new TextField();
        resultStack = new Stack<>();
        resultBox = new VBox();
        lastLenght = 0;

        for (int i = 0; i < resultsToShow; ++i) {
            resultBox.getChildren().add(new Label());
        }

        getChildren().add(textToPredict);
        getChildren().add(resultBox);
        textToPredict.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                int lenght = textToPredict.getText().length();
                if (lenght > lastLenght) {
                    nextStep();
                }
                else if (lenght < lastLenght) {
                    previousStep();
                }
                lastLenght = lenght;
            }
        });

    }

    private void nextStep() {
        LinkedList<SearchString> result = new LinkedList<>();
        Label predicted;
        int count = 0;
        if (resultStack.isEmpty()) {
            for (String word : data) {
                if (word.contains(textToPredict.getText())) {
                    if (count < resultsToShow) {
                        ((Label)resultBox.getChildren().get(count)).setText(word);
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
                        ((Label)resultBox.getChildren().get(count)).setText(word);
                        ++count;
                    }
                    result.add(new SearchString(word, 0));
                }
            }
        }

        for (; count < resultsToShow; ++count) {
            ((Label)resultBox.getChildren().get(count)).setText("");
        }
        resultStack.push(result);
    }

    private void previousStep() {
        if (resultStack.size() <= 1) {
            for (int count = 0; count < resultsToShow; ++count) {
                ((Label)resultBox.getChildren().get(count)).setText("");
            }
            return;
        }
        resultStack.pop();
        LinkedList<SearchString> previousResult = resultStack.peek();
        ListIterator<SearchString> it = previousResult.listIterator();
        int count = 0;
        for (; count < resultsToShow && it.hasNext(); ++count) {
            ((Label)resultBox.getChildren().get(count)).setText(it.next().getString());
        }

        for (; count < resultsToShow; ++count) {
            ((Label)resultBox.getChildren().get(count)).setText("");
        }
    }


}
