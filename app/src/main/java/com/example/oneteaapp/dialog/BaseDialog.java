package com.example.oneteaapp.dialog;

import android.app.Dialog;
import android.content.Context;

/**
 * 公共样式dialog
 */
public class BaseDialog extends Dialog {

	public BaseDialog(Context context) {
		super(context);
		getContext().setTheme(android.R.style.Theme_InputMethod);
	}

}
