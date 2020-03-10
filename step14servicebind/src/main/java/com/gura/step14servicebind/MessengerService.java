package com.gura.step14servicebind;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

/*
    액티비티와 서비스가 서로 데이터를 주고 받을수 있다.
    - Binder 을 객체 이용하기
 */
public class MessengerService extends Service {
    //맴버필드로 바인더 객체를 가지고 있는다.
    final IBinder mBinder=new LocalBinder();
    //MainActivity 의 참조값을 저장할 맴버필드
    MainActivity mActivity;

    //Activity 에서 연결요청이 오면 호출되는 메소드
    @Override
    public IBinder onBind(Intent intent) {
        //맴버필드의 IBinder 객체를 리턴해준다.
        return mBinder;
    }
    //연결이 해제 되었을때 호출되는 메소드
    @Override
    public boolean onUnbind(Intent intent) {
        //초기화 작업을 한다.
        mActivity=null;
        return super.onUnbind(intent);
    }

    //서비스 시작 요청이 왔을때 호출되는 메소드
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        handler.removeMessages(0);
        //핸들러에 메세지를 보내서 동작하게 만든다.
        handler.sendEmptyMessage(0);

        return super.onStartCommand(intent, flags, startId);
    }

    //Binder 클래스를 상속받아서 LocalBinder 클래스를 정의한다.
    public class LocalBinder extends Binder {
        //MessengerService 의 참조값을 리턴해주는 메소드
        public MessengerService getService(){
            return MessengerService.this;
        }
        //액티비티의 참조값을 전달받아서 맴버필드에 저장하는 메소드
        public void setActivity(MainActivity activity){
            mActivity = activity;
        }
    }
    //셈플 데이터
    String[] msgList={"안녕!","뭐해?","한잔하까?","한게임?",
            "으 추워!","졸립다. ㅋㅋ","언제 끈나","오케!","...",
            "전화해!"};
    int index=0;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(mActivity != null){
                mActivity.setMsg(msgList[index]);
            }
            index++;
            if(index==10){//없는 인덱스라면
                index=0;//다시 처음으로
            }
            handler.sendEmptyMessageDelayed(0, 5000);
        }
    };

    @Override
    public void onDestroy() {
        handler.removeMessages(0);
        super.onDestroy();
    }
}





