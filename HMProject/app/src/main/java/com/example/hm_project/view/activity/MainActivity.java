package com.example.hm_project.view.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hm_project.R;
import com.example.hm_project.data.SystemManager;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;


public class MainActivity extends Activity {

    //이메일, 나잇대, 성별, 생일값 String 추가됨
    String strNickname, strProfile, strEmail;
    TextView TextView_get;
    //Btn
    Button calendarBtn, galleryBtn, mypageBtn, nearbyBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        mainBtnSetting();


    }
    private void init(){

        Button calendarBtn, galleryBtn, mypageBtn, nearbyBtn;

        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvNickname = findViewById(R.id.tvNickname);
        ImageView ivProfile = findViewById(R.id.ivProfile);
        Button btnLogout = findViewById(R.id.btnLogout);

        strNickname = SystemManager.NicName;
        strProfile = SystemManager.profilePhoto;
        strEmail = SystemManager.ID;

        tvNickname.setText(strNickname);
        Glide.with(this).load(strProfile).into(ivProfile);
        tvEmail.setText(strEmail);

        btnLogout.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "정상적으로 로그아웃되었습니다.", Toast.LENGTH_SHORT).show();

                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
            }
        });
    }
    private void mainBtnSetting(){
        calendarBtn=findViewById(R.id.calendarBtn);
        galleryBtn=findViewById(R.id.galleryBtn);
        mypageBtn=findViewById(R.id.mypageBtn);
        nearbyBtn=findViewById(R.id.nearbyBtn);

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                switch (view.getId()) {
                    case R.id.calendarBtn :
                        intent = new Intent(MainActivity.this,CalendarActivity.class);
                        startActivity(intent);
                        break ;
                    case R.id.galleryBtn :
                        intent = new Intent(MainActivity.this,GalleryActivity.class);
                        startActivity(intent);
                        break ;
                    case R.id.mypageBtn :
                        //Update
                        break ;
                    case R.id.nearbyBtn:
                        intent = new Intent(MainActivity.this,CalendarActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        } ;

        calendarBtn.setOnClickListener(onClickListener) ;
        galleryBtn.setOnClickListener(onClickListener) ;
        mypageBtn.setOnClickListener(onClickListener) ;
        nearbyBtn.setOnClickListener(onClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}