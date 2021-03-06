package fa.dfa;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.io.*;
import states.DetState;

public class DFA{
    public ArrayList<DetState> states;
    public DetState startState;
    public ArrayList<String> alphabet;

    public boolean isAccepted(String str){
	String []parts = str.trim().split(" ");
	DetState state = this.startState;
	for (String s : parts){
	    //System.out.println(state.getId() + " " + alphabet.indexOf(s) + " " + state.getStates(0));
	    state = state.getStates(this.alphabet.indexOf(s));	    		
//state = this.states.get((state.getStates(this.alphabet.indexOf(s))).getId());	    		
	}
	return state.isFinalState();
    }

}
