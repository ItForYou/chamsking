package kr.co.itforone.chamsking;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        FirebaseApp.initializeApp(this);//firebase 등록함
//        FirebaseMessaging.getInstance().subscribeToTopic("namo");
        //토큰 생성
        //Common.TOKEN= FirebaseInstanceId.getInstance().getToken();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
