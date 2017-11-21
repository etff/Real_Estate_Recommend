package com.recommend.arearecommend;


import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlaceDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("address");

        ListView listview ;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.back_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        // 첫 번째 아이템 추가.
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_home_black_36dp),
//                "Box", "Account Box Black 36dp") ;
//        // 두 번째 아이템 추가.
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_pets_black_36dp),
//                "Circle", "Account Circle Black 36dp") ;
//        // 세 번째 아이템 추가.
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_pets_black_36dp),
//                "Ind", "Assignment Ind Black 36dp") ;






        String a = name;
        String title[] = {"주소","동물 병원","동물 약국","의원","CCTV","편의점","장애인복지관현황",
                "장애인단기보호시설","초등학교","종합병원","고등학교","병원","키즈카페","한의원","공공도서관",
                "중학교","주차시설","사고다발지","산모사회서비스제공기관현황","치과"};
        String b = "";
        //TextView tv = (TextView) findViewById (R.id.textview);
        //tv.setMovementMethod(new ScrollingMovementMethod());
        String[][] indat = new String[1251][20];

        try {
            InputStreamReader is = new InputStreamReader(getResources().openRawResource(R.raw.main_count));
            BufferedReader reader = new BufferedReader(is);
            String line = "";
            int row = 0 ,i;
            while ((line = reader.readLine()) != null) {
                // -1 옵션은 마지막 "," 이후 빈 공백도 읽기 위한 옵션
                String[] token = line.split(",");
                for(i=0;i<20;i++)  {
                    indat[row][i] = token[i];
                }
                row++;
            }
            is.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
//        for(int i = 0; i < 1251; i++){
//            if (a.equals(indat[i][0])) {
//                for (int j = 0; j < 20; j++) {
//                    if(indat[i][j].equals("0") == false)
//                        b += (title[j] + " : " + indat[i][j] + "\n");
//                }
//                tv.setText(b);
//            }
//        }

        for(int i = 0; i < 1251; i++){
            if (a.equals(indat[i][0])) {
                //for (int j = 0; j < 20; j++) {
                //  if (indat[i][j].equals("0") == false)

                // 1 번째 아이템 추가. 주소
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_home_black_36dp),
                        title[0], indat[i][0]);
                // 2 번째 아이템 추가. 동물병원
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_pets_black_36dp),
                        title[1], indat[i][1]);
                // 3 번째 아이템 추가. 동물약국
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_local_pharmacy_black_36dp),
                        title[2], indat[i][2]);
                // 4 번째 아이템 추가. 의원
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_local_hospital_black_36dp),
                        title[3], indat[i][3]);
                // 5 번째 아이템 추가. cctv
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_voice_chat_black_36dp),
                        title[4], indat[i][4]);
                // 6 번째 아이템 추가. 편의점
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_store_mall_directory_black_36dp),
                        title[5], indat[i][5]);
                // 7 번째 아이템 추가. 장애인복지관현황
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.disable),
                        title[6], indat[i][6]);
                // 8 번째 아이템 추가. 장애인단기보호시설
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.disable),
                        title[7], indat[i][7]);
                // 9 번째 아이템 추가. 초등학교
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_school_black_36dp),
                        title[8], indat[i][8]);
                // 10 번째 아이템 추가. 종합병원
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_local_hospital_black_36dp),
                        title[9], indat[i][9]);
                // 11 번째 아이템 추가. 고등학교
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_school_black_36dp),
                        title[10], indat[i][10]);
                // 12 번째 아이템 추가. 병원
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_local_hospital_black_36dp),
                        title[11], indat[i][11]);
                // 13 번째 아이템 추가. 키즈카페
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_sentiment_satisfied_black_36dp),
                        title[12], indat[i][12]);
                // 14 번째 아이템 추가. 한의원
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_local_hospital_black_36dp),
                        title[13], indat[i][13]);
                // 15 번째 아이템 추가. 도서관
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_local_library_black_36dp),
                        title[14], indat[i][14]);
                // 16 번째 아이템 추가. 중학교
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_school_black_36dp),
                        title[15], indat[i][15]);
                // 17 번째 아이템 추가. 주차시설
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_local_parking_black_36dp),
                        title[16], indat[i][16]);
                // 18 번째 아이템 추가. 사고다발지
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_priority_high_black_36dp),
                        title[17], indat[i][17]);
                // 19 번째 아이템 추가. 산모사회서비스제공기관현황
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_child_friendly_black_36dp),
                        title[18], indat[i][18]);
                // 20 번째 아이템 추가. 치과
                adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_local_hospital_black_36dp),
                        title[19], indat[i][19]);


                //  }

            }
        }
    }

}
