package test;

import fa.dfa.DFA;
import states.DetState;
import fa.conversion.Conversion;
import java.util.*;

public class Main{
    public static void main(String [] args){
	Conversion c = new Conversion();
	DFA dfa = c.convert();
	for (DetState d : dfa.states){
	    System.out.println(d.getId() + " " + d.isStartingState() + " " + d.isFinalState());
	    for (int i = 0; i< dfa.alphabet.size(); i++)
		System.out.println("{" + d.getStates(i).getId() + "}");	    
	}
	for (String c1 : dfa.alphabet)
	    System.out.println(c1 + " ");
	System.out.println(dfa.isAccepted("1 0 1 1 0 1"));
	System.out.println(dfa.isAccepted("0 0"));
    }
}
