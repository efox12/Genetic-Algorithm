/*
Class: CPSC 427-01
Name: Erik Fox
GU	Username: efox2
Assignment:	Assignment 7
*/

import java.util.*;
import java.lang.*;


class SortbyCost implements Comparator<Chromosome> {
    public int compare(Chromosome a, Chromosome b)
    {
        return a.GetCost() - b.GetCost();
    }
}

public class Mate {
     private Chromosome MT_father, MT_mother, MT_child1, MT_child2;
     private int MT_posChild1, MT_posChild2, MT_posLastChild,MT_posFather, MT_posMother, MT_numGenes, MT_numChromes;

     public Mate(ArrayList<Chromosome> population, int numGenes, int numChromes) {
            MT_numGenes     = numGenes;
            MT_numChromes   = numChromes;

            MT_posChild1    = population.size()/2;
            MT_posChild2    = MT_posChild1 + 1;
            MT_posLastChild = population.size() - 1;

            for (int i = MT_posLastChild; i >= MT_posChild1; i--)
                population.remove(i);

            MT_posFather = 0;
            MT_posMother = 1;
        }

    //Top-Down Pairing with single point crossover
    public ArrayList<Chromosome> TopDownCrossover(ArrayList<Chromosome> population, int numPairs) {
        for (int j = 0; j < numPairs; j++) {
            MT_father       =  population.get(MT_posFather);
            MT_mother       =  population.get(MT_posMother);
            MT_child1       = new Chromosome(MT_numGenes);
            MT_child2       = new Chromosome(MT_numGenes);
            Random rnum     = new Random();
            int crossPoint  = rnum.nextInt(MT_numGenes);

            //left side
            for (int i = 0; i < crossPoint; i++) {
                MT_child1.SetGene(i,MT_father.GetGene(i));
                MT_child2.SetGene(i,MT_mother.GetGene(i));
            }

            //right side
            for (int i = crossPoint; i < MT_numGenes; i++) {
                MT_child1.SetGene(i,MT_father.GetGene(i));
                MT_child2.SetGene(i,MT_mother.GetGene(i));
            }

            population.add(MT_posChild1,MT_child1);
            population.add(MT_posChild2,MT_child2);

            MT_posChild1    = MT_posChild1 + 2;
            MT_posChild2    = MT_posChild2 + 2;
            MT_posFather    = MT_posFather + 2;
            MT_posMother    = MT_posMother + 2;
        }
        return population;
    }

    //Top-Down Pairing with double point crossover
    public ArrayList<Chromosome> TopDownDoubleCrossover(ArrayList<Chromosome> population, int numPairs) {
        for (int j = 0; j < numPairs; j++) {
            MT_father       =  population.get(MT_posFather);
            MT_mother       =  population.get(MT_posMother);
            MT_child1       = new Chromosome(MT_numGenes);
            MT_child2       = new Chromosome(MT_numGenes);
            Random rnum     = new Random();
            int crossPoint1  = rnum.nextInt(MT_numGenes);
            int crossPoint2  = rnum.nextInt(MT_numGenes);
            if(crossPoint2 > crossPoint1){
                int temp = crossPoint1;
                crossPoint1 = crossPoint2;
                crossPoint2 = temp;
            }
            //left side
            for (int i = 0; i < crossPoint1; i++) {
                MT_child1.SetGene(i,MT_father.GetGene(i));
                MT_child2.SetGene(i,MT_mother.GetGene(i));
            }

            //middle
            for (int i = crossPoint1; i < crossPoint2; i++) {
                MT_child1.SetGene(i, MT_mother.GetGene(i));
                MT_child2.SetGene(i, MT_father.GetGene(i));
            }

            //right side
            for (int i = crossPoint2; i < MT_numGenes; i++) {
                MT_child1.SetGene(i,MT_father.GetGene(i));
                MT_child2.SetGene(i,MT_mother.GetGene(i));
            }

            population.add(MT_posChild1,MT_child1);
            population.add(MT_posChild2,MT_child2);

            MT_posChild1    = MT_posChild1 + 2;
            MT_posChild2    = MT_posChild2 + 2;
            MT_posFather    = MT_posFather + 2;
            MT_posMother    = MT_posMother + 2;
        }
        return population;
    }

    //Tournament pairing with single point crossover
    public ArrayList<Chromosome> TournamentCrossover(ArrayList<Chromosome> population, int numPairs) {
        for (int j = 0; j < numPairs; j++) {
            Random rnum = new Random();

            // get a two random sections of Chromosomes and find the best one from each
            int startIndex = rnum.nextInt(16);
            ArrayList<Chromosome> subset1 = new  ArrayList<Chromosome>();
            for(int i = startIndex; i< startIndex+16; i++){
                subset1.add(population.get(i));
            }
            Collections.sort(subset1, new SortbyCost());

            startIndex = rnum.nextInt(16);
            ArrayList<Chromosome> subset2 = new  ArrayList<Chromosome>();
            for(int i = startIndex; i< startIndex+16; i++){
                subset2.add(population.get(i));
            }
            Collections.sort(subset2, new SortbyCost());

            MT_father = subset1.get(0);
            MT_mother = subset2.get(0);
            MT_child1 = new Chromosome(MT_numGenes);
            MT_child2 = new Chromosome(MT_numGenes);

            int crossPoint  = rnum.nextInt(MT_numGenes);

            //left side
            for (int i = 0; i < crossPoint; i++) {
                MT_child1.SetGene(i,MT_father.GetGene(i));
                MT_child2.SetGene(i,MT_mother.GetGene(i));
            }

            //right side
            for (int i = crossPoint; i < MT_numGenes; i++) {
                MT_child1.SetGene(i,MT_father.GetGene(i));
                MT_child2.SetGene(i,MT_mother.GetGene(i));
            }

            population.add(MT_posChild1,MT_child1);
            population.add(MT_posChild2,MT_child2);

            MT_posChild1    = MT_posChild1 + 2;
            MT_posChild2    = MT_posChild2 + 2;
        }
        return population;
    }

    //Tournament pairing with double point crossover
    public ArrayList<Chromosome> TournamentDoubleCrossover(ArrayList<Chromosome> population, int numPairs) {
        for (int j = 0; j < numPairs; j++) {
            Random rnum = new Random();

            // get a two random sections of Chromosomes and find the best one from each
            int startIndex = rnum.nextInt(16);
            ArrayList<Chromosome> subset1 = new  ArrayList<Chromosome>();
            for(int i = startIndex; i< startIndex+16; i++){
                subset1.add(population.get(i));
            }
            Collections.sort(subset1, new SortbyCost());

            startIndex = rnum.nextInt(16);
            ArrayList<Chromosome> subset2 = new  ArrayList<Chromosome>();
            for(int i = startIndex; i< startIndex+16; i++){
                subset2.add(population.get(i));
            }
            Collections.sort(subset2, new SortbyCost());

            MT_father = subset1.get(0);
            MT_mother = subset2.get(0);
            MT_child1 = new Chromosome(MT_numGenes);
            MT_child2 = new Chromosome(MT_numGenes);


            int crossPoint1  = rnum.nextInt(MT_numGenes);
            int crossPoint2  = rnum.nextInt(MT_numGenes);

            if(crossPoint2 > crossPoint1){
                int temp = crossPoint1;
                crossPoint1 = crossPoint2;
                crossPoint2 = temp;
            }

            //left side
            for (int i = 0; i < crossPoint1; i++) {
                MT_child1.SetGene(i,MT_father.GetGene(i));
                MT_child2.SetGene(i,MT_mother.GetGene(i));
            }

            //middle
            for (int i = crossPoint1; i < crossPoint2; i++) {
                MT_child1.SetGene(i, MT_mother.GetGene(i));
                MT_child2.SetGene(i, MT_father.GetGene(i));
            }

            //right side
            for (int i = crossPoint2; i < MT_numGenes; i++) {
                MT_child1.SetGene(i,MT_father.GetGene(i));
                MT_child2.SetGene(i,MT_mother.GetGene(i));
            }

            population.add(MT_posChild1,MT_child1);
            population.add(MT_posChild2,MT_child2);

            MT_posChild1    = MT_posChild1 + 2;
            MT_posChild2    = MT_posChild2 + 2;
        }
        return population;
    }
 }
