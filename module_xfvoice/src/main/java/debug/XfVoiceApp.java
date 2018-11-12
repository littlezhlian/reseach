package debug;

import com.iflytek.cloud.SpeechUtility;
import com.zhlian.lib_common.base.BaseApplication;
import com.zhlian.module_xfvoice.R;

/**
 * Created by 64553 on 2018-11-07.
 * Power By ChangnAutoMobile RCLink Team
 */

public class XfVoiceApp extends BaseApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        SpeechUtility.createUtility(XfVoiceApp.this, "appid=" + getString(R.string.app_id));

    }
}
