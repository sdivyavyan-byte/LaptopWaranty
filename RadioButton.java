import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;

public class RadioButton extends JFrame {
    JRadioButton option1;
    JRadioButton option2;
    JLabel label;

    public RadioButton(){
        option1 = new JRadioButton("option 1");
        option2 = new JRadioButton("option 2");
        label = new JLabel("select an option.");

        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);

        setLayout(new FlowLayout());

        add(option1);
        add(option2);
        add(label);

        option1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("option 1 selected.");
            }
        });

        option2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("option 2 selected.");
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(300,200);   
        setVisible(true);
    }                                                                   

    public static void main(String[] args) {
        new RadioButton();
    }

}
