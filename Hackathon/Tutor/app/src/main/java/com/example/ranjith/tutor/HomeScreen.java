package com.example.ranjith.tutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {
    Button tutor,student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

         tutor=(Button)findViewById(R.id.tutor);
         student=(Button)findViewById(R.id.student);


    }


    public  void tutors(View v)
    {

        Intent i=new Intent(this,tutorlogin.class);
        startActivity(i);
    }

    public  void students(View v)
    {
        Intent i=new Intent(this,StudentLogin.class);
        startActivity(i);





    }


}
