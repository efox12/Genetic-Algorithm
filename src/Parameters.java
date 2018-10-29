/*
Class: CPSC 427-01
Name: Erik Fox
GU	Username: efox2
Assignment:	Assignment 7
*/
import java.io.Serializable;
public class Parameters implements Serializable {
    private int P_numChromesI, P_numChromes, P_numIterations;
    private double P_mutFact;

    public Parameters(int numChromesI,int numChromes, double mutFact,
                    int numIterations) {
            P_numChromesI = numChromesI;
            P_numChromes = numChromes;
            P_mutFact = mutFact;
            P_numIterations = numIterations;
    }

     public int GetNumChromesI()
        {
            return P_numChromesI;
        }

     public int GetNumChromes()
        {
            return P_numChromes;
        }

     public double GetMutFact()
        {
            return P_mutFact;
        }

     public int GetNumIterations()
        {
            return P_numIterations;
        }

}
