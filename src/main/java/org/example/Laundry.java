package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class Laundry {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Laundry Service");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 900);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setPreferredSize(new Dimension(800, 450));
        formPanel.setBackground(new Color(224, 255, 255));
        formPanel.setBorder(BorderFactory.createTitledBorder("Laundry Information"));

        JLabel titleLabel = new JLabel("Washiland");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(150, 10, 500, 40);
        formPanel.add(titleLabel);

        JLabel nameLabel = new JLabel("Customer Name:");
        nameLabel.setBounds(30, 70, 150, 25);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField nameField = new JTextField();
        nameField.setBounds(180, 70, 250, 25);
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        JLabel laundryTypeLabel = new JLabel("Type of Laundry:");
        laundryTypeLabel.setBounds(30, 110, 150, 25);
        laundryTypeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        String[] laundryOptions = {"Clothes", "Bedding", "Others"};
        JComboBox<String> laundryTypeCombo = new JComboBox<>(laundryOptions);
        laundryTypeCombo.setBounds(180, 110, 250, 25);
        formPanel.add(laundryTypeLabel);
        formPanel.add(laundryTypeCombo);

        JLabel weightLabel = new JLabel("Weight of Laundry (kg):");
        weightLabel.setBounds(30, 150, 150, 25);
        weightLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField weightField = new JTextField();
        weightField.setBounds(180, 150, 250, 25);
        formPanel.add(weightLabel);
        formPanel.add(weightField);

        JLabel discountLabel = new JLabel("Discount (%):");
        discountLabel.setBounds(30, 190, 150, 25);
        discountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField discountField = new JTextField();
        discountField.setBounds(180, 190, 250, 25);
        formPanel.add(discountLabel);
        formPanel.add(discountField);

        JLabel imageLabel = new JLabel("Image Path:");
        imageLabel.setBounds(30, 230, 150, 25);
        imageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField imageField = new JTextField();
        imageField.setBounds(180, 230, 250, 25);
        formPanel.add(imageLabel);
        formPanel.add(imageField);

        JLabel resultLabel = new JLabel("Total Price: $0.00");
        resultLabel.setBounds(30, 320, 250, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setForeground(new Color(0, 153, 76));
        formPanel.add(resultLabel);

        JLabel timeLabel = new JLabel("Estimated Time (hours): 0");
        timeLabel.setBounds(30, 350, 250, 30);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        timeLabel.setForeground(new Color(0, 153, 76));
        formPanel.add(timeLabel);

        JButton calculateButton = new JButton("Calculate Total Price");
        calculateButton.setBounds(10, 270, 200, 40);
        calculateButton.setBackground(new Color(0, 153, 255));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(calculateButton);


        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        actionPanel.setBounds(10, 380, 290, 40);

        JButton resetButton = new JButton("Reset");
        resetButton.setBackground(Color.ORANGE);
        actionPanel.add(resetButton);

        JButton saveButton = new JButton("Save");
        saveButton.setBackground(Color.GREEN);
        actionPanel.add(saveButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.RED);
        actionPanel.add(deleteButton);

        JButton editButton = new JButton("Edit");
        editButton.setBackground(Color.CYAN);
        actionPanel.add(editButton);
        formPanel.add(actionPanel);


        String[] columnNames = {"Customer Name", "Laundry Type", "Weight (kg)", "Total Price", "Estimated Time (hrs)", "Image Path"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable dataTable = new JTable(tableModel);
        dataTable.getTableHeader().setBackground(new Color(0, 102, 204));
        dataTable.getTableHeader().setForeground(Color.WHITE);
        dataTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        dataTable.setFont(new Font("Arial", Font.PLAIN, 14));
        dataTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(dataTable);

        calculateButton.addActionListener(e -> {
            String customerName = nameField.getText().trim();
            String laundryType = (String) laundryTypeCombo.getSelectedItem();
            String weightText = weightField.getText().trim();
            String discountText = discountField.getText().trim();

            if (customerName.isEmpty() || weightText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Customer Name and Weight fields must be filled!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double weight = Double.parseDouble(weightText);
                double discount = 0;
                if (discountText.equalsIgnoreCase("hahaha")) {
                    discount = 10;
                } else if (!discountText.isEmpty()) {
                    discount = Double.parseDouble(discountText);
                }

                if (weight <= 0 || discount < 0) {
                    throw new NumberFormatException();
                }
                double totalPrice = calculateTotalPrice(laundryType, weight);
                totalPrice -= totalPrice * (discount / 100);

                int estimatedTime = (int) Math.ceil(weight);
                timeLabel.setText("Estimated Time (hours): " + estimatedTime);
                resultLabel.setText(String.format("Total Price: $%.2f", totalPrice));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Weight and Discount must be valid positive numbers!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        resetButton.addActionListener(e -> {
            nameField.setText("");
            laundryTypeCombo.setSelectedIndex(0);
            weightField.setText("");
            discountField.setText("");
            imageField.setText("");
            resultLabel.setText("Total Price: $0.00");
            timeLabel.setText("Estimated Time (hours): 0");
        });


        saveButton.addActionListener(e -> {
            String customerName = nameField.getText().trim();
            String laundryType = (String) laundryTypeCombo.getSelectedItem();
            String weightText = weightField.getText().trim();
            String totalPrice = resultLabel.getText().replace("Total Price: $", "");
            String estimatedTime = timeLabel.getText().replace("Estimated Time (hours): ", "");
            String imagePath = imageField.getText().trim();

            if (customerName.isEmpty() || weightText.isEmpty() || totalPrice.equals("0.00")) {
                JOptionPane.showMessageDialog(frame, "Please calculate the total price before saving!", "Save Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            tableModel.addRow(new Object[]{
                    customerName, laundryType, weightText, totalPrice, estimatedTime, imagePath
            });

            saveToFile(tableModel);
        });


        deleteButton.addActionListener(e -> {
            int selectedRow = dataTable.getSelectedRow();
            if (selectedRow >= 0) {
                tableModel.removeRow(selectedRow);
                saveToFile(tableModel);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a row to delete!", "Delete Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        editButton.addActionListener(e -> {
            int selectedRow = dataTable.getSelectedRow();
            if (selectedRow >= 0) {
                String customerName = (String) tableModel.getValueAt(selectedRow, 0);
                String laundryType = (String) tableModel.getValueAt(selectedRow, 1);
                String weight = (String) tableModel.getValueAt(selectedRow, 2);
                String totalPrice = (String) tableModel.getValueAt(selectedRow, 3);
                String estimatedTime = (String) tableModel.getValueAt(selectedRow, 4);

                nameField.setText(customerName);
                laundryTypeCombo.setSelectedItem(laundryType);
                weightField.setText(weight);
                discountField.setText("");
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a row to edit!", "Edit Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public static double calculateTotalPrice(String laundryType, double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }
        double pricePerKilogram = getPricePerKilogram(laundryType);
        return weight * pricePerKilogram;
    }

    private static double getPricePerKilogram(String laundryType) {
        return switch (laundryType.toLowerCase()) {
            case "clothes" -> 5;
            case "bedding" -> 8;
            default -> 6;
        };
    }

    public static void saveToFile(DefaultTableModel tableModel) {
        try (FileWriter writer = new FileWriter("receipt.html", false)) {
            writer.write("<html><body>\n");
            writer.write("<h1>--- Laundry Receipts ---</h1>\n");

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                writer.write("<h2>--- Laundry Receipt ---</h2>\n");
                writer.write("<p>Customer Name: " + tableModel.getValueAt(i, 0) + "</p>\n");
                writer.write("<p>Laundry Type: " + tableModel.getValueAt(i, 1) + "</p>\n");
                writer.write("<p>Weight: " + tableModel.getValueAt(i, 2) + " kg</p>\n");
                writer.write("<p>Total Price: $" + tableModel.getValueAt(i, 3) + "</p>\n");
                writer.write("<p>Estimated Time: " + tableModel.getValueAt(i, 4) + " hours</p>\n");

                String imagePath = (String) tableModel.getValueAt(i, 5);
                if (imagePath != null && !imagePath.isEmpty()) {
                    writer.write("<p><img src='" + imagePath + "' alt='Laundry Image' width='200' height='200'></p>\n");
                }

                writer.write("<hr>\n");
            }

            writer.write("</body></html>\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "An error occurred while saving the file.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
