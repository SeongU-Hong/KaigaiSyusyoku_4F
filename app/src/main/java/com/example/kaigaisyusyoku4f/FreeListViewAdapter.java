package com.example.kaigaisyusyoku4f;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kaigaisyusyoku4f.models.Board;

import java.util.ArrayList;
import java.util.List;

public class FreeListViewAdapter extends BaseAdapter {

    public  ArrayList<Board> freeList;
    TextView freeListTitle;
    TextView freeListDate;
    TextView freeListHit;
    TextView freeListComment;


    public FreeListViewAdapter() {
        freeList = new ArrayList<>();
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return freeList.size();
    }
    public void clear(){
        freeList.clear();
    }
    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.free_list_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        freeListTitle = (TextView) convertView.findViewById(R.id.freeListTitle);
        freeListDate = (TextView) convertView.findViewById(R.id.freeListDate);
        freeListHit = (TextView) convertView.findViewById(R.id.freeListHit);
        freeListComment = (TextView) convertView.findViewById(R.id.freeListComment);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Board listItemTest = freeList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        freeListTitle.setText(listItemTest.getTitle());
        freeListDate.setText(listItemTest.getDateTime().toString());
        freeListHit.setText("조회수:" + String.valueOf(listItemTest.getCount()));
        freeListComment.setText(String.valueOf(listItemTest.getReplyCount()));

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return freeList.get(position);
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String title, String date, int hitCount, int commentCount) {
        Board item = new Board();

        item.setTitle(title);
        item.setDateTime(date);
        item.setCount(hitCount);
        item.setReplyCount(commentCount);

        freeList.add(item);
    }
}
