package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Encode extends JPanel {

    private JButton NButton;
    private JLabel P, Q, PQTime;
    private JTextField NInput;

    public Encode(){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JLabel title = new JLabel("Encode");
        JLabel NLabel = new JLabel("Give me the N:");
        NInput = new JTextField();
        NButton = new JButton("Send N");
        JLabel Pis = new JLabel("P is");
        P = new JLabel("0");
        JLabel Qis = new JLabel("Q is");
        Q = new JLabel("0");
        JLabel PQTimeLabel = new JLabel("Amount of time busy finding p and q:");
        PQTime = new JLabel("0");


        NButton.addActionListener(actionEvent -> NButtonPressed());
        NLabel.setLabelFor(NInput);
        Pis.setLabelFor(P);
        Qis.setLabelFor(Q);
        PQTimeLabel.setLabelFor(PQTime);

        this.add(title);
        this.add(NLabel);
        this.add(NInput);
        this.add(NButton);
        this.add(Pis);
        this.add(P);
        this.add(Qis);
        this.add(Q);
        this.add(PQTime);
    }

    public void showP(long value){
        P.setText(Long.toString(value));
    }
    public void showQ(long value){
        Q.setText(Long.toString(value));
    }

    public void showPQTime(int value){
        PQTime.setText(Long.toString(value));
    }
    public void NButtonPressed(){
        long N = Long.parseLong(NInput.getText());
        showP(N);
        showQ(N);
        showPQTime(420);
    }
}
