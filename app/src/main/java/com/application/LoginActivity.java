package com.application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends ParentNavigationActivity {

    private static boolean isDeletePressed;
    TextView loginInfoText;
    public static EditText phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        onCreateOption(savedInstanceState);
        loginInfoText = findViewById(R.id.loginInfoText);
        phoneEditText = findViewById(R.id.editTextPhone);
//    }
//
//    protected static boolean checkCorrect(String phoneString) {
        phoneEditText.addTextChangedListener(new TextWatcher() {
            //we need to know if the user is erasing or inputing some new character
            private boolean backspacingFlag = false;
            //we need to block the :afterTextChanges method to be called again after we just replaced the EditText text
            private boolean editedFlag = false;
            //we need to mark the cursor position and restore it after the edition
            private int cursorComplement;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //we store the cursor local relative to the end of the string in the EditText before the edition
                cursorComplement = s.length() - phoneEditText.getSelectionStart();
                //we check if the user ir inputing or erasing a character
                if (count > after) {
                    backspacingFlag = true;
                } else {
                    backspacingFlag = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                //what matters are the phone digits beneath the mask, so we always work with a raw string with only digits
                String phone = string.replaceAll("[^\\d]", "");

                //if the text was just edited, :afterTextChanged is called another time... so we need to verify the flag of edition
                //if the flag is false, this is a original user-typed entry. so we go on and do some magic
                if (!editedFlag) {

                    //we start verifying the worst case, many characters mask need to be added
                    //example: 999999999 <- 6+ digits already typed
                    // masked: (999) 999-999
                    if (phone.length() >= 9 && !backspacingFlag) {
                        //we will edit. next call on this textWatcher will be ignored
                        editedFlag = true;
                        //here is the core. we substring the raw digits and add the mask as convenient
                        String ans = "+7(" + phone.substring(1, 4) + ") " + phone.substring(4, 7) + "-" + phone.substring(7, 9) + "-" + phone.substring(9);
                        phoneEditText.setText(ans);
                        //we deliver the cursor to its original position relative to the end of the string
                        phoneEditText.setSelection(phoneEditText.getText().length() - cursorComplement);

                        //we end at the most simple case, when just one character mask is needed
                        //example: 99999 <- 3+ digits already typed
                        // masked: (999) 99
                    } else if (phone.length() >= 4 && !backspacingFlag) {
                        editedFlag = true;
                        String ans = "+7(" + phone.substring(1, 4) + ") " + phone.substring(4);
                        phoneEditText.setText(ans);
                        phoneEditText.setSelection(phoneEditText.getText().length() - cursorComplement);
                    }
                    // We just edited the field, ignoring this cicle of the watcher and getting ready for the next
                } else {
                    editedFlag = false;
                }
            }
        });
        phoneEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                isDeletePressed = (i == KeyEvent.KEYCODE_DEL);
                return false;
            }
        });
      //  return true;
    }
//        if (phoneString.length() != 12) {
//            return false;
//        }
//        if (phoneString.toCharArray()[0] != '+' || phoneString.toCharArray()[1] != '7' || phoneString.toCharArray()[2] != '9') {
//            return false;
//        }
//        return true;
    //}

    public void click(View view) {
      //  if (checkCorrect(phoneEditText.getText().toString())) {
            MainActivity.authFlag = true;
            Intent intent = new Intent(this, AddEventActivity.class);
            startActivity(intent);
    //    } else {
//            final int DIALOG_EXIT = 1;
//            AlertDialog.Builder adb = new AlertDialog.Builder(this);
//            adb.setTitle(R.string.error);
//            adb.setMessage("Введите ваш номер телефона в формате: +7 (9ХХ) ХХХ-ХХ-ХХ");
//            adb.setNeutralButton(R.string.ok, null);
//            adb.create();
//            showDialog(DIALOG_EXIT);
//            adb.show();
//        }
    }


}