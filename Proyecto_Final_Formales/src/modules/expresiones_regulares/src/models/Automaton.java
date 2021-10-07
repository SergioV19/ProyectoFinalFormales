package modules.expresiones_regulares.src.models;

import java.util.ArrayList;

public class Automaton {

    private State start;
    private ArrayList<State> endings;
    private ArrayList<State> states;

    public Automaton () {
        endings = new ArrayList<State>();
        states = new ArrayList<State>();
    }

    void setStartState(State start){
        this.start = start;
    }

    void addState(State state){
        this.states.add(state);
    }

    void addEndState(State state){
        this.endings.add(state);
    }

    public State getStart() {
        return start;
    }

    public ArrayList<State> getStates() {
        return states;
    }

    public ArrayList<State> getEndings() {
        return endings;
    }
}
