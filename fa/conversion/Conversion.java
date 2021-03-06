package fa.conversion;

import java.util.*;
import states.*;
import fa.nfa.NFA;
import fa.dfa.DFA;

public class Conversion{
    NFA nfa = new NFA();
    DFA dfa = new DFA();
    int count = 0;
    TreeMap<String, Integer> newStates;
    public Conversion(){
	nfa.readNFA();
    }

    public TreeSet<State> getStates(TreeSet<State> eCl, int index){
	TreeSet<State> result = new TreeSet<State>();
	for(State s : eCl){
	    TreeSet<State> temp = s.getStates(index);//directly getting by the particular alphabet
	    for (State s1 : temp){	
		boolean f1 = result.add(s1);
		if (!f1) continue;
		TreeSet<State>temp1 = new TreeSet<State>();
		temp1.add(s1);
		temp1 = nfa.getEClosure(s1, temp1);//getting by EClosure
		for (State s2 : temp1)
		    result.add (s2);
	    }
	}
	return result;
    }

    public TreeMap<String, Integer> convert(TreeMap<String, Integer> newStates, TreeSet<State> eCl, String findKey){
	//System.out.println(count);
	for (int i = 0; i < nfa.alphabet.size(); i++){
	    TreeSet<State> temp = this.getStates(eCl, i);
	    String key = "";
	    boolean isFinal = false;
	    for (State s: temp){
		key = key + s.getId() + " ";
		if (!isFinal && s.isFinalState())
		    isFinal = true;		
	    }
	    Integer prev = newStates.put(key, count++);	
	    if (prev != null){
		newStates.put(key, prev);
		count--;
		(dfa.states.get(newStates.get(findKey))).addTransition(i, dfa.states.get(newStates.get(key)));//change
		continue;
	    }
	    DetState s = new DetState(newStates.get(key));
	    s.setFinalState(isFinal);
	    dfa.states.add(s);
	    (dfa.states.get(newStates.get(findKey))).addTransition(i, dfa.states.get(newStates.get(key)));//change
	    newStates = convert (newStates, temp, key);
		
	}
	return newStates;
    }//end of function

    public DFA convert(){
	State s = nfa.startState;
	TreeSet<State> eCl = new TreeSet<State>();
	eCl.add(s);
	eCl = nfa.getEClosure(s, eCl);
	newStates = new TreeMap<String, Integer>();
	String key = "";
	boolean isFinal = false;
	for (State s1: eCl){
	    key = key + s1.getId() + " ";
	    if (!isFinal && s1.isFinalState())
		isFinal = true;		
	}
	newStates.put(key, count++);
	dfa.startState = new DetState(0);
	dfa.startState.setStartingState(true);
	dfa.startState.setFinalState(isFinal);
	dfa.states = new ArrayList<DetState>();
	dfa.states.add(dfa.startState);
	convert (newStates, eCl, key);
	dfa.alphabet = nfa.alphabet;
	return dfa;
    }  
    
}
