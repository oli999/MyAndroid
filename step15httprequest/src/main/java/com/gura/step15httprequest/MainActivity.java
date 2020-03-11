package com.gura.step15httprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
                                implements View.OnClickListener{
    //필요한 필드 정의하기
    private EditText inputUrl, console;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //res/layout/activity_main.xml 문서를 전개해서 화면 구성하기
        setContentView(R.layout.activity_main);
        // id 가 requestBtn 인 Button 객체의 참조값을 얻어와서
        // requestBtn 이라는 지역변수에 담기
        Button requestBtn=findViewById(R.id.requestBtn);
        //버튼에 리스너 등록하기
        requestBtn.setOnClickListener(this);

        inputUrl=findViewById(R.id.inputUrl);
        console=findViewById(R.id.console);
    }

    @Override
    public void onClick(View view) {
        //입력한 url 주소를 읽어온다.
        String urlAddr=inputUrl.getText().toString();
        //GET 방식 http 요청을 해서 결과 문자열을 읽어와서
        GetRequestTask task=new GetRequestTask();
        task.execute(urlAddr);
        //console(EditText) 에 출력하기

    }

    public class GetRequestTask extends AsyncTask<String, Void, String>{
        //백그라운드에서 할 작업(다른 스레드)
        @Override
        protected String doInBackground(String... strings) {
            //요청할 url 주소를 읽어온다.
            String urlAddr=strings[0];
            //서버가 http 요청에 대해서 응답하는 문자열을 누적할 객체
            StringBuilder builder=new StringBuilder();
            HttpURLConnection conn=null;
            InputStreamReader isr=null;
            BufferedReader br=null;
            try{
                URL url=new URL(urlAddr);
                //HttpURLConnection 객체의 참조값 얻어오기
                conn=(HttpURLConnection)url.openConnection();
                if(conn!=null) {//연결이 되었다면
                    conn.setConnectTimeout(20000); //응답을 기다리는 최대 대기 시간
                    conn.setRequestMethod("GET");//Default 설정
                    conn.setUseCaches(false);//케쉬 사용 여부
                    //응답 코드를 읽어온다.
                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {//정상 응답이라면...
                        //서버가 출력하는 문자열을 읽어오기 위한 객체
                        isr = new InputStreamReader(conn.getInputStream());
                        br = new BufferedReader(isr);
                        //반복문 돌면서 읽어오기
                        while (true) {
                            //한줄씩 읽어들인다.
                            String line = br.readLine();
                            //더이상 읽어올 문자열이 없으면 반복문 탈출
                            if (line == null) break;
                            //읽어온 문자열 누적 시키기
                            builder.append(line);
                        }
                    }
                }
            }catch (Exception e){
                //예외 메세지
                Log.e("에러", e.getMessage());
                final String msg=e.getMessage();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        console.setText(msg);
                    }
                });
            }finally {
                try{
                    if(isr!=null)isr.close();
                    if(br!=null)br.close();
                    if(conn!=null)conn.disconnect();
                }catch(Exception e){}
            }
            //결과 데이터를 (문자열) 리턴해 준다.
            return builder.toString();
        }
        //작업 결과를 UI 에 출력할 메소드 (UI 스레드)
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //읽어낸 문자열을  EditText 에 출력하기
            console.setText(s);
        }
    }
}







