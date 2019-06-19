package com.example.kaigaisyusyoku4f.fragment;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kaigaisyusyoku4f.DetailView;
import com.example.kaigaisyusyoku4f.R;
import com.example.kaigaisyusyoku4f.VO.FreeboardVO;
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
import java.util.Date;

public class FreeBoard extends Fragment {

    public static ArrayList<Board> mList;
    public static ListView mListView;
    public static ArrayAdapter mAdapter;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;

    public FreeBoard() {

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.free_board, container, false);
        mList = new ArrayList<Board>();
        mListView = (ListView) view.findViewById(R.id.listView1);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, new ArrayList<Board>());
        mListView.setAdapter(mAdapter);

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    Board msg2 = (Board) messageData.child("freeboard").child("write").getValue();
                    mList.add(msg2);
                    mAdapter.add(msg2);
                    // child 내에 있는 데이터만큼 반복합니다.

                }
                mAdapter.notifyDataSetChanged();
                mListView.setSelection(mAdapter.getCount() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int i, long l) {
                Intent intent = new Intent(getContext(), DetailView.class);
                intent.putExtra("id",String.valueOf(mList.get(i).getId()));
                intent.putExtra("title",mList.get(i).getTitle());
                //Log.d(mList.get(i).getTitle(),"title");
                intent.putExtra("contents",mList.get(i).getContents());

                intent.putExtra("dateTime",String.valueOf(mList.get(i).getDateTime()));
                mList.get(i).setCount(mList.get(i).getCount()+1);
                intent.putExtra("count",String.valueOf(mList.get(i).getCount()));
                //Log.d(String.valueOf(mList.get(i).getHitCount()),"hitCount");
                Toast.makeText(getContext(), "클릭"+mList.get(i).getCount(), Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });

        return view;
    }

}
