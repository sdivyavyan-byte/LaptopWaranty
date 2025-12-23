import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;

public class CheckBox extends JFrame {
    private JCheckBox checkBox;
    private JLabel label;

    public CheckBox() {
        checkBox = new JCheckBox("Check");
        label = new JLabel("Unchecked");
        
        setLayout(new FlowLayout());

        add(checkBox);
        add(label);

        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected()) {
                    label.setText("Checked");
                } else {
                    label.setText("Unchecked");
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CheckBox();
    }
}