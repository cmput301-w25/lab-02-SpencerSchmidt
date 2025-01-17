package com.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    ListView citiesView;
    ArrayAdapter<String> cityAdapter;
    List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        this.setContentView(R.layout.activity_main);
        this.citiesView = this.findViewById(R.id.city_list);

        String[] cityNames = new String[]{"Edmonton", "Vancouver", "Moscow",
                "Sydney", "Berlin", "Vienna", "Tokyo",
                "Beijing", "Osaka", "New Delhi"};
        this.dataList = Arrays.asList(cityNames);
        this.cityAdapter = new ArrayAdapter<>(this, R.layout.content, this.dataList);
        this.citiesView.setAdapter(this.cityAdapter);

        Button addButton = this.findViewById(R.id.button_addcity);
        addButton.setOnClickListener(v ->
        {

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}