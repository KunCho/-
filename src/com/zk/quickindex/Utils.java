package com.zk.quickindex;

import android.content.Context;
import android.widget.Toast;

public class Utils {
	private static Toast toast;
	public static void showToast(Context context,String content) {
		if (toast == null) {
			toast =Toast.makeText(context, "", Toast.LENGTH_SHORT);
		}
		toast.setText(content);
		toast.show();
	}
}
