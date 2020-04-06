package main.java.gui;

import main.java.rsa.Rsa;

import javax.swing.*;
import java.awt.*;

public class Decode extends JPanel {

    private JTextField Ninput, Einput, Cinput, MessageUnencrypted;
    private JLabel Dlab;
    private Rsa rsa;


    public Decode() {
        rsa = new Rsa();

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Decode");

        JLabel Nlab = new JLabel("Give me the N:");
        Ninput = new JTextField("123");
        JLabel Elab = new JLabel("Give me the E:");
        Einput = new JTextField("123");
        JButton NEButton = new JButton("Step 1: Generate D");
        JLabel Dis = new JLabel("d is: ");
        Dlab = new JLabel("0");

        JLabel Clab = new JLabel("Give me a message: ");
        Cinput = new JTextField("Hello");
        JButton CButton = new JButton("Step 2: Decode M");
        JLabel CMessageAfter = new JLabel("Message after decryption is: ");
        MessageUnencrypted = new JTextField("0");


        Ninput.setMaximumSize(new Dimension(99999999, 20));
        Einput.setMaximumSize(new Dimension(99999999, 20));
        Cinput.setMaximumSize(new Dimension(99999999, 200));

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
        add(Clab);
        add(Cinput);
        add(CButton);
        add(CMessageAfter);
        add(MessageUnencrypted);
    }

    public void showD(long value) {
        Dlab.setText(Long.toString(value));
    }

    public void showCM(String value) {
        MessageUnencrypted.setText(value);
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
        if (!rsa.validateE(N,E)) {
            JOptionPane.showMessageDialog(this, "E is not valid");
            return;
        }

        rsa.calcD(N, E);
        showD(rsa.getD());
    }

    public void CMButtonPressed() {
        String C;
        long N;

        N = Long.parseLong(Ninput.getText());
        try {
            C = Cinput.getText();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid C provided");
            return;
        }
        showCM(rsa.decrypt(C,N));
    }
}
