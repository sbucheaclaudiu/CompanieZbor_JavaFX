package com.example.agentiezboruri_lab02mpp.controller;

import com.example.agentiezboruri_lab02mpp.domain.User;
import com.example.agentiezboruri_lab02mpp.domain.Zbor;
import com.example.agentiezboruri_lab02mpp.service.ServiceMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainController implements Initializable {

    @FXML
    private TableColumn<Zbor, String> airportColumn;

    @FXML
    private Button buyButton;

    @FXML
    private TableColumn<Zbor, LocalDate> dateColumn;

    @FXML
    private TableColumn<Zbor, String> destinationColumn;

    @FXML
    private TableColumn<Zbor, Integer> freeSeatsColumn;

    @FXML
    private Button logoutButton;

    @FXML
    private Button searchButton;

    @FXML
    private TableColumn<Zbor, LocalTime> timeColumn;

    @FXML
    private TableView<Zbor> zborTableView;

    private ServiceMain service;

    private User user;

    public void setService(ServiceMain service){
        this.service = service;
        fillZbor();
        refresh1();
    }
    public void setUser(User user){
        this.user = user;
    }

    void fillZbor(){
        List<Zbor> lst_zbor = service.getZboruriDisponibile();

        ObservableList<Zbor> list = FXCollections.observableList(lst_zbor);

        zborTableView.setItems(list);
    }

    void refresh1(){
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                fillZbor();
            }
        }, 0, 5000);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        destinationColumn.setCellValueFactory(new PropertyValueFactory<Zbor, String>("destination"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Zbor, LocalDate>("data"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Zbor, LocalTime>("time"));
        airportColumn.setCellValueFactory(new PropertyValueFactory<Zbor, String>("airport"));
        freeSeatsColumn.setCellValueFactory(new PropertyValueFactory<Zbor, Integer>("nrLocuriLibere"));
    }

    @FXML
    void handleBuyButton(ActionEvent event) throws IOException {
        Zbor zbor = zborTableView.getSelectionModel().getSelectedItem();

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/agentiezboruri_lab02mpp/buyView.fxml"));
        Parent root = loader.load();
        BuyController buyController = loader.getController();
        buyController.setZbor(zbor);
        buyController.setService(service);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("User: " + this.user.getUsername() + " | Buy Ticket");
    }

    @FXML
    void handleLogoutButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/agentiezboruri_lab02mpp/loginView.fxml"));

        Parent root = loader.load();

        LoginController loginController = loader.getController();
        loginController.setService(service);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Agentie de turism");
    }

    @FXML
    void handleSearchButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/agentiezboruri_lab02mpp/searchView.fxml"));
        Parent root = loader.load();
        SearchController searchController = loader.getController();
        searchController.setUser(this.user);
        searchController.setService(service);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("User: " + user.getUsername() + " | search");
    }

}
