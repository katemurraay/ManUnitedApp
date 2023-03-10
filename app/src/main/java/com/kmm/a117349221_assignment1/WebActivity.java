package com.kmm.a117349221_assignment1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.loader.content.AsyncTaskLoader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kmm.a117349221_assignment1.model.Player;

import java.util.concurrent.ExecutionException;

public class WebActivity extends AppCompatActivity {
    private WebView webView = null;
    private String url = null;
    private Toolbar toolbar = null;
    private Player player = null;

@SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
       Intent intent = getIntent();
       player = (Player) intent.getSerializableExtra("player");
        url = player.getUrl();
        webView = (WebView) findViewById(R.id.webView);
        toolbar = findViewById(R.id.tbWeb);

        String title = player.getName() + "'s Webpage";
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        webView.getSettings().setJavaScriptEnabled(true);
       try{
           webView.loadUrl(url);
       }catch (Exception e){
           e.printStackTrace();
           Toast.makeText(this, R.string.url_error, Toast.LENGTH_SHORT).show();
       }

        ActionBar actionBar = getSupportActionBar();
         actionBar.setDisplayHomeAsUpEnabled(true);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
        menuInflater.inflate(R.menu.menu_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
       switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.home:
                intent = new Intent(WebActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.profile:
                intent = new Intent(WebActivity.this, ProfileActivity.class);
                populateIntent(intent);
                break;
            case R.id.lastMatch:
                intent = new Intent(WebActivity.this, LastMatchActivity.class);
                populateIntent(intent);
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