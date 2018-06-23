package org.marshallbros.chris.scheduling;

public class Main {
    public static void main(String[] args) {
        GUI g = new GUI();
        Population population = new Population(200, 0.01);

        while(!population.isDone()) {
            population.calculateFitness();
            population.generateMatingPool();
            population.generateNewPopulation();

            g.displayWorking();
            g.displayGeneration(population.generations);
            g.displayMaxFitness(population.maxFitness);
            g.displayAverageFitness(population.averageFitness);
            g.displayBestSchedule(population.bestSchedule);
        }
        g.displayDone();
    }
}
