package com.example.kaigaisyusyoku4f;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.kaigaisyusyoku4f.fragment.MyPagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyPagerAdapter myPagerAdapter;
    private ViewPager viewPager;
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;
    private Toolbar toolbar;
    private NavigationView nav;
    private DrawerLayout drawerLayout;
    private FloatingActionButton writeBoardBtn;
    private FloatingActionButton searchBoardBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(myPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        tabLayout.setupWithViewPager(viewPager);

        fab();              //Float버튼 실행ll
        navigation();
        writeBoard();
        searchBoard();
    }

    private void searchBoard() {
        searchBoardBtn=(FloatingActionButton)findViewById(R.id.fab1);
        searchBoardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), SearchBoardActivity.class);
                startActivity(intent);
            }
        });
    }

    //글쓰기 버튼 클릭
    private void writeBoard() {
        writeBoardBtn=(FloatingActionButton)findViewById(R.id.fab2);
        writeBoardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), WriteBoardActivity.class);
                //해당 인텐트 실행
                startActivity(intent);
            }
        });
    }

    public void navigation() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        nav = (NavigationView) findViewById(R.id.navigationView);

        // 툴바 생성 및 세팅하는 부분
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.berger);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // 네비게이션 뷰 아이템 클릭시 이뤄지는 이벤트
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                //drawerLayout.closeDrawers();

                int id = item.getItemId();
                // 각 메뉴 클릭시 이뤄지는 이벤트
                switch (id){
                    case R.id.navigationItem1:
                        Toast.makeText(MainActivity.this,"메뉴1 잘됨", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.navigationItem2:
                        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
                        break;
                }

                return true;
            }
        });
    }

    // 햄버거 버튼 클릭 시 드로어 열리도록 하는 곳
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                anim();
                break;
            case R.id.fab1:
                anim();
                break;
            case R.id.fab2:
                anim();
                break;
        }
    }

    //FloatActionButton 메소드
    public void fab() {
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);

        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
    }

    public void anim() {

        if (isFabOpen) {
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
        } else {
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }
}
