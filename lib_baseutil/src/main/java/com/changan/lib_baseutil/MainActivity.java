package com.changan.lib_baseutil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.text_view);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.shake);
        textView.setAnimation(animation);
        GreenDaoUtil.getInstance().init(getApplicationContext());
        TestBean testBean = new TestBean();
        testBean.setText("test1");
        GreenDaoUtil.getInstance().insertData(testBean);
        List<TestBean>datas = GreenDaoUtil.getInstance().getAll();
        for (TestBean bean:datas){
            Log.e(TAG,"getbean --"+bean.getId()+bean.getText());
        }

//        DaoSession daoSession = ( getApplication()).getDaoSession();
//        noteDao = daoSession.getNoteDao();
    }
}
