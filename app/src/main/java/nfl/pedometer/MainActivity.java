package nfl.pedometer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import nfl.pedometerlibrary.StepsCountTool;

public class MainActivity extends AppCompatActivity {

    private TextView tv_steps;
    private Timer timer;
    private TimerTask timmerTask;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                tv_steps.setText("今天的步数：" + StepsCountTool.getTodaySteps());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        Log.i("NFL", "onCreate");
    }

    private void initView() {
        tv_steps = (TextView) findViewById(R.id.tv_steps);
    }

    private void initData() {
        timer = new Timer();
        timmerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(100);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        timer.schedule(timmerTask, 0, 1000);
        Log.i("NFL", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != timer) {
            timer.cancel();
        }
        Log.i("NFL", "onPause");
    }

}
