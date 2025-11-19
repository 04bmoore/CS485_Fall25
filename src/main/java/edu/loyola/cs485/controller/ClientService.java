package edu.loyola.cs485.controller;

import edu.loyola.cs485.model.dao.ClientDAO;
import edu.loyola.cs485.model.entity.Client;
import java.util.List;

import java.text.SimpleDateFormat;

/**
 * For an MVC architecture, we need another class to separate the Interface (View) from the DAOs (Model)
 * Even if it seems redudant to you, it is a good practice (make a habit of it).
 */
public class ClientService {

    public Client createClient(String name, String email, String strDob) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        java.sql.Date dob = new java.sql.Date(sdf.parse(strDob).getTime());

        Client client = new Client();
        client.setName(name);
        client.setEmail(email);
        client.setDob(dob);

        ClientDAO dao = new ClientDAO();
        dao.create(client);

        return client;
    }

    public List<Client> getAllClients() throws Exception {
        ClientDAO dao = new ClientDAO();
        return dao.list();
    }

    public void deleteClient(int id) throws Exception {
        ClientDAO dao = new ClientDAO();
        dao.delete(id);
    }
}