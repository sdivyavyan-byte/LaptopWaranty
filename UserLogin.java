import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class UserLogin {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("User Login - Laptop Warranty Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
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

        // Create login panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(new Color(255, 255, 255, 220));
        loginPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(66, 139, 202), 2),
            BorderFactory.createEmptyBorder(40, 50, 40, 50)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Title
        JLabel titleLabel = new JLabel("User Login");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        loginPanel.add(titleLabel, gbc);

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome Back!");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(Color.BLACK);
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 10, 0);
        loginPanel.add(welcomeLabel, gbc);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Please login to manage your warranty");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.BLACK);
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        loginPanel.add(subtitleLabel, gbc);

        // User ID field
        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        userIdLabel.setForeground(Color.BLACK);
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 5, 0);
        loginPanel.add(userIdLabel, gbc);

        JTextField userIdField = createStyledTextField();
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 15, 0);
        loginPanel.add(userIdField, gbc);

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        passwordLabel.setForeground(Color.BLACK);
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 5, 0);
        loginPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = createStyledPasswordField();
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 25, 0);
        loginPanel.add(passwordField, gbc);

        // Login button
        JButton loginButton = new JButton("Login");
        styleButton(loginButton);
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 0, 20, 0);
        loginPanel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String userid = userIdField.getText();
            String password = new String(passwordField.getPassword());
            
            if (userid.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                    "Please enter both User ID and Password",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            int loginResult = UserData.validateLogin(userid, password);
            
            if (loginResult == UserData.LOGIN_WARRANTY_EXPIRED) {
                UserData deletedUserData = UserData.getDeletedUserData(userid);
                if (deletedUserData != null) {
                    String message = String.format(
                        "Your warranty has expired!\n\n" +
                        "User Details:\n" +
                        "Name: %s\n" +
                        "Contact: %s\n" +
                        "Laptop: %s %s\n" +
                        "Warranty ID: %s\n\n" +
                        "Please contact support for warranty renewal.",
                        deletedUserData.getOwnerName(),
                        deletedUserData.getOwnerContact(),
                        deletedUserData.getLaptopBrand(),
                        deletedUserData.getLaptopModel(),
                        deletedUserData.getWarrantyId()
                    );
                    JOptionPane.showMessageDialog(frame,
                        message,
                        "Warranty Expired",
                        JOptionPane.WARNING_MESSAGE);
                }
                return;
            } else if (loginResult == UserData.LOGIN_INVALID) {
                JOptionPane.showMessageDialog(frame,
                    "Invalid User ID or Password",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            frame.dispose();
            new User(userid, password);
        });

        mainPanel.add(loginPanel);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static JTextField createStyledTextField() {
        JTextField textField = new JTextField(20);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return textField;
    }

    private static JPasswordField createStyledPasswordField() {
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return passwordField;
    }

    private static void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(66, 139, 202));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
