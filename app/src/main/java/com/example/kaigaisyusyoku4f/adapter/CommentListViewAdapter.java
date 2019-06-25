package com.example.kaigaisyusyoku4f.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kaigaisyusyoku4f.R;
import com.example.kaigaisyusyoku4f.models.Board;
import com.example.kaigaisyusyoku4f.models.Reply;

import java.util.ArrayList;

public class CommentListViewAdapter extends BaseAdapter {

    public static ArrayList<Reply> commnetList;
    private TextView commentIdentity;
    private TextView commentDate;
    private TextView commentContent;


    public CommentListViewAdapter() {
        commnetList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return commnetList.size();
    }

    public void clear(){
        commnetList.clear();
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.free_board_comment_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        commentIdentity = (TextView) convertView.findViewById(R.id.commentIdentity);
        commentDate = (TextView) convertView.findViewById(R.id.commentDate);
        commentContent = (TextView) convertView.findViewById(R.id.commentContent);


        // 아이템 내 각 위젯에 데이터 반영
        commentIdentity.setText(commnetList.get(position).getId());
        commentDate.setText(commnetList.get(position).getDateTime().toString());
        commentContent.setText(commnetList.get(position).getReply());

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return commnetList.get(position);
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String id, String date, String content) {
        Reply item = new Reply();

        item.setId(id);
        item.setDateTime(date);
        item.setReply(content);
        commnetList.add(item);
    }
}
