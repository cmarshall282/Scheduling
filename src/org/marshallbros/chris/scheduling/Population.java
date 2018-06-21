package org.marshallbros.chris.scheduling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

class Population {
    private Random r;

    //Variables for genetic algorithm
    private Schedule[] schedules;
    private LinkedList<Schedule> matingPool;
    private double mutationRate;

    //Master group list
    private Group[] masterList = new Group[1];

    //Data variables
    int generations;
    double maxFitness;
    private Schedule bestSchedule;
    double averageFitness;

    public Population(int size, double mutationRate) {
        r = new Random();

        schedules = new Schedule[size];
        readData();
        for(int i = 0; i < schedules.length; i++) schedules[i] = new Schedule(masterList, r);
        matingPool = new LinkedList<>();
        this.mutationRate = mutationRate;

        generations = 0;
        maxFitness = 0.0;
        averageFitness = 0.0;
    }

    private void readData() {
        File inputFile = new File("/home/chris/IdeaProjects/Scheduling/src/org/marshallbros/chris/scheduling/GroupInput.txt");

        try {
            Scanner input = new Scanner(inputFile);

            //This section reads the file into a linked list and then copies it to an array to allow for dynamic input
            LinkedList<Group> tempList = new LinkedList<>();

            //Skip the title line
            input.nextLine();


            while(input.hasNextLine()) {
                String name = input.nextLine();

                int groupSize = 0;
                try {
                    groupSize = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                String[] members = new String[groupSize];
                for(int i = 0; i < members.length; i++) {
                    members[i] = input.nextLine();
                }

                tempList.add(new Group(name, groupSize, members));
            }

            masterList = new Group[tempList.size()];

            for(int i = 0; i < masterList.length; i++) {
                masterList[i] = tempList.get(i);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void calculateFitness() {
        for(int i = 0; i < schedules.length; i++) {
            schedules[i].calculateFitness();
        }

        double localMaxFitness = 0;
        for(Schedule s : schedules) {
            s.calculateFitness();
            if(s.fitness > localMaxFitness) localMaxFitness = s.fitness;
        }

        double totalFitness = 0.0;
        for(Schedule s : schedules) {
            s.normalizeFitness(localMaxFitness);
            //max fitness is normalized
            if(s.fitness > maxFitness)  {
                maxFitness = s.fitness;
                bestSchedule = s;
            }
            totalFitness += s.fitness;
        }
        totalFitness /= schedules.length;
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
            child.mutate(mutationRate, r);

            schedules[i] = child;
        }

        generations++;
    }

    boolean isDone() {
        if(maxFitness >= 100 || generations > 2000) return true;
        else return false;
    }

    void printBest() {
        for(Group g : bestSchedule.groups) System.out.println(g.name);
    }
}
