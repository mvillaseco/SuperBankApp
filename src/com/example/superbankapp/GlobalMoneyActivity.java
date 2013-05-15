package com.example.superbankapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class GlobalMoneyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_global_money);
		DDBB handler = new DDBB(this);
		TextView text = (TextView) findViewById(R.id.globalmoney);
		int valores = handler.getGlobalMoney();
				text.setText("Actualmente posee un total de: $"+Integer.toString(valores));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.global_money, menu);
		return true;
	}

}
