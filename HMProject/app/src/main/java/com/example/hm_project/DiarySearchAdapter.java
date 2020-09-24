package com.example.hm_project;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 갤러리 그리드뷰에 넣기 위한 일기어뎁터
 */

public class DiarySearchAdapter extends BaseAdapter {

    private Context context;
    ArrayList<DiaryData> diaryList;
    private LayoutInflater inflate;
    private ViewHolder viewHolder;

    public DiarySearchAdapter(ArrayList<DiaryData> diaryList, Context context){
        this.diaryList = diaryList;
        this.context = context;
        this.inflate = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return diaryList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public DiaryData getItem(int position) {
        return diaryList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = inflate.inflate(R.layout.row_gridview,null);

            viewHolder = new ViewHolder();
            viewHolder.diaryPhoto = (ImageView)convertView.findViewById(R.id.diaryPhoto);
            viewHolder.diaryTitle = (TextView)convertView.findViewById(R.id.diaryTitle);


            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // 리스트에 있는 데이터를 리스트뷰 셀에 뿌린다.
        viewHolder.diaryPhoto.setImageResource(diaryList.get(position).getDiaryPhoto());
        viewHolder.diaryTitle.setText(diaryList.get(position).getDiaryTitle());

        return convertView;
        }

class ViewHolder{
    public ImageView diaryPhoto;
    public TextView diaryTitle;
}
}