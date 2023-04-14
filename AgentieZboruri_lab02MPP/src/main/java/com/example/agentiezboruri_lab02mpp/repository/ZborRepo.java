package com.example.agentiezboruri_lab02mpp.repository;

import com.example.agentiezboruri_lab02mpp.domain.Bilet;
import com.example.agentiezboruri_lab02mpp.domain.Zbor;
import com.example.agentiezboruri_lab02mpp.repoInterface.ZborRepoInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ZborRepo extends RepoAbstract<Long, Zbor> implements ZborRepoInterface {

    public ZborRepo(Properties props, String table_name) {
        super(props, table_name);
    }

    public Zbor createEntity(ResultSet result) throws SQLException {
        Long id = result.getLong("idZ");
        String destinatie = result.getString("destinatie");
        String data = result.getString("data");
        String ora = result.getString("ora");
        String airport = result.getString("airport");
        int nrLocuriLibere = result.getInt("nrLocuriLibere");

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate data2 = LocalDate.parse(data, formatter1);
        LocalTime ora2 = LocalTime.parse(ora, formatter2);

        return new Zbor(id, destinatie, data2, ora2, airport, nrLocuriLibere);
    }

    @Override
    public void update(Zbor zbor) {
        logger.traceEntry("saving task () ", zbor);
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("update Zbor set destinatie = ?, data = ?, ora = ?, airport = ?, nrLocuriLibere = ? where idZ = ?")) {
            preStmt.setString(1, zbor.getDestination());
            preStmt.setString(2, String.valueOf(zbor.getData()));
            preStmt.setString(3, String.valueOf(zbor.getTime()));
            preStmt.setString(4, zbor.getAirport());
            preStmt.setString(5, String.valueOf(zbor.getNrLocuriLibere()));
            preStmt.setString(6, String.valueOf(zbor.getId()));
            int result = preStmt.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
    }

    @Override
    public void insert(Zbor zbor) {
        logger.traceEntry("saving task () ", zbor);
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("insert into Zbor (idZ, destinatie, data, ora, airport, nrLocuriLibere) values (?,?,?,?,?,?)")) {
            preStmt.setString(1, String.valueOf(zbor.getId()));
            preStmt.setString(2, zbor.getDestination());
            preStmt.setString(3, String.valueOf(zbor.getData()));
            preStmt.setString(4, String.valueOf(zbor.getTime()));
            preStmt.setString(5, zbor.getAirport());
            preStmt.setString(6, String.valueOf(zbor.getNrLocuriLibere()));
            int result = preStmt.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
    }

    @Override
    public List<Zbor> getZborbySearch(String destination, LocalDate date) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Zbor> lst = new ArrayList<>();

        try (PreparedStatement preStmt = con.prepareStatement("select * from Zbor where destinatie = ? and data = ? and nrLocuriLibere != 0")) {
            preStmt.setString(1, destination);
            preStmt.setString(2, String.valueOf(date));
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    Zbor entity = createEntity(result);
                    lst.add(entity);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }
        return lst;
    }

    @Override
    public List<String> getDestinations() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<String> lst = new ArrayList<>();

        try (PreparedStatement preStmt = con.prepareStatement("select * from Zbor")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    String destinatie = result.getString("destinatie");
                    lst.add(destinatie);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }
        return lst;
    }

    @Override
    public List<Zbor> getZboruriDisponibile() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Zbor> lst = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("select * from Zbor where nrLocuriLibere != 0")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    Zbor entity = createEntity(result);
                    lst.add(entity);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }
        return lst;
    }

    @Override
    public void modifZborBilet(Zbor zbor, int nrBilete) {
        logger.traceEntry("saving task () ", zbor);
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("update Zbor set nrLocuriLibere = ? where idZ = ?")) {
            preStmt.setString(1, String.valueOf(nrBilete));
            preStmt.setString(2, String.valueOf(zbor.getId()));
            int result = preStmt.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
    }
}
