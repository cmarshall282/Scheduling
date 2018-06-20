package org.marshallbros.chris.scheduling;

import java.util.Scanner;

public class Schedule {
    Group[] groups = new Group[10];

    public Schedule() {
        readData();
    }

    //Move to a better location later. Don't want to read the file every time we make a new schedule
    private void readData() {
        Scanner input = new Scanner("input-file");
        int count = 0;

        while(input.hasNextLine()) {
            String name = input.nextLine();


            String temp = input.nextLine();

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

            groups[count] = new Group(name, members);
        }
    }
}
