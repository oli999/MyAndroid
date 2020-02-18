package com.gura.step06fragment2;

import java.io.Serializable;

/*
        Intent 객체에 putExtra() 해서 담을수 있도록
        Serializable 인터페이스를 구현 시킨다.
 */
public class CountryDto implements Serializable {
    //필드
    private int resId;
    private String name;
    private String content;

    //생성자
    public CountryDto(){}

    public CountryDto(int resId, String name, String content) {
        this.resId = resId;
        this.name = name;
        this.content = content;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
