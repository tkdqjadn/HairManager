package com.example.hm_project;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    Calendar myCalendar = Calendar.getInstance();
    EditText input_email, input_password, input_again_password, input_phoneNO, input_birthday;
    TextView error_email, error_password, error_again_apssword, error_phoneNO, error_birthday;
    String user_email,user_password,user_name,user_phoneNO,user_birthday,user_sex;
    RelativeLayout RelativeLayout_signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        setInput_birthday();
    }
    private void init(){
        input_email=findViewById(R.id.input_email);
        input_password=findViewById(R.id.input_password);
        input_again_password=findViewById(R.id.input_again_password);
        input_phoneNO=findViewById(R.id.input_phoneNO);
        error_email=findViewById(R.id.error_email);
        error_password=findViewById(R.id.error_password);
        error_again_apssword=findViewById(R.id.error_again_password);
        error_phoneNO=findViewById(R.id.error_phoneNO);

        //EditText에서 이메일을 입력받을 때 이메일형식대로 입력하지 않으면 빨간색 테두리로 알려준다.
        input_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()){
                    error_email.setText("이메일 형식으로 입력해주세요.");    // 경고 메세지
                    input_email.setBackgroundResource(R.drawable.red_edittext);  // 적색 테두리 적용
                }
                else{
                    error_email.setText("");         //에러 메세지 제거
                    input_email.setBackgroundResource(R.drawable.white_edittext);  //테투리 흰색으로 변경
                }
            }// afterTextChanged()..
        });

        //EditText에서 패스워드를 입력받을 때 정해진 형식대로 입력하지 않으면 빨간색 테두리로 알려준다.
        input_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$",input_password.getText().toString()))
                {
                    error_password.setText("8~20자 알파벳 대·소문자, 숫자, 특수문자를 사용하세요.");    // 경고 메세지
                    input_password.setBackgroundResource(R.drawable.red_edittext);  // 적색 테두리 적용
                }
                else{
                    error_password.setText("");         //에러 메세지 제거
                    input_password.setBackgroundResource(R.drawable.white_edittext);  //테투리 흰색으로 변경
                }
            }// afterTextChanged()..
        });

        //EditText에서 패스워드를 재입력받을 때 위에 입력된 패스워드와 일치하지 않으면 빨간색 테두리로 알려준다.
        input_again_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(input_again_password.getText().toString().equals(input_password.getText().toString())){
                    error_again_apssword.setText("");         //에러 메세지 제거
                    input_again_password.setBackgroundResource(R.drawable.white_edittext);  //테두리 흰색으로 변경
                }
                else{
                    error_again_apssword.setText("비밀번호가 일치하지 않습니다.");    // 경고 메세지
                    input_again_password.setBackgroundResource(R.drawable.red_edittext);  // 적색 테두리 적용
                }
            }// afterTextChanged()..
        });

        //EditText에서 핸드폰번호를 입력받을 때 정해진 형식대로 입력하지 않으면 빨간색 테두리로 알려준다.
        input_phoneNO.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$",input_phoneNO.getText().toString())){
                    error_phoneNO.setText("-을 제외하고 입력해주세요.");    // 경고 메세지
                    input_phoneNO.setBackgroundResource(R.drawable.red_edittext);  // 적색 테두리 적용
                }
                else{
                    error_phoneNO.setText("");         //에러 메세지 제거
                    input_phoneNO.setBackgroundResource(R.drawable.white_edittext);  //테투리 흰색으로 변경
                }
            }// afterTextChanged()..
        });
    }

    private void setInput_birthday(){

        final DatePickerDialog.OnDateSetListener birthdayPicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                view.setSpinnersShown(true);
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy/MM/dd";    // 출력형식   2020/09/29
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

                EditText et_date = (EditText) findViewById(R.id.input_birthday);
                et_date.setText(sdf.format(myCalendar.getTime()));
            }
        };

        input_birthday=findViewById(R.id.input_birthday);
        input_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog =  new DatePickerDialog(SignUpActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth, birthdayPicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                datePickerDialog.show();
            }
        });
    }
}
