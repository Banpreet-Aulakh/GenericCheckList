package com.project.genericchecklist.UI;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.project.genericchecklist.R;
import com.project.genericchecklist.Utilities.DatabaseHelper;
import com.project.genericchecklist.Utilities.OnDialogCloseListener;
import com.project.genericchecklist.model.ListItem;

public class NewTaskFragment extends BottomSheetDialogFragment {
    public static final String TAG = "NewTaskFragment";

    private EditText editText;
    private Button buttonSave;
    private DatabaseHelper db;

    public static NewTaskFragment newInstance(){
        return new NewTaskFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_task, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText = view.findViewById(R.id.editText);
        buttonSave = view.findViewById(R.id.save_button);
        db = new DatabaseHelper(getActivity());
        boolean isUpdate = false;
        Bundle bundle = getArguments();
        if (bundle != null) {
            isUpdate = true;
            String task = bundle.getString("task");
            editText.setText(task);

            if (task.length() > 0) {
                buttonSave.setEnabled(false);
            }
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    buttonSave.setEnabled(false);
                    buttonSave.setBackgroundColor(Color.GRAY);
                }
                else {
                    buttonSave.setEnabled(true);
                    buttonSave.setBackgroundColor(getResources().getColor(R.color.purple_500));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        boolean finalIsUpdate = isUpdate;

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();

                if(finalIsUpdate) {
                    db.updateTask(bundle.getInt("id"), text);
                }
                else {
                    ListItem item = new ListItem();
                    item.setTitle(text);
                    item.setDone(false);
                    db.insertTask(item);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if (activity instanceof OnDialogCloseListener){
            ((OnDialogCloseListener)activity).onDialogClose(dialog);
        }
    }
}
