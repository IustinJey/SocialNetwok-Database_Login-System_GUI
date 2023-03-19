package com.example.guiex1.controller;

import com.example.guiex1.data.UserData;
import com.example.guiex1.domain.User;
import com.example.guiex1.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class registerController {
    UserService service = null;
    public void setService(UserService service)
    {
        this.service = service;
    }

    @FXML
    private TextField email;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private PasswordField password;
    @FXML
    private Button sup;
    @FXML
    private AnchorPane errorRegister;

    public void createAccount(ActionEvent actionEvent)
    {
        String email1 = email.getText();
        String firstName1 = firstName.getText();
        String lastName1 = lastName.getText();
        String password1 = password.getText();
        User user = new User(email1, firstName1, lastName1, password1);
        System.out.println("Create account");
        service.addUtilizator(user);
        Stage stage = (Stage) sup.getScene().getWindow();
            // do what you have to do
        stage.close();

    }
}

