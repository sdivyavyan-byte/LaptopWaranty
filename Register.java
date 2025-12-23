import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.Enumeration;

public class Register {
    private JTextField ownerNameField;
    private JTextField ownerContactField;
    private JTextField ownerUseridField;
    private JPasswordField ownerPasswordField;
    private JTextField laptopBrandField;
    private JTextField laptopModelField;
    private JComboBox<String> laptopBatteryLifeComboBox;
    private ButtonGroup warrantyDurationGroup;
    private JTextField warrantyIdField;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new Register().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Laptop Warranty Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
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

        // Title
        JLabel titleLabel = new JLabel("Laptop Warranty Registration");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        contentPanel.add(titleLabel, gbc);
        gbc.gridwidth = 1;

        // Owner Details Section
        addSectionHeader(contentPanel, "Owner Details", 0, 1, gbc);
        
        // Owner Details Fields
        ownerNameField = createStyledTextField();
        addFormField(contentPanel, "Name:", ownerNameField, 0, 2, gbc);
        ownerContactField = createStyledTextField();
        addFormField(contentPanel, "Contact:", ownerContactField, 0, 3, gbc);
        ownerUseridField = createStyledTextField();
        addFormField(contentPanel, "User ID:", ownerUseridField, 0, 4, gbc);
        
        ownerPasswordField = createStyledPasswordField();
        addFormField(contentPanel, "Password:", ownerPasswordField, 0, 5, gbc);

        // Laptop Details Section
        addSectionHeader(contentPanel, "Laptop Details", 2, 1, gbc);
        
        // Laptop Details Fields
        laptopBrandField = createStyledTextField();
        addFormField(contentPanel, "Brand:", laptopBrandField, 2, 2, gbc);
        laptopModelField = createStyledTextField();
        addFormField(contentPanel, "Model:", laptopModelField, 2, 3, gbc);
        
        String[] batteryOptions = {"12 hrs", "20 hrs", "24 hrs"};
        laptopBatteryLifeComboBox = createStyledComboBox(batteryOptions);
        addFormField(contentPanel, "Battery Life:", laptopBatteryLifeComboBox, 2, 4, gbc);

        // Warranty Details Section
        addSectionHeader(contentPanel, "Warranty Details", 0, 6, gbc);
        gbc.gridwidth = 4;

        // Warranty Duration Panel
        JPanel warrantyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        warrantyPanel.setOpaque(false);
        
        warrantyDurationGroup = new ButtonGroup();
        JRadioButton[] warrantyOptions = {
            createStyledRadioButton("3 months"),
            createStyledRadioButton("6 months"),
            createStyledRadioButton("12 months")
        };
        
        for (JRadioButton rb : warrantyOptions) {
            rb.setActionCommand(rb.getText()); // Set action command to match the text
            warrantyDurationGroup.add(rb);
            warrantyPanel.add(rb);
        }
        warrantyOptions[0].setSelected(true); // Set default selection

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 4;
        contentPanel.add(new JLabel("Warranty Duration:"), gbc);
        
        gbc.gridy = 8;
        contentPanel.add(warrantyPanel, gbc);

        warrantyIdField = createStyledTextField();
        addFormField(contentPanel, "Warranty ID:", warrantyIdField, 0, 9, gbc);

        // Submit Button
        JButton submitButton = new JButton("Submit Registration");
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        submitButton.setForeground(Color.BLACK);
        submitButton.setBackground(new Color(40, 167, 69));
        submitButton.setPreferredSize(new Dimension(200, 40));
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        submitButton.setFocusPainted(false);
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        submitButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                submitButton.setBackground(new Color(51, 122, 183));
            }
            public void mouseExited(MouseEvent e) {
                submitButton.setBackground(new Color(40, 167, 69));
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 0, 0, 0);
        contentPanel.add(submitButton, gbc);

        submitButton.addActionListener(e -> {
            String warrantyDuration = "";
            Enumeration<AbstractButton> buttons = warrantyDurationGroup.getElements();
            while (buttons.hasMoreElements()) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    warrantyDuration = button.getText();
                    break;
                }
            }

            // Generate a simple warranty ID
            String warrantyId = "WAR" + System.currentTimeMillis() % 100000;

            // Create and save user data
            UserData userData = new UserData(
                ownerNameField.getText(),
                ownerContactField.getText(),
                ownerUseridField.getText(),
                new String(ownerPasswordField.getPassword()),
                laptopBrandField.getText(),
                laptopModelField.getText(),
                (String) laptopBatteryLifeComboBox.getSelectedItem(),
                warrantyDuration,
                warrantyId
            );
            UserData.saveUser(userData);

            JOptionPane.showMessageDialog(frame,
                "Registration successful!\nYour Warranty ID is: " + warrantyId,
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            frame.dispose();
            Homepage.main(new String[]{});
        });

        // Add content panel to main panel
        mainPanel.add(contentPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void addSectionHeader(JPanel panel, String text, int x, int y, GridBagConstraints gbc) {
        JLabel header = new JLabel(text);
        header.setFont(new Font("Segoe UI", Font.BOLD, 18));
        header.setForeground(Color.BLACK);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 0, 10, 0);
        panel.add(header, gbc);
        gbc.gridwidth = 1;
        gbc.insets = new Insets(8, 10, 8, 10);
    }

    private void addFormField(JPanel panel, String labelText, JComponent field, int x, int y, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(Color.BLACK);
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(label, gbc);

        gbc.gridx = x + 1;
        panel.add(field, gbc);
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(Color.BLACK);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField(20);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(Color.BLACK);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setForeground(Color.BLACK);
        comboBox.setBackground(Color.WHITE);
        ((JComponent) comboBox.getRenderer()).setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return comboBox;
    }

    private JRadioButton createStyledRadioButton(String text) {
        JRadioButton radio = new JRadioButton(text);
        radio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        radio.setForeground(Color.BLACK);
        radio.setOpaque(false);
        radio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return radio;
    }

    // Getter and Setter methods
    public String getOwnerName() {
        return ownerNameField.getText();
    }

    public void setOwnerName(String name) {
        ownerNameField.setText(name);
    }

    public String getOwnerContact() {
        return ownerContactField.getText();
    }

    public void setOwnerContact(String contact) {
        ownerContactField.setText(contact);
    }

    public String getOwnerUserid() {
        return ownerUseridField.getText();
    }

    public void setOwnerUserid(String userid) {
        ownerUseridField.setText(userid);
    }

    public String getOwnerPassword() {
        return new String(ownerPasswordField.getPassword());
    }

    public void setOwnerPassword(String password) {
        ownerPasswordField.setText(password);
    }

    public String getLaptopBrand() {
        return laptopBrandField.getText();
    }

    public void setLaptopBrand(String brand) {
        laptopBrandField.setText(brand);
    }

    public String getLaptopModel() {
        return laptopModelField.getText();
    }

    public void setLaptopModel(String model) {
        laptopModelField.setText(model);
    }

    public String getLaptopBatteryLife() {
        return (String) laptopBatteryLifeComboBox.getSelectedItem();
    }

    public void setLaptopBatteryLife(String batteryLife) {
        laptopBatteryLifeComboBox.setSelectedItem(batteryLife);
    }

    public String getWarrantyDuration() {
        ButtonModel selectedButton = warrantyDurationGroup.getSelection();
        if (selectedButton != null) {
            return selectedButton.getActionCommand();
        }
        return "";
    }

    public void setWarrantyDuration(String duration) {
        Enumeration<AbstractButton> buttons = warrantyDurationGroup.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();
            if (button.getActionCommand().equals(duration)) {
                button.setSelected(true);
                break;
            }
        }
    }

    public String getWarrantyId() {
        return warrantyIdField.getText();
    }

    public void setWarrantyId(String warrantyId) {
        warrantyIdField.setText(warrantyId);
    }
}