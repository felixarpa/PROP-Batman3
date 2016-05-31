package view;

import domain.UserController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;

import presentation.SettingsPresenter;
import view.auxiliarViews.ImageButton;



public class SettingsView extends BaseView {

    private VBox contentVBox;
    private HBox contentHBox;

    private Font font;
    private Font font2;
    private Font fontError;
    private Text settingsText;
    private HBox settingsTextHBox;
    private HBox horizontalSeparator;
    private HBox horizontalSeparatorContainer;
    private VBox verticalSeparator;
    private VBox verticalSeparator2;

    private VBox leftVBox;
    private VBox rightVBox;

    private ImageButton changePasswordButton;

    private VBox blackVBox;
    private VBox blackVBoxContent;
    private Text currentPasswordText;
    private PasswordField currentPasswordPasswordField;
    private Text newPasswordText;
    private PasswordField newPasswordPasswordField;
    private Text confirmNewPasswordText;
    private PasswordField confirmNewPasswordPasswordField;
    private ImageButton saveChangesButton;

    private ImageButton clearGraphButton;
    private ImageButton resetGraphButton;

    private HBox errorMessageHBox;
    private Label errorMessage;

    private HBox changePasswordButtonHBox;

    public SettingsView(SettingsPresenter presenter) {

        this.presenter = presenter;
        settings.press();
        initializeFonts();
        initializeViews();
        initializePanes();
        buildPanes();
        setListeners();
        topBarPane.setCenter(contentVBox);
    }

    private void initializeFonts() {
        font = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 14);
        font2 = Font.loadFont(this.getClass().getResource("../fonts/Nilland.ttf").toExternalForm(), 36);
        fontError = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 12);
    }

    private void initializeViews() {
        errorMessage = new Label();
        errorMessage.setTextFill(Paint.valueOf("#A51212"));
        errorMessage.setFont(fontError);
        errorMessage.setMaxWidth(400);
        errorMessage.setMinWidth(100);

        settingsText = new Text("Settings");
        settingsText.setFont(font2);
        settingsText.setFill(Paint.valueOf("white"));
        changePasswordButton = new ImageButton("changePasswordButton",208,51);
        currentPasswordText = new Text("Current password:");
        currentPasswordText.setFill(Paint.valueOf("white"));
        currentPasswordPasswordField = new PasswordField();
        currentPasswordPasswordField.setMinWidth(300);
        newPasswordText = new Text("New password:");
        newPasswordText.setFill(Paint.valueOf("white"));
        newPasswordPasswordField = new PasswordField();
        newPasswordPasswordField.setMinWidth(300);
        confirmNewPasswordText = new Text("Confirm new password:");
        confirmNewPasswordText.setFill(Paint.valueOf("white"));
        confirmNewPasswordPasswordField = new PasswordField();
        confirmNewPasswordPasswordField.setMinWidth(300);
        saveChangesButton = new ImageButton("saveChangesButton",134,53);
        clearGraphButton = new ImageButton("clearGraphButton",208,51);
        resetGraphButton = new ImageButton("resetGraphButton",208,51);
    }

    private void initializePanes() {
        changePasswordButtonHBox = new HBox();
        changePasswordButtonHBox.setAlignment(Pos.CENTER);
        //changePasswordButtonHBox.setPadding(new Insets(0,100,0,0));


        errorMessageHBox = new HBox();
        //errorMessageHBox.setAlignment(Pos.CENTER);
        errorMessageHBox.setPadding(new Insets(15,0,0,0));
        verticalSeparator = new VBox();
        verticalSeparator.setMinSize(1,310);
        verticalSeparator.setMaxSize(1,310);
        verticalSeparator.setStyle("-fx-background-color: #ffffff");

        verticalSeparator2 = new VBox();
        verticalSeparator2.setMinSize(1,310);
        verticalSeparator2.setMaxSize(1,310);
        verticalSeparator2.setStyle("-fx-background-color: #ffffff");

        horizontalSeparatorContainer = new HBox();
        horizontalSeparatorContainer.setAlignment(Pos.CENTER);
        horizontalSeparator = new HBox();
        horizontalSeparator.setMinSize(850,1);
        horizontalSeparator.setMaxSize(850,1);
        horizontalSeparator.setStyle("-fx-background-color: #ffffff");
        settingsTextHBox = new HBox();
        settingsTextHBox.setPadding(new Insets(5,0,0,20));
        leftVBox = new VBox();
        //leftVBox.setPadding(new Insets(0,80,0,0));
        leftVBox.setMaxWidth(400);
        leftVBox.setMinWidth(400);
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.setFillWidth(false);
        leftVBox.setSpacing(5);
        rightVBox = new VBox();
        rightVBox.setPadding(new Insets(35,0,0,25));
        blackVBoxContent = new VBox();
        blackVBoxContent.setSpacing(5);
        blackVBox = new VBox();
        blackVBox.setAlignment(Pos.CENTER);
        blackVBox.setStyle("-fx-background-color: rgba(0,0,0,.7)");
        blackVBox.setPadding(new Insets(10,20,10,20));
        blackVBox.setSpacing(5);


        contentHBox = new HBox();
        //contentHBox.setAlignment(Pos.CENTER);
        contentHBox.setPadding(new Insets(0,0,0,0));
        contentHBox.setSpacing(10);
        contentVBox = new VBox();
        //contentVBox.setAlignment(Pos.CENTER);

    }

    private void buildPanes() {
        settingsTextHBox.getChildren().add(settingsText);
        errorMessageHBox.getChildren().add(errorMessage);
        changePasswordButtonHBox.getChildren().add(changePasswordButton);

        if(UserController.isAdmin()){
            leftVBox.getChildren().add(clearGraphButton);
            leftVBox.getChildren().add(resetGraphButton);
        }

        leftVBox.getChildren().add(changePasswordButtonHBox);
        //leftVBoxGrande.setFillWidth(false);

        leftVBox.getChildren().add(errorMessage);
        blackVBoxContent.getChildren().add(currentPasswordText);
        blackVBoxContent.getChildren().add(currentPasswordPasswordField);
        blackVBoxContent.getChildren().add(newPasswordText);
        blackVBoxContent.getChildren().add(newPasswordPasswordField);
        blackVBoxContent.getChildren().add(confirmNewPasswordText);
        blackVBoxContent.getChildren().add(confirmNewPasswordPasswordField);
        blackVBox.getChildren().add(blackVBoxContent);
        blackVBox.getChildren().add(saveChangesButton);
        rightVBox.getChildren().add(blackVBox);
        rightVBox.setVisible(false);
        contentHBox.getChildren().add(leftVBox);
        contentHBox.getChildren().add(verticalSeparator);
        contentHBox.getChildren().add(verticalSeparator2);
        contentHBox.getChildren().add(rightVBox);
        contentVBox.getChildren().add(settingsTextHBox);
        horizontalSeparatorContainer.getChildren().add(horizontalSeparator);
        contentVBox.getChildren().add(horizontalSeparatorContainer);
        contentVBox.getChildren().add(contentHBox);
    }

    private void setListeners() {

        clearGraphButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearGraphButton.press();
            }
        });

        clearGraphButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearGraphButton.release();
                ((SettingsPresenter)presenter).onClearGraph();
            }
        });

        resetGraphButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resetGraphButton.press();
            }
        });

        resetGraphButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resetGraphButton.release();
                ((SettingsPresenter)presenter).onResetGraph();
            }
        });

        changePasswordButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!rightVBox.isVisible()) {
                    changePasswordButton.press();
                    rightVBox.setVisible(true);
                }else{
                    changePasswordButton.release();
                    rightVBox.setVisible(false);
                }
            }
        });


        saveChangesButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                saveChangesButton.press();
            }
        });
        saveChangesButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                saveChangesButton.release();
                ((SettingsPresenter)presenter).onSaveChangesClick();
            }
        });
    }

    public String getCurrentPassword() {
        return currentPasswordPasswordField.getText();
    }

    public String getNewPassword() {
        return newPasswordPasswordField.getText();
    }

    public String getConfirmNewPassword() {
        return confirmNewPasswordPasswordField.getText();
    }

    public void showError(String error) {
        errorMessage.setTextFill(Paint.valueOf("#A51212"));
        errorMessage.setText(error);
    }

    public void showSuccess(String error) {

        errorMessage.setTextFill(Paint.valueOf("green"));

        errorMessage.setText(error);
    }
}
