package com.example.hm_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FindEmailActivity extends AppCompatActivity {

    EditText input_name, input_phoneNO,input_birthday;
    TextView error_phoneNO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findemail);

        init();
    }

    private void init(){
        input_name=findViewById(R.id.fe_input_name);
        input_phoneNO=findViewById(R.id.fe_input_phoneno);
        input_birthday=findViewById(R.id.fe_input_birthday);

    }
}