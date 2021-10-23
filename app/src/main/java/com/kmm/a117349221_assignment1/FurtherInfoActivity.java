package com.kmm.a117349221_assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.kmm.a117349221_assignment1.model.Player;

public class FurtherInfoActivity extends AppCompatActivity {
private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_further_info);
        btn = findViewById(R.id.button2);
        Intent previousIntent = getIntent();
        Player player =(Player) previousIntent.getSerializableExtra("player");
        btn.setOnClickListener((v)->{
            Intent intent = new Intent(FurtherInfoActivity.this, WebActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("url", player.getUrl());
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}