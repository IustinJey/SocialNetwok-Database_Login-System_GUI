package com.example.guiex1.controller;

import com.example.guiex1.HelloApplication;
import com.example.guiex1.data.UserData;
import com.example.guiex1.domain.Friendship;
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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UserController implements Observer<UserEntityChangeEvent> {
    UserService service;
    FriendshipService service2;
    ObservableList<User> model = FXCollections.observableArrayList();
    ObservableList<User> model2 = FXCollections.observableArrayList();

    @FXML
    private Circle circle_f;
    @FXML
    private Circle circle_n;
    @FXML
    private AnchorPane friends_anchor;
    @FXML
    private AnchorPane notifications_anchor;
    @FXML
    private Text welcomeMessage;
    @FXML
    private AnchorPane reel;
    @FXML
    private Button acceptRequest;
    @FXML
    private ImageView logOutIcon;
    @FXML
    private ImageView deleteAccountIcon;
  @FXML
  TableView<User> tableView;
  @FXML
  TableColumn<User,String> tableColumnFirstName;
  @FXML
  TableColumn<User,String> tableColumnLastName;
    @FXML
    TableView<User> tableView1;
    @FXML
    TableColumn<User,String> tableColumnFirstName1;
    @FXML
    TableColumn<User,String> tableColumnLastName1;


    public void setService(UserService service, FriendshipService service2) {
        this.service = service;
        this.service2 = service2;
        service.addObserver(this);
       // initModel();
    }

    @FXML
    public void initialize() {
        welcomeMessage.setText("Merry Christmas, "+UserData.connectedUser.getFirstName());
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        tableView.setItems(model);
        tableColumnFirstName1.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        tableColumnLastName1.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        tableView1.setItems(model2);
    }
    private void initModel() throws SQLException {
        Iterable<Friendship> friendsShips = service2.getAll();
        friendsShips.forEach(x-> System.out.println(x.getUser2()));
        List<User> friends = new ArrayList<User>();
        List<User> notifications = new ArrayList<User>();
        List<Friendship> friendsShipsList = StreamSupport.stream(friendsShips.spliterator(), false)
                .collect(Collectors.toList());
        for (Friendship f:friendsShipsList) {
            System.out.println(f.getUser1());
            System.out.println(UserData.connectedUser.getEmail());
            if(Objects.equals(f.getUser1(),UserData.connectedUser.getEmail()) && f.getStatus() == true)
                friends.add(service.getOne(f.getUser2()));
            else if(Objects.equals(f.getUser2(),UserData.connectedUser.getEmail()) && f.getStatus() == true)
                friends.add(service.getOne(f.getUser1()));
            else if(Objects.equals(f.getUser2(),UserData.connectedUser.getEmail()) && f.getStatus() == false)
                notifications.add(service.getOne(f.getUser1()));
        }
        friends.forEach(x-> System.out.println(x.getEmail()));
        model.setAll(friends);
        model2.setAll(notifications);
    }

    @Override
    public void update(UserEntityChangeEvent utilizatorEntityChangeEvent) throws SQLException {
        initModel();
    }

    public void handleAddUtilizator(ActionEvent actionEvent) throws IOException {
        //User user = new User();
        //Parent parent = FXMLLoader.load(getClass().getResource("views/addUser.fxml"));
        //Scene scene = new Scene(parent);
        //Stage stage = new Stage();
        //stage.setScene(scene);
        //stage.show();

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/addUser.fxml"));
        //AnchorPane userLayout = fxmlLoader.load();
        //Stage stage = new Stage();
        //stage.setScene(new Scene(userLayout));
        //stage.showAndWait();

        //User user = new User(UserData.firstName, UserData.lastName);
        //UserData.clearDataUser();
        //System.out.println(user);
        //service.addUtilizator(user);

        //try{
            //User saved = service.addUtilizator(user);
        //} catch (ValidationException e){
         //   MessageAlert.showErrorMessage(null, e.getMessage());
           // return;
       // }

        //MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", "User adaugat cu succes!");
//        if(service.addUtilizator(user) == null){
//            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", "User adaugat cu succes!");
//        } else{
//            MessageAlert.showErrorMessage(null, "Failed adding user");
//        }
    }

    public void handleDeleteUtilizator(ActionEvent actionEvent) {
       // System.out.println("stergere user");
       // User user=(User) tableView.getSelectionModel().getSelectedItem();
       // if (user!=null) {
       //     System.out.println("user sters");
       //     service.deleteUtilizator(user.getId());
        }
     //   initModel();
    //}

    public void handleUpdateUtilizator(ActionEvent actionEvent) throws IOException {
       // User selectedUser=(User) tableView.getSelectionModel().getSelectedItem();
       /// FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/updateUser.fxml"));
       // AnchorPane userLayout = fxmlLoader.load();
       // Stage stage = new Stage();
        //stage.setScene(new Scene(userLayout));
       // stage.showAndWait();
      //  selectedUser.setFirstName(UserData.firstName);
       // selectedUser.setLastName(UserData.lastName);
       // UserData.clearDataUser();
       // System.out.println(selectedUser);
      //  service.updateUtilizator(selectedUser);
       // initModel();
    }
    public void handleAcceptRequest() throws SQLException {
        Friendship friendship = service2.getOne(tableView1.getSelectionModel().getSelectedItem().getEmail(), UserData.connectedUser.getEmail());
        friendship.setStatus(true);
        friendship.setDate(LocalDateTime.now());
        service2.updateFriendship(friendship);
        initModel();
    }

    public void handleRemoveFriend() throws SQLException {
        System.out.println("handle delete friends");
        System.out.println(service2.getOne(UserData.connectedUser.getEmail(), tableView.getSelectionModel().getSelectedItem().getEmail()).getUser1());
        System.out.println(service2.getOne(UserData.connectedUser.getEmail(), tableView.getSelectionModel().getSelectedItem().getEmail()).getUser2());
        if(tableView.getSelectionModel().getSelectedItem() != null) {
            service2.deleteFriendship(tableView.getSelectionModel().getSelectedItem().getEmail(), UserData.connectedUser.getEmail());
            initModel();
        }
    }

    public void handleRejectRequest() throws SQLException {
        if(tableView1.getSelectionModel().getSelectedItem() != null) {
            service2.deleteFriendship(tableView1.getSelectionModel().getSelectedItem().getEmail(), UserData.connectedUser.getEmail());
            initModel();
        }
    }

    public void handleAddFriend() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/addUser.fxml"));
        AnchorPane userLayout = fxmlLoader.load();
//
        AddFriendController addFriendController = fxmlLoader.getController();
        addFriendController.setService(service, service2);

        Stage stage = new Stage();
        stage.setScene(new Scene(userLayout));
        stage.show();
    }
    public void handleShowHome()
    {
        friends_anchor.setVisible(false);
        notifications_anchor.setVisible(false);
        circle_f.setVisible(false);
        circle_n.setVisible(false);
        reel.setVisible(true);
    }
    public void handleShowFriends() throws IOException, SQLException {
        friends_anchor.setVisible(true);
        notifications_anchor.setVisible(false);
        circle_f.setVisible(true);
        circle_n.setVisible(false);
        reel.setVisible(false);
        initModel();
      //  Long selectedUser=tableView.getSelectionModel().getSelectedItem().getId();
      //  FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/friends.fxml"));
       // AnchorPane userLayout = fxmlLoader.load();
//
      //  Stage stage = new Stage();
       // stage.setScene(new Scene(userLayout));
//
    //    FriendsController friendsController = fxmlLoader.getController();
     //   friendsController.setFriendsController(service2, selectedUser, service);
     //   stage.show();
    }

    public void handleShowFriendInfo() throws IOException, SQLException {
        if(tableView.getSelectionModel().getSelectedItem() != null) {
            UserData.selectedFriend = tableView.getSelectionModel().getSelectedItem();
            UserData.friendsSince = service2.getOne(UserData.connectedUser.getEmail(), UserData.selectedFriend.getEmail()).getDate();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/friendProfileView.fxml"));
            AnchorPane userLayout = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(userLayout));
            friendProfileController friendProfileController = fxmlLoader.getController();
            friendProfileController.setService(service, service2);

            stage.show();
        }
    }

    public void handleShowProfile() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/profileView.fxml"));
        AnchorPane userLayout = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(userLayout));

        profileController profileController = fxmlLoader.getController();
        profileController.setService(service);
        stage.show();
    }
    public void handleShowNotifications() throws SQLException {
        friends_anchor.setVisible(false);
        notifications_anchor.setVisible(true);
        circle_f.setVisible(false);
        circle_n.setVisible(true);
        reel.setVisible(false);
        initModel();
    }

    public void handleLogOut() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/loginView.fxml"));
        AnchorPane userLayout = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(userLayout));
        loginController loginController = fxmlLoader.getController();
        loginController.setService(service, service2);
        stage.show();

        Stage stage2 = (Stage) logOutIcon.getScene().getWindow();
        stage2.close();

    }

    public void handleDeleteAccount() throws IOException, SQLException {

        Iterable<Friendship> friendsShips = service2.getAll();
        List<Friendship> friendsShipsList = StreamSupport.stream(friendsShips.spliterator(), false)
                .collect(Collectors.toList());
        for (Friendship f:friendsShipsList) {
            System.out.println(f.getUser1());
            System.out.println(UserData.connectedUser.getEmail());
            if(Objects.equals(f.getUser1(),UserData.connectedUser.getEmail()))
                service2.deleteFriendship(f.getUser2(), UserData.connectedUser.getEmail());
            else if(Objects.equals(f.getUser2(),UserData.connectedUser.getEmail()))
                service2.deleteFriendship(f.getUser1(), UserData.connectedUser.getEmail());

        }


        service.deleteUser(UserData.connectedUser.getEmail());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/loginView.fxml"));
        AnchorPane userLayout = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(userLayout));
        loginController loginController = fxmlLoader.getController();
        loginController.setService(service, service2);
        stage.show();

        Stage stage2 = (Stage) deleteAccountIcon.getScene().getWindow();
        stage2.close();

    }
//    public void handleDeleteMessage(ActionEvent actionEvent) {
//        MessageTask selected = (MessageTask) tableView.getSelectionModel().getSelectedItem();
//        if (selected != null) {
//            MessageTask deleted = service.deleteMessageTask(selected);
//            if (null != deleted)
//                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Delete", "Studentul a fost sters cu succes!");
//        } else MessageAlert.showErrorMessage(null, "Nu ati selectat nici un student!");
//    }
//

//
//    @FXML
//    public void handleUpdateMessage(ActionEvent ev) {
//        MessageTask selected = tableView.getSelectionModel().getSelectedItem();
//        if (selected != null) {
//            showMessageTaskEditDialog(selected);
//        } else
//            MessageAlert.showErrorMessage(null, "NU ati selectat nici un student");
//    }
//
//    @FXML
//    public void handleAddMessage(ActionEvent ev) {
//
//        showMessageTaskEditDialog(null);
//    }
//
//    public void showMessageTaskEditDialog(MessageTask messageTask) {
//        try {
//            // create a new stage for the popup dialog.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/views/editMessageTaskView.fxml"));
//
//            AnchorPane root = (AnchorPane) loader.load();
//
//            // Create the dialog Stage.
//            Stage dialogStage = new Stage();
//            dialogStage.setTitle("Edit Message");
//            dialogStage.initModality(Modality.WINDOW_MODAL);
//            //dialogStage.initOwner(primaryStage);
//            Scene scene = new Scene(root);
//            dialogStage.setScene(scene);
//
//            EditMessageTaskController editMessageViewController = loader.getController();
//            editMessageViewController.setService(service, dialogStage, messageTask);
//
//            dialogStage.show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//


}
