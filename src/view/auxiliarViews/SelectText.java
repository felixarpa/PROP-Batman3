package view.auxiliarViews;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SelectText extends HBox {

    private Text text;

    public SelectText(String text) {
        this.text = new Text(text);
        getChildren().add(this.text);
    }

    public SelectText(String text, Font font) {
        this.text = new Text(text);
        this.text.setFont(font);
        getChildren().add(this.text);
        setStyle("-fx-background-color: transparent");
        this.text.setFill(Paint.valueOf("black"));
    }

    public void setTextFill(Paint value) {
        text.setFill(value);
    }

    public void setFont(Font font) {
        text.setFont(font);
    }

    public void press() {
        setStyle("-fx-background-color: #000000");
        text.setFill(Paint.valueOf("white"));
    }

    public void release() {
        setStyle("-fx-background-color: transparent");
        text.setFill(Paint.valueOf("black"));
    }

}
