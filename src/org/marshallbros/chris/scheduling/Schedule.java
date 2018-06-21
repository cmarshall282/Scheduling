package org.marshallbros.chris.scheduling;

import java.util.Random;

class Schedule {
    Group[] groups;
    int fitness;

    protected Schedule(Group[] parentA, Group[] parentB, Random r) {
        int midPoint = r.nextInt(parentA.length);
        for(int i = 0; i < parentA.length; i++) {
            if(i < midPoint) groups[i] = parentA[i];
            else groups[i] = parentB[i];
        }
    }

    protected Schedule(Group[] masterList, Random r) {
        groups = masterList;

        randomizeGroups(r);
    }

    private void randomizeGroups(Random r) {
        for(int i = 0; i < groups.length; i++) {
            int index = r.nextInt(groups.length);

            Group a = groups[index];
            groups[index] = groups[i];
            groups[i] = a;
        }
    }

    void calculateFitness() {
        fitness = 0;

        //Individual fitness is how many conflicts a schedule has the lower the better. This will be normalized and inverted against the population.
        for(int i = 0; i < groups.length - 1; i++) {
            //Calculate fitness three groups out when possible.
            fitness += groups[i].conflicts(groups[i+1]);
            //make sure it doesn't pull from a non-existent index
            if(i < groups.length - 2) fitness += groups[i].conflicts(groups[i+2]);
            if(i < groups.length - 3) fitness += groups[i].conflicts(groups[i+3]);
        }
    }

    void normalizeFitness(int maxFitness) {
        fitness = maxFitness - fitness;
        fitness /= maxFitness;
        fitness *= 100;
    }

    void mutate(double mutationRate, Random r) {
        if(r.nextDouble() < mutationRate) {
            int indexOne = r.nextInt(groups.length);
            int indexTwo = r.nextInt(groups.length);

            Group a = groups[indexOne];
            groups[indexOne] = groups[indexTwo];
            groups[indexTwo] = a;
        }
    }
}
