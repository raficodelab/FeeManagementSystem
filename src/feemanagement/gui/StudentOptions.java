package feemanagement.gui;

import javax.swing.*;

public class StudentOptions extends JFrame {
    private String studentEmail;

    public StudentOptions(String studentEmail) {
        this.studentEmail = studentEmail;

        setTitle("Student Options");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton btnViewFee = new JButton("View Fee Structure");
        btnViewFee.setBounds(80, 30, 180, 30);
        add(btnViewFee);

        JButton btnPayFee = new JButton("Pay Fee Online");
        btnPayFee.setBounds(80, 80, 180, 30);
        add(btnPayFee);

        btnViewFee.addActionListener(e -> {
            ViewFeeStructureForm view = new ViewFeeStructureForm(studentEmail);
            view.setVisible(true);
        });

        btnPayFee.addActionListener(e -> {
            PayFeeonline pay = new PayFeeonline(studentEmail);
            pay.setVisible(true);
        });
    }
}
