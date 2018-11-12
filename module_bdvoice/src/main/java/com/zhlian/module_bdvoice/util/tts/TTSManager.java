package com.zhlian.module_bdvoice.util.tts;

import android.content.Context;

import com.baidu.tts.client.SpeechSynthesizer;
import com.zhlian.module_bdvoice.util.tts.control.InitConfig;

/**
 * Created by 64553 on 2018-11-08.
 * Power By ChangnAutoMobile RCLink Team
 */

final public class TTSManager {
    private static final String TAG = TTSManager.class.getSimpleName();
    private SpeechSynthesizer mSpeechSynthesizer;
    private Context context;
    private static boolean isInitied = false;

    private TTSManager(){
    }

    private void initTTSEngine(InitConfig initConfig){

    }

    public void init(Context context){
        this.context = context;
    }

    public void changePlayConfig(InitConfig config){

    }

    public void play(String text){

    }

    public void release(){

    }
}
