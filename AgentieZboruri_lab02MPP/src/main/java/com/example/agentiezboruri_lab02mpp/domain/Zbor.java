package com.example.agentiezboruri_lab02mpp.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Zbor extends Entity<Long>{
    private String destination;
    private LocalDate data;
    private LocalTime time;
    private String airport;
    private int nrLocuriLibere;

    public Zbor(Long id, String destination, LocalDate data, LocalTime time, String airport, int nrLocuriLibere) {
        super(id);
        this.destination = destination;
        this.data = data;
        this.time = time;
        this.airport = airport;
        this.nrLocuriLibere = nrLocuriLibere;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public int getNrLocuriLibere() {
        return nrLocuriLibere;
    }

    public void setNrLocuriLibere(int nrLocuriLibere) {
        this.nrLocuriLibere = nrLocuriLibere;
    }

    @Override
    public String toString() {
        return "Zbor{" +
                "destination='" + destination + '\'' +
                ", data=" + data +
                ", time=" + time +
                ", airport='" + airport + '\'' +
                ", nrLocuriLibere=" + nrLocuriLibere +
                '}';
    }
}
