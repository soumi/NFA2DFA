package states;

import java.util.ArrayList;
import java.util.TreeSet;

public class State implements Comparable<State>{

    private Integer id;
    private boolean finalState;
    private boolean startingState;
    private ArrayList<TreeSet<State>> transition;

    public State(int id){
	this.id = id;
	transition = new ArrayList<TreeSet<State>>();
    }

    public void setId(int id){
	this.id = id;
    }
    public Integer getId(){
	return this.id;
    }
    public boolean isFinalState(){
	return finalState;
    }
    public void setFinalState(boolean finalState){
	this.finalState = finalState;
    }
    public boolean isStartingState(){
	return startingState;
    }
    public void setStartingState(boolean startingState){
	this.startingState = startingState;
    }
    public void addTransition(int index, State s){
	try{	
	    (transition.get(index)).add(s);
	}catch(Exception e){
	    transition.add(new TreeSet<State>());
	    if (s != null)	
	       (transition.get(index)).add(s);
	}
    }
    public TreeSet<State> getStates(int index){
	return transition.get(index);
    }
    public int compareTo(State s){//Overriding method
	return id.compareTo(s.getId());
    }
}
