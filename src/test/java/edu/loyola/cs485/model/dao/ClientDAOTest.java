package edu.loyola.cs485.model.dao;
import edu.loyola.cs485.model.entity.Client;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;
import java.util.List;

public class ClientDAOTest {

    public class ClientDAOTest {

        @Test
        public void testFake(){
            assertAll(
                    () -> assertEquals(1,1)
            );
        }

        @Test
        public void testCreateClient() throws Exception{
            ClientDAO dao = new ClientDAO();
            dao.setTestDatabase();

            Client client = new Client();
            client.setName("Test Client");
            client.setEmail("test@email.com");

            dao.create(client); // Method Under Test

            //clean up
            dao.delete( client.getID() );

            assertAll(
                    () -> assertNotNull( client.getID() )
            );
        }

        @Test
        public void testReadClient() throws Exception{
            ClientDAO dao = new ClientDAO();
            dao.setTestDatabase();

            Client client = new Client();
            client.setName("Test Client");
            client.setEmail("test@email.com");

            dao.create(client);
            Client found = dao.read(client.getID()); //Function under test

            //clean up
            dao.delete( client.getID() );

            assertAll(
                    () -> assertEquals(found.getID(), client.getID()),
                    () -> assertEquals(found.getName(), client.getName()),
                    () -> assertEquals(found.getEmail(), client.getEmail()),
                    () -> assertEquals(found.getDob(), client.getDob())
            );

        }

        @Test
        public void testReadClientDoesNotExist() throws Exception{
            ClientDAO dao = new ClientDAO();
            dao.setTestDatabase();

            Client found = dao.read(10);
            assertAll(
                    () -> assertNull( found.getID() ),
                    () -> assertNull( found.getName() )
            );
        }

        @Test
        public void testListClient() throws Exception{
            ClientDAO dao = new ClientDAO();
            dao.setTestDatabase();

            List<Client> lst = dao.list();
            assertAll(
                    () -> assertEquals(0, lst.size())
            );

        }
    }