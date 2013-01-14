#!/bin/bash

cd states
javac State.java
javac DetState.java
cd ../fa/nfa
javac -classpath ../.. NFA.java
cd ../dfa
javac -classpath ../.. DFA.java
cd ../conversion/ 
javac -classpath ../.. Conversion.java
cd ../../test
javac -classpath .. Main.java
cd ..
java test.Main

