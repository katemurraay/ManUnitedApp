package com.kmm.a117349221_assignment1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

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

       private TextView tvDuels = null;
       private TextView tvInceptions = null;

       private TextView tvTackles = null;
       private ImageView imgGeneralArrow = null;
       private ImageView imgDefendingArrow= null;
       private ImageView imgPassingArrow= null;
       private TableLayout tbGeneral = null;
       private TableLayout tbDefending = null;
       private PieChart pcPassing =null;
       private boolean generalVisible, defendingVisible, passingVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_match);
        Intent previousIntent = getIntent();
        Player player =(Player) previousIntent.getSerializableExtra("player");
        int playerID = Integer.parseInt(player.getId());
        //Wiring objects
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
        tbDefending = findViewById(R.id.tlDefending);
        tbGeneral = findViewById(R.id.tlGeneral);
        generalVisible = true;
        defendingVisible =true;
        passingVisible = true;
        pcPassing.getDescription().setEnabled(false);
        pcPassing.setExtraOffsets(5,10,5,5);
        ;
        pcPassing.setDrawHoleEnabled(true);
        pcPassing.setHoleColor(Color.WHITE);
        pcPassing.setHoleRadius(70f);



        MatchAsyncTask asyncTask = new MatchAsyncTask(this, playerID);
        asyncTask.loadInBackground();

        imgGeneralArrow.setOnClickListener((v)->{
            generalVisible = changeTableVisibility(generalVisible, tbGeneral, imgGeneralArrow);
        });
        imgDefendingArrow.setOnClickListener((v)->{
            defendingVisible = changeTableVisibility(defendingVisible, tbDefending, imgDefendingArrow);
        });

        imgPassingArrow.setOnClickListener((v)->{
            if (passingVisible){
                pcPassing.setVisibility(View.GONE);
                imgPassingArrow.setImageResource(R.drawable.ic_arrow_down);
                passingVisible = false;
            } else{
                pcPassing.setVisibility(View.VISIBLE);
                imgPassingArrow.setImageResource(R.drawable.ic_arrow_up);
                passingVisible= true;
            }
        });




   /*
   Intent intent = new Intent(FurtherInfoActivity.this, WebActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("url", player.getUrl());
            intent.putExtras(bundle);
            startActivity(intent);
    */
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
            XMLData xml = new XMLData(c);
            Match match =  xml.getLastMatch(playerId);
            tvRedCard.setText(match.getRedCards());
            tvMinutes.setText(match.getMinutesPlayed());
            tvYellowCard.setText(match.getYellowCards());
            tvName.setText(match.getMatchName());
            String duels = match.getDuelsWon() + " / " + match.getDuels();
            tvDuels.setText(duels);
            String interceptions = match.getInterceptionsWon() + " / " + match.getInterceptions();
            tvInceptions.setText(interceptions);
            String tackles = match.getTacklesWon() + " / " + match.getTackles();
            tvTackles.setText(tackles);
             try {
                Date matchDate = Util.format1.parse(match.getDate());
                String strDate = Util.format2.format(matchDate);
                tvDate.setText(strDate);
//https://www.youtube.com/watch?v=MiVx3AQD_PI
                 ArrayList<PieEntry> yValues = new ArrayList<>();
                 yValues.add(new PieEntry(Float.parseFloat(match.getPasses()), "Successful Passes"));
                 yValues.add(new PieEntry(Float.parseFloat(match.getNoPasses()), "Unsuccessful Passes"));
                 PieDataSet dataSet = new PieDataSet(yValues, "Passes");
                 dataSet.setSliceSpace(3f);
                 dataSet.setSelectionShift(5f);
                 dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                 dataSet.setValueFormatter(new ValueFormatter() {
                     @Override
                     public String getFormattedValue(float value) {
                         return String.valueOf((int) Math.floor(value));
                     }
                 });
                 PieData data = new PieData(dataSet);
                 data.setValueTextSize(10f);
                 data.setValueTextColor(Color.YELLOW);

                double totalPasses = match.getTotalPasses();
                String strTotalPasses = String.valueOf(totalPasses);
                strTotalPasses = strTotalPasses.substring(0, strTotalPasses.indexOf("."));
                pcPassing.setCenterText(strTotalPasses + " \n Total Passes");
                pcPassing.setCenterTextSize(22f);
                pcPassing.animateY(1000, Easing.EaseInOutCubic);
                pcPassing.setData(data);
                pcPassing.getLegend().setEnabled(false);


            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}