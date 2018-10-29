/*
Class: CPSC 427-01
Name: Erik Fox
GU	Username: efox2
Assignment:	Assignment 7
*/

import java.io.*;
import java.util.*;
import java.lang.*;

public class TSP extends GA {
    private String GP_fileName;
    private ObjectInputStream GP_input;
    ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

    public TSP(String fileName) {
        super(fileName);
        GA_numGenes = 8;

        InitializeMatrix();
        InitPop();
    }

    // create the matrix
    private void InitializeMatrix() {
        Integer[] mat = {
            0, 2080, 991, 326, 2086, 1080, 2518, 166,
            2080, 0, 1407, 1883, 993, 1260, 944, 2225,
            991, 1407, 0, 669, 1840, 148, 2184, 1075,
            326, 1883, 669, 0, 1965, 784, 2397, 407,
            2086, 993, 1840, 1965, 0, 1787, 622, 2265,
            1080, 1260, 148, 784, 1787, 0, 2131, 1187,
            2518, 944, 2184, 2397, 622, 2131, 0, 2697,
            166, 2225, 1075, 407, 2265, 1187, 2697, 0
        };
        int count = 0;
        for(int i = 0; i < 8; i++){
            matrix.add(new ArrayList<Integer>());
            for(int j = 0; j < 8; j++){
                matrix.get(i).add(mat[count]);
                count++;
            }
        }
    }

    public void InitPop() {
        super.InitPop();
        ComputeCost();
        SortPop();
        TidyUp();
    }

    // gets the row or column number in the matrix
    public int getSpot(char ch){
        if(ch == 'a'){
            return 0;
        } else if(ch == 'b'){
            return 1;
        } else if(ch == 'c'){
            return 2;
        } else if(ch == 'd'){
            return 3;
        } else if(ch == 'e'){
            return 4;
        } else if(ch == 'f'){
            return 5;
        } else if(ch == 'g') {
            return 6;
        } else {
            return 7;
        }
    }

    // computes the total cost based on total distance traveled
    protected void ComputeCost() {
        for (int i = 0; i < GA_pop.size(); i++) {
            int cost = 0;
            Chromosome chrom = GA_pop.remove(i);
            for (int j = 0; j < GA_numGenes; j++) {
                if(j< GA_numGenes-1)
                    cost += matrix.get(getSpot(chrom.GetGene(j))).get(getSpot(chrom.GetGene(j + 1)));
                else
                    cost += matrix.get(getSpot(chrom.GetGene(0))).get(getSpot(chrom.GetGene(GA_numGenes - 1)));
            }

            chrom.SetCost(cost);
            GA_pop.add(i,chrom);
        }
    }
    //in earlier versions (as on ada) Evolve() from GA is here
}


