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
    private ArrayList<Player> players;


    public PlayerRecyclerViewAdapter(Context context, ArrayList<Player> players) {
        this.context = context;
        this.players = players;

    }

    @NonNull
    @Override
    public RecyclerCustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.frame_player, parent,false);
        RecyclerCustomView rcView = new RecyclerCustomView(view);
        return rcView;
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerCustomView holder, int position) {
        Player player = players.get(position);
        holder.tvPName.setText(player.getName());
        holder.tvPNo.setText(player.getId());
        String imgName = player.getImage();
        imgName = imgName.substring(0, imgName.indexOf("."));
        int imgID = context.getResources().getIdentifier(imgName, "drawable", context.getPackageName());
        holder.imgPlayer.setImageResource(imgID);

        holder.llPlayer.setOnClickListener(v -> {
            getProfileActivity(context, player);
        });
        holder.imgPlayer.setOnClickListener(v ->{
            getProfileActivity(context, player);
        });


    }

    public void getProfileActivity (Context c, Player player){
        Intent intent = new Intent(c, ProfileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("player", player);
        intent.putExtras(bundle);
        c.startActivity(intent);
    }
    public class RecyclerCustomView extends RecyclerView.ViewHolder{
        public TextView tvPName, tvPNo;
        public LinearLayout llPlayer;
        public ImageView imgPlayer;

        public RecyclerCustomView(@NonNull View itemView) {
            super(itemView);
            tvPNo = itemView.findViewById(R.id.tvPNo);
            tvPName = itemView.findViewById(R.id.tvPName);
            llPlayer = itemView.findViewById(R.id.llPlayer);
            imgPlayer = itemView.findViewById(R.id.imgPlayer);


        }
    }

}



