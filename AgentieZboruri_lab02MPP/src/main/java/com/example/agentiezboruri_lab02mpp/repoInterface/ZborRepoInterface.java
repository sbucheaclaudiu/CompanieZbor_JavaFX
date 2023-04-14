package com.example.agentiezboruri_lab02mpp.repoInterface;

import com.example.agentiezboruri_lab02mpp.domain.Bilet;
import com.example.agentiezboruri_lab02mpp.domain.Zbor;
import com.example.agentiezboruri_lab02mpp.repository.RepoInterface;

import java.time.LocalDate;
import java.util.List;

public interface ZborRepoInterface extends RepoInterface<Long, Zbor> {
    public List<Zbor> getZborbySearch(String destination, LocalDate date);
    public List<String> getDestinations();
    public List<Zbor> getZboruriDisponibile();
    public void modifZborBilet(Zbor zbor, int nrBilete);
}
