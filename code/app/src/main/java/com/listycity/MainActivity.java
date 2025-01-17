package com.listycity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    private long selectedCityIndex;
    ListView citiesView;
    ArrayAdapter<String> cityAdapter;
    List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        this.setContentView(R.layout.activity_main);
        this.citiesView = this.findViewById(R.id.city_list);

        String[] cityNames = new String[]{"Edmonton", "Vancouver", "Moscow",
                "Sydney", "Berlin", "Vienna", "Tokyo",
                "Beijing", "Osaka", "New Delhi"};
        // Have to use the constructor because asList creates an unmodifiable list.
        this.dataList = new ArrayList<>(Arrays.asList(cityNames));
        this.cityAdapter = new ArrayAdapter<>(this, R.layout.content, this.dataList);
        this.citiesView.setAdapter(this.cityAdapter);

        LinearLayout addCityLayout = this.findViewById(R.id.add_city_layout);
        // Only set text field and confirm visible when the user clicks
        // on the add button, otherwise the add button is redundant.
        addCityLayout.setVisibility(ListView.INVISIBLE);
        Button addButton = this.findViewById(R.id.button_addcity);
        Button delButton = this.findViewById(R.id.button_remcity);
        addButton.setOnClickListener(v ->
        {
            addCityLayout.setVisibility(ListView.VISIBLE);
            delButton.setEnabled(false);
        });
        delButton.setEnabled(false);
        delButton.setOnClickListener(v ->
        {
            this.dataList.remove((int) this.selectedCityIndex);
            delButton.setEnabled(false);
            cityAdapter.notifyDataSetChanged();
        });

        /*
         * Adapted from: https://stackoverflow.com/a/9427860
         * Authored by: user370305, Milos Cuculovic
         * Taken by: Spencer Schmidt
         * Taken on: Jan. 17, 2025
         */
        this.citiesView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                selectedCityIndex = id;
                delButton.setEnabled(true);
            }
        });

        // Handle adding new city
        EditText cityTextField = addCityLayout.findViewById(R.id.text_city_name);
        Button confirmButton = addCityLayout.findViewById(R.id.button_confirm_add);
        confirmButton.setEnabled(false);
        confirmButton.setOnClickListener(v ->
        {
            String cityName = cityTextField.getText().toString();

            if (!dataList.contains(cityName))
            {
                addCityLayout.setVisibility(ListView.INVISIBLE);
                cityTextField.setText("");
                this.dataList.add(cityName);
                /*
                 * Taken from: https://stackoverflow.com/a/4540859
                 * Authored by: Shardul, elimirks
                 * Taken by: Spencer Schmidt
                 * Taken on: Jan. 17, 2025
                 */
                cityAdapter.notifyDataSetChanged();
            }
        });

        /*
         * Adapted from: https://www.geeksforgeeks.org/how-to-enable-disable-button-in-android/
         * Authored by: ayu5hkarn
         * Taken by: Spencer Schmidt
         * Taken on: Jan. 17, 2025
         */
        cityTextField.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                    int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start,
                    int before, int count)
            {
                String text = s.toString();
                confirmButton.setEnabled(!text.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top,
                    systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}