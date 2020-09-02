package org.maktab.homework10_maktab37.model;

public class Task {
    private String mName;
    private String mState;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public Task(String name, String state) {
        mName = name;
        mState = state;
    }
}
