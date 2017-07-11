package com.example.android.githubrestcalls;

/**
 * Created by Android on 7/11/2017.
 */

public class Projects {
    String Name;
    String ID;
    String Commits;
    String Updated;

    public Projects(String name, String ID, String commits, String updated) {
        Name = name;
        this.ID = ID;
        Commits = commits;
        Updated = updated;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCommits() {
        return Commits;
    }

    public void setCommits(String commits) {
        Commits = commits;
    }

    public String getUpdated() {
        return Updated;
    }

    public void setUpdated(String updated) {
        Updated = updated;
    }
}
