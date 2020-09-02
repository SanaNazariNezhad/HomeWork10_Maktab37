package org.maktab.homework10_maktab37.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.maktab.homework10_maktab37.R;
import org.maktab.homework10_maktab37.activity.TaskListActivity;

public class TaskFragment extends Fragment {

    public static final String EXTRA_NAME = "org.maktab.homework10_maktab37.fragment.extra_name";
    public static final String EXTRA_NUMBER = "org.maktab.homework10_maktab37.fragment.extra_number";
    private Button mBtnCreate;
    private TextInputEditText mTextName;
    private TextInputEditText mTextNumber;
    private TextInputLayout mNameForm;
    private TextInputLayout mNumberForm;

    public TaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        findViews(view);
        listeners();
        return view;
    }

    private void listeners() {
        mBtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNameForm.setErrorEnabled(false);
                mNumberForm.setErrorEnabled(false);
                Intent intent = new Intent(getActivity(), TaskListActivity.class);
                if (validateInput()) {
                    intent.putExtra(EXTRA_NAME,mTextName.getText().toString());
                    float number = Float.parseFloat(mTextNumber.getText().toString());
                    intent.putExtra(EXTRA_NUMBER,number);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateInput() {
        if (mTextName.getText().toString().trim().isEmpty() && mTextNumber.getText().toString().trim().isEmpty()) {
            mNameForm.setErrorEnabled(true);
            mNameForm.setError("Field cannot be empty!");
            mNumberForm.setErrorEnabled(true);
            mNumberForm.setError("Field cannot be empty!");
            return false;
        }else if (mTextName.getText().toString().trim().isEmpty()){
            mNameForm.setErrorEnabled(true);
            mNameForm.setError("Field cannot be empty!");
            return false;
        }else if (mTextNumber.getText().toString().trim().isEmpty()){
            mNumberForm.setErrorEnabled(true);
            mNumberForm.setError("Field cannot be empty!");
            return false;
        }
        mNameForm.setErrorEnabled(false);
        mNumberForm.setErrorEnabled(false);
        return true;
    }

    private void findViews(View view) {
        mBtnCreate = view.findViewById(R.id.btn_create);
        mTextName = view.findViewById(R.id.name);
        mTextNumber = view.findViewById(R.id.number);
        mNameForm = view.findViewById(R.id.name_form);
        mNumberForm = view.findViewById(R.id.number_form);
    }
}