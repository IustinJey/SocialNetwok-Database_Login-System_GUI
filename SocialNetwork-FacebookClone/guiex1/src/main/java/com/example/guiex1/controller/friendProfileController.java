package com.example.guiex1.controller;

import com.example.guiex1.data.UserData;
import com.example.guiex1.domain.User;
import com.example.guiex1.services.FriendshipService;
import com.example.guiex1.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class friendProfileController {
    UserService service;
    FriendshipService service2;
    @FXML
    private Text emailAccount;
    @FXML
    private Text nameAccount;
    @FXML
    private Button updateButton;
    @FXML
    private Text friendsSince;


    public void setService(UserService service1, FriendshipService service22){
        service = service1;
        service2 = service22;
    }

    @FXML
    public void initialize() throws SQLException {
        nameAccount.setText(UserData.selectedFriend.getFirstName() + " " + UserData.selectedFriend.getLastName());
        emailAccount.setText(UserData.selectedFriend.getEmail());
        friendsSince.setText("You are friends since " + UserData.friendsSince);
    }

}
