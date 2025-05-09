package com.mycompany.mycontacts;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    RoundedTextField emailField;
    JPasswordField passwordField;
    RoundedButton loginButton;

    Login() {
        // ImageIcon
        ImageIcon loginIcon = new ImageIcon("login.png");

        JLabel titleLabel = new JLabel("Welcome Back!");
        titleLabel.setBounds(75, 35, 400, 50);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        titleLabel.setForeground(new Color(44,44, 44));

        // Email field
        emailField = new RoundedTextField();
        emailField.setBounds(130, 150, 210, 40);
        emailField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        emailField.setBackground(new Color(236, 236, 236));

        // Password field
        passwordField = new RoundedPasswordField();
        passwordField.setBounds(130, 210, 210, 40);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        passwordField.setBackground(new Color(236, 236, 236));

        // Labels
        JLabel userLabel = new JLabel("Email:");
        userLabel.setBounds(30, 145, 220, 50);
        userLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        userLabel.setForeground(new Color(55, 54, 53));

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 205, 220, 50);
        passLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        passLabel.setForeground(new Color(55, 54, 53));

        // Login Button
        loginButton = new RoundedButton();
        loginButton.setBounds(128, 280, 130, 50);
        loginButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        loginButton.setText("Login");
        loginButton.setBackground(new Color(41, 110, 69));
        loginButton.setForeground(Color.white);
        loginButton.setFocusable(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(this);

        // Register label
        JLabel registerLabel = new JLabel("Don't have an account? Create one");
        registerLabel.setBounds(85, 370, 250, 30);
        registerLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        registerLabel.setForeground(new Color(99,99,99));
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
                new Register();
            }
        });

        // Panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setBounds(5, 5, 375, 451);
        panel.setLayout(null);
        panel.add(emailField);
        panel.add(passwordField);
        panel.add(userLabel);
        panel.add(passLabel);
        panel.add(loginButton);
        panel.add(titleLabel);
        panel.add(registerLabel);

        // Background Panel
        GradientPanel behindPanel = new GradientPanel();
        behindPanel.setBackground(Color.white);
        behindPanel.setBounds(0, 0, 395, 470);
        behindPanel.setLayout(null);

        // Frame setup
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(400, 500);
        this.add(panel);
        this.add(behindPanel);
        this.setTitle("Login");
        this.setIconImage(loginIcon.getImage());
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == loginButton) {
        String email = emailField.getText().trim();
        String password = String.valueOf(passwordField.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both email and password.", "Input Error", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                Connection conn = DBConnection.getConnection();
                String query = "SELECT * FROM Users WHERE email = ? AND password = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, email);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login successful!");
                    this.dispose();
                    new GUI();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid email or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

}