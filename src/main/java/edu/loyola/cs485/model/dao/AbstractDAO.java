package edu.loyola.cs485.model.dao;

import edu.loyola.cs485.model.entity.AbstractEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public abstract class AbstractDAO<E extends AbstractEntity> {
    //By using Generics, we can make sure each DAO is bound to a specific Entity

    private String ConUrl = "jdbc:mysql://localhost"; //protocol + url
    private String Port = "3306"; //default MySQL port
    private String Database = "music_db"; // database/schema name

    public static String ReadLine(String filePath){
        String processedLineFromFile;
        try {
            // Specify the path to your text file
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // Read the first line from the file
            String line = reader.readLine();

            // Remove all spaces from the line if it's not null
            if (line != null) {
                processedLineFromFile = line.replaceAll("\\s+", "");
            } else {
                processedLineFromFile = ""; // Handle empty file case
            }

            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            processedLineFromFile = ""; // Set to empty string in case of error
        }
        return processedLineFromFile;
    }
    static String Username = ReadLine("C:\\Users\\brend\\OneDrive - Loyola University Maryland\\Junior\\Fall 25\\Database\\CS485_Fall25\\user.txt"); //read this from a local file
    static String Password = ReadLine("C:\\Users\\brend\\OneDrive - Loyola University Maryland\\Junior\\Fall 25\\Database\\CS485_Fall25\\pass.txt"); //Also read this from a file

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(ConUrl+":"+Port+"/"+Database+ "?user="+Username+"&password="+Password);
    }

    //Adding this method to the AbstractDAO class
    public void setTestDB(){
        this.Database ="music_db_test"; //Adding _test to DB Name
    }

    // Abstract Methods for each CRUD operation
    public abstract void create(E entity) throws SQLException;
    public abstract E read(int ID) throws SQLException;
    public abstract void update(E entity) throws SQLException;
    public abstract void delete(int ID) throws SQLException;
}