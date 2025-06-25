package feemanagement.gui;

import javax.swing.*;

public class StudentPanel extends JFrame {
    public StudentPanel(String studentEmail) {
        setTitle("Student Panel");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome " + studentEmail);
        welcomeLabel.setBounds(100, 20, 200, 30);
        add(welcomeLabel);

        JButton btnViewFeeStructure = new JButton("View Fee Structure");
        btnViewFeeStructure.setBounds(100, 70, 180, 30);
        add(btnViewFeeStructure);

        JButton btnPayFee = new JButton("Pay Fee Online");
        btnPayFee.setBounds(100, 120, 180, 30);
        add(btnPayFee);

        btnViewFeeStructure.addActionListener(e -> {
           // Open the fee structure form
ViewFeeStructureForm viewForm = new ViewFeeStructureForm();
        });

        btnPayFee.addActionListener(e -> {
            PayFeeonline payFee = new PayFeeonline(studentEmail);
            payFee.setVisible(true);
        });
    }
}
