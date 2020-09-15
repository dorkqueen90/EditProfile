package com.example.editprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.editprofile.MainActivity.PROFILE_KEY;

public class DisplayActivity extends AppCompatActivity {

    TextView name;
    TextView email;
    TextView dep;
    TextView mood;
    Student person;
    int moodValue;
    String element;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        name = findViewById(R.id.nameDisplayed);
        email = findViewById(R.id.emailDisplay);
        dep = findViewById(R.id.depDisplay);
        mood = findViewById(R.id.moodDisplay);

        if(getIntent() != null && getIntent().getExtras() != null){
            person = (Student) getIntent().getExtras().getSerializable(PROFILE_KEY);

            name.setText(person.name);
            email.setText(person.email);
            dep.setText(person.department);
            mood.setText(person.mood);
            moodValue = person.moodValue;
            element = person.element;
        }
        findViewById(R.id.nameEditView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.setElement("name");
                Intent intentToEdit = new Intent(DisplayActivity.this, EditActivity.class);
                intentToEdit.putExtra(PROFILE_KEY, person);
                startActivity(intentToEdit);
            }
        });
        findViewById(R.id.emailEditView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.setElement("email");
                Intent intentToEdit = new Intent(DisplayActivity.this, EditActivity.class);
                intentToEdit.putExtra(PROFILE_KEY, person);
                startActivity(intentToEdit);
            }
        });
        findViewById(R.id.depEditView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.setElement("dep");
                Intent intentToEdit = new Intent(DisplayActivity.this, EditActivity.class);
                intentToEdit.putExtra(PROFILE_KEY, person);
                startActivity(intentToEdit);
            }
        });
        findViewById(R.id.moodEditView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.setElement("mood");
                Intent intentToEdit = new Intent(DisplayActivity.this, EditActivity.class);
                intentToEdit.putExtra(PROFILE_KEY, person);
                startActivity(intentToEdit);
            }
        });
    }
}
