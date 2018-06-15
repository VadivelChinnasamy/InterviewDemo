package com.datastructure.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.datastructure.R;


/**
 * Created CustomToast Vadivel on 12-06-2018.
 */

public class CustomToast {

    /***
     *
     * TOAST: WHICH'S SHOW THE CUSTOM ERROR
     * COLOR: RED
     * MSG: ITS USER MESSAGE WHICH IS PASSED FROM CLASSES
     *
     * */
    public static void ErrorToast(Context mContext, String msg) {
       //Creating the LayoutInflater instance
        LayoutInflater li =((Activity) mContext).getLayoutInflater();
        View layout = li.inflate(R.layout.custom_toast,null);
        TextView error_msg=layout.findViewById(R.id.error_msg);
        error_msg.setText(String.format("%s", msg));

        //Creating the Toast object
        Toast toast = new Toast(mContext);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setView(layout);//setting the view of custom toast layout
        toast.show();
    }
}
