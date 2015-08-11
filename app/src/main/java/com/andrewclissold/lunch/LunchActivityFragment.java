package com.andrewclissold.lunch;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class LunchActivityFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LunchAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private EditText mEditText;

    public LunchActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lunch, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.eatery_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<String> lunchDataset = Arrays.asList(new String[]{"hello", "world"});
        mAdapter = new LunchAdapter(lunchDataset);
        mRecyclerView.setAdapter(mAdapter);

        mEditText = (EditText) rootView.findViewById(R.id.editText);
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL
                    && event.getAction() == KeyEvent.ACTION_DOWN) {
                    mAdapter.add(mEditText.getText().toString());
                    mAdapter.notifyDataSetChanged();
                    v.setText("");
                    return true;
                }
                return false;
            }
        });

        return rootView;
    }
}
