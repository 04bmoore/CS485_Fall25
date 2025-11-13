package edu.loyola.cs485.model.dao;

import edu.loyola.cs485.model.entity.AbstractEntity;

import java.sql.*;

public abstract class AbstractDAO<E extends AbstractEntity> {
    //By using Generics, we can make sure each DAO is bound to a specific Entity

    private String ConUrl = "jdbc:mysql://localhost"; //protocol + url
    private String Port = "3306"; //default MySQL port
    private String Database = "music_db"; // database/schema name
    private String Username = "cs485dev"; //read this from a local file
    private String Password = "csforever"; //Also read this from a file

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(ConUrl+":"+Port+"/"+Database+ "?user="+Username+"&password="+Password);
    }

    // Abstract Methods for each CRUD operation
    public abstract void create(E entity) throws SQLException;
    public abstract E read(int ID) throws SQLException;
    public abstract void update(E entity) throws SQLException;
    public abstract void delete(int ID) throws SQLException;
}