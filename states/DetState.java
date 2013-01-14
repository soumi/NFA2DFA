package states;

import java.util.ArrayList;

public class DetState implements Comparable<DetState>{

    private Integer id;
    private boolean finalState;
    private boolean startingState;
    private ArrayList<DetState> transition;

    public DetState(int id){
	this.id = id;
	transition = new ArrayList<DetState>();
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
    public void addTransition(int index, DetState s){		
	transition.add(s);	
    }
    public DetState getStates(int index){
	return transition.get(index);
    }
    public int compareTo(DetState s){//Overriding method
	return id.compareTo(s.getId());
    }
}
