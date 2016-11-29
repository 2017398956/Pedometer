package nfl.pedometer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import nfl.pedometerlibrary.StepsCountTool;

public class MainActivity extends AppCompatActivity {

    private String s ;
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
        System.out.println(s.equals("any string"));
//        initView();
//        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        timer.schedule(timmerTask, 0, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
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

}
