package com.recommend.arearecommend;

import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private String initAdd;
    private String top1;
    private String top2;
    private String top3;
    private String current_address = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Intent intent = getIntent();
        if(intent!=null) {
            String temp[] = intent.getStringArrayExtra("1");
            top1 = temp[0];
            top2 = temp[1];
            top3 = temp[2];

        }



        //top1 이동
        FloatingActionButton btn1 = (FloatingActionButton) findViewById(R.id.place1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = top1;
                current_address = top1;
                LatLng newPlace = toAddress(address);

                goMap(newPlace, address);
            }
        });

        //top2
        FloatingActionButton btn2 = (FloatingActionButton) findViewById(R.id.place2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = top2;
                current_address = top2;
                LatLng newPlace = toAddress(address);
                goMap(newPlace, address);
            }
        });

        //top3
        FloatingActionButton btn3 = (FloatingActionButton) findViewById(R.id.place3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = top3;
                current_address = top3;
                LatLng newPlace = toAddress(address);
                goMap(newPlace, address);

            }
        });

        //리스트 플로팅 버튼
        FloatingActionMenu list_btn = (FloatingActionMenu)findViewById(R.id.fab_list);
        //        list_btn.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //            }
        //        });

        //floating button 자세히 보기
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current_address == null){
                    Toast.makeText(getApplicationContext(),"마지막으로 조회한 지역이 없습니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(MapsActivity.this, PlaceDetail.class);
                i.putExtra("address", current_address);
                startActivity(i);
            }
        });

        FloatingActionButton rtn_btn = (FloatingActionButton) findViewById(R.id.back_btn);
        rtn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }





    //주소->좌표
    public LatLng toAddress(String address) {
        List<Address> addr = null;
        LatLng total = new LatLng(0, 0);
        try {
            Geocoder geo = new Geocoder(this, Locale.KOREAN);
            addr = geo.getFromLocationName(address, 1);
            total = new LatLng(addr.get(0).getLatitude(), addr.get(0).getLongitude());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }



    //지도 이동 매소드
    public void goMap(LatLng place, String address) {
        // 맵 객체 생성

        //맵 위치를 이동하기

        mMap.moveCamera(CameraUpdateFactory.newLatLng(place));
//        CameraUpdate update = CameraUpdateFactory.newLatLng(place);
        //위도,경도
//        mMap.moveCamera(update);

        //보기 좋게 확대 zoom 하기
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        mMap.animateCamera(zoom);

        // 마커 표시 하기 : 위치지정, 풍선말 설정
        MarkerOptions markerOptions = new MarkerOptions()
                // 마커 위치
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.house2))
                .position(place)
                .title(address); // 말풍선 주 내용


        // 마커를 추가하고 말풍선 표시한 것을 보여주도록 호출
        mMap.addMarker(markerOptions).showInfoWindow();

        // 마커 클릭했을 때 처리 : 리스너 달기
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Toast.makeText(MapsActivity.this, "아래 아이콘으로 자세한 설명을 볼수 있습니다",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    //지도 초기설정
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setMapToolbarEnabled(false);
        LatLng seoul = new LatLng(37.2410347, 127.177954);

        mMap.addMarker(new MarkerOptions().position(seoul).title(initAdd).position(seoul)).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(seoul));

        CameraUpdate zoom = CameraUpdateFactory.zoomTo(10);
        mMap.animateCamera(zoom);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "메인 화면으로 돌아갑니다.", Toast.LENGTH_SHORT).show();
        Intent intent =new Intent (getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}