package com.junior.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.junior.festafimdeano.FimDeAnoConstants;
import com.junior.festafimdeano.R;
import com.junior.festafimdeano.dados.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);

        this.mViewHolder.buttonConfirm.setOnClickListener(this);
        this.mViewHolder.textToday.setText(dateFormat.format(Calendar.getInstance().getTime()));

        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeft()), getString(R.string.days));

        this.mViewHolder.textDaysLeft.setText(daysLeft);

        this.verifyPresence();
    }


    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }

    private void verifyPresence() {
        String confirmPresence = this.mSecurityPreferences.getStoreString(FimDeAnoConstants.PRESENCE_KEY);

        if(confirmPresence.equals("")){
            this.mViewHolder.buttonConfirm.setText(getString(R.string.not_answered));
        }
        else if(confirmPresence.equals(FimDeAnoConstants.CONFIRMATION_YES)){
            this.mViewHolder.buttonConfirm.setText(getString(R.string.yes));
        }else{
            this.mViewHolder.buttonConfirm.setText(getString(R.string.no));
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_confirm){

            String presence = this.mSecurityPreferences.getStoreString(FimDeAnoConstants.PRESENCE_KEY);

            Intent intentDetails = new Intent(this,DetailsActivity.class);
            intentDetails.putExtra(FimDeAnoConstants.PRESENCE_KEY, presence);
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
