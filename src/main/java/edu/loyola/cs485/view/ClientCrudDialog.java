package edu.loyola.cs485.view;

import edu.loyola.cs485.controller.ClientService;
import edu.loyola.cs485.model.entity.Client;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class ClientCrudDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton deleteButton;
    private JButton updateButton;
    private JList lstClientUI;

    public ClientCrudDialog() {
        setContentPane(contentPane);
        setModal(true);
        //getRootPane().setDefaultButton(buttonOK);
        populateUI();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newClick();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteClick();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClick();
            }
        });
    }

    private void newClick() {
        // add your code here
        ClientInfoDialog dialog = new ClientInfoDialog();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void deleteClick() {
        try{
            ClientService service = new ClientService();
            Client c = (Client) lstClientUI.getSelectedValue();
            if (c != null) {
                service.deleteClient(c.getID());
                lstClientUI.clearSelection();

                // Repopulate the JList to get new data
                populateUI(); // fetch everything again from the DB
            }

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void populateUI() {
        try {
            ClientService service = new ClientService();
            List<Client> lstdata = service.getAllClients();

            lstClientUI.setListData( lstdata.toArray() );

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void updateClick() {
        try{
            ClientService service = new ClientService();
            // Left as an exercise for you to practice

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ClientCrudDialog dialog = new ClientCrudDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
