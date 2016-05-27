package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import presentation.AddNodePresenter;
import util.ProjectConstants;

import static java.lang.Integer.parseInt;

public class AddNodeView extends MainAdminView {

    private EditText nodeName;
    private ComboBox nodeLabel;
    private ComboBox nodeSelector;
    private Label labelLabel;
    private Text nodeSelectorText;
    private ImageButton addNodeButton;

    private VBox contentVBox;
    private VBox leftVBox;
    private VBox rightVBox;
    private HBox topPartHBox;
    private HBox buttonHBox;
    private HBox labelHBox;
    private HBox nodeTextHBox;
    private HBox messageHBox;

    private Text message;

    private Font font;

    public AddNodeView(AddNodePresenter addNodePresenter) {
        presenter = addNodePresenter;
        initializeFonts();
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
        topBarPane.setCenter(contentVBox);
    }

    public void initializeFonts(){
        font = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 14);
    }

    public void initializePanes(){
        contentVBox = new VBox();
        contentVBox.setAlignment(Pos.CENTER);
        //contentVBox.setPadding(new Insets(0,0,0,300));

        nodeTextHBox = new HBox();
        nodeTextHBox.setPadding(new Insets(0,0,10,0));

        leftVBox = new VBox();
        leftVBox.setPadding(new Insets(0,50,0,0));
        rightVBox = new VBox();
        rightVBox.setPadding(new Insets(0,0,0,50));
        rightVBox.setAlignment(Pos.CENTER);
        topPartHBox = new HBox();
        topPartHBox.setPadding(new Insets(0,0,50,0));
        topPartHBox.setAlignment(Pos.CENTER);
        buttonHBox = new HBox();
        buttonHBox.setAlignment(Pos.CENTER);
        labelHBox = new HBox();
        labelHBox.setPadding(new Insets(10,0,0,0));

        messageHBox = new HBox();
        messageHBox.setAlignment(Pos.CENTER);
        messageHBox.setPadding(new Insets(0,0,10,0));
    }

    public void initializeViews(){
        nodeName = new EditText("Name",Pos.CENTER);
        nodeName.setFont(font);
        nodeName.setFill(Paint.valueOf("white"));
        nodeName.setMinSize(100,20);
        nodeName.setMaxSize(100,20);
        nodeLabel = new ComboBox();
        nodeLabel.getItems().addAll(
                "No Label",
                "0",
                "1",
                "2",
                "3"
        );
        nodeSelector = new ComboBox();
        nodeSelector.getItems().addAll(
                "Author",
                "Conference",
                "Paper",
                "Term"
        );
        labelLabel = new Label("Label ");
        labelLabel.setFont(font);
        labelLabel.setTextFill(Paint.valueOf("white"));
        nodeSelectorText = new Text("Node Type");
        nodeSelectorText.setFont(font);
        nodeSelectorText.setFill(Paint.valueOf("white"));
        addNodeButton = new ImageButton("../images","addNodeButton",100,50);

        message = new Text();
        message.setFont(font);
        message.setFill(Paint.valueOf("#A51212"));

    }

    public void buildPanes(){
        labelHBox.getChildren().add(labelLabel);
        labelHBox.getChildren().add(nodeLabel);

        nodeTextHBox.getChildren().add(nodeSelectorText);

        leftVBox.getChildren().add(nodeTextHBox);
        leftVBox.getChildren().add(nodeSelector);

        rightVBox.getChildren().add(nodeName.getBase());
        rightVBox.getChildren().add(labelHBox);

        buttonHBox.getChildren().add(addNodeButton);
        messageHBox.getChildren().add(message);

        topPartHBox.getChildren().add(leftVBox);
        topPartHBox.getChildren().add(rightVBox);

        contentVBox.getChildren().add(message);
        contentVBox.getChildren().add(topPartHBox);
        contentVBox.getChildren().add(buttonHBox);
    }

    public void setListeners(){
        addNodeButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                addNodeButton.press();
            }
        });

        addNodeButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                addNodeButton.release();
                ((AddNodePresenter)presenter).addNode();
            }
        });

    }

    public int getLabel(){
        Object o = nodeLabel.getValue();
        if(o == null || o.toString().equals("No Label"))return -1;
        String s = o.toString();
        return Integer.parseInt(s);
    }

    public int getType() {
        Object o = nodeSelector.getValue();
        if(o == null) return -1;
        String type = o.toString();
        switch(type){
            case "Author":
                return ProjectConstants.AUTHOR_TYPE;
            case "Conference":
                return ProjectConstants.CONFERENCE_TYPE;
            case "Paper":
                return ProjectConstants.PAPER_TYPE;
            default:
                return ProjectConstants.TERM_TYPE;
        }
    }

    public String getName() {
        return nodeName.getText();
    }

    public void showMessage(String s) {
        message.setText(s);
    }

}
