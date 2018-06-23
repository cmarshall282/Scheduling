package org.marshallbros.chris.scheduling;

import javax.swing.*;

public class GUI {
    private JFrame frame;
    private JPanel dataPanel;
    private JLabel status;
    private JLabel generationLabel;
    private JLabel maxFitLabel;
    private JLabel averageFitLabel;
    private JLabel bestScheduleLabel;

    GUI() {
        frame = new JFrame();
        dataPanel = new JPanel();
        status = new JLabel();
        generationLabel = new JLabel();
        maxFitLabel = new JLabel();
        averageFitLabel = new JLabel();
        bestScheduleLabel = new JLabel();

        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dataPanel.setSize(800, 800);
        dataPanel.setLayout(null);

        status.setBounds(0, 0, 200, 20);
        status.setText("Status: Loading");

        generationLabel.setBounds(0, 20, 200, 20);
        generationLabel.setText("Generation: 0");

        maxFitLabel.setBounds(0, 40, 200, 20);
        maxFitLabel.setText("Max Fitness: 0");

        averageFitLabel.setBounds(0, 60, 200, 20);
        averageFitLabel.setText("Average Fitness: 0.0");

        bestScheduleLabel.setBounds(400, 0, 200, 800);
        bestScheduleLabel.setText("BEST SCHEDULE");

        dataPanel.add(status);
        dataPanel.add(generationLabel);
        dataPanel.add(maxFitLabel);
        dataPanel.add(averageFitLabel);
        dataPanel.add(bestScheduleLabel);

        frame.add(dataPanel);
        frame.setVisible(true);
    }

    void displayWorking() {
        status.setText("Status: Working");
    }

    void displayDone() {
        status.setText("Status: Done");
    }

    void displayGeneration(int generation) {
        generationLabel.setText("Generation: " + generation);
    }

    void displayMaxFitness(double maxFitness) {
        maxFitLabel.setText(formatFitness("Max Fitness: ", maxFitness));
    }

    void displayAverageFitness(double averageFitness) {
        averageFitLabel.setText(formatFitness("Average Fitness: ", averageFitness));
    }

    void displayBestSchedule(Schedule bestSchedule) {
        //fix so it displays vertically
        String displayValue = "";

        for(int i = 0; i < bestSchedule.groups.length; i++) {
            if(i == bestSchedule.groups.length - 1) displayValue += bestSchedule.groups[i].name;
            else displayValue += bestSchedule.groups[i].name + "\n";
        }

        System.out.println(displayValue);

        bestScheduleLabel.setText(displayValue);
    }

    private String formatFitness(String header, double value) {
        long rounded = Math.round(value);
        return header + rounded + "%";
    }
}
