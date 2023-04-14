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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class SearchController implements Initializable {

    @FXML
    private TableColumn<Zbor, String> airportColumn;

    @FXML
    private Button buyButton;

    @FXML
    private TableColumn<Zbor, LocalDate> dateColumn;

    @FXML
    private DatePicker datePicket;

    @FXML
    private ComboBox<String> destinatieComboBox;

    @FXML
    private TableColumn<Zbor, String> destinationColumn;

    @FXML
    private TableColumn<Zbor, Integer> freeSeatsColumn;

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
        List<String> lst_destination = this.service.getDestinations();
        ObservableList<String> list = FXCollections.observableList(lst_destination);

        this.destinatieComboBox.setItems(list);
    }

    void refresh1(String destination, LocalDate date){
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                fillZbor(destination, date);
            }
        }, 0, 5000);
    }

    public void setUser(User user){
        this.user = user;
    }

    void fillZbor(String destination, LocalDate date){
        List<Zbor> lst_zbor = service.getZborbySearch(destination, date);

        ObservableList<Zbor> list = FXCollections.observableList(lst_zbor);

        zborTableView.setItems(list);
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
        stage.setTitle("User: " + user.getUsername() + " | Search | Buy Ticket");
    }

    @FXML
    void handleSearchButton(ActionEvent event) {
        String destination = destinatieComboBox.getValue();
        LocalDate date = datePicket.getValue();

        fillZbor(destination, date);
        refresh1(destination, date);
    }

}

