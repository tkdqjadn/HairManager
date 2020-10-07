package com.example.hm_project.view.activity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hm_project.data.DiaryData;
import com.example.hm_project.view.adapter.DiarySearchAdapter;
import com.example.hm_project.R;

import java.util.ArrayList;


public class GalleryActivity extends AppCompatActivity {

    private ArrayList<DiaryData> diaryList;  // 데이터를 넣은 리스트변수
    private GridView gridView;          // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private DiarySearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<DiaryData> originalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        editSearch = (EditText) findViewById(R.id.editSearch);
        gridView = (GridView) findViewById(R.id.gridView);

        // 리스트를 생성한다.
        diaryList = new ArrayList<DiaryData>();

        // 검색에 사용할 샘플 데이터을 미리 저장한다.
        this.InitializeSampleData();

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        originalList = new ArrayList<DiaryData>();
        originalList.addAll(diaryList);

        // 리스트에 연동될 아답터를 생성한다.
        adapter = new DiarySearchAdapter(diaryList, this);

        // 리스트뷰에 아답터를 연결한다.
        gridView.setAdapter(adapter);

        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String searchdate = editSearch.getText().toString();
                search(searchdate);
            }
        });


    }

    // 검색을 수행하는 메소드
    public void search(String searchdate) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        diaryList.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (searchdate.length() == 0) {
            diaryList.addAll(originalList);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < originalList.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(searchdate)가 포함되어 있으면 true를 반환한다.
                if (originalList.get(i).getDiarydate().contains(searchdate))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    diaryList.add(originalList.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }

    // 검색에 사용될 데이터를 리스트에 추가한다.
    public void InitializeSampleData()
    {
        diaryList = new ArrayList<DiaryData>();

        diaryList.add(new DiaryData(R.drawable.movieposter1, "머리일기","20200909"));
        diaryList.add(new DiaryData(R.drawable.movieposter2, "기분좋음","20200809"));
        diaryList.add(new DiaryData(R.drawable.movieposter3, "날씨 맑음","20190204"));
    }
}
