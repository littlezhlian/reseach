package com.zhlian.module_bdvoice;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.zhlian.module_bdvoice.R;
import com.alibaba.android.arouter.facade.annotation.Route;

import com.zhlian.module_bdvoice.util.recog.MyRecognizer;
import com.zhlian.module_bdvoice.util.recog.RecogResult;
import com.zhlian.module_bdvoice.util.recog.listener.IRecogListener;
import com.zhlian.module_bdvoice.util.recog.listener.RecogEventAdapter;

import java.util.HashMap;
import java.util.Map;


@Route(path = "/module_bdvoice/MainActivity")
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    MyRecognizer recognizer;
    private TextView mTextView;
    RecogEventAdapter recogEventAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.zhlian.module_bdvoice.R.layout.activity_main);
        requestPermissions();
        recogEventAdapter = new RecogEventAdapter(new IRecogListener() {
            @Override
            public void onAsrReady() {
                Log.d(TAG,"onAsrReady");
            }

            @Override
            public void onAsrBegin() {
                Log.d(TAG,"onAsrBegin");

            }

            @Override
            public void onAsrEnd() {
                Log.d(TAG,"onAsrEnd----");

            }

            @Override
            public void onAsrPartialResult(String[] results, RecogResult recogResult) {
                Log.d(TAG,"onAsrPartialResult"+recogResult.getDesc()+results.toString());

            }

            @Override
            public void onAsrOnlineNluResult(String nluResult) {
                Log.d(TAG,"onAsrOnlineNluResult"+nluResult);

            }

            @Override
            public void onAsrFinalResult(String[] results, RecogResult recogResult) {
                Log.d(TAG,"onAsrFinalResult"+recogResult.getOrigalJson());
                mTextView.setText(results[0]
                );
            }

            @Override
            public void onAsrFinish(RecogResult recogResult) {
                Log.d(TAG,"onAsrFinish"+recogResult.getOrigalJson());

            }

            @Override
            public void onAsrFinishError(int errorCode, int subErrorCode, String descMessage, RecogResult recogResult) {
                Log.d(TAG,"onAsrFinishError");

            }

            @Override
            public void onAsrLongFinish() {
                Log.d(TAG,"onAsrLongFinish");

            }

            @Override
            public void onAsrVolume(int volumePercent, int volume) {
                Log.d(TAG,"onAsrVolume");

            }

            @Override
            public void onAsrAudio(byte[] data, int offset, int length) {
                Log.d(TAG,"onAsrAudio");

            }

            @Override
            public void onAsrExit() {
                Log.d(TAG,"onAsrExit");

            }

            @Override
            public void onOfflineLoaded() {
                Log.d(TAG,"onOfflineLoaded");

            }

            @Override
            public void onOfflineUnLoaded() {
                Log.d(TAG,"onOfflineUnLoaded");

            }
        });
        mTextView = findViewById(com.zhlian.module_bdvoice.R.id.recog_state);
        recognizer = new MyRecognizer(this,recogEventAdapter);
        findViewById(com.zhlian.module_bdvoice.R.id.speak_btn).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        recognizer.start(getInitParam());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        recognizer.stop();
                        break;
                    default:
                        break;
                }

                return false;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recognizer.release();
    }

    public Map<String,Object> getInitParam(){
        Map<String,Object>map = new HashMap<>();
        return map;
    }
    private void requestPermissions(){
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                int permission = ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if(permission!= PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,new String[]
                            {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.LOCATION_HARDWARE,Manifest.permission.READ_PHONE_STATE,
                                    Manifest.permission.WRITE_SETTINGS,Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_CONTACTS},0x0010);
                }

                if(permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,new String[] {
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},0x0010);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
