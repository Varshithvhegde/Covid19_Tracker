package com.example.covid19tracker;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Model> list;
    private JAdapter adapter;
    EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.IdRecyclerView);
        search = findViewById(R.id.searching);
        list = new ArrayList<>();

        adapter = new JAdapter(list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        getStateData();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterState(editable.toString());
            }
        });
    }

        private void getStateData () {
            //below is api link provided  By govt of india to get all details about covid 19 cases in india
            String url = "https://data.covid19india.org/state_district_wise.json";
            //volley is used to get url request
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            ///bcz all the api gives back json file as output we have parse json file so we create json object
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
                try {
                    Iterator<String> keys = response.keys();
                    keys.next();
                    while (keys.hasNext()) {
                        String stateName = keys.next();
                        JSONObject jsonObject = response.getJSONObject(stateName);
                        JSONObject distracted = jsonObject.getJSONObject("districtData");
                        Iterator<String> districts = distracted.keys();
                        while (districts.hasNext()) {
                            String districtName = districts.next();
                            JSONObject district = distracted.getJSONObject(districtName);
                            long active = district.getLong("active");
                            long decreased = district.getLong("deceased");
                            long recovered = district.getLong("recovered");

                            list.add(new Model(stateName, districtName, decreased, recovered, active));
                        }
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "Fail to extract json data!!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }, error -> Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show()) {

            };
            requestQueue.add(jsonObjectRequest);
        }
//below function used for search functionality based on states
    private void filterState(String state) {
        ArrayList<Model> filterers= new ArrayList<>();
        for(Model item: list)
        {
            if(item.getSname().toLowerCase().contains(state.toLowerCase())){
                filterers.add(item);
            }
        }
        if(filterers.isEmpty())
        {
            Toast.makeText(this,"state not available", Toast.LENGTH_SHORT).show();

        }
        else
        {
            adapter.filterList(filterers);
        }
    }

}