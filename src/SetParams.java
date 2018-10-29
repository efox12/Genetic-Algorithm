/*
Class: CPSC 427-01
Name: Erik Fox
GU	Username: efox2
Assignment:	Assignment 7
*/

/*  SetParams creates the parameter file. args[0] is its name.
    Here is a reasonable set of command line arguments:
    java SetParams params.dat 128 64 .1 1000
    where   128  = the size of the initial population
            64   = the size of the working population
            .1   = the mutation factor
            1000 = the number of generations
*/

import java.lang.*;

public class SetParams {
    public static void main(String args[]) {

        //int numChromesI = Integer.parseInt(args[0]);
        //int numChromes = Integer.parseInt(args[1]);
        //double mutFact = Double.parseDouble(args[2]);
        //int numIters = Integer.parseInt(args[3]);

        int numChromesI = 128;
        int numChromes = 64;
        double mutFact =.01;
        int numIters = 10000;
        GetParams GP = new GetParams("params.dat",numChromesI,numChromes,mutFact,numIters);
    }
}

