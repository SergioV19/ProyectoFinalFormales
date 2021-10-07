package modules.expresiones_regulares.src.views;


import modules.expresiones_regulares.src.models.NFA;
import modules.expresiones_regulares.src.models.Transition;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AutomatonPanel extends JPanel {

    public AutomatonPanel(NFA nfa, String expression) {
        add(new JLabel("Automata generado a partir de la expresion regular: "  + expression), BorderLayout.NORTH);
        setLayout(new BorderLayout());
        paintAutomatonInfo(nfa);
    }

    public void paintAutomatonInfo(NFA nfa) {
        String[] columnNames = {"Origen", "Valor", "Destino"};
        createTable(convertInfo(nfa), columnNames);
    }

    public void createTable(Object[][] info, String[] columnNames) {
        DefaultTableModel model = new DefaultTableModel(info, columnNames);
        JTable table = new JTable(model);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setBackground(Color.decode("#282E33"));
        table.getTableHeader().setForeground(Color.white);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.setRowHeight(20);
        table.setBackground(Color.white);
        table.setFillsViewportHeight(true);
        table.setBorder(null);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private String[][] convertInfo(NFA nfa) {
        ArrayList<Transition> transitions = nfa.getStatesInfo();
        String[][] info = new String[transitions.size()][3];
        for (int i = 0; i < info.length; i++) {
            info[i][0] = transitions.get(i).getOriginState();
            info[i][1] = transitions.get(i).getValue();
            info[i][2] = transitions.get(i).getDestinationState();
        }
        return info;
    }
}
