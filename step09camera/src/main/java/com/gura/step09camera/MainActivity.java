package com.gura.step09camera;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
                        implements View.OnClickListener,
                        Util.RequestListener{
    //필드
    private ImageView imageView;
    private String absolutePath; //사진이 저장된 절대 경로
    public final String UPLOAD_URL="http://192.168.0.5:8888/spring05/android/image/upload.do";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //버튼의 참조값 얻어와서 리스너 등록 하기
        Button takePicBtn=findViewById(R.id.takePicBtn);
        takePicBtn.setOnClickListener(this);
        //ImageView 의 참조값
        imageView=findViewById(R.id.imageView);

        Button uploadBtn=findViewById(R.id.uploadBtn);
        uploadBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.takePicBtn:
                int permissionCheck=
                        ContextCompat.checkSelfPermission(this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if(permissionCheck != PackageManager.PERMISSION_GRANTED){ //권한이 없다면
                    //권한을 얻어야하는 퍼미션 목록
                    String[] permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    //권한을 얻어내도록 유도한다.
                    ActivityCompat.requestPermissions(this,
                            permissions,
                            0);
                }else{//권한이 있다면
                    requestTakePic();
                }
                break;
            case R.id.uploadBtn:
                Map<String, String> map=new HashMap<>();
                map.put("writer", "김구라");
                Util.sendMultipartRequest(0, UPLOAD_URL, map, absolutePath, this);
                break;
        }
    }
    //퍼미션을 체크 했을때ㅐ 호출되는 메소드
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0: // 0 번 요청 코드인 경우
                //퍼미션을 Allow 했을경우
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    requestTakePic();
                }else{// Allow 하지 않았을 경우
                    Toast.makeText(this, "퍼미션을 체크해 주세요",
                            Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    //사진찍겠다는 요청하기
    public void requestTakePic(){
        //사진을 촬영하겠다는 인텐트 객체 작성하기
        Intent intent=new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        //사진을 촬영할수 있는 기기인지 확인해서
        if(intent.resolveActivity(getPackageManager())!=null){
            //이미지를 저장할 파일 객체를 얻어온다.
            File photoFile=null;
            try{
                //파일명 지정
                String timeStamp=
                        new SimpleDateFormat("yyyyMMdd_HHmmss")
                                .format(new Date());
                String path=getExternalFilesDir(null).getAbsolutePath();
                photoFile=new File(path+"/"+timeStamp+".jpg");
                //절대 경로를 맴버필드에 저장한다.
                absolutePath=photoFile.getAbsolutePath();
            }catch(Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            if(photoFile!=null){
                //사진을 저장할 파일의 Uri 객체를 얻어온다.
                Uri uri= FileProvider.getUriForFile(this, "com.gura.step09camera.fileprovider", photoFile);

                Log.i("uri", uri.getPath());
                //인텐트에 Uri 객체를 담고
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                //결과를 받아올수 있는 액티비티 시작 시키기
                startActivityForResult(intent, 0);
            }//if()
        }else{
            Toast.makeText(this,"카메라 App 이 필요합니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 0:
                    //여기에 실행순서가 들어오면 absolutePath 경로에 파일에는 이미지 정보가 들어있다.
                    Log.i("test", "ok!");
                    //이미지파일을 읽어들여서 Bitmap 으로 만들고
                    //Bitmap image= BitmapFactory.decodeFile(absolutePath);
                    //Bitmap 을 ImageView 에 출력하기
                    //imageView.setImageBitmap(image);

                    //이미지뷰에 딱 맞도록 출력
                    fitToImageView(imageView, absolutePath);

                    break;
            }
        }
    }


    //Bitmap 이미지 회전시켜서 리턴하는 메소드
    public static Bitmap rotateImage(Bitmap source, float angle) {
        Bitmap retVal;

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        retVal = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);

        return retVal;
    }
    //이미지 뷰의 크기에 맞게 이미지를 출력하는 메소드
    public static void fitToImageView(ImageView imageView, String absolutePath){
        //출력할 이미지 뷰의 크기를 얻어온다.
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(absolutePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeFile(absolutePath, bmOptions);
        /* 사진이 세로로 촬영했을때 회전하지 않도록 */
        try {
            ExifInterface ei = new ExifInterface(absolutePath);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            switch(orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    bitmap = rotateImage(bitmap, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    bitmap = rotateImage(bitmap, 180);
                    break;
                // etc.
            }
        }catch(IOException ie){
            Log.e("####", ie.getMessage());
        }

        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        new AlertDialog.Builder(this)
                .setMessage("업로드 되었습니다.")
                .setNeutralButton("확인", null)
                .create()
                .show();
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {
        new AlertDialog.Builder(this)
                .setMessage("업로드 실패!")
                .setNeutralButton("확인", null)
                .create()
                .show();
    }
}






