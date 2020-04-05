package main.java;

import rsa.java.Rsa;

import javax.swing.*;
import java.awt.*;


public class Encode extends JPanel {

    private JButton NButton;
    private JLabel Plab, Qlab, PQTime;
    private JTextField NInput;

    private long N, P, Q = 0;

    public Encode(){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JLabel title = new JLabel("Encode");
        JLabel NLabel = new JLabel("Give me the N:");
        NInput = new JTextField();
        NButton = new JButton("Send N");
        JLabel Pis = new JLabel("P is");
        Plab = new JLabel("0");
        JLabel Qis = new JLabel("Q is");
        Qlab = new JLabel("0");
        JLabel PQTimeLabel = new JLabel("Amount of time busy finding p and q (Nanoseconds):");
        PQTime = new JLabel("0");

        NInput.setMaximumSize(new Dimension(99999999, 20));
        NButton.addActionListener(actionEvent -> NButtonPressed());
        NLabel.setLabelFor(NInput);
        Pis.setLabelFor(Plab);
        Qis.setLabelFor(Qlab);
        PQTimeLabel.setLabelFor(PQTime);

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
    public void NButtonPressed(){
        N = Long.parseLong(NInput.getText());
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
}
