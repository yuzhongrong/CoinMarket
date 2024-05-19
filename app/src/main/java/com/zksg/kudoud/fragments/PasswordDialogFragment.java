package com.zksg.kudoud.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.zksg.kudoud.R;

public class PasswordDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AppTheme_Dialog);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_pwd, null);

        final EditText passwordEditText = dialogView.findViewById(R.id.password_edit_text);
        builder.setView(dialogView)
                .setTitle("Enter Password")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Handle the OK button click
                        String password = passwordEditText.getText().toString();
                        // TODO: Handle the password
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        PasswordDialogFragment.this.getDialog().cancel();
                    }
                });

        AlertDialog dialog = builder.create();


        // Show the dialog and adjust the window properties
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Window window = ((AlertDialog) dialog).getWindow();
                if (window != null) {
                    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

                }
            }
        });

        return dialog;
    }
}
