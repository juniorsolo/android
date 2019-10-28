package com.junior.festafimdeano.dados;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    private SharedPreferences mSharedPreferences;

    public SecurityPreferences(Context context){
        this.mSharedPreferences = context.getSharedPreferences("FestaFimAno",Context.MODE_PRIVATE);
    }

    public void storeString(String key, String value){
        this.mSharedPreferences.edit().putString(key,value).apply();
    }

    public String getStoreString(String key){
        return this.mSharedPreferences.getString(key,"");
    }
}
