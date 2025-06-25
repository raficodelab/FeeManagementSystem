package feemanagement.gui;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import feemanagement.db.DBConnection;

public class PayFeeonline extends JFrame {
    private String studentEmail;
    private JTextField txtAmount;
    private JButton btnPay;

    public PayFeeonline(String studentEmail) {
        this.studentEmail = studentEmail;

        setTitle("Pay Fee Online");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblAmount = new JLabel("Enter Amount:");
        lblAmount.setBounds(30, 30, 100, 25);
        add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(140, 30, 150, 25);
        add(txtAmount);

        btnPay = new JButton("Pay Now");
        btnPay.setBounds(100, 80, 120, 30);
        add(btnPay);

        btnPay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                payFee();
            }
        });
    }

    private void payFee() {
        String amountStr = txtAmount.getText();

        if (amountStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter amount.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);

            Connection conn = DBConnection.getConnection();

            // Get student_id from email
            int studentId = -1;
            String query = "SELECT id FROM students WHERE email = ?";
            PreparedStatement getIdStmt = conn.prepareStatement(query);
            getIdStmt.setString(1, studentEmail);
            ResultSet rs = getIdStmt.executeQuery();
            if (rs.next()) {
                studentId = rs.getInt("id");
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.");
                return;
            }

            // Insert payment
            String sql = "INSERT INTO payments (student_id, amount_paid, payment_date, student_email) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            stmt.setDouble(2, amount);
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            stmt.setString(4, studentEmail);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Fee Paid Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to Pay Fee.");
            }

            stmt.close();
            conn.close();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount entered.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
