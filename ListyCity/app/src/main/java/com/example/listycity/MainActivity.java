package com.example.listycity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Integer positionSelected = null;
    String citySelected = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //mandatory
        //EdgeToEdge.enable(this); not mandatory (minor visual change) like viewcompat
        setContentView(R.layout.activity_main); //mandatory

        cityList = findViewById(R.id.city_list);

        Button addCityButton = (Button) findViewById(R.id.add_city_btn);
        Button deleteCityButton = (Button) findViewById(R.id.delete_city_btn);
        Button confirmCityButton = (Button) findViewById(R.id.confirm_city_btn);
        EditText cityText = (EditText) findViewById(R.id.city_text_input);

        cityText.setVisibility(View.GONE);
        confirmCityButton.setVisibility(View.GONE);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};


        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);


        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped add city button");
                cityText.setText("");
                cityText.setVisibility(View.VISIBLE);
                confirmCityButton.setVisibility(View.VISIBLE);
            }
        });


        deleteCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped delete city button");
                if (positionSelected != null) { //only delete a city if one was selected
                    dataList.remove(positionSelected.intValue());
                    positionSelected = null; //set the position back to default
                    cityAdapter.notifyDataSetChanged();
                    Log.d("BUTTONS", "Deleted Following City:");
                    Log.d("BUTTONS", citySelected);
                    citySelected = null;
                }
            }
        });

        confirmCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped confirm city button");
                String cityResult = cityText.getText().toString();
                if (!cityResult.isBlank()) {
                    cityText.setVisibility(View.GONE);
                    confirmCityButton.setVisibility(View.GONE);
                    Log.d("BUTTONS", "Text entered by user is:");
                    Log.d("BUTTONS", cityResult);
                    dataList.add(cityResult);
                    cityAdapter.notifyDataSetChanged();
                }
            }
        });


        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionSelected =  position;
                citySelected = cityList.getItemAtPosition(position).toString();
                Log.d("LISTVIEW", "SELECTED AN ITEM:");
                Log.d("LISTVIEW", citySelected);
            }
        });
    }
}