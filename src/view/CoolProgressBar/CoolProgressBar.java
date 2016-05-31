package view.CoolProgressBar;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 * Created by Fanz0 on 31/05/2016.
 */
public class CoolProgressBar extends StackPane {
    private Label progress;
    private ProgressBar progressBar;
    private Font font;

    public CoolProgressBar() {
        font = Font.loadFont(this.getClass().getResource("../../fonts/Nilland-Black.ttf").toExternalForm(), 12);
        progress = new Label();
        progress.setFont(font);
        progressBar = new ProgressBar();
        progressBar.setMinHeight(20);
        progressBar.setStyle("-fx-accent: green");
        getChildren().addAll(progressBar,progress);
    }

    public CoolProgressBar(Double valor) {
        font = Font.loadFont(this.getClass().getResource("../../fonts/Nilland-Black.ttf").toExternalForm(), 12);
        progressBar = new ProgressBar(valor);
        progressBar.setStyle("-fx-accent: green");
        progressBar.setMinHeight(20);
        progress = new Label(String.format("%.2f",valor*100) + "%");
        progress.setFont(font);
        getChildren().addAll(progressBar,progress);
    }

    public void setProgress(Double valor) {
        progressBar.setProgress(valor);
        progress.setText(String.format("%.2f",valor*100) + "%");

    }
}
