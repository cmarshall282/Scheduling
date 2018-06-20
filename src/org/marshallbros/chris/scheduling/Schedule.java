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
        //add a fitness function that deals with scheduling conflicts
        fitness = 1;
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
