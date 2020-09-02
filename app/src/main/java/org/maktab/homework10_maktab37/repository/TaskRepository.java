package org.maktab.homework10_maktab37.repository;

import org.maktab.homework10_maktab37.R;
import org.maktab.homework10_maktab37.model.State;
import org.maktab.homework10_maktab37.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskRepository {

    public static int LIST_SIZE;
    public static String LIST_NAME;
    private List<Task> mTaskList;

    public static void setListSize (int size){
        LIST_SIZE = size;
    }

    public static void setListName(String listName) {
        LIST_NAME = listName;
    }

    public static TaskRepository sInstance;

    private TaskRepository() {
        mTaskList = new ArrayList<>();
        for (int i = 0; i <LIST_SIZE ; i++) {
            State state = getState();
            Task task = new Task(LIST_NAME,state.toString());

            mTaskList.add(task);
        }

    }

    public static State getState() {
        Random mRandom = new Random();
        int random = mRandom.nextInt(3);
        State state;
        if (random == 0 )
            state = State.Todo;
        else if (random == 1)
            state = State.Doing;
        else
            state = State.Done;
        return state;
    }

    public List<Task> getTaskList() {
        return mTaskList;
    }

    public void addTaskList(Task task) {
        mTaskList.add(task);
    }

    public static TaskRepository getInstance() {
        if (sInstance == null)
            sInstance = new TaskRepository();

        return sInstance;
    }
}
