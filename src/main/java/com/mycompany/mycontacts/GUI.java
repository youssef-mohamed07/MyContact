package com.mycompany.mycontacts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame implements ActionListener, MouseListener {

    RoundedTextField namefField;
    RoundedTextField namelField;
    RoundedTextField emailField;
    RoundedTextField mobilePhoneField;
    RoundedTextField homePhoneField;
    RoundedTextField AddressField;
    RoundedTextField searchlField;
    RoundedTextField countField;
    RoundedButton addButton;
    RoundedButton deleteButton;
    RoundedButton updateButton;
    RoundedButton clearButton;
    JRadioButton searchButton;
    RoundedButton editButton;
    DefaultTableModel model;
    @SuppressWarnings("rawtypes")
    JComboBox searchComboBox;
    JToggleButton dark;
    JTable table;
    public static JLabel label;
    public static JLabel label2;
    public static JLabel label4;
    public static JLabel label6;
    int count = 0;
    static Clip clip;
    RoundedPanel topPanel;
    RoundedPanel addPanel;
    JPanel SearchBarPanel;
    private ContactDAO contactDAO;
    private UserDAO userDAO;
    private User currentUser;
    protected int selectedContactId = -1;


    GUI(User user) {
        this.currentUser = user;
        contactDAO = new ContactDAO();
        userDAO = new UserDAO();
        
        // Update user info in side panel
        label.setText(user.getUsername());
        label2.setText(user.getPhone());
        label4.setText(user.getWork());
        label6.setText(user.getEmail());
        
        // SidePanel

        // ImageIcon
        ImageIcon person = new ImageIcon(getClass().getResource("/user.png"));
        ImageIcon search = new ImageIcon(getClass().getResource("/search.png"));
        ImageIcon iTitle = new ImageIcon(getClass().getResource("/laptop.png"));

        if (person.getImage() == null) person = new ImageIcon("user.png");
        if (search.getImage() == null) search = new ImageIcon("search.png");
        if (iTitle.getImage() == null) iTitle = new ImageIcon("laptop.png");

        // label
        label = new JLabel();
        label.setBounds(60, 70, 200, 200);
        label.setIcon(person);
        label.setText("Yousef Ashraf");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.white);
        label.setFont(new Font("Corbel", Font.CENTER_BASELINE, 28));
        label.setIconTextGap(30);

        JLabel label1 = new JLabel();
        label1.setText("Mobile Phone");
        label1.setBounds(20, 400, 200, 20);
        label1.setFont(new Font("Corbel", Font.BOLD, 20));
        label1.setForeground(Color.white);

        label2 = new JLabel();
        label2.setText("+20 xx-xxxx-xxxx");
        label2.setBounds(20, 430, 200, 20);
        label2.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 13));
        label2.setForeground(new Color(180, 180, 180));

        JLabel label3 = new JLabel();
        label3.setText("Work");
        label3.setBounds(20, 500, 200, 20);
        label3.setFont(new Font("Corbel", Font.BOLD, 20));
        label3.setForeground(Color.white);

        label4 = new JLabel();
        label4.setText("+20 xx-xxxx-xxxx");
        label4.setBounds(20, 530, 200, 15);
        label4.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 13));
        label4.setForeground(new Color(180, 180, 180));

        JLabel label5 = new JLabel();
        label5.setText("Email");
        label5.setBounds(20, 600, 200, 20);
        label5.setFont(new Font("Corbel", Font.BOLD, 20));
        label5.setForeground(Color.white);

        label6 = new JLabel();
        label6.setText("Yousif012077@gmail.com");
        label6.setBounds(20, 630, 200, 20);
        label6.setFont(new Font("Corbel", Font.CENTER_BASELINE, 15));
        label6.setForeground(new Color(180, 180, 180));

        // button
        editButton = new RoundedButton();
        editButton.setBounds(75, 700, 130, 35);
        editButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        editButton.setText("Edit");
        editButton.setBackground(new Color(26, 140, 89));
        editButton.setForeground(Color.white);
        editButton.setFocusable(false);
        editButton.setBorderPainted(false);
        editButton.addActionListener(this);

        // panel
        GradientPanel sidePanel = new GradientPanel();
        sidePanel.setBounds(0, 0, 300, 840);
        sidePanel.setLayout(null);
        Border sideBorder = BorderFactory.createCompoundBorder();
        sidePanel.setBorder(sideBorder);
        sidePanel.add(label);
        sidePanel.add(editButton);
        sidePanel.add(label1);
        sidePanel.add(label2);
        sidePanel.add(label3);
        sidePanel.add(label4);
        sidePanel.add(label5);
        sidePanel.add(label6);

        // Finish SidePanal

        // AddPanel

        // TextField
        namefField = new RoundedTextField();
        namefField.setBounds(155, 131, 210, 30);
        namefField.setFont(new Font("Corbel", Font.PLAIN, 18));
        namefField.setBackground(new Color(236, 236, 236));

        namelField = new RoundedTextField();
        namelField.setBounds(155, 186, 210, 30);
        namelField.setFont(new Font("Corbel", Font.PLAIN, 18));
        namelField.setBackground(new Color(236, 236, 236));

        emailField = new RoundedTextField();
        emailField.setBounds(155, 241, 210, 30);
        emailField.setFont(new Font("Corbel", Font.PLAIN, 18));
        emailField.setBackground(new Color(236, 236, 236));

        mobilePhoneField = new RoundedTextField();
        mobilePhoneField.setBounds(600, 131, 210, 30);
        mobilePhoneField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mobilePhoneField.setBackground(new Color(236, 236, 236));

        homePhoneField = new RoundedTextField();
        homePhoneField.setBounds(600, 186, 210, 30);
        homePhoneField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        homePhoneField.setBackground(new Color(236, 236, 236));

        AddressField = new RoundedTextField();
        AddressField.setBounds(600, 241, 210, 30);
        AddressField.setFont(new Font("Corbel", Font.PLAIN, 18));
        AddressField.setBackground(new Color(236, 236, 236));

        // Label
        JLabel label7 = new JLabel();
        label7.setText("Frist Name:");
        label7.setBounds(45, 124, 220, 40);
        label7.setForeground(new Color(55, 54, 53));
        label7.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel label8 = new JLabel();
        label8.setText("Last Name:");
        label8.setBounds(45, 179, 220, 40);
        label8.setForeground(new Color(55, 54, 53));
        label8.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel label9 = new JLabel();
        label9.setText("Email:");
        label9.setBounds(85, 234, 220, 40);
        label9.setForeground(new Color(55, 54, 53));
        label9.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel labelAddress = new JLabel();
        labelAddress.setText("Address:");
        labelAddress.setBounds(515, 235, 220, 40);
        labelAddress.setForeground(new Color(55, 54, 53));
        labelAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel label10 = new JLabel();
        label10.setText("Mobile phone:");
        label10.setBounds(470, 124, 220, 40);
        label10.setForeground(new Color(55, 54, 53));
        label10.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel label11 = new JLabel();
        label11.setText("Home phone:");
        label11.setBounds(475, 179, 220, 40);
        label11.setForeground(new Color(55, 54, 53));
        label11.setFont(new Font("Tahoma", Font.PLAIN, 18));

        // Buttons
        clearButton = new RoundedButton();
        clearButton.setBounds(450, 350, 130, 57);
        clearButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        clearButton.setText("Clear");
        clearButton.setBackground(new Color(97, 44, 81));
        clearButton.setForeground(Color.white);
        clearButton.setFocusable(false);
        clearButton.addActionListener(this);
        clearButton.setBorderPainted(false);

        deleteButton = new RoundedButton();
        deleteButton.setBounds(610, 350, 130, 57);
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        deleteButton.setText("Delete");
        deleteButton.setBackground(new Color(111, 30, 53));
        deleteButton.setForeground(Color.white);
        deleteButton.setFocusable(false);
        deleteButton.setBorderPainted(false);
        deleteButton.addActionListener(this);

        updateButton = new RoundedButton();
        updateButton.setBounds(830, 350, 130, 57);
        updateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        updateButton.setText("Update");
        updateButton.setBackground(new Color(41, 95, 69));
        updateButton.setForeground(Color.white);
        updateButton.setFocusable(false);
        updateButton.setBorderPainted(false);
        updateButton.addActionListener(this);

        addButton = new RoundedButton();
        addButton.setBounds(990, 350, 130, 57);
        addButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        addButton.setText("Add");
        addButton.setBackground(new Color(41, 58, 96));
        addButton.setForeground(Color.white);
        addButton.setFocusable(false);
        addButton.setBorderPainted(false);
        addButton.addActionListener(this);

        // panel
        addPanel = new RoundedPanel();
        addPanel.setBounds(320, 350, 1175, 440);
        addPanel.setLayout(null);
        addPanel.setBackground(Color.white);
        addPanel.setPreferredSize(new Dimension(400, 200));
        addPanel.add(namefField);
        addPanel.add(namelField);
        addPanel.add(emailField);
        addPanel.add(mobilePhoneField);
        addPanel.add(homePhoneField);
        addPanel.add(AddressField);
        addPanel.add(clearButton);
        addPanel.add(deleteButton);
        addPanel.add(updateButton);
        addPanel.add(addButton);
        addPanel.add(label7);
        addPanel.add(label8);
        addPanel.add(label9);
        addPanel.add(label10);
        addPanel.add(label11);
        addPanel.add(labelAddress);

        // Finish addPanal

        // SearchBarPanel

        // TextFiled
        searchlField = new RoundedTextField();
        searchlField.setBounds(240, 3, 385, 40);
        searchlField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        searchlField.setBackground(new Color(236, 236, 236));
        searchlField.setCaretColor(Color.black);

        // comboBox
        String[] items = { "Name", "Email", "Mobile Phone", "Home Phone", "City" };
        searchComboBox = new JComboBox<String>(items);
        searchComboBox.setBounds(70, 10, 130, 30);
        searchComboBox.setFont(new Font("Arial", Font.PLAIN, 17));
        searchComboBox.setBackground(new Color(236, 236, 236));
        searchComboBox.setForeground(new Color(55, 54, 53));
        searchComboBox.setFocusable(false);

        // Radio button
        searchButton = new JRadioButton();
        searchButton.setIcon(search);
        searchButton.setBackground(Color.white);
        searchButton.setBounds(630, 6, 50, 35);
        searchButton.addActionListener(this);
        searchButton.setFocusable(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        // panel
        JPanel SearchBarPanel = new JPanel();
        SearchBarPanel.setBounds(340, 25, 850, 46);
        SearchBarPanel.setLayout(null);
        SearchBarPanel.setBackground(Color.white);
        Border SearchBarBorder = BorderFactory.createCompoundBorder();
        SearchBarPanel.setBorder(SearchBarBorder);
        SearchBarPanel.add(searchButton);
        SearchBarPanel.add(searchlField);
        SearchBarPanel.add(searchComboBox);

        // Finish SearchBarPanel

        // tablePanel

        // Table
        String coulmn[] = { "First Name", "Last Name", "Email", "Mobile phone", "Home phone", "Address" };
        String data[][] = {};
        model = new DefaultTableModel(data, coulmn);
        table = new JTable(model);
        table.getTableHeader().setPreferredSize(new Dimension(0, 55));
        table.getTableHeader().setFont(new Font("Arial Rounded MT", Font.BOLD, 16));
        table.getTableHeader().setForeground(Color.white);
        table.getTableHeader().setBackground(new Color(39, 85, 77));
        table.setRowHeight(30);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.addMouseListener(this);

        // panel
        RoundedPanel tablePanel = new RoundedPanel();
        tablePanel.setBounds(340, 100, 1135, 350);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBackground(Color.lightGray);
        Border tableBorderPanel = BorderFactory.createEmptyBorder();
        tablePanel.setBorder(tableBorderPanel);
        tablePanel.add(new JScrollPane(table));

        // Finish tablePanel

        // Start TopPanel

        // count
        countField = new RoundedTextField();
        countField.setBounds(1020, 25, 45, 30);
        countField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        countField.setBackground(new Color(236, 236, 236));
        countField.setText(String.valueOf(count));
        countField.setFocusable(false);
        countField.setCaretColor(Color.black);

        // label
        JLabel labelCount = new JLabel("Contacts:");
        labelCount.setBounds(920, 23, 100, 30);
        labelCount.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelCount.setBackground(Color.white);

        // panel
        topPanel = new RoundedPanel();
        topPanel.setBounds(320, 10, 1175, 440);
        topPanel.setLayout(null);
        topPanel.setBackground(Color.white);
        topPanel.setPreferredSize(new Dimension(400, 200));
        topPanel.add(countField);
        topPanel.add(labelCount);

        // Finish TopPanel

        // Start behindPanel

        GradientPanel behindPanel = new GradientPanel();
        behindPanel.setBounds(337, 85, 1141, 353);
        behindPanel.setLayout(null);
        Border behindPanelBorder = BorderFactory.createCompoundBorder();
        tablePanel.setBorder(behindPanelBorder);

        // Finish behindPanel

        // Frame
        this.setSize(1530, 840);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.add(tablePanel);
        this.setTitle("My Contacts");
        this.setIconImage(iTitle.getImage());
        this.add(SearchBarPanel);
        this.add(topPanel);
        this.add(addPanel);
        this.add(sidePanel);

        // Load contacts from database for current user
        loadContactsFromDatabase();

        this.setVisible(true);

    }

    private void loadContactsFromDatabase() {
        List<Contacts> contacts = contactDAO.getAllContacts(currentUser.getId());
        model.setRowCount(0); // Clear existing rows
        for (Contacts contact : contacts) {
            String[] row = {
                contact.getFirstName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getMobilePhone(),
                contact.getHomePhone(),
                contact.getAddress()
            };
            model.addRow(row);
        }
        count = model.getRowCount();
        countField.setText(String.valueOf(count));
    }

    // Buttons
    @Override
    public void actionPerformed(ActionEvent e) {

        // AddButton
        if (e.getSource() == addButton) {
            Contacts contact;
            Contacts.valid = true;
            if (homePhoneField.getText().isEmpty() && AddressField.getText().isEmpty()) {
                contact = new Contacts(namefField.getText(), namelField.getText(), emailField.getText(),
                        mobilePhoneField.getText());
            } else if (AddressField.getText().isEmpty()) {
                contact = new Contacts(namefField.getText(), namelField.getText(), emailField.getText(),
                        mobilePhoneField.getText(), homePhoneField.getText());
            } else {
                contact = new Contacts(namefField.getText(), namelField.getText(), emailField.getText(),
                        mobilePhoneField.getText(), homePhoneField.getText(), AddressField.getText());
            }

            if (contact.valid) {
                if (contactDAO.createContact(contact, currentUser.getId())) {
                    String[] row = {
                        contact.getFirstName(),
                        contact.getLastName(),
                        contact.getEmail(),
                        contact.getMobilePhone(),
                        contact.getHomePhone(),
                        contact.getAddress()
                    };
                    model.addRow(row);
                    count++;
                    countField.setText(String.valueOf(count));
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add contact to database", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        // clearButton
        if (e.getSource() == clearButton) {
            clearFields();
        }

        // deleteButton
        if (e.getSource() == deleteButton) {
            if (table.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Please, Select a contact", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                int num = JOptionPane.showConfirmDialog(null, "Do you want to delete the contact?",
                        "Confirmation message", JOptionPane.YES_NO_OPTION);
                if (num == 0) {
                    if (contactDAO.deleteContact(selectedContactId, currentUser.getId())) {
                        model.removeRow(table.getSelectedRow());
                        count--;
                        countField.setText(String.valueOf(count));
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete contact from database", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        // updateButton
        if (e.getSource() == updateButton) {
            if (table.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Please, Select a contact", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Contacts contact;
            Contacts.valid = true;
            if (homePhoneField.getText().isEmpty() && AddressField.getText().isEmpty()) {
                contact = new Contacts(namefField.getText(), namelField.getText(), emailField.getText(),
                        mobilePhoneField.getText());
            } else if (AddressField.getText().isEmpty()) {
                contact = new Contacts(namefField.getText(), namelField.getText(), emailField.getText(),
                        mobilePhoneField.getText(), homePhoneField.getText());
            } else {
                contact = new Contacts(namefField.getText(), namelField.getText(), emailField.getText(),
                        mobilePhoneField.getText(), homePhoneField.getText(), AddressField.getText());
            }

            if (Contacts.valid) {
                if (contactDAO.updateContact(selectedContactId, contact, currentUser.getId())) {
                    int rowIndex = table.getSelectedRow();
                    model.setValueAt(contact.getFirstName(), rowIndex, 0);
                    model.setValueAt(contact.getLastName(), rowIndex, 1);
                    model.setValueAt(contact.getEmail(), rowIndex, 2);
                    model.setValueAt(contact.getMobilePhone(), rowIndex, 3);
                    model.setValueAt(contact.getHomePhone(), rowIndex, 4);
                    model.setValueAt(contact.getAddress(), rowIndex, 5);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update contact in database", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        // editButton
        if (e.getSource() == editButton) {
            EditPersonal edit = new EditPersonal(currentUser);
            edit.nameField.setText(currentUser.getUsername());
            edit.phoneField.setText(currentUser.getPhone());
            edit.workField.setText(currentUser.getWork());
            edit.emailField.setText(currentUser.getEmail());
        }


        // search
        if (e.getSource() == searchButton) {
            if (searchlField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a search term", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String searchField = "";
                switch (searchComboBox.getSelectedItem().toString()) {
                    case "Name":
                        searchField = "FirstName";
                        break;
                    case "Email":
                        searchField = "Email";
                        break;
                    case "Mobile Phone":
                        searchField = "MobilePhone";
                        break;
                    case "Home Phone":
                        searchField = "HomePhone";
                        break;
                    case "City":
                        searchField = "Address";
                        break;
                }

                List<Contacts> searchResults = contactDAO.searchContacts(searchField, searchlField.getText().trim(), currentUser.getId());
                if (!searchResults.isEmpty()) {
                    model.setRowCount(0);
                    for (Contacts contact : searchResults) {
                        String[] row = {
                            contact.getFirstName(),
                            contact.getLastName(),
                            contact.getEmail(),
                            contact.getMobilePhone(),
                            contact.getHomePhone(),
                            contact.getAddress()
                        };
                        model.addRow(row);
                    }
                    JOptionPane.showMessageDialog(this, "Found " + searchResults.size() + " matching contacts",
                            "Search Results", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No contacts found matching your search criteria",
                            "Search Results", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void clearFields() {
        namefField.setText("");
        namelField.setText("");
        emailField.setText("");
        mobilePhoneField.setText("");
        homePhoneField.setText("");
        AddressField.setText("");
        selectedContactId = -1;
    }

    // mouse Selected Row
    @SuppressWarnings("unused")
    @Override
    public void mouseClicked(MouseEvent e) {
        int rowIndex = table.getSelectedRow();
        if (rowIndex != -1) {
            selectedContactId = rowIndex + 1; // Assuming ID starts from 1
            String fName = (String) model.getValueAt(rowIndex, 0);
            String lName = (String) model.getValueAt(rowIndex, 1);
            String email = (String) model.getValueAt(rowIndex, 2);
            String mobilePhone = (String) model.getValueAt(rowIndex, 3);
            String homePhone = (String) model.getValueAt(rowIndex, 4);
            String address = (String) model.getValueAt(rowIndex, 5);

            namefField.setText(fName);
            namelField.setText(lName);
            emailField.setText(email);
            mobilePhoneField.setText(mobilePhone);
            homePhoneField.setText(homePhone);
            AddressField.setText(address);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public static void main(String[] args) {
        new GUI();
    }
}
