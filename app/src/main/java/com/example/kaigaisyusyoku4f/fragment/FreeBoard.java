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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
    public static FreeListViewAdapter fla;
    private SwipeRefreshLayout swipe;
    private boolean lastItemVisibleFlag = false;        //화면에 리스트의 마지막 아이템이 보여지는지 체크

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

        //리스트뷰가 바닥에 닿을 경우
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //현재 화면에 보이는 첫번째 리스트 아이템의 번호(firstVisibleItem) + 현재 화면에 보이는 리스트 아이템의 갯수(visibleItemCount)가 리스트 전체의 갯수(totalItemCount) -1 보다 크거나 같을때
                lastItemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
            }
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //OnScrollListener.SCROLL_STATE_IDLE은 스크롤이 이동하다가 멈추었을때 발생되는 스크롤 상태입니다.
                //즉 스크롤이 바닦에 닿아 멈춘 상태에 처리를 하겠다는 뜻
                if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastItemVisibleFlag) {
                    //TODO 화면이 바닦에 닿을때 처리
                    Toast.makeText(getActivity(),"마지막 똥글", Toast.LENGTH_SHORT).show();
                }
            }

        });

       mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("freeboard");

        Query query = mReference.orderByChild("dateTime");


        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fla.clear();
                int replyCount = 0;
                for (DataSnapshot write : dataSnapshot.getChildren()) {
//                    if(messageData.child("freeboard").child("write").exists()){
                        Board board = write.getValue(Board.class);

                        replyCount = (int)write.child("replyList").getChildrenCount();

                         SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd aaa HH:mm:ss");
                         String dateTime = format.format(write.child("dateTime").getValue());
                         fla.addItem(board.getTitle(),dateTime,(int)board.getCount(),replyCount,board.getKey(),board.getId(),board.getContents(),board.getFlag());
//                    }
                    // child 내에 있는 데이터만큼 반복합니다.
                }
//                mAdapter.add(List);
//                mAdapter.notifyDataSetChanged();
//                mListView.setSelection(mAdapter.getCount() - 1);
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

                intent.putExtra("dateTime", "등록일 "+board.getDateTime().toString());

                intent.putExtra("count", "조회수 "+ board.getCount());
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
                fla.notifyDataSetChanged();
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
        mReference.removeEventListener(mChild);
    }
}

