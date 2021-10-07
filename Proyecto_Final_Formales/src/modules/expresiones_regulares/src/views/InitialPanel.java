package modules.expresiones_regulares.src.views;

import modules.expresiones_regulares.src.controllers.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InitialPanel extends JPanel {

    public static final String CONVERT_BTN_TXT = "Convertir";
    private JTextField expressionTxt;

    public InitialPanel(ActionListener listener){
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        expressionTxt = new JTextField();
        expressionTxt.setBorder(BorderFactory.createTitledBorder("Ingrese la expresion regular: "));
        add(expressionTxt, BorderLayout.NORTH);

        JButton convertBtn = new JButton(CONVERT_BTN_TXT);
        convertBtn.setActionCommand(Events.CONVERT_EXPRESSION.toString());
        convertBtn.addActionListener(listener);
        add(convertBtn, BorderLayout.PAGE_END);
    }

    public String getExpression(){
        String expression = expressionTxt.getText();
        expressionTxt.setText("");
        return expression;
    }
}
