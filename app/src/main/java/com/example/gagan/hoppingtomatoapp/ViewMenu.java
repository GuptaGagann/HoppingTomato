package com.example.gagan.hoppingtomatoapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewMenu extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    String email;
    SharedPreferences sp;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;
    private ArrayList<String> data = new ArrayList<String>();
    private ViewMenuRecyclerView adapter;
    Button editMenu;

    ViewPager viewPager;
    String finalResult ;
    String HttpURL = "https://hoppingtomato.swatantranews.info/getMenu.php";
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    public static final String UserEmail = "";

    String[] menuItems, prices, availabilities;


    public ViewMenu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewMenuu.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewMenu newInstance(String param1, String param2) {
        ViewMenu fragment = new ViewMenu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        email = sp.getString("email","");

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void initializeRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ViewMenuRecyclerView(getContext(), data);
        mRecyclerView.setAdapter(adapter);
    }

    private void initializeData() {
        data.clear();
        for (int i = 0; i < menuItems.length; i++) {
            data.add(menuItems[i]+" : "+prices[i]);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        GetMenuFunction(email);
        View view = inflater.inflate(R.layout.fragment_view_menu,container,false);
        editMenu = view.findViewById(R.id.editMenu);
        mRecyclerView = view.findViewById(R.id.menuChef);
        viewPager = getActivity().findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                GetMenuFunction(email);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        // bind your data here.
        editMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        return view;

    }
    public void GetMenuFunction(final String email){

        class GetMenuClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(getContext(),"Loading Data",null,true,true);
            }
            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();
                //Toast.makeText(getContext(),httpResponseMsg,Toast.LENGTH_LONG).show();
                try {
                    getChefMenu(httpResponseMsg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("email",params[0]);
                finalResult = httpParse.postRequest(hashMap, HttpURL);
                return finalResult;
            }
        }

        GetMenuClass getMenuClass = new GetMenuClass();

        getMenuClass.execute(email);
    }

    private void getChefMenu(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        menuItems = jsonArray.getJSONObject(0).getString("menuitem").split(",");
        prices = jsonArray.getJSONObject(0).getString("menuprice").split(",");
        availabilities = jsonArray.getJSONObject(0).getString("availability").split(",");
        initializeData();
        initializeRecyclerView();
    }

}
