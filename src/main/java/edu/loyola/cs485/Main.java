package edu.loyola.cs485;

import edu.loyola.cs485.model.dao.ClientDAO;
import edu.loyola.cs485.model.entity.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static String ConUrl = "jdbc:mysql://localhost"; //protocol + url
    static String Port = "3306"; //default MySQL port
    static String Database = "music_db"; // database/schema name

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

    //static String Username = "bsmoore";
    //static String Password = "1223711RMCF$";
    static String url = ConUrl+":"+Port+"/"+Database+ "?user="+Username+"&password="+Password;

    public static void main(String[] args) {

        System.out.println("Hello World!");
        try{
            ClientDAO dao = new ClientDAO();
            //dao.delete(100);
            //Client c = dao.read(102);
            //System.out.println(c.getName() );

            Client newClient = new Client();
            newClient.setName("Example 2");
            newClient.setEmail("ex2@world.com");
            dao.create(newClient);

            System.out.println("Client has been created "+newClient.getID() );
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    public static void secureInsertClient() throws SQLException{
        Connection con = DriverManager.getConnection(url);

        String sql = "INSERT INTO client(name_client,email) VALUES (?,?);";
        // We use question marks for parameters on our SQL

        PreparedStatement pst = con.prepareStatement(sql); //PreparedStatement are SECURE
        pst.setString(1, "CS485 Student"); // Parameter 1 = CS495 Student
        pst.setString(2,"student@loyola.edu"); // Parameter 2 = student@loyola.edu
        pst.executeUpdate(); // executeUpdate() is for INSERT, DELETE, UPDATE

        con.close();
    }

    @Deprecated
    public static void unsecureInsertArtist() throws SQLException{
        Connection con = DriverManager.getConnection(url);

        // We write our SQL inside a String
        String sql = "INSERT INTO artist(name_artist) VALUES ('Elton John');";
        Statement st = con.createStatement(); //Creates an UNSECURE statement
        st.executeUpdate(sql); //executeQuery() are used in SELECTs

        con.close();
    }

    @Deprecated
    public static void unsecureSelectClient() throws SQLException{
        Connection con = DriverManager.getConnection(url);

        String sql = "SELECT * FROM client ORDER BY name_client";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while(rs.next()) { //next() moves to the next row of results

            //Now we can fetch each attribute on the row
            int id = rs.getInt("id_client");
            String name = rs.getString("name_client");
            String email = rs.getString("email");
            Date dob = rs.getDate("dob");

            System.out.printf("%d:%s - %s - %s \n", id, name, email, dob);
        }
        con.close();
    }
}