package com.example.guiex1.controller;

import com.example.guiex1.HelloApplication;
import com.example.guiex1.data.UserData;
import com.example.guiex1.domain.Friendship;
import com.example.guiex1.domain.User;
import com.example.guiex1.services.FriendshipService;
import com.example.guiex1.services.UserService;
import com.example.guiex1.utils.events.FriendshipEntityChangeEvent;
import com.example.guiex1.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FriendsController {
    FriendshipService service;
    UserService userService;
    Long selectedUser;
    ObservableList<User> model = FXCollections.observableArrayList();

    @FXML
    private TableColumn<User, String> tableColumnFirstName;

    @FXML
    private TableColumn<User, String> tableColumnLastName;

    @FXML
    private TableView<User> tableView;

    public void setFriendsController(FriendshipService service, Long selectedUser, UserService userService) throws SQLException {
        this.service = service;
        this.selectedUser = selectedUser;
        this.userService =userService;
        initModel();
    }

    @FXML
    public void initialize() {
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        tableView.setItems(model);
    }

    private void initModel() throws SQLException {
        Iterable<Friendship> friendsShips = service.getAll();
        friendsShips.forEach(x-> System.out.println(x));
        List<User> friends = new ArrayList<User>();
        List<Friendship> friendsShipsList = StreamSupport.stream(friendsShips.spliterator(), false)
                .collect(Collectors.toList());
        for (Friendship f:friendsShipsList) {
            if(f.getUser1() == UserData.connectedUser.getEmail())
                friends.add(userService.getOne(f.getUser2()));
            else if(f.getUser2() == UserData.connectedUser.getEmail())
                friends.add(userService.getOne(f.getUser1()));
        }
        friends.forEach(x-> System.out.println(x));
        model.setAll(friends);
    }

    public void handleAddFriend(ActionEvent actionEvent) throws IOException, SQLException {
      //  FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/usersListView.fxml"));
      //  AnchorPane userLayout = fxmlLoader.load();
       // Stage stage = new Stage();
        //stage.setScene(new Scene(userLayout));

      //  UsersListController usersController = fxmlLoader.getController();
      //  usersController.setUtilizatorService(userService);

       // stage.showAndWait();

      //  System.out.println("ahhahhahahahahahaah");
       // Friendship friendship = new Friendship(selectedUser, UserData.selectedUser2);
       // System.out.println(friendship.getUser1());
       // System.out.println(friendship.getUser2());
        //System.out.println("AICI BOSULE");
       // System.out.println(friendship);
       // UserData.clearDataUser();
        //service.addFriendship(friendship);

        //initModel();
    }
}