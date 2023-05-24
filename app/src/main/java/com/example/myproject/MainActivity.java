package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private ArrayList<Birds> birds = new ArrayList<Birds>();
    private RecyclerViewAdapter adapter;

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=d22samma";
    private final String JSON_File = "Birds.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.Aboutbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);

                setContentView(R.layout.activity_about);

            }
        });

        new JsonTask(this).execute(JSON_URL);
    }
    @Override
    public void onPostExecute(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Birds>>() {}.getType();

        birds = gson.fromJson(json, type);
        Log.d( "==>", json);

        // type cast from ArrayList<Mountain> to ArrayList<RecyclerViewItem>
        ArrayList<RecyclerViewItem> items = new ArrayList<>();
        for(Birds b : birds){
            items.add(new RecyclerViewItem(b.getName(), b.getSize(), b.getLocation()));
            Log.d("ITEMS_IN_MOUNTAIN", "_" + items.get(items.size()-1).getName());
            Log.d("ITEMS_IN_MOUNTAIN", "_" + items.get(items.size()-1).getType());
            Log.d("ITEMS_IN_MOUNTAIN", "_" + items.get(items.size()-1).getLocation());
        }

        adapter = new RecyclerViewAdapter(this, items, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(RecyclerViewItem item) {
            }
        });

        adapter.refreshItems(items);
        adapter.notifyDataSetChanged();

        RecyclerView recycler_view = findViewById(R.id.recyclerView);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(adapter);
}
}