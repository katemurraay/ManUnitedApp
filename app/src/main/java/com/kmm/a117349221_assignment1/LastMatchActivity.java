package com.kmm.a117349221_assignment1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.loader.content.AsyncTaskLoader;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.kmm.a117349221_assignment1.model.Match;
import com.kmm.a117349221_assignment1.model.Player;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class LastMatchActivity extends AppCompatActivity {

        private TextView tvMinutes = null;
       private TextView tvRedCard = null;
       private  TextView tvYellowCard = null;
       private TextView tvName = null;
       private TextView tvDate = null;
       private Button btnWeb = null;
       private TextView tvDuels = null;
       private TextView tvInceptions = null;
        private TableLayout tlPassing = null;
       private TextView tvTackles = null;
       private ImageView imgGeneralArrow = null;
       private ImageView imgDefendingArrow= null;
       private ImageView imgPassingArrow= null;
       private TableLayout tlGeneral = null;
       private TableLayout tlDefending = null;
       private LinearLayout llGeneral =null;
       private LinearLayout llPassing =null;
       private LinearLayout llDefending =null;
       private PieChart pcPassing =null;
       private Toolbar tbLastMatch =null;
       private boolean generalVisible, defendingVisible, passingVisible;
private  Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_match);
        Intent previousIntent = getIntent();
        player =(Player) previousIntent.getSerializableExtra("player");
        int playerID = Integer.parseInt(player.getId());
        // <editor-fold default-state= "collapsed" desc = "Wiring Objects" >
        tvMinutes = findViewById(R.id.tvMinutes);
        tvRedCard = findViewById(R.id.tvRedCard);
        tvYellowCard = findViewById(R.id.tvYellowCard);
        tvDate = findViewById(R.id.tvDate);
        tvName = findViewById(R.id.tvMName);
        tvDuels = findViewById(R.id.tvDuel);
        tvTackles = findViewById(R.id.tvTackles);
        pcPassing = findViewById(R.id.pcPassing);
        tvInceptions = findViewById(R.id.tvInceptions);
        imgPassingArrow = findViewById(R.id.imgPassing);
        imgGeneralArrow =findViewById(R.id.imgGeneralArrow);
        imgDefendingArrow = findViewById(R.id.imgDefenceArrow);
        tlDefending = findViewById(R.id.tlDefending);
        tlGeneral = findViewById(R.id.tlGeneral);
        tlPassing = findViewById(R.id.tlPassing);
        btnWeb = findViewById(R.id.btnWebPage);
        llDefending = findViewById(R.id.llDefending);
        llGeneral = findViewById(R.id.llGeneral);
        llPassing = findViewById(R.id.llPassing);
        tbLastMatch = findViewById(R.id.tbMatch);
        // </editor-fold>
        generalVisible = true;
        defendingVisible =true;
        passingVisible = true;
        pcPassing.getDescription().setEnabled(false);
        pcPassing.setExtraOffsets(5,10,5,5);
        String strRes = getResources().getString(R.string.tv_last_match);
        String title = player.getName()+ "'s " + strRes;
        tbLastMatch.setTitle(title);
        setSupportActionBar(tbLastMatch);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        pcPassing.setDrawHoleEnabled(true);
        pcPassing.setHoleColor(Color.WHITE);
        pcPassing.setHoleRadius(65f);



        MatchAsyncTask asyncTask = new MatchAsyncTask(this, playerID);
        try {
            asyncTask.loadInBackground();
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, R.string.match_xml_error, Toast.LENGTH_SHORT).show();
        }
        //OnClick Listeners
        llGeneral.setOnClickListener((v)-> generalVisible = changeTableVisibility(generalVisible, tlGeneral, imgGeneralArrow));
        llDefending.setOnClickListener((v)-> defendingVisible = changeTableVisibility(defendingVisible, tlDefending, imgDefendingArrow));
        llPassing.setOnClickListener((v)-> {
             if (passingVisible){
                pcPassing.setVisibility(View.GONE);

            } else{
                pcPassing.setVisibility(View.VISIBLE);
                      }
            passingVisible = changeTableVisibility(passingVisible, tlPassing, imgPassingArrow);
        });

         btnWeb.setOnClickListener((v)->{
            Intent intent = new Intent(LastMatchActivity.this, WebActivity.class);
            populateIntent(intent);

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
        menuInflater.inflate(R.menu.menu_match, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.url:
                intent = new Intent(LastMatchActivity.this, WebActivity.class);
               populateIntent(intent);
                break;
            case R.id.home:
                intent = new Intent(LastMatchActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.profile:
                intent = new Intent(LastMatchActivity.this, ProfileActivity.class);
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


    public boolean changeTableVisibility(boolean visible, TableLayout tableLayout, ImageView imgArrow){
        if(visible){
            imgArrow.setImageResource(R.drawable.ic_arrow_down);
            tableLayout.setVisibility(View.GONE);
            visible = false;
        } else{
            imgArrow.setImageResource(R.drawable.ic_arrow_up);
            tableLayout.setVisibility(View.VISIBLE);
            visible = true;
        }
        return visible;
}

    public class MatchAsyncTask extends AsyncTaskLoader<Void> {
    private  Context c;
    private  int playerId;
        public MatchAsyncTask(@NonNull Context context, int playerId) {
            super(context);
            this.c = context;
            this.playerId = playerId;
        }

        @Nullable
        @Override
        public Void loadInBackground() {
            try{
            XMLData xml = new XMLData(c);
            Match match =  xml.getLastMatch(playerId);
            tvRedCard.setText(match.getRedCards());
            tvMinutes.setText(match.getMinutesPlayed());
            tvYellowCard.setText(match.getYellowCards());
            tvName.setText(match.getMatchName());
            String duels = match.getDuelsWon() + " " +"/" + " " + match.getDuels();
            tvDuels.setText(duels);
            String interceptions = match.getInterceptionsWon() +" " +"/" + " "  + match.getInterceptions();
            tvInceptions.setText(interceptions);
            String tackles = match.getTacklesWon() + " " +"/" + " " + match.getTackles();
            tvTackles.setText(tackles);
            if (!(match.getDuels().trim().equals("0"))){
                tvDuels.setTextColor(getResources().getColor(R.color.crimson));
            }
            if(!(match.getInterceptions().trim().equals("0"))){
                tvInceptions.setTextColor(getResources().getColor(R.color.crimson));
            }
            if(!(match.getTackles().trim().equals("0"))){
                tvTackles.setTextColor(getResources().getColor(R.color.crimson));
            } if(!(match.getRedCards().trim().equals("0"))){
                tvRedCard.setTextColor(getResources().getColor(R.color.crimson));
            }
            if (!(match.getYellowCards().trim().equals("0"))){
                tvYellowCard.setTextColor(getResources().getColor(R.color.crimson));
            }

                Date matchDate = Util.format1.parse(match.getDate());
                String strDate = Util.format2.format(matchDate);
                tvDate.setText(strDate);
/*  Code below is based on:
Youtube Video: "MPAndroidChart Tutorials Better Than Android GraphView 1- Beautiful Animated Pi Chart",
KGP Talkie,
https://www.youtube.com/watch?v=MiVx3AQD_PI
*/
                 ArrayList<PieEntry> yValues = new ArrayList<>();
                 yValues.add(new PieEntry(Float.parseFloat(match.getPasses()), "Successful"));
                 yValues.add(new PieEntry(Float.parseFloat(match.getNoPasses()), "Unsuccessful"));
                 PieDataSet dataSet = new PieDataSet(yValues, "Passes");
                 dataSet.setSliceSpace(3f);
                 dataSet.setSelectionShift(5f);
                 dataSet.setYValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);


                 int[] allColors = c.getResources().getIntArray(R.array.pie_chart_colours);
                 dataSet.setColors(allColors);
                 dataSet.setValueFormatter(new ValueFormatter() {
                     @Override
                     public String getFormattedValue(float value) {
                         return String.valueOf((int) Math.floor(value));
                     }
                 });
                 PieData data = new PieData(dataSet);
                 data.setValueTextSize(18f);
                 data.setValueTextColor(Color.WHITE);

                double totalPasses = match.getTotalPasses();
                String strTotalPasses = String.valueOf(totalPasses);
                strTotalPasses = strTotalPasses.substring(0, strTotalPasses.indexOf("."));
                pcPassing.setCenterText(strTotalPasses + " \n Total Passes");
                pcPassing.setCenterTextSize(22f);
                pcPassing.animateY(1000, Easing.EaseInOutCubic);
                pcPassing.setData(data);
                pcPassing.getLegend().setEnabled(false);
                pcPassing.setDrawEntryLabels(false);
//END

            } catch (Exception e) {
                e.printStackTrace();


            }


            return null;
        }
    }
}