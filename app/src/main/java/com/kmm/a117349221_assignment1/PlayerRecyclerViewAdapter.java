package com.kmm.a117349221_assignment1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kmm.a117349221_assignment1.model.Player;

import java.util.ArrayList;

public class PlayerRecyclerViewAdapter extends RecyclerView.Adapter<PlayerRecyclerViewAdapter.RecyclerCustomView> {
private Context context;
private Player[] players;


    public PlayerRecyclerViewAdapter(Context context, Player[] players) {
        this.context = context;
        this.players = players;

    }

    @NonNull
    @Override
    public RecyclerCustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.player_row, parent,false);
        RecyclerCustomView rcView = new RecyclerCustomView(view);
        return rcView;
    }

    @Override
    public int getItemCount() {
        return players.length;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerCustomView holder, int position) {
        Player player = players[position];
        holder.tvName.setText(player.getName());
        holder.tvPosition.setText(player.getPosition());
        holder.llPlayer.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProfileActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("player", player);
            intent.putExtras(bundle);
            context.startActivity(intent);

        });


    }


    public class RecyclerCustomView extends RecyclerView.ViewHolder{
public TextView tvName, tvPosition;
public LinearLayout llPlayer;

        public RecyclerCustomView(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPosition = itemView.findViewById(R.id.tvPosition);
            llPlayer = itemView.findViewById(R.id.llPlayer);


        }
    }
}
