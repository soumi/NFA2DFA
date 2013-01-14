package fa.nfa;


import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Scanner;
import java.io.*;
import states.State;

public class NFA{
    public ArrayList<State> states;
    public ArrayList<String> alphabet;//change
    public State startState;

    public void readNFA(){
	try{
	    Scanner sc =new Scanner(new File("test.txt"));
	    while (sc.hasNext()){
		String next = sc.next().trim();
		if (next.equals("Q")){
		    states = new ArrayList<State>();
		    int no = Integer.parseInt(sc.next());
		    for (int i = 0; i < no; i++)
			states.add(new State(i));		    
		} 
		if (next.equals("A")){
		    alphabet = new ArrayList<String>();//change
		    next = sc.next();
		    while (!next.equals("S")){
			alphabet.add(next.trim());//change
			next = sc.next();
		    }		
		}
		if (next.equals("S")){
		    next = sc.next();
		    while (!next.equals("F")){
			states.get(Integer.parseInt(next)).setStartingState(true);
			this.startState = states.get(Integer.parseInt(next));
			next = sc.next();
		    }
		}
		if (next.equals("F")){
		    next = sc.next();
		    while (!next.equals("T")){
			states.get(Integer.parseInt(next)).setFinalState(true);
			next = sc.next();
		    }
		}
		if (next.equals("T")){
		    int countStates = 0;
		    //int countAlphabet = 0;
		    while (sc.hasNext()){
			for (int i = 0; i <= alphabet.size(); i++){
			    next = sc.next();
			    next = next.substring(1, next.length() - 1);
			    //System.out.println("first : " + i + " " + countStates);
			    if (next.length() == 0){ 
				states.get(countStates).addTransition(i, null);
				continue;
			    }
			    String str[] = next.split(",");
			    //System.out.println(i + " " + countStates);
			    for (String s: str)
				states.get(countStates).addTransition(i, states.get(Integer.parseInt(s)));
			}
			countStates++;
		    }
		    
		}
	    }
	}catch(Exception e){}
    }

    public TreeSet<State> getEClosure(State q, TreeSet<State> eCl){
	//eCl = q.getStates(alphabet.size());
	TreeSet<State> temp = q.getStates(alphabet.size());
	if (temp.isEmpty())
	    return eCl;
	for (State s1 : temp){
	    boolean f = eCl.add(s1);
	    if (f)
		eCl = getEClosure(s1, eCl);
	}		    
	return eCl;
    }    
    
}
