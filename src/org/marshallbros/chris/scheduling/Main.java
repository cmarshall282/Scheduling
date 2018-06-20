package org.marshallbros.chris.scheduling;

public class Main {
    public static void main(String[] args) {
        Population population = new Population(200, 0.01);

        while(!population.isDone()) {
            population.calculateFitness();
            population.generateMatingPool();
            population.generateNewPopulation();

            System.out.println("Generation:" + population.generations);
            System.out.println("Max Fitness:" + population.maxFitness);
            System.out.println("Average Fitness:" + population.averageFitness);
        }

        //Print out end result
        for(int i = 0; i < 10; i++) System.out.println("");
        population.printBest();
    }
}
