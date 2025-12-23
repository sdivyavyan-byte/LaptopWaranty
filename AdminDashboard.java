import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class AdminDashboard {
    private JFrame frame;
    private JTable userTable;
    private DefaultTableModel tableModel;

    public AdminDashboard() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Create main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(66, 139, 202);
                Color color2 = new Color(255, 255, 255);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        JLabel titleLabel = new JLabel("User Registrations");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titlePanel.add(titleLabel);

        // Table Panel
        String[] columnNames = {"Owner Name", "Contact", "User ID", "Laptop Brand", "Laptop Model", "Warranty ID"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        userTable = new JTable(tableModel);
        userTable.setForeground(Color.BLACK);
        userTable.getTableHeader().setForeground(Color.BLACK);
        userTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBackground(Color.WHITE);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        JButton homeButton = new JButton("Home");
        homeButton.setBackground(Color.BLACK);
        homeButton.setForeground(Color.BLACK);
        homeButton.setFocusPainted(false);
        homeButton.addActionListener(e -> {
            frame.dispose();
            new Homepage();
        });
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(40, 167, 69));
        refreshButton.setForeground(Color.BLACK);
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(e -> refreshTable());
        
        JButton deleteButton = new JButton("Delete Selected");
        deleteButton.setBackground(new Color(220, 53, 69));
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(e -> deleteSelectedUser());
        
        buttonPanel.add(homeButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);

        // Add components to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // Clear existing rows
        HashMap<String, UserData> users = UserData.getAllUsers();
        if (users != null) {
            for (UserData user : users.values()) {
                tableModel.addRow(new Object[]{
                    user.getOwnerName(),
                    user.getOwnerContact(),
                    user.getOwnerUserid(),
                    user.getLaptopBrand(),
                    user.getLaptopModel(),
                    user.getWarrantyId()
                });
            }
        }
    }

    private void deleteSelectedUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow != -1) {
            String userId = (String) tableModel.getValueAt(selectedRow, 2); // User ID is in column 2
            int confirm = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to delete this user?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (UserData.deleteUser(userId)) {
                    refreshTable();
                    JOptionPane.showMessageDialog(frame, "User deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Error deleting user!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a user to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void show() {
        frame.setVisible(true);
    }
}
