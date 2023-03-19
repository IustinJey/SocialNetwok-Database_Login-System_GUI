package com.example.guiex1;

import com.example.guiex1.controller.FriendsController;
import com.example.guiex1.controller.UserController;
import com.example.guiex1.controller.loginController;
import com.example.guiex1.domain.Friendship;
import com.example.guiex1.domain.User;
import com.example.guiex1.domain.UserValidator;
import com.example.guiex1.repository.Repository;
import com.example.guiex1.repository.dbrepo.FriendshipDbRepository;
import com.example.guiex1.repository.dbrepo.UserDbRepository;
import com.example.guiex1.services.FriendshipService;
import com.example.guiex1.services.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

//public class HelloApplication extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}

public class HelloApplication extends Application {

    Repository<Long, User> userRepository;
    UserService service;
    FriendshipService service2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        System.out.println("Reading data from file");
        String username="postgres";
        String pasword="postgres";
        String url="jdbc:postgresql://localhost:5432/postgres";
        Repository<Long, User> utilizatorRepository =
                new UserDbRepository(url,username, pasword,  new UserValidator());
        Repository<Long, Friendship> friendShipRepository =
                new FriendshipDbRepository(url,username, pasword);


        utilizatorRepository.findAll().forEach(x-> System.out.println(x));
        service =new UserService(utilizatorRepository);
        service2 = new FriendshipService(friendShipRepository);
        initView(primaryStage);
        primaryStage.setWidth(800);
        primaryStage.show();


    }

    private void initView(Stage primaryStage) throws IOException {

       // FXMLLoader fxmlLoader = new FXMLLoader();
        //fxmlLoader.setLocation(getClass().getResource("com/example/guiex1/views/UtilizatorView.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/loginView.fxml"));
        AnchorPane userLayout = fxmlLoader.load();
        primaryStage.setScene(new Scene(userLayout));
        loginController loginController = fxmlLoader.getController();
        loginController.setService(service, service2);


    }
}