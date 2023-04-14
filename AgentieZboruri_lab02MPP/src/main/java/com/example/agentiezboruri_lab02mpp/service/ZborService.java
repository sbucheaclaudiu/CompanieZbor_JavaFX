package com.example.agentiezboruri_lab02mpp.service;

import com.example.agentiezboruri_lab02mpp.domain.User;
import com.example.agentiezboruri_lab02mpp.domain.Zbor;
import com.example.agentiezboruri_lab02mpp.repoInterface.ZborRepoInterface;
import com.example.agentiezboruri_lab02mpp.repository.RepoInterface;
import com.example.agentiezboruri_lab02mpp.repository.ZborRepo;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ZborService {
    protected final ZborRepoInterface repo;

    public ZborService(ZborRepoInterface repo){
        this.repo = repo;
    }

    public List<Zbor> getAll(){
        return this.repo.getAll();
    }

    public List<Zbor> getZboruriDisponibile(){
        return this.repo.getZboruriDisponibile();
    }

    public List<Zbor> getZborbySearch(String destination, LocalDate date){
        return this.repo.getZborbySearch(destination, date);
    }

    public List<String> getDestinations(){
        List<String> lst = this.repo.getDestinations();
        Set<String> set = new LinkedHashSet<>();
        set.addAll(lst);
        lst.clear();
        lst.addAll(set);
        return lst;
    }

    public void modifZborBilet(Zbor zbor, int nrBilete){
        this.repo.modifZborBilet(zbor, nrBilete);
    }
}
