package com.andrewclissold.lunch;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class LunchActivityFragment extends Fragment {

    private String TAG = getClass().getName();

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

        configureEditText(rootView);
        configureRecyclerView(rootView);
        getEateriesInBackground();

        return rootView;
    }

    private void configureRecyclerView(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.eatery_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new LunchAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void configureEditText(View rootView) {
        mEditText = (EditText) rootView.findViewById(R.id.editText);
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    String place = mEditText.getText().toString();

                    ParseObject eateryObject = new ParseObject("Eateries");
                    eateryObject.put("place", place);
                    eateryObject.put("vote", 0);
                    eateryObject.put("isPushed", false);
                    eateryObject.saveEventually();

                    mAdapter.add(place);
                    mAdapter.notifyDataSetChanged();
                    v.setText("");
                    int position = mAdapter.getItemCount() - 1;
                    if (position < 0) {
                        position = 0;
                    }
                    mRecyclerView.smoothScrollToPosition(position);
                    return true;
                }
                return false;
            }
        });
    }

    private void getEateriesInBackground() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Eateries");
        query.orderByDescending("vote");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject object : list) {
                        mAdapter.add(object.getString("place"));
                    }
                    mAdapter.notifyDataSetChanged();
                } else {
                    Log.d(TAG, e.getLocalizedMessage());
                }
            }
        });
    }

}
