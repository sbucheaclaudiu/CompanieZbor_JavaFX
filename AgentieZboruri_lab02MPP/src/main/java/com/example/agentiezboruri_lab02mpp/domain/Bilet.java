package com.example.agentiezboruri_lab02mpp.domain;

public class Bilet extends Entity<Long>{
    private String numeClient;
    private String numeTuristi;
    private String adresa;
    private int nrLocuri;
    private Zbor zbor;

    public Bilet(Long id, String numeClient, String numeTuristi, String adresa, int nrLocuri, Zbor zbor) {
        super(id);
        this.numeClient = numeClient;
        this.numeTuristi = numeTuristi;
        this.adresa = adresa;
        this.nrLocuri = nrLocuri;
        this.zbor = zbor;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getNumeTuristi() {
        return numeTuristi;
    }

    public void setNumeTuristi(String numeTuristi) {
        this.numeTuristi = numeTuristi;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    public Zbor getZbor() {
        return zbor;
    }

    public void setZbor(Zbor zbor) {
        this.zbor = zbor;
    }

    @Override
    public String toString() {
        return "Bilet{" +
                "numeClient='" + numeClient + '\'' +
                ", numeTuristi='" + numeTuristi + '\'' +
                ", adresa='" + adresa + '\'' +
                ", nrLocuri=" + nrLocuri +
                ", zbor=" + zbor +
                '}';
    }
}
