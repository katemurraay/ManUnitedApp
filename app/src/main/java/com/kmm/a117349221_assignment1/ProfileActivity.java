package com.kmm.a117349221_assignment1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private TextView tvAge = null;
    private TextView tvPosition = null;
    private TextView tvPlayerNo = null;
    private TextView tvCountry = null;
    private TextView tvDOB = null;
    private TextView tvJoined = null;
    private TextView tvAppearances = null;
     private Toolbar tbProfile = null;
    private ImageView imageView = null;

    private String firstName="";

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

        tvDOB = findViewById(R.id.tvDOB);
        tvJoined = findViewById(R.id.tvJoined);
        tvCountry = findViewById(R.id.tvCountry);
        tvAppearances = findViewById(R.id.tvAppearances);
        tvPosition = findViewById(R.id.tvPosition);
        imageView = findViewById(R.id.ivPlayer);
        tbProfile = findViewById(R.id.tbProfile);
        tbProfile.setTitle(player.getName());
        setSupportActionBar(tbProfile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        String imgName = player.getImage();
        imgName = imgName.substring(0, imgName.indexOf("."));
        int imgID = getResources().getIdentifier(imgName, "drawable", getPackageName());
        imageView.setImageResource(imgID);

        tvPosition.setText(player.getPosition().toUpperCase());
        tvAge.setText(player.getAge());
        tvPlayerNo.setText(player.getId());

      firstName = player.getName().toUpperCase();


        tvName.setText(firstName);

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



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        Bundle bundle;
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.url:
                intent = new Intent(ProfileActivity.this, WebActivity.class);
                bundle = new Bundle();
                bundle.putString("url", player.getUrl());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.lastMatch:
                intent = new Intent(ProfileActivity.this, LastMatchActivity.class);
                bundle = new Bundle();
                bundle.putSerializable("player", player);
                intent.putExtras(bundle);
                startActivity(intent);
                break;


        }
        return true;
    }
}