package com.softbrain.sfa.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class ToastUtilsEnum {
    private static View v;
    private static Toast it;
    public ToastUtilsEnum(Context c){
    	  LayoutInflater inflate =(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          v = Toast.makeText(c, "", Toast.LENGTH_SHORT).getView();
          it = new Toast(c);
          it.setView(v);
    }
       
    public void show(CharSequence text, int duration) {
        it.setText(text);
        it.setDuration(duration);
        it.show();
    }

    public void show(int Resid, int duration) {
        it.setText(Resid);
        it.setDuration(duration);
        it.show();
    }
}
