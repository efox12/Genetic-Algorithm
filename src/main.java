/*
Class: CPSC 427-01
Name: Erik Fox
GU	Username: efox2
Assignment:	Assignment 7
*/


import java.lang.*;

public class main {

 public static void main(String args[]) {
        TSP tsp = new TSP("params.dat");
        TSP tsp1 = new TSP("params.dat");
        TSP tsp2 = new TSP("params.dat");
        TSP tsp3 = new TSP("params.dat");

        System.out.println();

        System.out.println("Type" + "\t# Generations\t" + "Path\t\t" + "Cost\t" +"Starting Pop\t"+"Working Pop"+"\tMutation");
        tsp.Evolve(0);
        tsp1.Evolve(1);
        tsp2.Evolve(2);
        tsp3.Evolve(3);
    }
}

