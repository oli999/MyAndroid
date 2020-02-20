package com.gura.drawpanel;

import java.io.Serializable;

public class Point implements Serializable { //객체를 파일에 저장, 읽어들이기 하기 위해
    public int x;
    public int y;
    public boolean isStartPoint;
}
