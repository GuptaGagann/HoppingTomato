package com.example.gagan.hoppingtomatoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMenu extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;
    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<Float> price = new ArrayList<Float>();
    private AddMenuRecyclerViewWithFooterAdapter adapter;
    Button saveMenu;

    ViewPager viewPager;

    public AddMenu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddMenu.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMenu newInstance(String param1, String param2) {
        AddMenu fragment = new AddMenu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_menu,container,false);
        saveMenu = view.findViewById(R.id.saveMenu);
        mRecyclerView = view.findViewById(R.id.addMenu);
        viewPager = getActivity().findViewById(R.id.viewPager);
        // bind your data here.
        initializeRecyclerView();
        saveMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count=mRecyclerView.getAdapter().getItemCount();
                String itemsName = "";
                String prices = "";
                for(int i=0; i<count; i++) {
                    View itemView = mRecyclerView.findViewHolderForAdapterPosition(0).itemView;
                    TextView itemName = itemView.findViewById(R.id.menuListItem);
                    itemsName += itemName.getText()+",";
                }

                for(int i=0; i<count; i++) {
                    View itemView = mRecyclerView.findViewHolderForAdapterPosition(0).itemView;
                    TextView price = itemView.findViewById(R.id.price);
                    prices += price.getText()+",";
                }
                Toast.makeText(getContext(),itemsName,Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),prices,Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(1);
            }
        });
        return view;
    }

    private void initializeRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AddMenuRecyclerViewWithFooterAdapter(getContext(), data, price);
        mRecyclerView.setAdapter(adapter);
    }

    private void initializeData() {
        for (int i = 0; i < 10; i++) data.add("Position :" + i);
    }
}
