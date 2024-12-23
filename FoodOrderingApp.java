import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FoodOrderingApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Food Ordering App - Login");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if ("admin".equals(username) && "admin123".equals(password)) {
                new AdminDashboard().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        gbc.gridx = 0; gbc.gridy = 0; panel.add(usernameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(usernameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(passwordLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(passwordField, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(loginButton, gbc);

        add(panel);
    }
}

class AdminDashboard extends JFrame {
    private final ArrayList<FoodItem> foodList;
    private final DefaultTableModel tableModel;
    private final DefaultTableModel orderTableModel;

    public AdminDashboard() {
        setTitle("Food Ordering App - Admin Dashboard");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        foodList = new ArrayList<>();
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Price", "Image"}, 0);
        orderTableModel = new DefaultTableModel(new Object[]{"Name", "Price", "Quantity", "Total"}, 0);

        JTable foodTable = new JTable(tableModel) {
            public Class<?> getColumnClass(int column) {
                return column == 3 ? ImageIcon.class : Object.class;
            }
        };

        foodTable.setRowHeight(100); // Set row height to accommodate image size
        foodTable.getColumnModel().getColumn(3).setCellRenderer(new ImageRenderer());

        JTable orderTable = new JTable(orderTableModel);

        JScrollPane foodTableScrollPane = new JScrollPane(foodTable);
        JScrollPane orderTableScrollPane = new JScrollPane(orderTable);

        JButton addButton = new JButton("Add Food");
        JButton editButton = new JButton("Edit Food");
        JButton deleteButton = new JButton("Delete Food");
        JButton orderButton = new JButton("Place Order");
//        JButton calculateTotalButton = new JButton("Calculate Total");

        addButton.addActionListener(e -> openFoodForm(null));

        editButton.addActionListener(e -> {
            int selectedRow = foodTable.getSelectedRow();
            if (selectedRow >= 0) {
                openFoodForm(foodList.get(selectedRow));
            } else {
                JOptionPane.showMessageDialog(this, "Please select a food item to edit!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = foodTable.getSelectedRow();
            if (selectedRow >= 0) {
                foodList.remove(selectedRow);
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a food item to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        orderButton.addActionListener(e -> {
            int selectedRow = foodTable.getSelectedRow();
            if (selectedRow >= 0) {
                FoodItem foodItem = foodList.get(selectedRow);
                String quantityText = JOptionPane.showInputDialog(this, "Enter quantity for " + foodItem.getName());
                try {
                    int quantity = Integer.parseInt(quantityText);
                    double total = foodItem.getPrice() * quantity;
                    orderTableModel.addRow(new Object[]{foodItem.getName(), foodItem.getPrice(), quantity, total});
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a food item to order!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

//        calculateTotalButton.addActionListener(e -> {
//            double totalPrice = 0;
//            for (int i = 0; i < orderTableModel.getRowCount(); i++) {
//                totalPrice += (double) orderTableModel.getValueAt(i, 4);
//            }
//            JOptionPane.showMessageDialog(this, "Total Order Price: " + totalPrice, "Total", JOptionPane.INFORMATION_MESSAGE);
//        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(orderButton);
//        buttonPanel.add(calculateTotalButton);

        setLayout(new BorderLayout());
        add(foodTableScrollPane, BorderLayout.WEST);
        add(orderTableScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void openFoodForm(FoodItem foodItem) {
        JDialog dialog = new JDialog(this, "Food Form", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(20);

        JLabel imageLabel = new JLabel("Image Path:");
        JTextField imageField = new JTextField(20);
        JButton browseButton = new JButton("Browse");

        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(dialog) == JFileChooser.APPROVE_OPTION) {
                imageField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        JButton saveButton = new JButton("Save");

        if (foodItem != null) {
            nameField.setText(foodItem.getName());
            priceField.setText(String.valueOf(foodItem.getPrice()));
            imageField.setText(foodItem.getImagePath());
        }

        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String priceText = priceField.getText();
            String imagePath = imageField.getText();

            try {
                double price = Double.parseDouble(priceText);
                if (foodItem == null) {
                    FoodItem newFood = new FoodItem(name, price, imagePath);
                    foodList.add(newFood);
                    tableModel.addRow(new Object[]{foodList.size(), name, price, new ImageIcon(imagePath)});
                } else {
                    foodItem.setName(name);
                    foodItem.setPrice(price);
                    foodItem.setImagePath(imagePath);
                    int rowIndex = foodList.indexOf(foodItem);
                    tableModel.setValueAt(name, rowIndex, 1);
                    tableModel.setValueAt(price, rowIndex, 2);
                    tableModel.setValueAt(new ImageIcon(imagePath), rowIndex, 3);
                }
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Price must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setLayout(new GridLayout(5, 2));
        dialog.add(nameLabel);
        dialog.add(nameField);
        dialog.add(priceLabel);
        dialog.add(priceField);
        dialog.add(imageLabel);
        dialog.add(imageField);
        dialog.add(new JLabel());
        dialog.add(browseButton);
        dialog.add(new JLabel());
        dialog.add(saveButton);

        dialog.setVisible(true);
    }

    private class ImageRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof ImageIcon) {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);
                ImageIcon icon = (ImageIcon) value;
                Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Scale to fit row height
                label.setIcon(new ImageIcon(scaledImage));
                return label;
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
}

class FoodItem {
    private String name;
    private double price;
    private String imagePath;

    public FoodItem(String name, double price, String imagePath) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
