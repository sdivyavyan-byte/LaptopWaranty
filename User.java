import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class User {
    private JFrame frame;
    private UserData userData;

    public User(String userid, String password) {
        this.userData = UserData.getUser(userid, password);
        if (userData == null) {
            JOptionPane.showMessageDialog(null,
                "Invalid login credentials",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            Homepage.main(new String[]{});
            return;
        }
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("User Information");
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
        mainPanel.setLayout(new GridBagLayout());

        // Create content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(new Color(255, 255, 255, 220));
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(66, 139, 202), 2),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Add user information
        addHeader(contentPanel, "User Information", 0, gbc);
        addInfoField(contentPanel, "Name:", userData.getOwnerName(), 1, gbc);
        addInfoField(contentPanel, "Contact:", userData.getOwnerContact(), 2, gbc);
        addInfoField(contentPanel, "User ID:", userData.getOwnerUserid(), 3, gbc);

        addHeader(contentPanel, "Laptop Details", 4, gbc);
        addInfoField(contentPanel, "Brand:", userData.getLaptopBrand(), 5, gbc);
        addInfoField(contentPanel, "Model:", userData.getLaptopModel(), 6, gbc);
        addInfoField(contentPanel, "Battery Life:", userData.getLaptopBatteryLife(), 7, gbc);

        addHeader(contentPanel, "Warranty Information", 8, gbc);
        addInfoField(contentPanel, "Duration:", userData.getWarrantyDuration(), 9, gbc);
        addInfoField(contentPanel, "Warranty ID:", userData.getWarrantyId(), 10, gbc);

        // Logout button
        JButton logoutButton = new JButton("Logout");
        styleButton(logoutButton);
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(logoutButton, gbc);

        logoutButton.addActionListener(e -> {
            frame.dispose();
            Homepage.main(new String[]{});
        });

        // Add content panel to main panel
        mainPanel.add(contentPanel);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void addHeader(JPanel panel, String text, int y, GridBagConstraints gbc) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 10, 10, 10);
        panel.add(label, gbc);
        gbc.gridwidth = 1;
        gbc.insets = new Insets(8, 10, 8, 10);
    }

    private void addInfoField(JPanel panel, String labelText, String value, int y, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(label, gbc);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        valueLabel.setForeground(Color.BLACK);
        gbc.gridx = 1;
        panel.add(valueLabel, gbc);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(66, 139, 202));
        button.setPreferredSize(new Dimension(150, 40));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(51, 122, 183));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(66, 139, 202));
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new User("testuser", "password"));
    }
}
