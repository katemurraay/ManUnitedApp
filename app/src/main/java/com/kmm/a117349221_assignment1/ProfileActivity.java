package com.kmm.a117349221_assignment1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kmm.a117349221_assignment1.model.Player;

import java.text.ParseException;
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
    private Button btnLastMatch = null;
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
        btnLastMatch = findViewById(R.id.btnLastMatch);
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

        btnLastMatch.setOnClickListener((v)->{
            Intent intent = new Intent(ProfileActivity.this, LastMatchActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("player", player);
            intent.putExtras(bundle);
            startActivity(intent);
        });

    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
      /*Code below is base on StackOverflow Answer:
      Question: "menu icon not showing in toolbar",
      Answered by: Francisco Sales,
      https://stackoverflow.com/a/53283000
       */
               if (menu instanceof MenuBuilder) {
            ((MenuBuilder) menu).setOptionalIconsVisible(true);
        } //END
        menuInflater.inflate(R.menu.menu_profile, menu);

        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.url:
                intent = new Intent(ProfileActivity.this, WebActivity.class);
                populateIntent(intent);
                break;
            case R.id.lastMatch:
                intent = new Intent(ProfileActivity.this, LastMatchActivity.class);
                populateIntent(intent);
                break;
            case R.id.allPlayers:
                intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }
    public void populateIntent(Intent intent){
        Bundle bundle = new Bundle();
        bundle.putSerializable("player", player);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}