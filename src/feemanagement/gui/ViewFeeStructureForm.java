package feemanagement.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewFeeStructureForm extends JFrame {
    private JTable feeTable;
    private JScrollPane scrollPane;

    public ViewFeeStructureForm() {
        setTitle("View Fee Structure");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        feeTable = new JTable();
        scrollPane = new JScrollPane(feeTable);
        add(scrollPane, BorderLayout.CENTER);

        loadFeeStructureData();

        setVisible(true);
    }

    private void loadFeeStructureData() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Course", "Amount"});

        try {
            // Replace with your actual DB connection info
            String url = "jdbc:mysql://localhost:3306/feesystem";
            String user = "root";
            String password = "rafi033";

            Connection conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT id, course, amount FROM fee_structure";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String course = rs.getString("course");
                double amount = rs.getDouble("amount");

                model.addRow(new Object[]{id, course, amount});
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }

        feeTable.setModel(model);
    }

    // Optional main method for testing standalone
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewFeeStructureForm());
    }
}
