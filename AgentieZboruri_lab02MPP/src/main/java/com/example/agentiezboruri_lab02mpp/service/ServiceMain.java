package com.example.agentiezboruri_lab02mpp.service;

import com.example.agentiezboruri_lab02mpp.domain.Bilet;
import com.example.agentiezboruri_lab02mpp.domain.User;
import com.example.agentiezboruri_lab02mpp.domain.Zbor;

import java.time.LocalDate;
import java.util.List;

public class ServiceMain {
    private UserService userSrv;
    private ZborService zborService;
    private BiletService biletService;

    public ServiceMain(UserService userSrv, ZborService zborService, BiletService biletService){
        this.userSrv = userSrv;
        this.zborService = zborService;
        this.biletService = biletService;
    }
    public List<User> getAllUser(){
        return this.userSrv.getAll();
    }
    public List<Zbor> getAllZbor(){ return this.zborService.getAll();}
    public List<Bilet> getAllBilet(){ return this.biletService.getAll();}
    public User findUser(String username, String password){
        return this.userSrv.findUser(username, password);
    }
    public List<Zbor> getZborbySearch(String destination, LocalDate date){
        return this.zborService.getZborbySearch(destination, date);
    }
    public List<String> getDestinations(){
        return this.zborService.getDestinations();
    }
    public List<Zbor> getZboruriDisponibile(){ return this.zborService.getZboruriDisponibile();}

    public void modifZborBilet(Zbor zbor, int nrBilete){
        nrBilete = zbor.getNrLocuriLibere() - nrBilete;
        this.zborService.modifZborBilet(zbor, nrBilete);
    }

    public void addBilet(String numeClient, String numeTuristi, String adresa, int nrBilete, Zbor zbor){
        this.biletService.addBilet(numeClient, numeTuristi, adresa, nrBilete, zbor);
        this.modifZborBilet(zbor, nrBilete);
    }
}
