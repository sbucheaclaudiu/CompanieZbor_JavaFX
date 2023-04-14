package com.example.agentiezboruri_lab02mpp.repository;

import com.example.agentiezboruri_lab02mpp.domain.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public abstract class RepoAbstract<ID, E extends Entity<ID>> implements RepoInterface<ID, E> {
    public JdbcUtils dbUtils;

    public static final Logger logger = LogManager.getLogger();

    public String tableName;

    public RepoAbstract(Properties props, String table_name) {
        logger.info("Initializing AgentieZboruriDBRepository with properties: {} ", props);
        dbUtils = new JdbcUtils(props);
        this.tableName = table_name;
    }


    @Override
    public List<E> getAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<E> lst = new ArrayList<>();

        if (Objects.equals(this.tableName, "Bilet")) {
            try (PreparedStatement preStmt = con.prepareStatement("select * from Bilet INNER JOIN Zbor ON Bilet.zbor_id = Zbor.idZ")) {
                try (ResultSet result = preStmt.executeQuery()) {
                    while (result.next()) {
                        E entity = createEntity(result);
                        lst.add(entity);
                    }
                }
            } catch (SQLException e) {
                logger.error(e);
                System.err.println("Error DB " + e);
            }

        } else {
            try (PreparedStatement preStmt = con.prepareStatement("select * from " + this.tableName)) {
                try (ResultSet result = preStmt.executeQuery()) {
                    while (result.next()) {
                        E entity = createEntity(result);
                        lst.add(entity);
                    }
                }
            } catch (SQLException e) {
                logger.error(e);
                System.err.println("Error DB " + e);
            }
        }
        logger.traceExit(lst);
        return lst;
    }

    public abstract E createEntity(ResultSet result) throws SQLException;

    @Override
    public E getById(ID entityId) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        if (Objects.equals(tableName, "Zbor")) {
            try (PreparedStatement preStmt = con.prepareStatement("select * from Zbor where idZ = ?")) {
                preStmt.setString(1, String.valueOf(entityId));
                try (ResultSet result = preStmt.executeQuery()) {
                    return createEntity(result);
                }
            } catch (SQLException ex) {
                logger.error(ex);
                System.err.println("Error DB " + ex);
            }
        } else if (Objects.equals(tableName, "Bilet")) {
            try (PreparedStatement preStmt = con.prepareStatement("select * from Bilet INNER JOIN Zbor ON Bilet.zbor_id = Zbor.idZ where id = ?")) {
                preStmt.setString(1, String.valueOf(entityId));
                try (ResultSet result = preStmt.executeQuery()) {
                    return createEntity(result);
                }
            } catch (SQLException ex) {
                logger.error(ex);
                System.err.println("Error DB " + ex);
            }
        } else if (Objects.equals(tableName, "User")) {
            try (PreparedStatement preStmt = con.prepareStatement("select * from User where username = ?")) {
                preStmt.setString(1, String.valueOf(entityId));
                try (ResultSet result = preStmt.executeQuery()) {
                    return createEntity(result);
                }
            } catch (SQLException ex) {
                logger.error(ex);
                System.err.println("Error DB " + ex);
            }
        }
        logger.traceExit();
        return null;
    }

    @Override
    public abstract void update(E updateEntity);

    @Override
    public abstract void insert(E newEntity);

    @Override
    public void delete(E deleteEntity) {
        logger.traceEntry("saving task () ");
        Connection con = dbUtils.getConnection();
        if (Objects.equals(tableName, "Zbor")) {
            try (PreparedStatement preStmt = con.prepareStatement("delete from " + this.tableName + " where idZ = ?")) {
                preStmt.setString(1, String.valueOf(deleteEntity.getId()));
                int result = preStmt.executeUpdate();
                logger.trace("Saved {} instances", result);
            } catch (SQLException ex) {
                logger.error(ex);
                System.err.println("Error DB " + ex);
            }
        }
        else if (Objects.equals(tableName, "Bilet")) {
            try (PreparedStatement preStmt = con.prepareStatement("delete from " + this.tableName + " where id = ?")) {
            preStmt.setString(1, String.valueOf(deleteEntity.getId()));
            int result = preStmt.executeUpdate();
            logger.trace("Saved {} instances", result);
            } catch(SQLException ex){
                logger.error(ex);
                System.err.println("Error DB " + ex);
            }
        }
        else if (Objects.equals(tableName, "User")) {
            try (PreparedStatement preStmt = con.prepareStatement("delete from " + this.tableName + " where username = ?")) {
                preStmt.setString(1, (String) deleteEntity.getId());
                int result = preStmt.executeUpdate();
                logger.trace("Saved {} instances", result);
            } catch(SQLException ex){
                logger.error(ex);
                System.err.println("Error DB " + ex);
            }
        }
        logger.traceExit();
    }
}
