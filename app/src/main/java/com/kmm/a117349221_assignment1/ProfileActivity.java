package com.kmm.a117349221_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kmm.a117349221_assignment1.model.Player;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileActivity extends AppCompatActivity {
    private Intent intent = null;
    private Player player = null;
    private TextView tvName = null;
    private TextView tvSurname = null;
    private TextView tvAge = null;
    private TextView tvPosition = null;
    private TextView tvPlayerNo = null;
    private TextView tvCountry = null;
    private TextView tvDOB = null;
    private TextView tvJoined = null;
    private TextView tvAppearances = null;

    private ImageView imageView = null;
    private Button button =null;
    private String firstName="";
    private String surName="";
    private Date dob, joinedDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        intent = getIntent();
        player = (Player) intent.getExtras().getSerializable("player");
        tvAge = findViewById(R.id.tvAge);
        tvName = findViewById(R.id.tvName);
        tvPlayerNo = findViewById(R.id.tvPlayerNo);
        tvSurname = findViewById(R.id.tvSurname);
        tvDOB = findViewById(R.id.tvDOB);
        tvJoined = findViewById(R.id.tvJoined);
        tvCountry = findViewById(R.id.tvCountry);
        tvAppearances = findViewById(R.id.tvAppearances);
        tvPosition = findViewById(R.id.tvPosition);
        imageView = findViewById(R.id.ivPlayer);
        button = findViewById(R.id.button);


        String imgName = player.getImage();
        imgName = imgName.substring(0, imgName.indexOf("."));
        int imgID = getResources().getIdentifier(imgName, "drawable", getPackageName());
        imageView.setImageResource(imgID);

        tvPosition.setText(player.getPosition().toUpperCase());
        tvAge.setText(player.getAge());
        tvPlayerNo.setText(player.getId());

      firstName = player.getName().toUpperCase();
      firstName = firstName.substring(0, firstName.indexOf(" "));
      surName = player.getName().toUpperCase();
      surName = surName.substring(surName.indexOf(" "));

      tvName.setText(firstName);
      tvSurname.setText(surName);
      tvAppearances.setText(player.getAppearances());
      tvCountry.setText(player.getCountry().toUpperCase());
        try {
            dob = Util.format1.parse(player.getDob());
            joinedDate = Util.format1.parse(player.getJoinedDate());
            tvDOB.setText((Util.format2.format(dob).toUpperCase()));
            tvJoined.setText((Util.format2.format(joinedDate)).toUpperCase());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        button.setOnClickListener((v)->{
            intent = new Intent(ProfileActivity.this, LastMatchActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("player", player);
            intent.putExtras(bundle);
            startActivity(intent);

        });
    }
}