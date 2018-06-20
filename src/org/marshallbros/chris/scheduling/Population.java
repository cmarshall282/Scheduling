package org.marshallbros.chris.scheduling;

import java.util.LinkedList;
import java.util.Random;

public class Population {
    private Random r;

    //Variables for genetic algorithm
    private Schedule[] schedules;
    private LinkedList<Schedule> matingPool;
    private double mutationRate;

    //Data variables
    int generations;
    int maxFitness;
    private Schedule bestSchedule;
    double averageFitness;

    public Population(int size, double mutationRate) {
        schedules = new Schedule[size];
        for(Schedule s : schedules) s = new Schedule(r);
        matingPool = new LinkedList<>();
        this.mutationRate = mutationRate;

        generations = 0;
        maxFitness = 0;
        averageFitness = 0;
    }

    void calculateFitness() {
        for(Schedule s : schedules) {
            s.calculateFitness();
            if(s.fitness > maxFitness) {
                maxFitness = s.fitness;
                bestSchedule = s;
            }
        }
    }

    void generateMatingPool() {
        matingPool.clear();

        for(Schedule s : schedules) {
            for(int i = 0; i < s.fitness; i++) {
                matingPool.add(s);
            }
        }
    }

    void generateNewPopulation() {
        for(int i = 0; i < schedules.length; i++) {
            int a = r.nextInt(matingPool.size());
            int b = r.nextInt(matingPool.size());

            Schedule parentA = matingPool.get(a);
            Schedule parentB = matingPool.get(b);

            Schedule child = new Schedule(parentA.groups, parentB.groups, r);
            //child.mutate();

            schedules[i] = child;
        }
    }

    boolean isDone() {
        if(generations > 15000) return true;
        else return false;
    }

    void printBest() {
        for(Group g : bestSchedule.groups) System.out.println(g.name);
    }
}
