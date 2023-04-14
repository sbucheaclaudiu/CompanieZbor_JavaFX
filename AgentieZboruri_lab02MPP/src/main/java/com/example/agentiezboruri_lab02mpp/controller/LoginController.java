package com.example.agentiezboruri_lab02mpp.controller;

import com.example.agentiezboruri_lab02mpp.domain.User;
import com.example.agentiezboruri_lab02mpp.service.ServiceMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    private ServiceMain service;

    public void setService(ServiceMain service){
        this.service = service;
    }
    @FXML
    void handleLogin(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = service.findUser(username, password);

        if(user == null){
            PopupMessageController.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Date introduse gresit!");
        }
        else{
            PopupMessageController.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Succes login!");
            openMainPage(user, event);
        }
    }

    private void openMainPage(User user, ActionEvent event) throws IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/agentiezboruri_lab02mpp/mainView.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        mainController.setUser(user);
        mainController.setService(service);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("User: " + user.getUsername());
    }

}

