package org.marshallbros.chris.scheduling;

class Group {
    String name;
    String type;
    int groupSize;
    String[] members;

    public Group(String name, String type, int groupSize, String[] members) {
        this.name = name;
        this.type = type;
        this.groupSize = groupSize;
        this.members = members;
    }

    double typeConflict(Group nextGroup) {
        //return a half so it is less important that member scheduling
        if(type.equals(nextGroup.type)) return 0.5;
        return 0.0;
    }

    int conflicts(Group nextGroup) {
        int totalConflicts = 0;

        for(int i = 0; i < members.length; i++) {
            for(int j = 0; j < nextGroup.members.length; j++) {
                if(members[i].equals(nextGroup.members[j])) totalConflicts++;
            }
        }

        return totalConflicts;
    }
}
