package main.java;

import rsa.java.Rsa;

import javax.swing.*;
import java.awt.*;


public class Encode extends JPanel {

    private JLabel Plab, Qlab, PQTime, Evalue, Mvalue;
    private JTextField NInput, Minput;

    private long N, P, Q, E = 0;

    public Encode(){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JLabel title = new JLabel("Encode");
        JLabel NLabel = new JLabel("Give me the N:");
        NInput = new JTextField("123");
        JButton NButton = new JButton("Step 1: Send N");
        JLabel Pis = new JLabel("P is");
        Plab = new JLabel("0");
        JLabel Qis = new JLabel("Q is");
        Qlab = new JLabel("0");
        JLabel PQTimeLabel = new JLabel("Amount of time busy finding p and q (Nanoseconds):");
        PQTime = new JLabel("0");

        JButton EButton = new JButton("Step 2: Generate an E");
        JLabel Elab =  new JLabel("E is: ");
        Evalue = new JLabel("0");

        Minput = new JTextField("Hello");
        JButton MButton = new JButton("Step 3: Encrypt M");
        JLabel MAfterEnc = new JLabel("Message after encryption is: ");
        Mvalue = new JLabel("0");


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
        add(Minput);
        add(MButton);
        add(MAfterEnc);
        add(Mvalue);
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

    public void showM(long value){
        Mvalue.setText(Long.toString(value));
    }
    public void NButtonPressed(){
        try {
            N = Long.parseLong(NInput.getText());
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"Invalid N provided");
            return;
        }
        if (N == 0){
            JOptionPane.showMessageDialog(this,"N Cannot be 0");
            return;
        }

        Rsa rsa = new Rsa();
        showPQTime(rsa.calcPQ(N));
        P = rsa.getP();
        Q = rsa.getQ();
        showP(P);
        showQ(Q);
    }

    public void EButtonPressed(){
        showE(123);
    }

    public void MButtonPressed(){
        showM(69);
    }
}
