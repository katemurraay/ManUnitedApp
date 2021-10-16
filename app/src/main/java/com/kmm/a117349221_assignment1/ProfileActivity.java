package com.kmm.a117349221_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.kmm.a117349221_assignment1.model.Player;

public class ProfileActivity extends AppCompatActivity {
    private Intent intent = null;
    private Player player = null;
    private TextView tvName = null;
    private TextView tvAge = null;
    private TextView tvPosition = null;
    private TextView tvPlayerNo = null;
    private TextView tvTeam = null;
    private ImageView imageView = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        intent = getIntent();
        player = (Player) intent.getExtras().getSerializable("player");
        tvAge = findViewById(R.id.tvAge);
        tvName = findViewById(R.id.tvName);
        tvPlayerNo = findViewById(R.id.tvPlayerNo);
        tvTeam = findViewById(R.id.tvTeam);
        tvPosition = findViewById(R.id.tvPosition);
        imageView = findViewById(R.id.ivPlayer);

        String imgName = player.getImage();
        imgName = imgName.substring(0, imgName.indexOf("."));
        int imgID = getResources().getIdentifier(imgName, "drawable", getPackageName());
        imageView.setImageResource(imgID);

        tvPosition.setText(player.getPosition());
        tvAge.setText(player.getAge());
        tvPlayerNo.setText(player.getId());
        tvName.setText(player.getName());
        tvTeam.setText(player.getTeam());
    }
}