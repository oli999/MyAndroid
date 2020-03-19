package com.gura.step09camera;

public class ImageDto {
    private int num;
    private String writer;
    private String imageUrl;
    private String regdate;

    public ImageDto(){}

    public ImageDto(int num, String writer, String imageUrl, String regdate) {
        this.num = num;
        this.writer = writer;
        this.imageUrl = imageUrl;
        this.regdate = regdate;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }
}
