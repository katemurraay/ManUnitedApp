
package com.kmm.a117349221_assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;

import com.kmm.a117349221_assignment1.model.Player;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private RecyclerView rcPlayer = null;
    private PlayerRecyclerViewAdapter adapter;
    private XMLData data;
    private Player[] players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcPlayer = findViewById(R.id.rcPlayers);
        data = new XMLData(this);
        players = new PlayerAsyncTask(this).loadInBackground();
        adapter = new PlayerRecyclerViewAdapter(this, players);
        rcPlayer.setHasFixedSize(true);
        rcPlayer.setAdapter(adapter);
        rcPlayer.setLayoutManager(new LinearLayoutManager(this));
    //    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


    }

public class PlayerAsyncTask extends AsyncTaskLoader<Player[]> {

    public PlayerAsyncTask(Context context) {
        super(context);
    }

    @Override
    public Player[] loadInBackground() {

        Player[] players = data.getData();

        return players;
    }
}

}