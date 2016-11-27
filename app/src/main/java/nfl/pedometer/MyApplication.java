package nfl.pedometer;

import android.app.Application;

import nfl.pedometerlibrary.CrashHandler;
import nfl.pedometerlibrary.StepsCountTool;

/**
 * Created by niu on 2016/11/27.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        StepsCountTool.context = getApplicationContext() ;
    }
}
