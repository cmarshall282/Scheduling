package org.marshallbros.chris.scheduling;

import java.util.LinkedList;
import java.util.Random;

public class Population {
    private Random r;

    //Variables for genetic algorithm
    Schedule[] schedules;
    LinkedList<Schedule> matingPool;
    double mutationRate;

    //Data variables
    int generations;
    int maxFitness;
    double averageFitness;

    public Population(int size, double mutationRate) {
        schedules = new Schedule[size];
        for(Schedule s : schedules) s = new Schedule();
        matingPool = new LinkedList<>();
        this.mutationRate = mutationRate;

        generations = 0;
        maxFitness = 0;
        averageFitness = 0;
    }

}
