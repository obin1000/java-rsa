package main.java;

import javax.swing.*;
import java.awt.*;

public class Decode extends JPanel {

    private JTextField Ninput, Einput, Cinput;
    private JLabel Dlab, Clab;


    public Decode() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Decode");

        JLabel Nlab = new JLabel("Give me the N:");
        Ninput = new JTextField("123");
        JLabel Elab = new JLabel("Give me the E:");
        Einput = new JTextField("123");
        JButton NEButton = new JButton("Step 1: Generate D");
        JLabel Dis = new JLabel("d is: ");
        Dlab = new JLabel("0");

        Cinput = new JTextField("123");
        JButton CButton = new JButton("Step 2: Decode M");
        JLabel CMessageAfter = new JLabel("Message after decryption is: ");
        Clab = new JLabel("0");


        Ninput.setMaximumSize(new Dimension(99999999, 20));
        Einput.setMaximumSize(new Dimension(99999999, 20));
        Cinput.setMaximumSize(new Dimension(99999999, 20));


        NEButton.addActionListener(actionEvent -> NEButtonPressed());
        CButton.addActionListener(actionEvent -> CMButtonPressed());


        add(title);
        add(Nlab);
        add(Ninput);
        add(Elab);
        add(Einput);
        add(NEButton);
        add(Dis);
        add(Dlab);
        add(Cinput);
        add(CButton);
        add(CMessageAfter);
        add(Clab);
    }

    public void showD(long value) {
        Dlab.setText(Long.toString(value));
    }

    public void showCM(long value) {
        Clab.setText(Long.toString(value));
    }


    public void NEButtonPressed() {
        long N, E = 0;
        try {
            N = Long.parseLong(Ninput.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid N provided");
            return;
        }
        if (N < 2) {
            JOptionPane.showMessageDialog(this, "N cannot be smaller than 2");
            return;
        }

        try {
            E = Long.parseLong(Einput.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid E provided");
            return;
        }
        if (E < 2) {
            JOptionPane.showMessageDialog(this, "E cannot be smaller than 2");
            return;
        }
        showD(E);
    }

    public void CMButtonPressed() {
        long C = 0;
        try {
            C = Long.parseLong(Cinput.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid C provided");
            return;
        }
        if (C < 2) {
            JOptionPane.showMessageDialog(this, "C cannot be smaller than 2");
            return;
        }

    }
}
