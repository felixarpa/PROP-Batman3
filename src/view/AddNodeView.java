package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import presentation.AddNodePresenter;
import util.ProjectConstants;
import view.auxiliarViews.ImageButton;

import static java.lang.Integer.parseInt;

public class AddNodeView extends MainAdminView {

    private TextField nodeName;
    private ComboBox nodeLabel;
    private ComboBox nodeSelector;
    private Text labelText;
    private Text nodeSelectorText;
    private Text nodeNameText;
    private ImageButton addNodeButton;

    private VBox contentVBox;
    private VBox leftVBox;
    private VBox rightVBox;
    private VBox generalVBox;
    private HBox topPartHBox;
    private HBox buttonHBox;
    private HBox labelHBox;
    private HBox nodeTextHBox;
    private HBox messageHBox;
    private HBox bottomHBox;
    private HBox labelTextHBox;
    private HBox nameTextHBox;

    private Text message;

    private Font font;

    public AddNodeView(AddNodePresenter addNodePresenter) {
        presenter = addNodePresenter;
        super.addNodeButton.press();
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
        nameTextHBox = new HBox();
        nameTextHBox.setAlignment(Pos.CENTER);
        nameTextHBox.setPadding(new Insets(0,0,20,0));
        generalVBox = new VBox();
        generalVBox.setAlignment(Pos.CENTER);
        contentVBox = new VBox();
        bottomHBox = new HBox();
        bottomHBox.setAlignment(Pos.CENTER);
        bottomHBox.setPadding(new Insets(25,0,0,0));
        contentVBox.setAlignment(Pos.CENTER);
        //contentVBox.setPadding(new Insets(0,0,0,300));
        labelTextHBox = new HBox();
        labelTextHBox.setAlignment(Pos.CENTER);
        labelTextHBox.setPadding(new Insets(0,0,10,0));

        nodeTextHBox = new HBox();
        nodeTextHBox.setAlignment(Pos.CENTER);
        nodeTextHBox.setPadding(new Insets(0,0,10,0));

        leftVBox = new VBox();
       // leftVBox.setPadding(new Insets(0,50,0,0));
        leftVBox.setAlignment(Pos.CENTER);
        rightVBox = new VBox();
        rightVBox.setPadding(new Insets(0,0,0,50));
        rightVBox.setAlignment(Pos.CENTER);
        topPartHBox = new HBox();
        topPartHBox.setPadding(new Insets(0,0,50,0));
        topPartHBox.setAlignment(Pos.CENTER);
        buttonHBox = new HBox();
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setPadding(new Insets(25,0,0,0));
        labelHBox = new HBox();
        labelHBox.setPadding(new Insets(10,0,0,0));
        labelHBox.setAlignment(Pos.CENTER);

        messageHBox = new HBox();
        messageHBox.setAlignment(Pos.CENTER);
        messageHBox.setPadding(new Insets(0,0,20,0));
    }

    public void initializeViews(){
        nodeNameText = new Text("Name");
        nodeNameText.setFont(font);
        nodeNameText.setFill(Paint.valueOf("white"));
        nodeName = new TextField();
        nodeName.setMinSize(400,25);
        nodeName.setMaxSize(400,25);
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
        labelText = new Text("Label");
        labelText.setFont(font);
        labelText.setFill(Paint.valueOf("white"));
        nodeSelectorText = new Text("Node Type");
        nodeSelectorText.setFont(font);
        nodeSelectorText.setFill(Paint.valueOf("white"));
        addNodeButton = new ImageButton("addNodeButton",100,50);

        message = new Text();
        message.setFont(font);
        message.setFill(Paint.valueOf("#A51212"));

    }

    public void buildPanes(){
        messageHBox.getChildren().add(message);
        generalVBox.getChildren().add(messageHBox);

        nameTextHBox.getChildren().add(nodeNameText);
       // generalVBox.getChildren().add(nodeNameText);
        generalVBox.getChildren().add(nameTextHBox);
        generalVBox.getChildren().add(nodeName);

        nodeTextHBox.getChildren().add(nodeSelectorText);
        labelTextHBox.getChildren().add(labelText);
        leftVBox.getChildren().add(nodeTextHBox);
        leftVBox.getChildren().add(nodeSelector);
        rightVBox.getChildren().add(labelTextHBox);
        rightVBox.getChildren().add(nodeLabel);
        bottomHBox.getChildren().add(leftVBox);
        bottomHBox.getChildren().add(rightVBox);

        generalVBox.getChildren().add(bottomHBox);

        buttonHBox.getChildren().add(addNodeButton);
        generalVBox.getChildren().add(buttonHBox);
        contentVBox.getChildren().add(generalVBox);
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

    public void showErrorMessage(String s) {
        message.setFill(Paint.valueOf("#A51212"));
        message.setText(s);
    }


    public void showSuccessMessage(String s) {
        message.setFill(Paint.valueOf("green"));

        message.setText(s);
    }

}
