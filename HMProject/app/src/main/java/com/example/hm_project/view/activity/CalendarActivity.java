package com.example.hm_project.view.activity;

import android.content.Intent;
import android.os.Bundle;

import android.graphics.Color;
import android.os.AsyncTask;

import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hm_project.util.EventDecorator;
import com.example.hm_project.util.OneDayDecorator;
import com.example.hm_project.R;
import com.example.hm_project.util.SaturdayDecorator;
import com.example.hm_project.util.SundayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
public class CalendarActivity extends AppCompatActivity {

    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    MaterialCalendarView materialCalendarView;

    //Btn
    Button calendarBtn, galleryBtn, mypageBtn, nearbyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        init();
        //Main 버튼 셋팅
        mainBtnSetting();

    }
    private  void init(){
        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendarView);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2017, 0, 1)) // 달력의 시작
                .setMaximumDate(CalendarDay.from(2030, 11, 31)) // 달력의 끝
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                oneDayDecorator);

        String[] result = {"2020,09,18","2020,09,19","2020,09,20","2020,09,21"};

        new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();

                Log.i("Year test", Year + "");
                Log.i("Month test", Month + "");
                Log.i("Day test", Day + "");

                String shot_Day = Year + "," + Month + "," + Day;

                Log.i("shot_Day test", shot_Day + "");
                materialCalendarView.clearSelection();

                Toast.makeText(getApplicationContext(), shot_Day , Toast.LENGTH_SHORT).show();
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
                        //Update
                        break ;
                    case R.id.galleryBtn :
                        intent = new Intent(CalendarActivity.this,GalleryActivity.class);
                        startActivity(intent);
                        break ;
                    case R.id.mypageBtn :
                        intent = new Intent(CalendarActivity.this,MainActivity.class);
                        startActivity(intent);
                        break ;
                    case R.id.nearbyBtn:
                        intent = new Intent(CalendarActivity.this,CalendarActivity.class);
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


    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] Time_Result;

        ApiSimulator(String[] Time_Result){
            this.Time_Result = Time_Result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월 년,일은 그대로*/
            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
            for(int i = 0 ; i < Time_Result.length ; i ++){
                CalendarDay day = CalendarDay.from(calendar);
                String[] time = Time_Result[i].split(",");
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int dayy = Integer.parseInt(time[2]);
                Log.i("Day test", dayy + "몇이냐");
                dates.add(day);
                calendar.set(year,month-1,dayy);
            }
            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);
            if (isFinishing()) {
                return;
            }
            materialCalendarView.addDecorator(new EventDecorator(Color.RED, calendarDays,CalendarActivity.this));
        }
    }
}
