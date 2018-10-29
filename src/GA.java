/*
Class: CPSC 427-01
Name: Erik Fox
GU	Username: efox2
Assignment:	Assignment 7
*/
import java.lang.*;
import java.util.*;

public abstract class GA extends Object
{
     protected int GA_numChromesInit;
     protected int GA_numChromes;
     protected int GA_numGenes;
     protected double GA_mutFact;
     protected int GA_numIterations;
     protected ArrayList<Chromosome> GA_pop;

     public GA(String ParamFile) {
         GetParams GP = new GetParams(ParamFile);
         Parameters P = GP.GetParameters();
         GA_numChromesInit = P.GetNumChromesI();
         GA_numChromes = P.GetNumChromes();
         GA_mutFact = P.GetMutFact();
         GA_numIterations = P.GetNumIterations();
         GA_pop = new ArrayList<Chromosome>();
     }

     public void DisplayParams()
     {
         System.out.print("Initial Chromosomes:  ");
         System.out.println(GA_numChromesInit);
         System.out.print("Chromosomes: ");
         System.out.println(GA_numChromes);
         System.out.print("Genes: ");
         System.out.println(GA_numGenes);
         System.out.print("Mutation Factor: ");
         System.out.println(GA_mutFact);
         System.out.print("Iterations: ");
         System.out.println(GA_numIterations);
     }

     protected void SortPop()
        {
            Collections.sort(GA_pop, new CostComparator());
        }


     private class CostComparator implements Comparator <Chromosome> {
         int result;
         public int compare(Chromosome obj1, Chromosome obj2) {
             result = new Integer( obj1.GetCost() ).compareTo(new Integer( obj2.GetCost() ) );
             return result;
         }
     }

     protected void TidyUp() {
         int end = GA_numChromesInit - 1;
         while (GA_pop.size() > GA_numChromes) {
             GA_pop.remove(end);
             end--;
         }
     }

     private void Mutate() {
         int totalGenes = (GA_numGenes * GA_numChromes);
         int numMutate = (int) (totalGenes * GA_mutFact);
         Random rnum = new Random();

         for (int i = 0; i < numMutate; i++) {
             //position of chromosome to mutate--but not the first one
             //the number generated is in the range: [1..GA_numChromes)

             int chromMut = 1 + (rnum.nextInt(GA_numChromes - 1));

             int geneMut = rnum.nextInt(GA_numGenes); //pos of mutated gene
             int geneMut2 = rnum.nextInt(GA_numGenes);

             Chromosome newChromosome = GA_pop.remove(chromMut); //get chromosome
             char temp = newChromosome.GetGene(geneMut);

             newChromosome.SetGene(geneMut,newChromosome.GetGene(geneMut2));//mutate it
             newChromosome.SetGene(geneMut2,temp);

             GA_pop.add(newChromosome); //add mutated chromosome at the end
            }

        }

     protected void InitPop() {

         char letter;
         for (int index = 0; index < GA_numChromesInit; index++) {
             // Create a new random chromosome
             ArrayList<Character> initialChrome = new ArrayList<Character>();
             Chromosome Chrom = new Chromosome(GA_numGenes);
             for (int j = 0; j < 8; j++) {
                 letter = (char) (j + 97);
                 initialChrome.add(letter);

             }
             Collections.shuffle(initialChrome);

             // Assign the random gene values
             for (int j = 0; j < GA_numGenes; j++) {
                 Chrom.SetGene(j ,initialChrome.get(j));
             }

             Chrom.SetCost(0);
             GA_pop.add(Chrom);
         }
     }
     protected abstract void ComputeCost();

     //In earlier versions (as on ada) this is in WordGuess and is an abstract method here
     protected void Evolve(int type) {
         int iterationCt = 0;
         Pair pairs      = new Pair(GA_pop);
         int numPairs    = pairs.SimplePair();
         boolean found   = false;
         int prevGenCost = 0;
         int count = 0;
         while (iterationCt < GA_numIterations) {
             Mate mate = new Mate(GA_pop,GA_numGenes,GA_numChromes);

             // Choose a pairing strategy
             if(type == 0)
                 GA_pop = mate.TopDownCrossover(GA_pop,numPairs);
             else if(type == 1)
                 GA_pop = mate.TopDownDoubleCrossover(GA_pop,numPairs);
             else if(type == 2)
                 GA_pop = mate.TournamentCrossover(GA_pop,numPairs);
             else
                 GA_pop = mate.TournamentDoubleCrossover(GA_pop,numPairs);

             Mutate();
             ComputeCost();
             SortPop();
             Chromosome chrome = GA_pop.get(0); //get the best guess

             int totalGenCost = 0;
             for(int i = 0; i<GA_pop.size(); i++){
                 totalGenCost+=GA_pop.get(i).GetCost();
             }
             int averageChromeCost = totalGenCost/GA_pop.size();
             int standardDeviation = averageChromeCost - chrome.GetCost();
             if (standardDeviation < 10)
                 break;
             ++iterationCt;
             //prevChromeCost = chrome.GetCost();
         }
         Chromosome chrome = GA_pop.get(0);

         // Output a row of the table with the values
         if(iterationCt <1000)
             System.out.print(type + "\t\t" + iterationCt+"\t\t\t\t");
         else
             System.out.print(type + "\t\t" + iterationCt+"\t\t\t");
         chrome.DisplayGenes();
         System.out.print("\t"+chrome.GetCost());
         System.out.println("\t"+GA_numChromesInit+"\t\t\t\t"+GA_numChromes+"\t\t\t"+GA_mutFact);
     }
}

