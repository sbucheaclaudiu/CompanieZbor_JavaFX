package com.example.agentiezboruri_lab02mpp.service;

import com.example.agentiezboruri_lab02mpp.domain.Bilet;
import com.example.agentiezboruri_lab02mpp.domain.User;
import com.example.agentiezboruri_lab02mpp.domain.Zbor;
import com.example.agentiezboruri_lab02mpp.repoInterface.BiletRepoInterface;
import com.example.agentiezboruri_lab02mpp.repository.RepoInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BiletService {
    protected final BiletRepoInterface repo;
    public BiletService(BiletRepoInterface repo){
        this.repo = repo;
    }
    public List<Bilet> getAll(){
        return this.repo.getAll();
    }

    public void addBilet(String numeClient, String numeTuristi, String adresa, int nrBilete, Zbor zbor){
        List<Bilet> lst = this.getAll();
        Long max = 1L;
        if(lst.size() != 0){
            List<Long> lst_id = new ArrayList<>();
            for(Bilet m : lst){
                lst_id.add(m.getId());
            }
            max = Collections.max(lst_id, null);
        }

        Bilet bilet = new Bilet(max+1, numeClient, numeTuristi, adresa, nrBilete, zbor);
        this.repo.insert(bilet);
    }
}
