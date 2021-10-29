package com.kmm.a117349221_assignment1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
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
private Player[] defenders;
private Player[] goalkeeper;
private Player[] midfielders;
private Player[] forward;


    public PlayerRecyclerViewAdapter(Context context, Player[] players, Player[] defenders, Player[] midfielders, Player[] goalkeeper, Player[] forward) {
        this.context = context;
        this.players = players;
        this.defenders = defenders;
        this.goalkeeper = goalkeeper;
        this.midfielders = midfielders;
        this.forward =forward;

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

        holder.tvPosition.setText(player.getPosition());
       



    }

public void getProfileActivity (Context c, Player player){
    Intent intent = new Intent(c, ProfileActivity.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable("player", player);
    intent.putExtras(bundle);
    c.startActivity(intent);
}

public void setHorizontalFrame (RecyclerCustomView holder, Player[] tempPlayers){
        for (int i= 0; i <tempPlayers.length; i++){
            final View  singleFrame = ((Activity) context).getLayoutInflater().inflate(R.layout.frame_icon_caption, null);
            singleFrame.setId(i);
            TextView tvPNo = singleFrame.findViewById(R.id.tvPNo);
            TextView tvPName = singleFrame.findViewById(R.id.tvPName);
            ImageView imgPlayer = singleFrame.findViewById(R.id.imgPlayer);
            Player p = tempPlayers[i];
            tvPName.setText(p.getName());
            tvPNo.setText(p.getId());
            String imgName = p.getImage();
            imgName = imgName.substring(0, imgName.indexOf("."));
            int imgID = context.getResources().getIdentifier(imgName, "drawable", context.getPackageName());
            imgPlayer.setImageResource(imgID);
            holder.horizontalScrollView.addView(singleFrame);
        }

        }

    public class RecyclerCustomView extends RecyclerView.ViewHolder{
public TextView tvPosition;
public LinearLayout llPlayer;
public HorizontalScrollView horizontalScrollView;

        public RecyclerCustomView(@NonNull View itemView) {
            super(itemView);

            tvPosition = itemView.findViewById(R.id.tvPosition);
            llPlayer = itemView.findViewById(R.id.viewGroup);
            horizontalScrollView = itemView.findViewById(R.id.hsvPlayer);


        }
    }

}
