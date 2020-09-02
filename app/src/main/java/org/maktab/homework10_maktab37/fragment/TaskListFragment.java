package org.maktab.homework10_maktab37.fragment;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.maktab.homework10_maktab37.R;
import org.maktab.homework10_maktab37.model.State;
import org.maktab.homework10_maktab37.model.Task;
import org.maktab.homework10_maktab37.repository.TaskRepository;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class TaskListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ImageButton mButtonInsert;
    private TaskRepository mTaskRepository;
    private List<Task> mTaskList;
    private String mName;
    private int mSize;

    public TaskListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mName = getActivity().getIntent().getStringExtra(TaskFragment.EXTRA_NAME);
        float floatSize = getActivity().getIntent().getFloatExtra(TaskFragment.EXTRA_NUMBER, 0);
        mSize = (int) floatSize;
        TaskRepository.setListSize(mSize);
        TaskRepository.setListName(mName);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        findViews(view);
        listener();
        initView();
        return view;
    }

    private void listener() {
        mButtonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSize+=1;
                Task task = new Task(mName,TaskRepository.getState().toString());
                mTaskRepository.addTaskList(task);
                createRecyclerView();
            }
        });
    }

    private void initView() {
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE){
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        }else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        createRecyclerView();


    }

    private void createRecyclerView() {
        mTaskRepository = TaskRepository.getInstance();
        mTaskList = mTaskRepository.getTaskList();

        TaskAdapter taskAdapter = new TaskAdapter(mTaskList);
        mRecyclerView.setAdapter(taskAdapter);
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView_task);
        mButtonInsert = view.findViewById(R.id.btn_insert);
    }

    public class TaskHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewName;
        private TextView mTextViewState;
        private RelativeLayout mRelativeLayout;
        private Task mTask;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.txtview_name);
            mTextViewState = itemView.findViewById(R.id.txtview_state);
            mRelativeLayout = itemView.findViewById(R.id.relativeLayout_row);
        }

        public void bindViews(Task task, int position) {
            mTask = task;
            mTextViewName.setText(task.getName());
            mTextViewState.setText(task.getState());
            if (position % 2 == 0){
                setColor(R.color.BackGround_evenPosition);
            }else {
                setColor(R.color.BackGround_oddPosition);
            }
        }

        private void setColor(int color) {
            mTextViewName.setBackgroundResource(color);
            mTextViewState.setBackgroundResource(color);
            mRelativeLayout.setBackgroundResource(color);
        }
    }

    public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {

        private List<Task> TaskList;

        public List<Task> getTaskList() {
            return TaskList;
        }

        public void setTaskList(List<Task> taskList) {
            TaskList = taskList;
        }

        public TaskAdapter(List<Task> taskList) {
            TaskList = taskList;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.task_row_list, parent, false);
            TaskHolder taskHolder = new TaskHolder(view);
            return taskHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            Task task = TaskList.get(position);
            holder.bindViews(task, position);
        }

        @Override
        public int getItemCount() {
            return TaskList.size();
        }
    }
}