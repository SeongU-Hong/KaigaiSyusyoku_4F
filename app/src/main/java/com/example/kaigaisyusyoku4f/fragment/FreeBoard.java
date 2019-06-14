package com.example.kaigaisyusyoku4f.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.kaigaisyusyoku4f.R;

import java.util.ArrayList;

public class FreeBoard extends Fragment {

    private ArrayList<String> mList;
    private ListView mListView;
    private Button btn;
    private EditText editText;

    private ArrayAdapter mAdapter;


    public FreeBoard() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.free_board, container, false);

       /* mList = new ArrayList();
        mListView = (ListView) view.findViewById(R.id.listView1);
        mAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mList);
        mListView.setAdapter(mAdapter);

        btn=(Button)view.findViewById(R.id.testBtn);
        editText=(EditText)view.findViewById(R.id.testInput);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=editText.getText().toString();
                mList.add(str);
                mAdapter.notifyDataSetChanged();        //어댑터 새로고침(갱신)
                editText.setText("");
            }
        });*/


        return view;
    }
}
