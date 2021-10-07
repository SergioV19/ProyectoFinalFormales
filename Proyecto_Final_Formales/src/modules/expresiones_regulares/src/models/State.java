package modules.expresiones_regulares.src.models;

import java.util.ArrayList;
import java.util.HashMap;

public class State {

    private int id;
    private boolean start;
    private boolean end;
    private ArrayList<State> states;

    private HashMap<Character, ArrayList<State>> transitions;

    public State(int id, boolean start, boolean end){
        this.id = id;
        this.start = start;
        this.end = end;
        transitions = new HashMap<>();
        states = new ArrayList<State>();
    }

    public void addTransition(char c, State newState){
        if(!transitions.containsKey(c)){
            ArrayList<State> states = new ArrayList<>();
            states.add(newState);
            this.transitions.put(c, states);
        }
        else{
            this.transitions.get(c).add(newState);
        }
    }

    public ArrayList<Transition> printTransitions(){
        ArrayList<Transition> data = new ArrayList<>();
        for (Character i : transitions.keySet()) {
            for(State state : transitions.get(i)){
                data.add(new Transition("q"+ id, String.valueOf(i), "q" + state.getId()));
                System.out.println("{"+" q"+ this.id+ ", " + i + "," + " q" + state.getId() + " }");
            }
        }
        return data;
    }

    /*public String[][] getTransitionsInfo(){
        String[] transition = new String[3];
        for (Character i : transitions.keySet()) {
            for(State state: transitions.get(i)){
                transition[0] = "q" + id;
                transition[1] = String.valueOf(i);
                transition[2] = "q" + state.getId();
            }
        }
    }

    private void xd(int[] transition){
        String[][] info = new String[transitions][];
    }*/

    //si el conjunto de estados que compone el estado del AFD
    //contiene un estado final del AFND, entonces el estado pasa a ser un
    //estado final.
    public void verifyEndState(){
        for(State state : this.states){
            if(state.getEnd()){
                this.end = true;
            }
        }
    }

    public boolean getStart(){
        return start;
    }

    public boolean getEnd(){
        return start;
    }

    public int getId() {
        return id;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setStart(boolean inicio){
        this.start = inicio;
    }

    public void setEnd(boolean fin){
        this.end = fin;
    }

    public HashMap<Character, ArrayList<State>> getTransitions() {
        return transitions;
    }
}
