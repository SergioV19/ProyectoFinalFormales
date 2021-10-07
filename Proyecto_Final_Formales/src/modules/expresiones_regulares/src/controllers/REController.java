package modules.expresiones_regulares.src.controllers;

import modules.expresiones_regulares.src.models.NFA;
import modules.expresiones_regulares.src.models.Parser;
import modules.expresiones_regulares.src.views.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class REController implements ActionListener {

    private MainFrame mainFrame;

    public REController(){
        mainFrame = new MainFrame(this);
        mainFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand().equals(Events.CONVERT_EXPRESSION.toString())){
            String expression = mainFrame.getExpression();
            NFA nfa = convertExpression(expression);
            mainFrame.addAutomatonPanel(nfa, expression);
        }
    }

    private NFA convertExpression(String expression){
        Parser parser = new Parser(expression);
        NFA nfa= new NFA(parser.getAux());
        nfa.printAutomaton();
        return nfa;
    }
}
