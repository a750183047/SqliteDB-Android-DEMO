package yan.com.taobaodb.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import yan.com.taobaodb.activity.util.ReturnBitmap;

/**
 * 拍照活动
 * Created by yan on 2015/10/30.
 */
public class TakePhoto extends Activity {

    public static final int TAKE_PHOTO = 1;
    public static final int CORP_PHOTO = 2;
    private Uri imageUri;
    private String MerchantsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //创建file对象，用来储存图片
        File outputImage = new File(Environment.getExternalStorageDirectory(),"output_photo.jpg");
        try{
            if(outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        imageUri = Uri.fromFile(outputImage);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent, TAKE_PHOTO); //启动相机
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:
                if(resultCode == RESULT_OK){
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri,"image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                    startActivityForResult(intent,CORP_PHOTO);
                }
                break;
            case CORP_PHOTO:
                Log.d("cc", " " + resultCode);
                if(resultCode == RESULT_OK){
                    Log.d("wc"," "+resultCode);
                    try{
                        ReturnBitmap.bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        finish();
                        //   getContentResolver().openInputStream(imageUri).reset();
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
