
package com.kmm.a117349221_assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        data = new XMLData(getApplicationContext());
        players = data.getData();
        adapter = new PlayerRecyclerViewAdapter(this, players);
        rcPlayer.setHasFixedSize(true);
        rcPlayer.setAdapter(adapter);
        Log.d("AGE", players[1].getAge());
        rcPlayer.setLayoutManager(new LinearLayoutManager(this));


    }



}