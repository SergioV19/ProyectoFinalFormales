package modules.expresiones_regulares.src.models;

import java.util.ArrayList;
import java.util.Stack;

public class NFA {
    private Automaton automaton;
    private Stack<Automaton> stack;
    private String expression;
    private ArrayList<String> alphabet;
    private ArrayList<Integer> statesList;

    public NFA(String expression){
        this.stack = new Stack<>();
        this.alphabet = new ArrayList();
        this.statesList = new ArrayList();
        this.expression = expression;
        setAlphabet();
        this.generateAutomaton();
        setStatesList();
    }

    public void generateAutomaton(){
        for(char c: expression.toCharArray()){
            switch(c){
                case '+':
                    this.generateConcatenationAutomaton();
                    break;
                case '|':
                    this.generateJoinAutomaton();
                    break;
                case '*':
                    this.generateKleeneAutomaton();
                    break;
                default:
                    generateBasicAutomaton(c);
                    break;
            }
        }

        this.automaton = this.stack.pop();

        //Se reinician los valores boolean que indican si el estado es de inicio
        // o de aceptacion que tiene cada estado para luego asignarlos
        //de manera correcta
        for(State state: this.automaton.getStates()){
            state.setStart(false);
            state.setEnd(false);
        }

        //se indica mediante un boolean el estado de inicio del automata generado
        this.automaton.getStart().setStart(true);

        //se indican los estados finales correspondientes del automata mediante
        //booleans
        for(State state: this.automaton.getEndings()){
            state.setEnd(true);
        }
    }

    public void printAutomaton(){
        System.out.println("");
        System.out.println("NFA");
        System.out.printf("K = { ");
        for(int i = 0; i<this.statesList.size(); i++){
            if(this.statesList.size()-1 == i){
                System.out.printf("q"+ getStatesList().get(i));
            }else{
                System.out.printf("q"+ getStatesList().get(i)+" ,");
            }
        }
        System.out.printf(" }");
        System.out.println(" ");
        System.out.printf("Sigma = ");
        System.out.println(getAlphabet());
        System.out.println("Delta :");
        for(State state : this.automaton.getStates()){
            state.printTransitions();
        }
        System.out.println("s = { q" + this.automaton.getStart().getId() + " }");
        System.out.printf("F = { ");
        for(int i = 0; i<this.automaton.getEndings().size(); i++){
            if(this.automaton.getEndings().size() - 1 == i){
                System.out.printf("q" + this.automaton.getEndings().get(i).getId());
            }else{
                System.out.printf("q" + this.automaton.getEndings().get(i).getId() + ",");
            }
        }
        System.out.printf(" }");
        System.out.println("");
    }

    public ArrayList<Transition> getStatesInfo(){
        ArrayList<Transition> transitions = new ArrayList<>();
        for(State state : this.automaton.getStates()){
            transitions.addAll(state.printTransitions());
        }
        return transitions;
    }

    public void generateBasicAutomaton(char c){
        Automaton basic =  new Automaton();
        State stateOne =  new State(0, true, false);
        State stateTwo =  new State(1, false, true);

        stateOne.addTransition(c, stateTwo);

        basic.addState(stateOne);
        basic.addState(stateTwo);

        basic.setStartState(stateOne);
        basic.addEndState(stateTwo);

        this.stack.push(basic);
    }

    public void generateJoinAutomaton(){
        Automaton automatonOne = this.stack.pop();
        Automaton automatonTwo = this.stack.pop();
        Automaton union =  new Automaton();

        State start = new State(0, true, false);
        State end = new State(0, false, true);

        start.addTransition('_', automatonOne.getStart());
        start.addTransition('_', automatonTwo.getStart());

        automatonOne.getEndings().get(0).addTransition('_', end);
        automatonTwo.getEndings().get(0).addTransition('_', end);

        //se agregan los estados que conformaran el nuevo automata
        //para luego modificar sus id
        union.addState(start);

        for(State state: automatonOne.getStates()){
            union.addState(state);
        }

        for(State state: automatonTwo.getStates()){
            union.addState(state);
        }

        union.addState(end);

        //se actualizan los id
        for(int i = 0; i < union.getStates().size(); i++){
            union.getStates().get(i).setID(i);
        }

        union.setStartState(start);
        union.addEndState(end);

        this.stack.push(union);
    }

    public void generateConcatenationAutomaton(){
        Automaton automaton1;
        Automaton automaton2;
        Automaton concatenation =  new Automaton();

        automaton2 = this.stack.pop();
        automaton1 = this.stack.pop();

        //se crea la transicion entre los dos automatas.
        automaton1.getEndings().get(0).addTransition('_', automaton2.getStart());

        //se agregan todos los estados al automata de concatenacion.
        for(State state: automaton1.getStates()){
            concatenation.addState(state);
        }

        for(State state : automaton2.getStates()){
            concatenation.addState(state);
        }

        //se definen los estados de inicio y fin del nuevo automata
        concatenation.setStartState(automaton1.getStart());
        concatenation.getEndings().addAll(automaton2.getEndings());

        //se actualizan los id de los estados para el nuevo automata generado
        for(int i = 0; i < concatenation.getStates().size(); i++){
            concatenation.getStates().get(i).setID(i);
        }

        this.stack.push(concatenation);
    }

    public void generateKleeneAutomaton(){
        Automaton automaton = this.stack.pop();
        Automaton kleene = new Automaton();

        State start = new State(0, true, false);
        State end = new State(0, false, true);

        start.addTransition('_', automaton.getStart());
        start.addTransition('_', end);

        automaton.getEndings().get(0).addTransition('_', end);
        automaton.getEndings().get(0).addTransition('_', automaton.getStart());

        kleene.setStartState(start);
        kleene.addEndState(end);

        kleene.addState(start);

        for(State state: automaton.getStates()){
            kleene.addState(state);
        }

        kleene.addState(end);

        for(int i = 0; i < kleene.getStates().size(); i++){
            kleene.getStates().get(i).setID(i);
        }

        this.stack.push(kleene);
    }

    public Automaton getAutomaton() {
        return this.automaton;
    }

    public String getExpression() {
        return expression;
    }

    public ArrayList<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet() {
        for (Character c: getExpression().toCharArray()){
            if (c != '|' && c != '*' && c != '+' && c != '_' && c!='0'){
                if(!this.alphabet.contains(Character.toString(c))){
                    this.alphabet.add(Character.toString(c));
                }
            }
        }
    }

    public ArrayList<Integer> getStatesList(){
        return this.statesList;
    }

    private void setStatesList() {
        for(int i = 0; i<this.automaton.getStates().size(); i++){
            State state = this.automaton.getStates().get(i);
            this.statesList.add(state.getId());
        }
    }
}
