package com.recommend.arearecommend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.io.IOException;

public class OptionActivity extends Activity {

    private SeekBar seek1; // SeekBar 선언
    private SeekBar seek2;
    private SeekBar seek3;
    private SeekBar seek4;
    private SeekBar seek5;

    private TextView seektext1;
    private TextView seektext2;
    private TextView seektext3;
    private TextView seektext4;
    private TextView seektext5;

    private int mSeekBarVal1 = 0; // SeekBar에서 설정한 값을 받을 변수 선언
    private int mSeekBarVal2 = 0;
    private int mSeekBarVal3 = 0;
    private int mSeekBarVal4 = 0;
    private int mSeekBarVal5 = 0;

    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private Spinner spinner5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_get);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "main", null, 2);

        seek1 = (SeekBar)findViewById(R.id.first_seek);
        seek2 = (SeekBar)findViewById(R.id.second_seek);
        seek3 = (SeekBar)findViewById(R.id.third_seek);
        seek4 = (SeekBar)findViewById(R.id.fourth_seek);
        seek5 = (SeekBar)findViewById(R.id.fifth_seek);

        seektext1 =(TextView)findViewById(R.id.first_text);
        seektext2 =(TextView)findViewById(R.id.second_text);
        seektext3 =(TextView)findViewById(R.id.third_text);
        seektext4 =(TextView)findViewById(R.id.fourth_text);
        seektext5 =(TextView)findViewById(R.id.fifth_text);

        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //thumb가 멈출 때 호출
                mSeekBarVal1 = seek1.getProgress();
                seektext1.setText(String.valueOf(mSeekBarVal1));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //thumb가 움직이기 시작할 때 호출
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //thumb 가 움직일때 마다 호출
            }
        });
        seek1.incrementProgressBy(1);

        seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //thumb가 멈출 때 호출
                mSeekBarVal2 = seek2.getProgress();
                seektext2.setText(String.valueOf(mSeekBarVal2));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //thumb가 움직이기 시작할 때 호출
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //thumb 가 움직일때 마다 호출
            }
        });
        seek2.incrementProgressBy(1);

        seek3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //thumb가 멈출 때 호출
                mSeekBarVal3 = seek3.getProgress();
                seektext3.setText(String.valueOf(mSeekBarVal3));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //thumb가 움직이기 시작할 때 호출
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //thumb 가 움직일때 마다 호출
            }
        });
        seek3.incrementProgressBy(1);

        seek4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //thumb가 멈출 때 호출
                mSeekBarVal4 = seek4.getProgress();
                seektext4.setText(String.valueOf(mSeekBarVal4));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //thumb가 움직이기 시작할 때 호출
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //thumb 가 움직일때 마다 호출
            }
        });
        seek4.incrementProgressBy(1);

        seek5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //thumb가 멈출 때 호출
                mSeekBarVal5 = seek5.getProgress();
                seektext5.setText(String.valueOf(mSeekBarVal5));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //thumb가 움직이기 시작할 때 호출
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //thumb 가 움직일때 마다 호출
            }
        });
        seek5.incrementProgressBy(1);

        spinner1 = (Spinner)findViewById(R.id.first_spinner);
        spinner2 = (Spinner)findViewById(R.id.second_spinner);
        spinner3 = (Spinner)findViewById(R.id.third_spinner);
        spinner4 = (Spinner)findViewById(R.id.fourth_spinner);
        spinner5 = (Spinner)findViewById(R.id.fifth_spinner);

        FloatingActionButton cancel = (FloatingActionButton) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"메인 화면으로 돌아갑니다.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        FloatingActionButton result = (FloatingActionButton) findViewById(R.id.result);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner args[] = { spinner1, spinner2, spinner3, spinner4, spinner5 };
                String output[]= {"", "", ""};
                for(int i=4; i>=0; i--){
                    for(int j=4; j>=0; j--){
                        if(i != j){
                            if( args[i].getSelectedItem().toString() == args[j].getSelectedItem().toString()){
                                Toast.makeText(getApplicationContext(), "중복되는 시설이 존재합니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }
                }

                output = dbHelper.getTop3Result(spinner1.getSelectedItem().toString(), Integer.parseInt(seektext1.getText().toString()),
                        spinner2.getSelectedItem().toString(), Integer.parseInt(seektext2.getText().toString()),
                        spinner3.getSelectedItem().toString(), Integer.parseInt(seektext3.getText().toString()),
                        spinner4.getSelectedItem().toString(), Integer.parseInt(seektext4.getText().toString()),
                        spinner5.getSelectedItem().toString(), Integer.parseInt(seektext5.getText().toString()));
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("1", output);
                startActivity(intent);
                finish();
            }
        });
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "Typo_luckypangL.ttf"));

    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "메인 화면으로 돌아갑니다.", Toast.LENGTH_SHORT).show();
        Intent intent =new Intent (getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}
