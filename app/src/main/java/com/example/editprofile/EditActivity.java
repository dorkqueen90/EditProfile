package com.example.editprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.editprofile.MainActivity.PROFILE_KEY;

public class EditActivity extends AppCompatActivity {
    static String PROFILE_KEY = "PROFILE";
    String name;
    String email;
    String dep;
    String mood;
    String element;
    int moodValue;
    RadioGroup selected;
    EditText editName;
    EditText editEmail;
    SeekBar moodPercent;
    Student person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        selected = findViewById(R.id.buttonGroup2);
        editName = findViewById(R.id.editName2);
        editEmail = findViewById(R.id.editEmail2);
        moodPercent = findViewById(R.id.seekBar2);

        if(getIntent() != null && getIntent().getExtras() != null){
            person = (Student) getIntent().getExtras().getSerializable(PROFILE_KEY);

            name = person.name;
            email = person.email;
            dep = person.department;
            mood = person.mood;
            element = person.element;
            moodValue = person.moodValue;
            editName.setText(name);
            editEmail.setText(email);

            setInvisible();
            visibleElement(element);
        }
        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(element.equals("name")) {
                    editName = findViewById(R.id.editName2);
                    if (isNameFilled(editName)) {
                        name = editName.getText().toString();
                        person = new Student(name, email, dep, mood, moodValue, element);
                        Intent returnIntent = new Intent(EditActivity.this, DisplayActivity.class);
                        returnIntent.putExtra(PROFILE_KEY, person);
                        startActivity(returnIntent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error, please enter name",
                                Toast.LENGTH_LONG).show();
                    }
                }
                if(element.equals("email")) {
                    editEmail = findViewById(R.id.editEmail2);
                    if (isEmailValid(editEmail)) {
                        email = editEmail.getText().toString();
                        person = new Student(name, email, dep, mood, moodValue, element);
                        Intent returnIntent = new Intent(EditActivity.this, DisplayActivity.class);
                        returnIntent.putExtra(PROFILE_KEY, person);
                        startActivity(returnIntent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error, please enter email",
                                Toast.LENGTH_LONG).show();
                    }
                }
                if (element.equals("dep")) {
                    if (selected.getCheckedRadioButtonId() == R.id.sisButton2) {
                        dep = "SIS";
                    } else if (selected.getCheckedRadioButtonId() == R.id.csButton2) {
                        dep = "CS";
                    } else if (selected.getCheckedRadioButtonId() == R.id.bioButton2) {
                        dep = "BIO";
                    } else if (selected.getCheckedRadioButtonId() == R.id.othersButton2) {
                        dep = "Others";
                    }
                    person = new Student(name, email, dep, mood, moodValue, element);
                    Intent returnIntent = new Intent(EditActivity.this, DisplayActivity.class);
                    returnIntent.putExtra(PROFILE_KEY, person);
                    startActivity(returnIntent);
                }
                if(element.equals("mood")) {
                        moodValue = moodPercent.getProgress();
                        mood = moodValue + " % Positive";
                    person = new Student(name, email, dep, mood, moodValue, element);
                    Intent returnIntent = new Intent(EditActivity.this, DisplayActivity.class);
                    returnIntent.putExtra(PROFILE_KEY, person);
                    startActivity(returnIntent);
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
    private void visibleElement(String element){
        switch (element){
            case "name":
                editName.setVisibility(View.VISIBLE);
                break;
            case "email":
                editEmail.setVisibility(View.VISIBLE);
                break;
            case "dep":
                findViewById(R.id.depView2).setVisibility(View.VISIBLE);
                findViewById(R.id.buttonGroup2).setVisibility(View.VISIBLE);
                break;
            case "mood":
                findViewById(R.id.moodView2).setVisibility(View.VISIBLE);
                moodPercent.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setInvisible(){
        editName.setVisibility(View.INVISIBLE);
        editEmail.setVisibility(View.INVISIBLE);
        findViewById(R.id.depView2).setVisibility(View.INVISIBLE);
        findViewById(R.id.buttonGroup2).setVisibility(View.INVISIBLE);
        findViewById(R.id.moodView2).setVisibility(View.INVISIBLE);
        moodPercent.setVisibility(View.INVISIBLE);
    }
}
