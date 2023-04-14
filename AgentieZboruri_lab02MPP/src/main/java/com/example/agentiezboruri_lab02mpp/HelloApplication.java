package com.example.agentiezboruri_lab02mpp;

import com.example.agentiezboruri_lab02mpp.domain.Bilet;
import com.example.agentiezboruri_lab02mpp.domain.User;
import com.example.agentiezboruri_lab02mpp.domain.Zbor;
import com.example.agentiezboruri_lab02mpp.repository.BiletRepo;
import com.example.agentiezboruri_lab02mpp.repository.UserRepo;
import com.example.agentiezboruri_lab02mpp.repository.ZborRepo;
import com.example.agentiezboruri_lab02mpp.service.BiletService;
import com.example.agentiezboruri_lab02mpp.service.ServiceMain;
import com.example.agentiezboruri_lab02mpp.service.UserService;
import com.example.agentiezboruri_lab02mpp.service.ZborService;
import com.example.agentiezboruri_lab02mpp.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;

public class HelloApplication extends Application {

    //functie de teste
    public void test(){
        /*
        User user = new User("username1", "password1");
        System.out.println(user);

        Zbor zbor = new Zbor(1L,"destinatie", LocalDate.now(), LocalTime.now(), "airport1", 300);
        System.out.println(zbor);

        Bilet bilet = new Bilet(1L,"Alex", "Ana, Marius, Ion", "adresa1", 3, zbor);
        System.out.println(bilet);
         */

        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.properties"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        System.out.println(props);

        UserRepo userRepo = new UserRepo(props, "User");
        UserService userService = new UserService(userRepo);
        ZborRepo zborRepo = new ZborRepo(props, "Zbor");
        ZborService zborService = new ZborService(zborRepo);
        BiletRepo biletRepo = new BiletRepo(props, "Bilet");
        BiletService biletService = new BiletService(biletRepo);

        ServiceMain srv = new ServiceMain(userService, zborService, biletService);

        //System.out.println(srv.getAllBilet());
        //System.out.println(srv.getAllZbor());
        //System.out.println(srv.getAllUser());

        //System.out.println(userRepo.getById("user1"));
        //System.out.println(zborRepo.getById(1L));
        //System.out.println(biletRepo.getById(1L));

        User user = new User("username12", "password");
        userRepo.insert(user);

        Zbor zbor = new Zbor(1200L,"Paris",LocalDate.now(),LocalTime.now(), "Air",238);
        zborRepo.insert(zbor);

        Bilet bilet = new Bilet(1200L, "aa", "aa", "aa", 2, zbor);
        biletRepo.insert(bilet);

        biletRepo.update(new Bilet(1200L, "aa", "aa", "aaaaaa", 2, zbor));
        zborRepo.update(new Zbor(1200L,"Parissss",LocalDate.now(),LocalTime.now(), "Ai3r",238));
        userRepo.update(new User("username12", "pass"));

        userRepo.delete(user);
        biletRepo.delete(bilet);
        zborRepo.delete(zbor);

    }

    @Override
    public void start(Stage stage) throws IOException {

        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.properties"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        UserRepo userRepo = new UserRepo(props, "User");
        UserService userService = new UserService(userRepo);
        ZborRepo zborRepo = new ZborRepo(props, "Zbor");
        ZborService zborService = new ZborService(zborRepo);
        BiletRepo biletRepo = new BiletRepo(props, "Bilet");
        BiletService biletService = new BiletService(biletRepo);

        ServiceMain service = new ServiceMain(userService, zborService, biletService);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginView.fxml"));
        Parent root = fxmlLoader.load();
        LoginController loginController = fxmlLoader.getController();
        loginController.setService(service);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Agentie de turism");

        //fx:controller="com.example.agentiezboruri_lab02mpp.controller.LoginController"
    }

    public static void main(String[] args) {
        launch();
    }
}