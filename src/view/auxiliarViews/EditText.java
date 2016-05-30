package view.auxiliarViews;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class EditText extends Pane {

    private HBox hBox;
    private Label label;
    private TextField textField;

    public EditText(String name) {
        hBox = new HBox();
        label = new Label(name);
        textField = new TextField();
        build();
    }

    private void build() {
        label.setTextFill(Config.LABEL_DARK_COLOR);
        hBox.setSpacing(4);
        hBox.getChildren().add(label);
        hBox.getChildren().add(textField);
        hBox.setAlignment(Pos.CENTER);
    }

    public void setName(String name) {
        label.setText(name);
    }

    public void setFont(Font font){ label.setFont(font);}

    public void setMinSize(int width, int height) {
        textField.setMinSize(width, height);
    }

    public void setMaxSize(int width, int height) {
        textField.setMaxSize(width, height);
    }

    public Pane getBase() {
        return hBox;
    }

    public String getText() {
        return textField.getText();
    }

}
