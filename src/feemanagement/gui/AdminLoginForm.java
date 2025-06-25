package feemanagement.gui;

import javax.swing.*;

public class AdminLoginForm extends JFrame {
    public AdminLoginForm() {
        setTitle("Admin Login");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(30, 30, 80, 25);
        add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(120, 30, 150, 25);
        add(txtUser);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(30, 70, 80, 25);
        add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(120, 70, 150, 25);
        add(txtPass);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(120, 110, 100, 25);
        add(loginBtn);

        loginBtn.addActionListener(e -> {
            String username = txtUser.getText();
            String password = new String(txtPass.getPassword());

            // Placeholder validation - update with DB logic later
            if (username.equals("admin") && password.equals("admin123")) {
                JOptionPane.showMessageDialog(this, "Login successful");
                // AdminDashboard (not yet implemented)
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
        });
    }
}
