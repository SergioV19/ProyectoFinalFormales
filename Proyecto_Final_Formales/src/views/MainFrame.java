package views;

import controller.Actions;
import modules.expresiones_regulares.src.controllers.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public static final String TITLE = "Proyecto Final";

    public MainFrame(ActionListener listener){
        setTitle(TITLE);
        setSize(600,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(2,2));
        addButtons(listener);
    }

    private void addButtons(ActionListener listener){
        addREBTN(listener);
    }

    private void addREBTN(ActionListener listener){
        JButton reBtn = new JButton("Expresiones regulares");
        reBtn.setBackground(Color.WHITE);
        reBtn.setActionCommand(Actions.REGULAR_EXPRESSIONS.toString());
        reBtn.addActionListener(listener);
        add(reBtn);

        JButton btnPda = new JButton("Autómata de pila");
        btnPda.setBackground(Color.WHITE);
        btnPda.setActionCommand(Actions.PDA.toString());
        btnPda.addActionListener(listener);
        add(btnPda);

        JButton btnDfa = new JButton("Automata finito determinista");
        btnDfa.setBackground(Color.WHITE);
        btnDfa.setActionCommand(Actions.DFA.toString());
        btnDfa.addActionListener(listener);
        add(btnDfa);
    }
}
