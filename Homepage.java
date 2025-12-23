import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Homepage {
    public static void main(String[] args) {
        JFrame frame = new JFrame("LAPTOP WARRANTY MANAGEMENT");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        GridBagConstraints gbc = new GridBagConstraints();

        // Create a card panel for login options
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridBagLayout());
        cardPanel.setBackground(new Color(255, 255, 255, 200));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(66, 139, 202), 2),
            BorderFactory.createEmptyBorder(20, 40, 20, 40)
        ));

        // Title with better styling
        JLabel titleLabel = new JLabel("Laptop Warranty Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(Color.BLACK);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Please select your login option");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.BLACK);

        // Create radio buttons panel
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        radioPanel.setOpaque(false);

        ButtonGroup loginTypeGroup = new ButtonGroup();
        JRadioButton adminButton = createStyledRadioButton("Admin Login");
        JRadioButton userButton = createStyledRadioButton("User Login");
        JRadioButton newUserButton = createStyledRadioButton("New User Registration");
        JRadioButton newKieveButton = createStyledRadioButton("kieve");


        loginTypeGroup.add(adminButton);
        loginTypeGroup.add(userButton);
        loginTypeGroup.add(newUserButton);
        loginTypeGroup.add(newKieveButton);
        radioPanel.add(adminButton);
        radioPanel.add(userButton);
        radioPanel.add(newUserButton);
        radioPanel.add(newKieveButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        cardPanel.add(radioPanel, gbc);

        // Submit button
        JButton submitButton = new JButton("Continue");
        styleButton(submitButton);
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        cardPanel.add(submitButton, gbc);

        // New User Register button
        JButton registerButton = new JButton("New User Register");
        styleButton(registerButton);
        registerButton.setBackground(new Color(40, 167, 69)); // Green color for register button
        gbc.gridy = 4;
        cardPanel.add(registerButton, gbc);

        // Add action listeners
        submitButton.addActionListener(e -> {
            if (adminButton.isSelected()) {
                frame.dispose();
                AdminLogin.main(new String[]{});
            } else if (userButton.isSelected()) {
                frame.dispose();
                UserLogin.main(new String[]{});
            } else if (newUserButton.isSelected()) {
                frame.dispose();
                Register.main(new String[]{});
            } else {
                JOptionPane.showMessageDialog(frame,
                    "Please select a login option!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> {
            frame.dispose();
            Register.main(new String[]{});
        });

        // Layout components in card panel
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        cardPanel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 30, 0);
        cardPanel.add(subtitleLabel, gbc);

        // Add card panel to main panel
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 0, 50, 0); 
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(cardPanel, gbc);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static JRadioButton createStyledRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        radioButton.setForeground(Color.BLACK);
        radioButton.setOpaque(false);
        radioButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return radioButton;
    }

    private static void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(66, 139, 202));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(button.getBackground().darker());
            }
            
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(66, 139, 202));
            }
        });
    }

    private static void addHoverEffect(JButton button, Color defaultColor, Color hoverColor) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(defaultColor);
            }
        });
    }

    private static void showStyledMessage(JFrame parent, String message, String title) {
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.BOLD, 12));
        JOptionPane.showMessageDialog(
            parent,
            message,
            title,
            title.equals("Warning") ? JOptionPane.WARNING_MESSAGE : JOptionPane.INFORMATION_MESSAGE
        );
    }
}
