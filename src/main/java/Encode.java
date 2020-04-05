package main.java;

import rsa.java.Rsa;

import javax.swing.*;
import java.awt.*;


public class Encode extends JPanel {

    private JLabel Plab, Qlab, PQTime, Evalue;
    private JTextField NInput, Minput, MessageEncrypted;
    private Rsa rsa;
    private long lastE = 2;

    public Encode(){
        rsa = new Rsa();

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JLabel title = new JLabel("Encode");
        JLabel NLabel = new JLabel("Give me the N:");
        NInput = new JTextField("123");
        JButton NButton = new JButton("Step 1: Generate P and Q");

        JLabel Pis = new JLabel("P is");
        Plab = new JLabel("0");

        JLabel Qis = new JLabel("Q is");
        Qlab = new JLabel("0");

        JLabel PQTimeLabel = new JLabel("Amount of time busy finding p and q (Nanoseconds):");
        PQTime = new JLabel("0");

        JButton EButton = new JButton("Step 2: Generate an E (Press again for another)");
        JLabel Elab =  new JLabel("E is: ");
        Evalue = new JLabel("0");

        JLabel Mlab = new JLabel("Give me a message: ");
        Minput = new JTextField("Hello");
        JButton MButton = new JButton("Step 3: Encrypt M");
        JLabel MAfterEnc = new JLabel("Message after encryption is: ");
        MessageEncrypted = new JTextField("0");


        NInput.setMaximumSize(new Dimension(99999999, 20));
        Minput.setMaximumSize(new Dimension(99999999, 20));

        NButton.addActionListener(actionEvent -> NButtonPressed());
        EButton.addActionListener(actionEvent -> EButtonPressed());
        MButton.addActionListener(actionEvent -> MButtonPressed());

        add(title);
        add(NLabel);
        add(NInput);
        add(NButton);
        add(Pis);
        add(Plab);
        add(Qis);
        add(Qlab);
        add(PQTimeLabel);
        add(PQTime);
        add(EButton);
        add(Elab);
        add(Evalue);
        add(Mlab);
        add(Minput);
        add(MButton);
        add(MAfterEnc);
        add(MessageEncrypted);
    }

    public void showP(long value){
        Plab.setText(Long.toString(value));
    }
    public void showQ(long value){
        Qlab.setText(Long.toString(value));
    }

    public void showPQTime(long value){
        PQTime.setText(Long.toString(value));
    }

    public void showE(long value){
        Evalue.setText(Long.toString(value));
    }

    public void showM(String value){
        MessageEncrypted.setText(value);
    }
    public void NButtonPressed(){
        long N = 0;
        try {
            N = Long.parseLong(NInput.getText());
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"Invalid N provided");
            return;
        }
        if (N < 2){
            JOptionPane.showMessageDialog(this,"N cannot be smaller than 2");
            return;
        }


        showPQTime(rsa.calcPQ(N));
        showP(rsa.getP());
        showQ(rsa.getQ());
    }

    public void EButtonPressed(){
        rsa.calcE(lastE + 1);
        lastE = rsa.getE();
        showE(lastE);
    }

    public void MButtonPressed(){
        String C;

        try {
            C = Minput.getText();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid C provided");
            return;
        }
        showM(rsa.encrypt(C));
    }
}
