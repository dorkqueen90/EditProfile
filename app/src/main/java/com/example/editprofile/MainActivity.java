package com.example.editprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static String PROFILE_KEY = "PROFILE";
    String name;
    String email;
    String dep;
    String mood;
    String element = "";
    int moodValue;
    RadioGroup selected;
    EditText editName;
    EditText editEmail;
    SeekBar moodPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selected = findViewById(R.id.buttonGroup);
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        moodPercent = findViewById(R.id.seekBar);

        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNameFilled(editName)) {
                    name = editName.getText().toString();
                } else {
                    Toast.makeText(getApplicationContext(), "Error, please enter name",
                            Toast.LENGTH_LONG).show();
                }
                if (isEmailValid(editEmail)) {
                    email = editEmail.getText().toString();
                } else {
                    Toast.makeText(getApplicationContext(), "Error, please enter email",
                            Toast.LENGTH_LONG).show();
                }
                if (isNameFilled(editName) && isEmailValid(editEmail)) {
                    if (selected.getCheckedRadioButtonId() == R.id.sisButton) {
                        dep = "SIS";
                    } else if (selected.getCheckedRadioButtonId() == R.id.csButton) {
                        dep = "CS";
                    } else if (selected.getCheckedRadioButtonId() == R.id.bioButton) {
                        dep = "BIO";
                    } else if (selected.getCheckedRadioButtonId() == R.id.othersButton) {
                        dep = "Others";
                    }
                    moodValue = moodPercent.getProgress();
                    mood = moodValue + " % Positive";
                    Student person = new Student(name, email, dep, mood, moodValue, element);

                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                    intent.putExtra(PROFILE_KEY, person);
                    startActivity(intent);
                }
            }
            private boolean isNameFilled(EditText editName) {
                return editName.getText().toString().length() > 0;
            }
            private boolean isEmailValid(EditText editEmail) {
                return editEmail.getText().toString().length() > 0 && editEmail.getText().toString().contains("@") &&
                        editEmail.getText().toString().contains(".");
            }
        });
    }
}
