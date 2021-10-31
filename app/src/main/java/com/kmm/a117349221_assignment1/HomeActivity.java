package com.kmm.a117349221_assignment1;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kmm.a117349221_assignment1.model.Player;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rcForward = null;
    private RecyclerView rcMidfielder = null;
    private RecyclerView rcDefender= null;
    private RecyclerView rcGoalkeeper= null;
    private TextView tvTeam = null;
    private PlayerRecyclerViewAdapter forwardAdapter, midfieldAdapter, defenderAdapter, goalAdapter;
    private XMLData data;
    private Player[] players;
    private ArrayList<Player> defenders, midfielders, goalkeepers, forwards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rcForward = findViewById(R.id.rcForward);
        rcDefender = findViewById(R.id.rcDefender);
        rcMidfielder = findViewById(R.id.rcMidfielder);
        rcGoalkeeper = findViewById(R.id.rcGoalKeeper);
        tvTeam = findViewById(R.id.tvTeam);
        data = new XMLData(this);
        players = new PlayerAsyncTask(this).loadInBackground();





    }
public void setRecyclerView (RecyclerView rc, PlayerRecyclerViewAdapter adapter){
    rc.setHasFixedSize(true);
    rc.setAdapter(adapter);
    rc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

}

    public class PlayerAsyncTask extends AsyncTaskLoader<Player[]> {
private Context context;
        public PlayerAsyncTask(Context context) {
            super(context);
            this.context  = context;
        }

        @Override
        public Player[] loadInBackground() {

            Player[] players = data.getData();
            defenders = new ArrayList<Player>();
            midfielders = new ArrayList<Player>();
            forwards = new ArrayList<Player>();
            goalkeepers = new ArrayList<Player>();
            for (Player player: players){
                findPositions(player.getPosition(), player);
            }
            String team = players[0].getTeam() + " F.C.";
            team = team.toUpperCase();
            tvTeam.setText(team);

            forwardAdapter = new PlayerRecyclerViewAdapter(context, forwards);
            defenderAdapter = new PlayerRecyclerViewAdapter(context, defenders);
            midfieldAdapter = new PlayerRecyclerViewAdapter(context, midfielders);
            goalAdapter = new PlayerRecyclerViewAdapter(context, goalkeepers);
            setRecyclerView(rcForward, forwardAdapter);
            setRecyclerView(rcDefender, defenderAdapter);
            setRecyclerView(rcMidfielder, midfieldAdapter);
            setRecyclerView(rcGoalkeeper, goalAdapter);
            return players;
        }
    }

    private void findPositions(String position, Player player){
        switch (position){
            case "Defender":
                defenders.add(player);
                break;
            case "Midfielder":
                midfielders.add(player);
                break;
            case "Goalkeeper":
                goalkeepers.add(player);
                break;
            case "Forward":
                forwards.add(player);
                break;
        }
    }

}
