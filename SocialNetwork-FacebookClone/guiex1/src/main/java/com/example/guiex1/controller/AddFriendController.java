package com.example.guiex1.controller;

import com.example.guiex1.data.UserData;
import com.example.guiex1.domain.Friendship;
import com.example.guiex1.services.FriendshipService;
import com.example.guiex1.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

public class AddFriendController {
    UserService service;
    FriendshipService service2;

    public void setService(UserService service, FriendshipService service2) {
        this.service = service;
        this.service2 = service2;
        // initModel();
    }
    @FXML
    private AnchorPane error;
    @FXML
    private TextField email;
    @FXML
    private Button sendRequest;
    public void addFriend(ActionEvent actionEvent) throws SQLException {
        if(service.getOne(email.getText()) == null)
        {
            error.setVisible(true);
        }
        else if (Objects.equals(email.getText(), UserData.connectedUser.getEmail()))
        {
            error.setVisible(true);
        }
        else
        {
            Friendship friendship = new Friendship(UserData.connectedUser.getEmail(), email.getText(), false, LocalDateTime.now());
            service2.addFriendship(friendship);
            Stage stage = (Stage) sendRequest.getScene().getWindow();
            // do what you have to do
            stage.close();
        }

    }
}
