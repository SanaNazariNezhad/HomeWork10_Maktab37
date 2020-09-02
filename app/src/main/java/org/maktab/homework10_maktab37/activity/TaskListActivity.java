package org.maktab.homework10_maktab37.activity;

import androidx.fragment.app.Fragment;

import org.maktab.homework10_maktab37.fragment.TaskListFragment;

public class TaskListActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new TaskListFragment();
    }
}