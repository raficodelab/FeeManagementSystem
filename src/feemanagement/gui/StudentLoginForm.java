package feemanagement.gui;

import javax.swing.*;
import java.sql.*;

public class StudentLoginForm extends JFrame {
    public StudentLoginForm() {
        setTitle("Student Login");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 30, 80, 25);
        add(lblEmail);

        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(120, 30, 150, 25);
        add(txtEmail);

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
            String email = txtEmail.getText();
            String password = new String(txtPass.getPassword());

            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/feesystem", "root", "rafi033");
                String sql = "SELECT * FROM students WHERE email=? AND password=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login successful");
                    StudentPanel panel = new StudentPanel(email);
                    panel.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials");
                }

                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}
