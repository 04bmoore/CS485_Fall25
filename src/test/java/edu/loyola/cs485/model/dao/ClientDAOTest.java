package edu.loyola.cs485.model.dao;
import edu.loyola.cs485.model.entity.Client;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;
import java.util.List;
//import java.util.List;

public class ClientDAOTest {

    @Test
    public void testFake(){
        assertAll(
                () -> assertEquals(1,1)
            );
    }

    @Test
    public void testCreateClient() throws SQLException {
        ClientDAO dao = new ClientDAO();
        Client client = new Client();
        client.setName("Test Client");
        client.setEmail("test@email.com");

        dao.create(client);

        assertAll(
                () -> assertEquals(1,1)
        );
    }


    @Test
    public void testCreate() throws SQLException {
        ClientDAO dao = new ClientDAO();
        dao.setTestDB();
        Client entity = new Client();
        entity.setName("Test Name");
        entity.setEmail("test@test.com");

        dao.create(entity);
        dao.delete(entity.getID()); //Clean up
        assertAll(
                ()-> assertNotNull( entity.getID() )
        );
    }


    @Test
    public void testListClient() throws Exception {
        ClientDAO dao = new ClientDAO();
        dao.setTestDB();

        List<Client> lst = dao.list();
        assertAll(
                () -> assertEquals(1,1)
        );
    }


}
