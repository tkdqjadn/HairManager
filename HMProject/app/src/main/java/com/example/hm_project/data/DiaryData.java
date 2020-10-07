package com.example.hm_project.data;


/**
 * 갤러리를 위한 DTO
 */
public class DiaryData {
    private int diaryPhoto;
    private String diaryTitle;
    private String diarydate;

    public DiaryData(int diaryPhoto, String diaryTitle, String diarydate){
        this.diaryPhoto = diaryPhoto;
        this.diaryTitle = diaryTitle;
        this.diarydate = diarydate;
    }

    public int getDiaryPhoto() {
        return diaryPhoto;
    }

    public void setDiaryPhoto(int diaryPhoto) {
        this.diaryPhoto = diaryPhoto;
    }

    public String getDiaryTitle() {
        return diaryTitle;
    }

    public void setDiaryTitle(String diaryTitle) {
        this.diaryTitle = diaryTitle;
    }

    public String getDiarydate() {
        return diarydate;
    }

    public void setDiarydate(String diarydate) {
        this.diarydate = diarydate;
    }
}