package com.example.step19locationinfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity{
    //위치정보를 얻어낼수 있는 클라이언트 객체
    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //클라이언트의 참조값 얻어내서 필드에 저장하기
        client = LocationServices
                .getFusedLocationProviderClient(this);

        int permissionCheck=
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){ //권한이 없다면
            //권한을 얻어야하는 퍼미션 목록
            String[] permissions={Manifest.permission.ACCESS_FINE_LOCATION};
            //권한을 얻어내도록 유도한다.
            ActivityCompat.requestPermissions(this,
                    permissions,
                    0);
        }else{//권한이 있다면
            getLastLocation();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0:
                //퍼미션을 Allow 했을경우
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                   getLastLocation();
                }else{// Allow 하지 않았을 경우
                    Toast.makeText(this, "퍼미션을 체크해 주세요",
                            Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    //가장 최근의 위치 정보 얻어내는 메소드
    public void getLastLocation(){
        //클라이언트 객체를 이용해서 얻어낸다
        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            //성공 했을때 호출되는 메소드
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    //위도 얻어내기
                    double latitude=location.getLatitude();
                    //경도 얻어내기
                    double longitude=location.getLongitude();
                    //TextView 에 출력하기
                    TextView textView=findViewById(R.id.textView);
                    String info="위도:"+latitude+" 경도:"+longitude;
                    textView.setText(info);
                }
            }
        });
    }
}
