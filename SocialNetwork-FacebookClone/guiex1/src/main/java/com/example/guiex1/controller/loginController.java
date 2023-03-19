package com.example.guiex1.controller;

import com.example.guiex1.HelloApplication;
import com.example.guiex1.data.UserData;
import com.example.guiex1.domain.User;
import com.example.guiex1.domain.ValidationException;
import com.example.guiex1.services.FriendshipService;
import com.example.guiex1.services.UserService;
import com.example.guiex1.utils.events.UserEntityChangeEvent;
import com.example.guiex1.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class loginController {
    UserService service;
    FriendshipService service2;

    @FXML
    private Button logIn;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private AnchorPane errorLogIn;

    public void setService(UserService service, FriendshipService service2) {
        this.service = service;
        this.service2 = service2;
    }

    public void handleSignIn() throws IOException, SQLException {
        User user = service.getOne(email.getText());
        if(user != null){
            UserData.connectedUser = user;
            if(Objects.equals(user.getPassword(),password.getText()))
            {
                System.out.println("Parola e corecta");
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/UtilizatorView.fxml"));
                AnchorPane userLayout = fxmlLoader.load();
                Stage stage = new Stage();

                UserController userController = fxmlLoader.getController();
                userController.setService(service, service2);

                stage.setScene(new Scene(userLayout));
                stage.show();

                Stage stage2 = (Stage) logIn.getScene().getWindow();
                // do what you have to do
                stage2.close();
            }
            else{
                errorLogIn.setVisible(true);
            }
        }
        else
        {
            errorLogIn.setVisible(true);
        }

    }

    public void handleSignUp() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/registerView.fxml"));
        AnchorPane userLayout = fxmlLoader.load();
        Stage stage = new Stage();

        registerController registerController = fxmlLoader.getController();
        registerController.setService(service);

        stage.setScene(new Scene(userLayout));
        stage.showAndWait();
    }
}
