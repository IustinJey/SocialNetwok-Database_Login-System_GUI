package com.example.guiex1.controller;

import com.example.guiex1.data.UserData;
import com.example.guiex1.domain.User;
import com.example.guiex1.services.FriendshipService;
import com.example.guiex1.services.UserService;
import com.example.guiex1.utils.events.UserEntityChangeEvent;
import com.example.guiex1.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UsersListController {
    UserService service;
    ObservableList<User> model = FXCollections.observableArrayList();
    public void setUtilizatorService(UserService service) {
        this.service = service;
        initModel();
    }
    @FXML
    private TableColumn<User, String> tableColumnFirstName;

    @FXML
    private TableColumn<User, String> tableColumnLastName;

    @FXML
    private TableView<User> tableView;

    @FXML
    private Button addButton;

    @FXML
    public void initialize() {
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        tableView.setItems(model);
    }
    private void initModel() {
        Iterable<User> users = service.getAll();
        List<User> usersList = StreamSupport.stream(users.spliterator(), false)
                .collect(Collectors.toList());

        model.setAll(usersList);
    }

    public void Add(ActionEvent actionEvent)
    {
        UserData.selectedUser2 = tableView.getSelectionModel().getSelectedItem().getId();
        System.out.println(UserData.selectedUser2);
        Stage stage = (Stage) addButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}

