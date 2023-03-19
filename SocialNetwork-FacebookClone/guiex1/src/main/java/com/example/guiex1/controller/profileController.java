package com.example.guiex1.controller;

import com.example.guiex1.data.UserData;
import com.example.guiex1.domain.User;
import com.example.guiex1.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class profileController {
    UserService service;
    @FXML
    private Text emailAccount;
    @FXML
    private Text nameAccount;
    @FXML
    private Button updateButton;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;

    public void setService(UserService service1){
        service = service1;
    }

    @FXML
    public void initialize()
    {
        nameAccount.setText(UserData.connectedUser.getFirstName() + " " + UserData.connectedUser.getLastName());
        emailAccount.setText(UserData.connectedUser.getEmail());
        firstName.setText(UserData.connectedUser.getFirstName());
        lastName.setText(UserData.connectedUser.getLastName());
    }

    public void updateProfile()
    {
        User user = UserData.connectedUser;
        user.setLastName(lastName.getText());
        user.setFirstName(firstName.getText());
        service.updateUtilizator(user);
        initialize();
    }
}
