package modules.expresiones_regulares.src.views;

import modules.expresiones_regulares.src.models.NFA;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public static final String TITLE = "Expresiones regulares";
    private InitialPanel initialPanel;
    private JTabbedPane tabbedPane;
    private AutomatonPanel automatonPanel;

    public MainFrame(ActionListener listener){
        setTitle(TITLE);
        setSize(600,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        tabbedPane = new JTabbedPane();
        addInitialPanel(listener);
        add(tabbedPane);
    }

    private void addInitialPanel(ActionListener listener){
        initialPanel = new InitialPanel(listener);
        tabbedPane.add("ER - NFA", initialPanel);
    }

    public void addAutomatonPanel(NFA nfa, String expression){
       automatonPanel = new AutomatonPanel(nfa, expression);
       tabbedPane.add("Automata", automatonPanel);
    }

    public String getExpression(){
        return initialPanel.getExpression();
    }
}
