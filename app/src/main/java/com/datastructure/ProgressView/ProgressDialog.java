package com.datastructure.ProgressView;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;

import com.datastructure.R;


public class ProgressDialog extends Dialog {
	public ProgressDialog(Context context) {
		super(context);
	}

	public ProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	
	public static ProgressDialog show(Context context, boolean cancelable) {
		ProgressDialog dialog = new ProgressDialog(context, R.style.ProgressDialog);
		dialog.setTitle("");
		dialog.setContentView(R.layout.progress_dialog);
		/*if(message == null || message.length() == 0) {
			dialog.findViewById(R.id.message).setVisibility(View.GONE);			
		} else {
			TextView txt = (TextView)dialog.findViewById(R.id.message);
			txt.setText(message);
		}*/
		dialog.setCancelable(cancelable);
		dialog.getWindow().getAttributes().gravity=Gravity.CENTER;
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();  
		lp.dimAmount=0.2f;
		dialog.getWindow().setAttributes(lp); 
		//dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		dialog.show();
		return dialog;
	}	
}
