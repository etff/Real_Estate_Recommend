package com.recommend.arearecommend;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DBHelper extends SQLiteOpenHelper {
    public Context dbcontext;
    public String namestr[]= { "초등학교", "중학교", "고등학교", "병원", "키즈카페", "한의원", "종합병원",
                                "도서관", "주차장", "편의점", "치과", "보건소", "동물병원", "동물약국",
                                "장애인복지관", "장애인단기보호시설", "사고다발지", "산모사회서비스제공기관" };
    public String tablestr[] = { "elementary_school", "middle_school", "high_school", "hospital", "kidcafe", "korean_medical", "general_hospital",
                                    "library", "parking", "convini", "dentist", "clinic", "animal_hospital", "animal_pharm",
                                    "disable", "disable_dan", "sago", "sanmo" };

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        dbcontext = context;
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        db.execSQL("CREATE TABLE main (_id INTEGER PRIMARY KEY AUTOINCREMENT, address TEXT, animal_hospital INTEGER, animal_pharm INTEGER," +
                "clinic INTEGER, convini INTEGER, disable INTEGER, disable_dan INTEGER, elementary_school INTEGER," +
                "general_hospital INTEGER, high_school INTEGER, hospital INTEGER, kidcafe INTEGER, korean_medical INTEGER," +
                "library INTEGER, middle_school INTEGER, parking INTEGER, sago INTEGER, sanmo INTEGER, dentist INTEGER);");
        try {
            importMain(db);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE main;");
        onCreate(db);
    }

    public void delete(String address) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.rawQuery("DELETE FROM main WHERE address='" + address + "';", null);
        db.close();
    }

    public String[] test() {
        SQLiteDatabase db = getWritableDatabase();
        String temp = "";
        String result[] = {"", "", ""};
        Cursor cursor = db.rawQuery("SELECT ADDRESS FROM main LIMIT 3;", null);
        while (cursor.moveToNext()) {
            temp += cursor.getString(0)
                    + "\n";
        }
        result = temp.split("\n");
        db.close();
        return result;
    }

    public String[] getTop3Result(String first, int weight1, String second, int weight2, String third, int weight3, String fourth, int weight4, String fifth, int weight5) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String tmp = "";
        String result[] = {"", "", ""};
        String str[] = { first, second, third, fourth, fifth };
        String input[] = {"", "", "", "", ""};
        float wg1 = weight1;
        float wg2 = weight2;
        float wg3 = weight3;
        float wg4 = weight4;
        float wg5 = weight5;

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 18; j++){
                if(str[i].matches(namestr[j])){
                    input[i] = tablestr[j];
                }
            }
        }

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT r.address FROM (select address, ("
                                            + input[0] + " * " + (wg1/100+1) + " + "
                                            + input[1] + " * " + (wg2/100+1) + " + "
                                            + input[2] + " * " + (wg3/100+1) + " + "
                                            + input[3] + " * " + (wg4/100+1) + " + "
                                            + input[4] + " * " + (wg5/100+1) + " ) as result " + "FROM main ORDER BY result DESC LIMIT 3) r;", null);
        while (cursor.moveToNext()) {
            tmp += cursor.getString(0)
                    + "\n";
        }
        result = tmp.split("\n");
        db.close();
        return result;
    }

    public void importMain(SQLiteDatabase db) throws IOException {
        String mCSVfile = "main.csv";
        AssetManager manager = dbcontext.getAssets();
        InputStream inStream = null;
        try {
            inStream = manager.open(mCSVfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line = "";

        String tableName ="main";
        String columns = "address, animal_hospital, animal_pharm, clinic, convini, disable,disable_dan, elementary_school, general_hospital, high_school, hospital, kidcafe, korean_medical, library, middle_school, parking, sago, sanmo, dentist";
        String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
        String str2 = ");";

        db.beginTransaction();
        while ((line = buffer.readLine()) != null) {
            StringBuilder sb = new StringBuilder(str1);
            String[] str = line.split(",");
            sb.append("'" + str[0] + "',");
            sb.append(str[1] + ",");
            sb.append(str[2] + ",");
            sb.append(str[3] + ",");
            sb.append(str[4] + ",");
            sb.append(str[5] + ",");
            sb.append(str[6] + ",");
            sb.append(str[7] + ",");
            sb.append(str[8] + ",");
            sb.append(str[9] + ",");
            sb.append(str[10] + ",");
            sb.append(str[11] + ",");
            sb.append(str[12] + ",");
            sb.append(str[13] + ",");
            sb.append(str[14] + ",");
            sb.append(str[15] + ",");
            sb.append(str[16] + ",");
            sb.append(str[17] + ",");
            sb.append(str[18]);
            sb.append(str2);
            db.execSQL(sb.toString());
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }
}
