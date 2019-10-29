package com.junior.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.junior.festafimdeano.FimDeAnoConstants;
import com.junior.festafimdeano.R;
import com.junior.festafimdeano.dados.SecurityPreferences;

import java.text.SimpleDateFormat;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences securityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.securityPreferences = new SecurityPreferences(this);
        this.mViewHolder.check = findViewById(R.id.checkbox_participate);

        this.mViewHolder.check.setOnClickListener(this);

        this.onLoadFromActivity();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.checkbox_participate){
            if(this.mViewHolder.check.isChecked()){
                this.securityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_YES);
            }else{
                this.securityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_NO);
            }
        }
    }
    private void onLoadFromActivity(){
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            String presence = extras.getString(FimDeAnoConstants.PRESENCE_KEY);
            if(presence != null && presence.equals(FimDeAnoConstants.CONFIRMATION_YES)){
                this.mViewHolder.check.setChecked(true);
            }else{
                this.mViewHolder.check.setChecked(false);
            }
        }
    }
    private static class ViewHolder{
        CheckBox check;
    }
}
