package com.kmm.a117349221_assignment1;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kmm.a117349221_assignment1.model.Player;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rcPlayer = null;
    private TextView tvTeam = null;
    private PlayerRecyclerViewAdapter adapter;
    private XMLData data;
    private Player[] players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        rcPlayer = findViewById(R.id.rcPlayer);
        tvTeam = findViewById(R.id.tvTeam);
        data = new XMLData(this);
        players = new PlayerAsyncTask(this).loadInBackground();
        tvTeam.setText(players[0].getTeam());
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
