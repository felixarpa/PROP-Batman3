package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

    private Font font;

    public AddNodeView(AddNodePresenter addNodePresenter) {
        super(addNodePresenter);
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
        contentVBox.setPadding(new Insets(0,200,0,200));

        leftVBox = new VBox();
        rightVBox = new VBox();
        topPartHBox = new HBox();
        buttonHBox = new HBox();
        labelHBox = new HBox();
    }

    public void initializeViews(){
        nodeName = new EditText("Name ",Pos.CENTER);
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
    }

    public void buildPanes(){
        labelHBox.getChildren().add(labelLabel);
        labelHBox.getChildren().add(nodeLabel);

        leftVBox.getChildren().add(nodeSelectorText);
        leftVBox.getChildren().add(nodeSelector);

        rightVBox.getChildren().add(nodeName.getBase());
        rightVBox.getChildren().add(labelHBox);

        buttonHBox.getChildren().add(addNodeButton);

        topPartHBox.getChildren().add(leftVBox);
        topPartHBox.getChildren().add(rightVBox);

        contentVBox.getChildren().add(topPartHBox);
        contentVBox.getChildren().add(buttonHBox);
    }

    public void setListeners(){
        ((AddNodePresenter)presenter).addNode(getName(),getType(), getLabel());
    }

    public int getLabel(){
        Object o = nodeLabel.getValue();
        if(o == null)return -1;
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


}
