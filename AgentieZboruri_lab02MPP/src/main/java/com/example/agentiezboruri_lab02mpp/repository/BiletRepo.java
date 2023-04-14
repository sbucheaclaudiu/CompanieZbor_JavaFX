package com.example.agentiezboruri_lab02mpp.repository;

import com.example.agentiezboruri_lab02mpp.domain.Bilet;
import com.example.agentiezboruri_lab02mpp.domain.Zbor;
import com.example.agentiezboruri_lab02mpp.repoInterface.BiletRepoInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class BiletRepo extends RepoAbstract<Long, Bilet> implements BiletRepoInterface {

    public BiletRepo(Properties props, String table_name) {
        super(props, table_name);
    }

    public Bilet createEntity(ResultSet result) throws SQLException {
        Long id = result.getLong("id");
        String numeClient = result.getString("numeClient");
        String numeTuristi = result.getString("numeTuristi");
        String adresa = result.getString("adresa");
        int nrLocuri = result.getInt("nrLocuri");

        Long idZbor = result.getLong("idZ");
        String destinatie = result.getString("destinatie");
        String data = result.getString("data");
        String ora = result.getString("ora");
        String airport = result.getString("airport");
        int nrLocuriLibere = result.getInt("nrLocuriLibere");

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate data2 = LocalDate.parse(data, formatter1);
        LocalTime ora2 = LocalTime.parse(ora, formatter2);

        Zbor zbor = new Zbor(idZbor, destinatie, data2, ora2, airport, nrLocuriLibere);
        return new Bilet(id, numeClient, numeTuristi, adresa, nrLocuri, zbor);
        }

    @Override
    public void update(Bilet bilet) {
        logger.traceEntry("saving task () ", bilet);
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("update Bilet set numeClient = ?, numeTuristi = ?, adresa = ?, nrLocuri = ?, zbor_id = ? where id = ?")) {
            preStmt.setString(1, bilet.getNumeClient());
            preStmt.setString(2, bilet.getNumeTuristi());
            preStmt.setString(3, bilet.getAdresa());
            preStmt.setString(4, String.valueOf(bilet.getNrLocuri()));
            preStmt.setString(5, String.valueOf(bilet.getZbor().getId()));
            preStmt.setString(6, String.valueOf(bilet.getId()));
            int result = preStmt.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
    }

    @Override
    public void insert(Bilet bilet) {
        logger.traceEntry("saving task () ", bilet);
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("insert into Bilet (id, numeClient, numeTuristi, adresa, nrLocuri, zbor_id) values (?,?,?,?,?,?)")) {
            preStmt.setString(1, String.valueOf(bilet.getId()));
            preStmt.setString(2, bilet.getNumeClient());
            preStmt.setString(3, bilet.getNumeTuristi());
            preStmt.setString(4, bilet.getAdresa());
            preStmt.setString(5, String.valueOf(bilet.getNrLocuri()));
            preStmt.setString(6, String.valueOf(bilet.getZbor().getId()));
            int result = preStmt.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
    }

}
