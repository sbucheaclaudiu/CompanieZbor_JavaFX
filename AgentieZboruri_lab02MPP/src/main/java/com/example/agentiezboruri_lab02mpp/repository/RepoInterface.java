package com.example.agentiezboruri_lab02mpp.repository;

import com.example.agentiezboruri_lab02mpp.domain.Entity;

import java.util.List;

public interface RepoInterface<ID, E extends Entity<ID>> {
    /**
     * Returneaza toate entitatile din BD
     */
    List<E> getAll();

    /**
     * Returneaza entitatea cu id-ul dat
     */
    E getById(ID entityId);

    /**
     * Modifica entitatea din BD
     */
    void update(E updateEntity) throws Exception;

    /**
     * Adauga o entitate noua in BD
     */
    void insert(E newEntity);

    /**
     * Sterge entitatea din BD
     */
    void delete(E deleteEntity);
}
