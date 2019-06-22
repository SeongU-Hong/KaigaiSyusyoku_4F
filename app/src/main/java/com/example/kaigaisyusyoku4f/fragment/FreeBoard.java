package com.example.kaigaisyusyoku4f.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kaigaisyusyoku4f.DetailActivity;
import com.example.kaigaisyusyoku4f.FreeListViewAdapter;
import com.example.kaigaisyusyoku4f.R;
import com.example.kaigaisyusyoku4f.fireBase.FireBaseBasement;
import com.example.kaigaisyusyoku4f.models.Board;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FreeBoard extends Fragment {

    public static ArrayList<Board> mList;
    private ListView mListView;
    public static ArrayList<String> List;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;
    public FreeListViewAdapter fla;
    private SwipeRefreshLayout swipe;
    FireBaseBasement fbb;
    public FreeBoard() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.free_board, container, false);
        mList = new ArrayList<Board>();
        List = new ArrayList<>();
        mListView = (ListView) view.findViewById(R.id.listView1);
//        initDatabase();
//        mAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mList);
//        mListView.setAdapter(mAdapter);
        fla = new FreeListViewAdapter();
        mListView.setAdapter(fla);
        fbb = new FireBaseBasement();

        //새로고침
        swipe=(SwipeRefreshLayout)view.findViewById(R.id.swipeRefresh1);
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

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("freeboard");
        Query query = mReference.orderByChild("dateTime");
        mReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fla.clear();

                for (DataSnapshot write : dataSnapshot.getChildren()) {
//                    if(messageData.child("freeboard").child("write").exists()){
                        Board board = write.getValue(Board.class);

                         SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd aaa HH:mm:ss");
                         String dateTime = format.format(write.child("dateTime").getValue());
                         fla.addItem(board.getTitle(),dateTime,(int)board.getCount(),(int)board.getReplyCount(),board.getKey(),board.getId(),board.getContents(),board.getFlag());
//                    }
                    // child 내에 있는 데이터만큼 반복합니다.
                }
//                mAdapter.add(List);
//                mAdapter.notifyDataSetChanged();
//                mListView.setSelection(mAdapter.getCount() - 1);
                  Log.e("count : ", ""+fla.getCount());
                  fla.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int i, long l) {
                Board board = fla.freeList.get(i);
                board.setCount(board.getCount() + 1);
//                Log.e("1t","여기서 터짐");
                fbb.updateBoard(board);
//                Log.e("2t","아니 여기서 터짐");

                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("id", board.getId());
                intent.putExtra("title", board.getTitle());
                //Log.d(mList.get(i).getTitle(),"title");
                intent.putExtra("contents", board.getContents());

                intent.putExtra("dateTime", board.getDateTime().toString());

                intent.putExtra("count", String.valueOf(board.getCount()));
                intent.putExtra("key",board.getKey());
                intent.putExtra("replyCount",board.getReplyCount());

                //Log.d(String.valueOf(mList.get(i).getHitCount()),"hitCount");
                startActivity(intent);

            }
        });
        return view;
    }

    private void initDatabase() {
        mDatabase = FirebaseDatabase.getInstance();

        mReference = mDatabase.getReference("freeboard");

        mChild = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        };
        mReference.addChildEventListener(mChild);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mReference.removeEventListener(mChild);
    }
}

