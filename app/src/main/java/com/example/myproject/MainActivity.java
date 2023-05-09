package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

        new JsonFile(this, this).execute(JSON_File);
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
            items.add(new RecyclerViewItem(b.getName(), b.getType(), b.getLocation()));
            Log.d("ITEMS_IN_MOUNTAIN", "_" + items.get(items.size()-1).getName());
            Log.d("ITEMS_IN_MOUNTAIN", "_" + items.get(items.size()-1).getType());
            Log.d("ITEMS_IN_MOUNTAIN", "_" + items.get(items.size()-1).getLocation());
        }

        adapter = new RecyclerViewAdapter(this, items, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(RecyclerViewItem item) {
                Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_LONG).show();
            }
        });

        adapter.refreshItems(items);
        adapter.notifyDataSetChanged();

        RecyclerView recycler_view = findViewById(R.id.recyclerView);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(adapter);
}
}