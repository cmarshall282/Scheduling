package org.marshallbros.chris.scheduling;

class Group {
    String name;
    int groupSize;
    String[] members;

    public Group(String name, int groupSize, String[] members) {
        this.name = name;
        this.groupSize = groupSize;
        this.members = members;
    }

    int conflicts(Group nextGroup) {
        int totalConflicts = 0;

        for(int i = 0; i < members.length; i++) {
            for(int j = 0; j < nextGroup.members.length; j++) {
                if(members[i].equals(members[j])) totalConflicts++;
            }
        }

        return totalConflicts;
    }
}
