package com.example.superbankapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class BancosActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bancos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bancos, menu);
		return true;
	}
	
	public void seleccionBanco(View view)
	{		
		DDBB db = new DDBB(this);
		
		Object context = getSystemService(Context.VIBRATOR_SERVICE);
		db.vibrar(context);
		
		db.grabarpordefecto();
		Intent intent = new Intent(this, HomeActivity.class);
		String bankparsed = (String) view.getTag();
		intent.putExtra("Boton", Integer.parseInt(bankparsed));
		startActivity(intent);
	}	
}
