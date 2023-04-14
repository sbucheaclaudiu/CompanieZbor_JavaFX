package com.example.agentiezboruri_lab02mpp.repository;

import com.example.agentiezboruri_lab02mpp.domain.Bilet;
import com.example.agentiezboruri_lab02mpp.domain.User;
import com.example.agentiezboruri_lab02mpp.repoInterface.UserRepoInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserRepo extends RepoAbstract<String, User> implements UserRepoInterface {

    public UserRepo(Properties props, String table_name) {
        super(props, table_name);
    }

    public User createEntity(ResultSet result) throws SQLException {
        String username = result.getString("username");
        String password = result.getString("password");
        return new User(username, password);
    }

    @Override
    public void update(User user) {
        logger.traceEntry("saving task () ", user);
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("update User set password = ? where username = ?")) {
            preStmt.setString(1, user.getPassword());
            preStmt.setString(2, user.getUsername());
            int result = preStmt.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
    }

    public void insert(User user){
        logger.traceEntry("saving task () ", user);
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("insert into User (username, password) values (?,?)")) {
            preStmt.setString(1, user.getUsername());
            preStmt.setString(2, user.getPassword());
            int result = preStmt.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
    }

    @Override
    public User findUser(String username, String password) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();

        try (PreparedStatement preStmt = con.prepareStatement("select * from User where username = ? and password = ?")) {
            preStmt.setString(1, username);
            preStmt.setString(2, password);
            try (ResultSet result = preStmt.executeQuery()) {
                return createEntity(result);
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
        return null;
    }
}
