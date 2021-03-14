package com.example.notes;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textview.MaterialTextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TextNodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TextNodeFragment extends Fragment {


    private static final String ARG_PARAM1 = "text.node.fragment";


    private int mNodeInd;

    public TextNodeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param nodeInx node index.
     * @return A new instance of fragment TextNodeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TextNodeFragment newInstance(int nodeInx) {
        TextNodeFragment fragment = new TextNodeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, nodeInx);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNodeInd = getArguments().getInt(ARG_PARAM1, -1);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] textNodes = getResources().getStringArray(R.array.text_notes);
        MaterialTextView textViewNode = view.findViewById(R.id.text_node_fragment);
        textViewNode.setText(textNodes[mNodeInd]);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text_node, container, false);
    }
}