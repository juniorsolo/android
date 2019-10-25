package com.junior.conversormoedas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.editValue = findViewById(R.id.edit_value);
        this.mViewHolder.textDollar = findViewById(R.id.text_dollar);
        this.mViewHolder.textEuro = findViewById(R.id.text_euro);
        this.mViewHolder.buttonCalculate = findViewById(R.id.button_calculate);

        this.mViewHolder.buttonCalculate.setOnClickListener(this);

        this.clearValues();
    }

    private void clearValues(){
        this.mViewHolder.textEuro.setText("");
        this.mViewHolder.textDollar.setText("");
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.mViewHolder.buttonCalculate.getId()){
            String value = this.mViewHolder.editValue.getText().toString();
            if("".equals(value)){
                Toast.makeText(this, getString(R.string.informe_valor) , Toast.LENGTH_LONG).show();
            }else{
                Double real = Double.valueOf(value);

                mViewHolder.textDollar.setText(String.format("%.2f",(real /4)));
                mViewHolder.textEuro.setText(String.format("%.2f",(real /5)));
                
            }
        }
    }

    private static class ViewHolder{
        EditText editValue;
        TextView textDollar;
        TextView textEuro;
        Button buttonCalculate;
    }
}
