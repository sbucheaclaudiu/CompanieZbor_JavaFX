package com.example.agentiezboruri_lab02mpp.controller;

import com.example.agentiezboruri_lab02mpp.domain.User;
import com.example.agentiezboruri_lab02mpp.domain.Zbor;
import com.example.agentiezboruri_lab02mpp.service.ServiceMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Objects;

public class BuyController {

    @FXML
    private TextField adresaField;

    @FXML
    private TextField clientField;

    @FXML
    private Button cumparaButton;

    @FXML
    private Spinner<Integer> nrLocuriSpinner;

    @FXML
    private TextField turistiField;

    private ServiceMain service;

    private Zbor zbor;

    public void setService(ServiceMain service){
        this.service = service;
        int nrLocuriLibere = this.zbor.getNrLocuriLibere();

        this.nrLocuriSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, nrLocuriLibere)
                );
    }
    public void setZbor(Zbor zbor){
        this.zbor = zbor;
    }

    @FXML
    void handleCumparaButton(ActionEvent event) {
        String numeClient = this.clientField.getText();
        String numeTuristi = this.turistiField.getText();
        String adresa = this.adresaField.getText();
        int nrBilete = this.nrLocuriSpinner.getValue();

        if (Objects.equals(numeClient, "") || Objects.equals(numeTuristi, "") || Objects.equals(adresa, "")) {
            PopupMessageController.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Completati datele de mai sus!");

        }
        else {
            try {
                this.service.addBilet(numeClient, numeTuristi, adresa, nrBilete, this.zbor);
                PopupMessageController.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Bilet cumparat cu succes!");
            } catch (Exception e) {
                PopupMessageController.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Failed!");
            }
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

}
