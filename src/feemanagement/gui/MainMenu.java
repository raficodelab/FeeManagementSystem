package feemanagement.gui;

import javax.swing.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Main Menu");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton adminLoginBtn = new JButton("Admin Login");
        adminLoginBtn.setBounds(120, 40, 150, 30);
        add(adminLoginBtn);

        JButton studentLoginBtn = new JButton("Student Login");
        studentLoginBtn.setBounds(120, 90, 150, 30);
        add(studentLoginBtn);

        adminLoginBtn.addActionListener(e -> {
            AdminLoginForm adminLogin = new AdminLoginForm();
            adminLogin.setVisible(true);
            dispose();
        });

        studentLoginBtn.addActionListener(e -> {
            StudentLoginForm studentLogin = new StudentLoginForm();
            studentLogin.setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }
}
