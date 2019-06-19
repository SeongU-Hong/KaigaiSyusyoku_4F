package com.example.kaigaisyusyoku4f.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kaigaisyusyoku4f.InfoListViewAdapter;
import com.example.kaigaisyusyoku4f.R;
import com.example.kaigaisyusyoku4f.VO.ListItemTest;

public class InfoBoard extends Fragment {

    private InfoListViewAdapter infoListViewAdapter;
    private ListView listView;
    private SwipeRefreshLayout swipe;

    public InfoBoard() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_board, container, false);

        //리스트뷰
        // Adapter 생성
        infoListViewAdapter = new InfoListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listView = (ListView) view.findViewById(R.id.listView2);
        listView.setAdapter(infoListViewAdapter);

        //아이쳄 추가
        infoListViewAdapter.addItem("정보", "20190303", 10, 2);
        infoListViewAdapter.addItem("좋은정보", "20190513", 8, 0);
        infoListViewAdapter.addItem("좋지않은정보", "20190701", 7, 1);

        //리스트뷰 클릭이벤트
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListItemTest item = (ListItemTest) parent.getItemAtPosition(position);

                String title = item.getTitle();
                String date = item.getDate();
                int hit = item.getHit();
                int comment = item.getComment();

                //여기에 코드 작성
                // TODO : use item data.
            }
        });

        //새로고침
        swipe=(SwipeRefreshLayout)view.findViewById(R.id.swipeRefresh2);
        //색변경
        swipe.setColorSchemeResources(R.color.color8);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //코드 입력

                //새로고침 완료 후 아이콘 제거
                swipe.setRefreshing(false);
            }
        });

        return view;
    }

}
