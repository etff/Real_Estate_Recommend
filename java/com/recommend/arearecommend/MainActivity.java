package com.recommend.arearecommend;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.Random;

public class MainActivity extends Activity {
    LinearLayout back;
    ImageView title;
    int[] img = {R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4};
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        back = (LinearLayout) findViewById(R.id.background);

        Random ram = new Random();
        int num = ram.nextInt(img.length);

        back.setBackgroundResource(img[num]);

         title = (ImageView) findViewById (R.id.title);
        title.setBackgroundResource(R.drawable.name);

        TextView textView = (TextView) findViewById(R.id.mainText);
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "Typo_luckypangL.ttf"));

        FloatingActionButton insert = (FloatingActionButton) findViewById(R.id.button);
        insert.setVisibility(View.VISIBLE);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OptionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "뒤로가기 버튼을 2번 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
