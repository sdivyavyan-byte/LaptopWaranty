import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class AdminLogin {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Admin Login");
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
        JLabel titleLabel = new JLabel("Admin Login");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 30, 0);
        loginPanel.add(titleLabel, gbc);

        // Username field
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        usernameLabel.setForeground(Color.BLACK);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(usernameLabel, gbc);

        JTextField usernameField = createStyledTextField();
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 15, 0);
        loginPanel.add(usernameField, gbc);

        // Password field
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        passwordLabel.setForeground(Color.BLACK);
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 5, 0);
        loginPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = createStyledPasswordField();
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 25, 0);
        loginPanel.add(passwordField, gbc);

        // Login and submit buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setOpaque(false);

        JButton loginButton = new JButton("Login");
        styleButton(loginButton);
        loginButton.setPreferredSize(new Dimension(120, 40));

        JButton submitButton = new JButton("Submit");
        styleButton(submitButton);
        submitButton.setPreferredSize(new Dimension(120, 40));
        submitButton.setBackground(new Color(40, 167, 69)); // Green color for submit

        buttonPanel.add(loginButton);
        buttonPanel.add(submitButton);

        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 15, 0);
        loginPanel.add(buttonPanel, gbc);

        // Back button
        JButton backButton = new JButton("Back to Homepage");
        styleButton(backButton);
        backButton.setBackground(new Color(108, 117, 125));
        gbc.gridy = 6;
        loginPanel.add(backButton, gbc);

        // Add action listeners
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            // Simple admin authentication (you should implement proper authentication)
            if (username.isEmpty() || password.isEmpty()) {
                showErrorMessage(frame, "Please enter both username and password.");
            } else if (username.equals("admin") && password.equals("admin123")) {
                frame.dispose(); // Close login window
                AdminDashboard dashboard = new AdminDashboard();
                dashboard.show();
            } else {
                showErrorMessage(frame, "Invalid username or password.");
            }
        });

        submitButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (username.isEmpty() || password.isEmpty()) {
                showErrorMessage(frame, "Please enter both username and password.");
            } else {
                showSuccessMessage(frame, "Form submitted successfully!");
                // Clear the fields after submission
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            Homepage.main(new String[]{});
        });

        // Add hover effects
        addHoverEffect(loginButton, new Color(66, 139, 202), new Color(51, 122, 183));
        addHoverEffect(submitButton, new Color(40, 167, 69), new Color(33, 136, 56));
        addHoverEffect(backButton, new Color(108, 117, 125), new Color(90, 98, 104));

        mainPanel.add(loginPanel);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static JTextField createStyledTextField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(Color.BLACK);
        field.setPreferredSize(new Dimension(250, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private static JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField(20);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(Color.BLACK);
        field.setPreferredSize(new Dimension(250, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private static void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(66, 139, 202));
        button.setPreferredSize(new Dimension(250, 40));
        button.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private static void addHoverEffect(JButton button, Color defaultColor, Color hoverColor) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(defaultColor);
            }
        });
    }

    private static void showErrorMessage(JFrame parent, String message) {
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.BOLD, 12));
        JOptionPane.showMessageDialog(
            parent,
            message,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    private static void showSuccessMessage(JFrame parent, String message) {
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.BOLD, 12));
        JOptionPane.showMessageDialog(
            parent,
            message,
            "Success",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
