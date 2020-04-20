package com.zhoubrian.mathtrivia;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class FlashActivity extends AppCompatActivity {

    TextView myTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        myTimer=(TextView)findViewById(R.id.myTimer_id);

        new CountDownTimer(10000, 1000) { //40000 milli seconds is total time, 1000 milli seconds is time interval

            public void onTick(long millisUntilFinished) {
                myTimer.setText(""+(millisUntilFinished/1000));
            }
            public void onFinish() {
                Intent go_To_MainActivity=new Intent(FlashActivity.this,MainActivity.class);
                startActivity(go_To_MainActivity);
                finish();
            }

        }.start();

    }//end of onCreate..

}//end of FlashActivity..
