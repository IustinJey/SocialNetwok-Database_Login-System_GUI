package com.example.guiex1.controller;

import com.example.guiex1.data.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateUserController {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private Button updateButton;
    public void updateUser(ActionEvent actionEvent)
    {
        UserData.firstName = firstName.getText();
        UserData.lastName = lastName.getText();
        Stage stage = (Stage) updateButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
