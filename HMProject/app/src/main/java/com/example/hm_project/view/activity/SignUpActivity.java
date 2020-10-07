package com.example.hm_project.view.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hm_project.data.InterfaceManager;
import com.example.hm_project.R;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    Calendar myCalendar = Calendar.getInstance();
    Button overlap_email, overlap_phoneNO;
    EditText input_email, input_password, input_again_password,input_name, input_phoneNO, input_birthday;
    TextView error_email, error_password, error_again_apssword, error_phoneNO;
    String user_email,user_password,user_name,user_phoneNO,user_birthday;
    boolean again_password = false,user_sex = true,check_overlap_email=false,check_overlap_phoneNO=false;
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
        input_name=findViewById(R.id.input_name);
        input_phoneNO=findViewById(R.id.input_phoneNO);
        error_email=findViewById(R.id.error_email);
        error_password=findViewById(R.id.error_password);
        error_again_apssword=findViewById(R.id.error_again_password);
        error_phoneNO=findViewById(R.id.error_phoneNO);

        overlap_email=findViewById(R.id.overlap_email);
        overlap_phoneNO=findViewById(R.id.overlap_phoneNO);


        //EditText에서 이메일을 입력받을 때 이메일형식대로 입력하지 않으면 빨간색 테두리로 알려준다.
        //이메일형식으로 입력해야지만 중복확인 버튼을 클릭할 수 있도록 한다.
        input_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()){
                    error_email.setText("");         //에러 메세지 제거
                    input_email.setBackgroundResource(R.drawable.white_edittext);  //테투리 흰색으로 변경
                    overlap_email.setClickable(true);
                } else{
                    error_email.setText("이메일 형식으로 입력해주세요.");    // 경고 메세지
                    input_email.setBackgroundResource(R.drawable.red_edittext);  // 적색 테두리 적용
                    overlap_email.setClickable(false);
                }
            }// afterTextChanged()..
        });

        //이메일 중복확인 버튼
        overlap_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_email=findViewById(R.id.input_email);
                try {
                    URL url = new URL("http://218.234.77.97:8080/Ajpractice/exmaple/OverlapEmail.jsp");
                    String result;

                    InterfaceManager task = new InterfaceManager(url);
                    result = task.execute(input_email.getText().toString(),"","","","","").get();
                    if(result.equals("LO_0002")) {
                        Toast.makeText(getApplicationContext(), "이미 사용중인 이메일입니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                        Log.i("이메일 중복", result);
                    }
                    else {
                        overlap_email.setText("확인완료");
                        overlap_email.setClickable(false);
                        input_email.setEnabled(false);
                        Toast.makeText(getApplicationContext(), "이메일 확인 완료", Toast.LENGTH_SHORT).show();
                        user_email=input_email.getText().toString();
                        check_overlap_email=true;
                        Log.i("이메일 중복 아님", result);
                    }
                } catch (Exception e) {

                }
            }
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
                    user_password = null;
                }
                else{
                    error_password.setText("");         //에러 메세지 제거
                    input_password.setBackgroundResource(R.drawable.white_edittext);  //테투리 흰색으로 변경
                    user_password=input_password.getText().toString();
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
                    again_password = true;
                }
                else{
                    error_again_apssword.setText("비밀번호가 일치하지 않습니다.");    // 경고 메세지
                    input_again_password.setBackgroundResource(R.drawable.red_edittext);  // 적색 테두리 적용
                    again_password = false;
                }
            }// afterTextChanged()..
        });

        //사용자로부터 입력받은 이름 user_name에 저장
        user_name=input_name.getText().toString();

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
                    error_phoneNO.setText("핸드폰번호 형식에 맞게 입력해주세요.");    // 경고 메세지
                    input_phoneNO.setBackgroundResource(R.drawable.red_edittext);  // 적색 테두리 적용
                    overlap_phoneNO.setClickable(false);
                }
                else{
                    error_phoneNO.setText("");         //에러 메세지 제거
                    input_phoneNO.setBackgroundResource(R.drawable.white_edittext);  //테투리 흰색으로 변경
                    overlap_phoneNO.setClickable(true);
                }
            }// afterTextChanged()..
        });

        final Button overlap_phoneNO = (Button)findViewById(R.id.overlap_phoneNO);
        overlap_phoneNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_phoneNO=findViewById(R.id.input_phoneNO);
                try {
                    URL url = new URL("http://218.234.77.97:8080/Ajpractice/exmaple/OverlapPhoneNO.jsp");
                    String result;

                    InterfaceManager task = new InterfaceManager(url);
                    result = task.execute("","","",input_phoneNO.getText().toString(),"","").get();
                    if(result.equals("LO_0005")) {
                        Toast.makeText(getApplicationContext(), "이미 사용중인 핸드폰번호입니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                        Log.i("핸드폰번호 중복", result);
                    }
                    else {
                        overlap_phoneNO.setText("확인완료");
                        overlap_phoneNO.setClickable(false);
                        input_phoneNO.setEnabled(false);
                        Toast.makeText(getApplicationContext(), "핸드폰번호 확인 완료", Toast.LENGTH_SHORT).show();
                        user_phoneNO=input_phoneNO.getText().toString();
                        check_overlap_phoneNO=true;
                        Log.i("핸드폰번호 중복 아님", result);
                    }
                } catch (Exception e) {

                }
            }
        });


        // 사용자로부터 성별 입력받기
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.genderGroup);
        RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(i == R.id.genderMan) {
                    user_sex=true;
                }
                else if(i == R.id.genderWoman){
                    user_sex=false;
                }
            }
        };
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);
    }

    private void setInput_birthday(){

        final DatePickerDialog.OnDateSetListener birthdayPicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                view.setSpinnersShown(true);
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd";    // 출력형식   2020-09-29
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

                //유저를 위해 화면에 유저가 선택한 날짜 표시
                EditText et_date = (EditText) findViewById(R.id.input_birthday);
                et_date.setText(sdf.format(myCalendar.getTime()));
                // 서버에 전송할 값 저장
                user_birthday=sdf.format(myCalendar.getTime());
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

    //입력받은 값 서버에 저장하기 위한 if문 or switch문ㄴ
    private void checkedSignUp(){

    }
}
