package com.junior.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.junior.festafimdeano.R;
import com.junior.festafimdeano.dados.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);

        this.mViewHolder.buttonConfirm.setOnClickListener(this);
        this.mViewHolder.textToday.setText(dateFormat.format(Calendar.getInstance().getTime()));

        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeft()), getString(R.string.days));

        this.mViewHolder.textDaysLeft.setText(daysLeft);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_confirm){
            Intent intentDetails = new Intent(this,DetailsActivity.class);
            startActivity(intentDetails);
        }
    }

    private int getDaysLeft(){
        // Dia do ano
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);


        //ultimo dia do ano
        Calendar calendarLastDay = Calendar.getInstance();
        int lastDay = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return lastDay - today;
    }

    private static class ViewHolder{
        TextView textToday;
        TextView textDaysLeft;
        Button buttonConfirm;
    }
}
