package com.example.kaigaisyusyoku4f.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kaigaisyusyoku4f.FreeListViewAdapter;
import com.example.kaigaisyusyoku4f.R;
import com.example.kaigaisyusyoku4f.VO.ListItemTest;

public class FreeBoard extends Fragment {

    private ListView listView;
    private FreeListViewAdapter freeListViewAdapter;


    public FreeBoard() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.free_board, container, false);

        //리스트뷰
        // Adapter 생성
        freeListViewAdapter = new FreeListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listView = (ListView) view.findViewById(R.id.listView1);
        listView.setAdapter(freeListViewAdapter);

        //아이쳄 추가
        freeListViewAdapter.addItem("안뇽", "20190303", 10, 4);
        freeListViewAdapter.addItem("죽고싶냐", "20190513", 8, 3);
        freeListViewAdapter.addItem("잡페어가즈아", "20190701", 7, 1);

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

        return view;
    }
}
